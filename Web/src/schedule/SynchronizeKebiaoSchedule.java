package schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ecapi.model.KebiaoModel;
import util.DBUtil;
import util.DBUtil1;
import util.DateUtil;
import util.MyLog;
import util.TimerUtil;
/**
 * 同步课表核心类，该类负责同步课表，同时根据课表定时去启动控制教室开关
 * @author wjd
 *
 */
public class SynchronizeKebiaoSchedule implements Runnable {
	private String time;
	public SynchronizeKebiaoSchedule(String time) {
		this.time=time;
	}
	@Override
	public void run() {

		MyLog.debug(this.time+",当前时间===="+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"=====开始同步课表");
    	Date now = new Date();
    	String runDate = new SimpleDateFormat("yyyy-MM-dd").format(now);
    	Map<String, String> controlMap = ControlTime.controlTimeMap.get(this.time);
    	 //测试用的
    	//List<KebiaoModel> kbList = DBUtil1.getKB(controlMap);//改过的
    	//产线用的
    	List<KebiaoModel> kbList = DBUtil.getKB(runDate, controlMap);
    	String closeTime = null;
    	Map<String,Boolean> classroomTimeMap = new HashMap<String,Boolean>();
    	if (kbList.size() > 1) {
        	for(int i=0;i<kbList.size()-1;i++){
        		KebiaoModel kebiao = kbList.get(i);
        		KebiaoModel nextkebiao = kbList.get(i+1);
        		
        		String[] controlTime = ControlTime.generateContrlTimeByKebiao(controlMap,runDate, kebiao);
        		String[] nextControlTime = ControlTime.generateContrlTimeByKebiao(controlMap,runDate, nextkebiao);
        		String openTime = controlTime[0];
        		closeTime = controlTime[1];
        		String nextopenTime = nextControlTime[0];
        		String nextcloseTime = nextControlTime[1];
        		String jsh = kebiao.getJsh();
        		String nextjsh = nextkebiao.getJsh();
        		try {
					if(jsh.equals(nextjsh)&&(DateUtil.distanceMinuteBetweenDates(
							new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(closeTime), 
							new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(nextopenTime))<40)){
						closeTime = nextcloseTime;
						if(classroomTimeMap.get(kebiao.getJsh())==null){
							classroomTimeMap.put(kebiao.getJsh(), true);
							TimerUtil.ScheduleControlDevice(openTime,kebiao, true);
						}
						continue;
					}else if(classroomTimeMap.get(kebiao.getJsh())==null){
						TimerUtil.ScheduleControlDevice(openTime,kebiao, true);
					}
					TimerUtil.ScheduleControlDevice(closeTime,kebiao, false);
					classroomTimeMap.put(kebiao.getJsh(), null);
        		} catch (ParseException e) {
        			e.printStackTrace();
        		}
        	}
        	//获取当天最后一第上课信息
        	KebiaoModel lastKebiao = kbList.get(kbList.size()-1);
    		String[] lastContrlTime = ControlTime.generateContrlTimeByKebiao(controlMap,runDate, lastKebiao);

        	if(classroomTimeMap.get(lastKebiao.getJsh())==null){
        		TimerUtil.ScheduleControlDevice(lastContrlTime[0],lastKebiao, true);
        	}
			TimerUtil.ScheduleControlDevice(lastContrlTime[1],lastKebiao, false);
    	}else if(kbList.size()==1){
    		KebiaoModel kebiao= kbList.get(0);
    		String[] contrlTime = ControlTime.generateContrlTimeByKebiao(controlMap,runDate, kebiao);
    		TimerUtil.ScheduleControlDevice(contrlTime[0],kebiao, true);
    		TimerUtil.ScheduleControlDevice(contrlTime[1],kebiao, false);
    	}
    	MyLog.debug(this.time+",当前时间===="+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"=====同步课表结束，课表长度="+(kbList==null?0:kbList.size()));
	}

}
