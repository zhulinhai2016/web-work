package ecapi.api;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;

import com.iot.model.Classroom;
import com.iot.service.ClassroomService;

import ecapi.model.ClassesInfoModel;
import util.SpringContextUtil;
import util.TcpClient2;

/**
 * 
 * @author wjd
 *  教室信息修改器 修改思路是 先根据教室id查询教室 在set对应变量值
 *  构造一个教室对象  删除教室 之后再添加教室
 */
public class Updatter {
	/**
	 * 
	 * @param classRoomId
	 * @param serverHostIp   centerHost 中控IP
	 * @return
	 */
	public static synchronized boolean updateIp(String classRoomId,String serverHostIp,String centerHost,String monitorIp){
		ClassesInfoModel classesInfo = ClassesFactory.getClassesInfo(classRoomId);
		if(classesInfo==null){
			return false;
		}
		if(serverHostIp!=null){
			classesInfo.setServerHost(serverHostIp);
		}else if(centerHost!=null){
			classesInfo.setCenterHost(centerHost);
		}else if(monitorIp!=null){
			classesInfo.setTouyUrl(monitorIp);
		}
		boolean removeResult = ClassesFactory.removeClass(classRoomId);
		if(!removeResult){
			return false;
		}
		boolean addClassResult = ClassesFactory.addClass(classesInfo);
		if(addClassResult){
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @param classRoomId
	 * @param serverHostIp   centerHost 中控IP
	 * @return
	 */
	public static synchronized boolean updateTouy(String classRoomId,String touyType,String openCode,
			String closeCode){
		ClassroomService classroomService = (ClassroomService)SpringContextUtil.getBean("classroomServiceImpl");
		ClassesInfoModel classesInfo = ClassesFactory.getClassesInfo(classRoomId);
		if(classesInfo==null){
			return false;
		}
		if(touyType!=null){
			classesInfo.setTouyType(touyType);
			if("爱普生".equals(touyType)){
				TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getTouyAOpenCode());
				TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getTouyACloseCode());
				TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getMJAirCode());
			}else if("NEO".equals(touyType)){
				TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getTouyBOpenCode());
				TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getTouyBCloseCode());
				TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getNECAirCode());
			}else if("松下UX363C".equals(touyType)){
				TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getTouyCOpenCode());
				TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getTouyCCloseCode());
				TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getSXAirCode());
			}else if("松下UX35".equals(touyType)){
				TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getTouyFOpenCode());
				TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getTouyFCloseCode());
				TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getSX1AirCode());
			}else if("三洋".equals(touyType)){
				TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getTouyDOpenCode());
				TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getTouyDCloseCode());
				TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getSYAirCode());
			}else if("卡西欧".equals(touyType)){
				TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getTouyEOpenCode());
				TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getTouyECloseCode());
				TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getKXOAirCode());
			}
			Classroom classroom = new Classroom();
			classroom.setTouytype(touyType);
			classroom.setId(Long.parseLong(classRoomId));
			return classroomService.updateByPrimaryKeySelective(classroom)>0;
		}
		return false;
//		if(openCode!=null){
//			classesInfo.setTouyOpenCode(openCode);
//		}
//		if(closeCode!=null){
//			classesInfo.setTouyCloseCode(closeCode);
//		}
		
//		boolean removeResult = ClassesFactory.removeClass(classRoomId);
//		if(!removeResult){
//			return false;
//		}
//		boolean addClassResult = ClassesFactory.addClass(classesInfo);
//		if(addClassResult){
//			return true;
//		}
//		return false;
	}
	/**
	 * 
	 * @param classRoomId
	 * @param serverHostIp   centerHost 中控IP
	 * @return
	 */
	public static synchronized boolean updateAirCondition(String classRoomId,String[] kongtType){
		//ClassesInfoModel classesInfo = ClassesFactory.getClassesInfo(classRoomId);
		ClassroomService classroomService = (ClassroomService)SpringContextUtil.getBean("classroomServiceImpl");
		ClassesInfoModel classesInfo = ClassesFactory.getClassesInfo(classRoomId);
		if(classesInfo==null){
			return false;
		}
		if(kongtType!=null){
			String airConditionType = Arrays.toString(kongtType).replace("[", "").replace("]", "").replaceAll(" ", "");
/*			TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(), "clearall");
*/			classesInfo.setKongtType(airConditionType);
			if(StringUtils.isNotBlank(airConditionType)){
				String[] kttypes = airConditionType.split(",");
				for(int i=0;i<kttypes.length;i++){
					String type = kttypes[i].trim();
					if(type.indexOf("_") != -1){
						type = type.split("_")[0];
					}
					if(i==0){
						if("格力".equals(type.trim())){
							TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getaOpenCode16());
							TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getaOpenCode23());
							TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getaOpenCode26());
							TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getaCloseCode());
						}else if("美的".equals(type.trim())){
							TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getbOpenCode16());
							TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getbOpenCode23());
							TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getbOpenCode26());
							TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getbCloseCode());
						}
					} else {
						if("格力".equals(type.trim())){
							TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getcOpenCode16());
							TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getcOpenCode23());
							TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getcOpenCode26());
							TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getcCloseCode());
						}else if("美的".equals(type.trim())){
							TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getdOpenCode16());
							TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getdOpenCode23());
							TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getdOpenCode26());
							TcpClient2.sendTcpMsg(classesInfo.getServerHost(), classesInfo.getServerPort(),classesInfo.getdCloseCode());
						}
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
			}
			Classroom classroom = new Classroom();
			classroom.setKongtype(airConditionType);
			classroom.setId(Long.parseLong(classRoomId));
			return classroomService.updateByPrimaryKeySelective(classroom)>0;
		}
		return false;
		
		
		
		
		
		
		
		
		
		
//		if(classesInfo==null){
//			return false;
//		}
		//classesInfo.setaCloseCode(aCloseCode);
		//classesInfo.setaOpenCode16(aOpenCode16);
		//classesInfo.setaOpenCode23(aOpenCode23);
		//classesInfo.setaOpenCode26(aOpenCode26);
		
		//classesInfo.setbCloseCode(bCloseCode);
		//classesInfo.setbOpenCode16(bOpenCode16);
		//classesInfo.setbOpenCode23(bOpenCode23);
		//classesInfo.setbOpenCode26(bOpenCode26);
		
//		boolean removeResult = ClassesFactory.removeClass(classRoomId);
//		if(!removeResult){
//			return false;
//		}
//		boolean addClassResult = ClassesFactory.addClass(classesInfo);
//		if(addClassResult){
//			return true;
//		}
//		return false;
	}

}
