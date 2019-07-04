package com.iot.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iot.model.DevicePower;
import com.iot.model.HomeCountData;
import com.iot.model.RunCountData;
import com.iot.model.RunError;
import com.iot.model.RunErrorCountData;
import com.iot.model.RunInfo;
import com.iot.service.DevicePowerService;
import com.iot.service.RunErrorService;
import com.iot.service.RunInfoService;

import ecapi.api.ClassesFactory;
import ecapi.model.ClassesInfoModel;
import ecapi.model.ClassesModel;
import page.PageBean;
import util.Constans;
import util.JsonUtil;
import util.ResponseUtil;

@Controller
@RequestMapping(value="/runInfo")
public class RunInfoController {
	
	@Autowired
	private RunInfoService runInfoService;
	@Autowired
	private RunErrorService runErrorService;
	@Autowired
	private DevicePowerService devicePowerService;
	
	private static ExecutorService threadPool = Executors.newFixedThreadPool((Runtime.getRuntime().availableProcessors()*2)+1);
	
	@RequestMapping(value = "/toPage/{path}")
	public ModelAndView toPage(@PathVariable String path) {
		ModelAndView mv = new ModelAndView("/jsp/countdata/" + path);
		return mv;
	}
	
	@RequestMapping(value = "/toEditPower")
	public String toEditPower(HttpServletRequest request,HttpServletResponse response,ModelMap map) {
		DevicePower devicePower = devicePowerService.selectAll();
		map.put("model", devicePower);
		return "/jsp/countdata/editPower";
	}

	@RequestMapping(value = "/editPower")
	@ResponseBody
	public Object editPower(HttpServletRequest request,HttpServletResponse response,DevicePower devicePower) {
		int i = devicePowerService.savePower(devicePower);
		return i;
	}
	
	@RequestMapping(value = "/toHomePage")
	public String toHomePage(HttpServletRequest request,HttpServletResponse response,ModelMap map) {
		return "/jsp/home/homePage";
	}
	
	@RequestMapping(value = "/getHomePageData")
	@ResponseBody
	public void getHomePageData(HttpServletRequest request,HttpServletResponse response) {
		ArrayList<ClassesInfoModel> list = ClassesFactory.getClassesByBuildAndFloor("all", "all", null);
		Map<String, Object> map = new HashMap<>();
		map.put("status", true);
		map.put("msg", "操作成功！");
		if (list != null && !list.isEmpty()) {
			map.put("data", list);
			ResponseUtil.sendHtml(response, JsonUtil.toJson(map));
		} else {
			ResponseUtil.sendHtml(response, JsonUtil.toJson(map));
		}
		return;
	}
	@RequestMapping(value = "/getClassList")
	@ResponseBody
	public void getClassList(HttpServletRequest request,HttpServletResponse response) {
		List<ClassesInfoModel> list = ClassesFactory.getClassesByBuildAndFloor("all", "all", null);
		Map<String, Object> map = new HashMap<>();
		map.put("status", true);
		map.put("msg", "操作成功！");
		if (list != null && !list.isEmpty()) {
			map.put("data", list);
			ResponseUtil.sendHtml(response, JsonUtil.toJson(map));
		} else {
			ResponseUtil.sendHtml(response, JsonUtil.toJson(map));
		}
	}
	@RequestMapping(value = "/getHomeRunCount")
	@ResponseBody
	public void getHomeRunCount(HttpServletRequest request,HttpServletResponse response,RunInfo runInfo,Integer showRowsNum) {
		
		// 在用未用设备统计开始
		ArrayList<ClassesInfoModel> list = ClassesFactory.getClassesByBuildAndFloor("all", "all", null);
		Map<String, Object> map = new HashMap<>();
		map.put("status", true);
		map.put("msg", "操作成功！");
		
		if (list != null && !list.isEmpty()) {
			int total = list.size();
			int centerUsing = 0; //  启用的中控
			int centerNotUse = total; // 未启用的中控
			int tounyUsing = 0; // 启用的投影
			int tounyNotUse = total; // 未启用的投影
			int airUsing = 0; // 启用的空调
			int airNotUse = total; // 未启用的空调
			int powerUsing= 0; // 启用的电箱
			int powerNotUse = total; // 未启用的电箱
			
			// 在用设备集合
			List<Integer> using = new ArrayList<>();
			// 未用设备集合
			List<Integer> notUse = new ArrayList<>();
						
			List<HomeCountData> homeCountList = runInfoService.getUseOrNotUseData(runInfo);
			if (homeCountList != null && !homeCountList.isEmpty()) {
				for (HomeCountData homeCountData : homeCountList) {
					if (homeCountData.getName().equals(Constans.DEVICE_TYPE[1])) {
						// 中控
						centerUsing = homeCountData.getValue();
						centerNotUse = total-homeCountData.getValue();
					}
					if (homeCountData.getName().equals(Constans.DEVICE_TYPE[2])) {
						// 投影
						tounyUsing = homeCountData.getValue();
						tounyNotUse = total-homeCountData.getValue();
					}
					if (homeCountData.getName().equals(Constans.DEVICE_TYPE[3])) {
						// 空调
						airUsing = homeCountData.getValue();
						airNotUse = total-homeCountData.getValue();
					}
					if (homeCountData.getName().equals(Constans.DEVICE_TYPE[4])) {
						// 电箱
						powerUsing = homeCountData.getValue();
						powerNotUse = total-homeCountData.getValue();
					}
				}
			}
			using.add(centerUsing);
			using.add(tounyUsing);
			using.add(airUsing);
			using.add(powerUsing);
			notUse.add(centerNotUse);
			notUse.add(tounyNotUse);
			notUse.add(airNotUse);
			notUse.add(powerNotUse);
			Map<String, Object> useOrNotUse = new HashMap<>();
			useOrNotUse.put("using", using);
			useOrNotUse.put("notUse",notUse);
			map.put("useOrNotUse", useOrNotUse);
			// 在用未用设备统计结束
			
			// 教室使用时长排名开始
			Map<String, Object> roomRankings = runInfoService.getClassRoomRankings(runInfo,showRowsNum);
			if (roomRankings != null && !roomRankings.isEmpty()) {
				map.put("classRoomRank", roomRankings);
			} else {
				map.put("classRoomRank", "");
			}
			// 教室使用时长排名结束
			
			// 设备累积运行时长排名开始
			Map<String, Object> rankings = runInfoService.getDeviceRankings(runInfo);
			map.put("deviceRankings", rankings);
			// TODO
			// 设备累积运行时长排名结束
			ResponseUtil.sendHtml(response, JsonUtil.toJson(map));
		} else {
			ResponseUtil.sendHtml(response, JsonUtil.toJson(map));
		}
		return;
	}
	
	
	@RequestMapping("getRunCount")
	@ResponseBody
	public Object  getRunDataCount(HttpSession session,HttpServletRequest request,HttpServletResponse response,RunInfo runInfo,RunError error){
		List<RunCountData> runData = runInfoService.runDataCount(runInfo);
		List<RunCountData> powerData = runInfoService.powerDataCount(runInfo);
		
		Map<String, Object> map = new HashMap<>();
		map.put("runData", runData);
		map.put("powerData", powerData);
		RunError error1 = new RunError();
		RunError error2 = new RunError();
		RunError error3 = new RunError();
		RunError error4 = new RunError();
		error1.setDeviceType(Constans.DEVICE_TYPE[1]);
		error2.setDeviceType(Constans.DEVICE_TYPE[2]);
		error3.setDeviceType(Constans.DEVICE_TYPE[3]);
		error4.setDeviceType(Constans.DEVICE_TYPE[4]);
		if (error.getCreateYear()!=null && !error.getCreateYear().equals("")) {
			error1.setCreateYear(error.getCreateYear());
			error2.setCreateYear(error.getCreateYear());
			error3.setCreateYear(error.getCreateYear());
			error4.setCreateYear(error.getCreateYear());
		}
		if (error.getErrorType()!= null && !error.getErrorType().equals("")) {
			error1.setErrorType(error.getErrorType());
			error2.setErrorType(error.getErrorType());
			error3.setErrorType(error.getErrorType());
			error4.setErrorType(error.getErrorType());
		} else {
			// 默认统计远程故障
			error1.setErrorType(Constans.ERROR_TYPE[1]);
			error2.setErrorType(Constans.ERROR_TYPE[1]);
			error3.setErrorType(Constans.ERROR_TYPE[1]);
			error4.setErrorType(Constans.ERROR_TYPE[1]);
		}
		List<RunErrorCountData> errorInfo1 = runErrorService.getErrorDataByInfo(error1);
		List<RunErrorCountData> errorInfo2 = runErrorService.getErrorDataByInfo(error2);
		List<RunErrorCountData> errorInfo3 = runErrorService.getErrorDataByInfo(error3);
		List<RunErrorCountData> errorInfo4 = runErrorService.getErrorDataByInfo(error4);
		List<Long> values1 = new ArrayList<>();
		List<Long> values2 = new ArrayList<>();
		List<Long> values3 = new ArrayList<>();
		List<Long> values4 = new ArrayList<>();
		if (errorInfo1 != null && !errorInfo1.isEmpty()) {
			
			for (int i = 0; i < errorInfo1.size(); i++) {
				if (i==0) {
					for (int j = 1; j < errorInfo1.get(i).getCreateMonth(); j++) {
						values1.add(0L);
					}
					
				} else {
					for (int j = 1; j < errorInfo1.get(i).getCreateMonth()-errorInfo1.get(i-1).getCreateMonth(); j++) {
						values1.add(0L);
					}
					
				}
				values1.add(errorInfo1.get(i).getTimes());
			}
		}
		if (errorInfo2 != null && !errorInfo2.isEmpty()) {
			for (int i = 0; i < errorInfo2.size(); i++) {
				if (i==0) {
					for (int j = 1; j < errorInfo2.get(i).getCreateMonth(); j++) {
						values2.add(0L);
					}
				} else {
					for (int j = 1; j < errorInfo2.get(i).getCreateMonth()-errorInfo2.get(i-1).getCreateMonth(); j++) {
						values2.add(0L);
					}
				}
				values2.add(errorInfo2.get(i).getTimes());
			}
		}
		if (errorInfo3 != null && !errorInfo3.isEmpty()) {
			for (int i = 0; i < errorInfo3.size(); i++) {
				if (i==0) {
					for (int j = 1; j < errorInfo3.get(i).getCreateMonth(); j++) {
						values3.add(0L);
					}
				} else {
					for (int j = 1; j < errorInfo3.get(i).getCreateMonth()-errorInfo3.get(i-1).getCreateMonth(); j++) {
						values3.add(0L);
					}
				}
				values3.add(errorInfo3.get(i).getTimes());
			}

		}
		if (errorInfo4 != null && !errorInfo4.isEmpty()) {
			for (int i = 0; i < errorInfo4.size(); i++) {
				if (i==0) {
					for (int j = 1; j < errorInfo4.get(i).getCreateMonth(); j++) {
						values4.add(0L);
					}
				} else {
					for (int j = 1; j < errorInfo4.get(i).getCreateMonth()-errorInfo4.get(i-1).getCreateMonth(); j++) {
						values4.add(0L);
					}
				}
				values4.add(errorInfo4.get(i).getTimes());
			}
		}

		map.put("values1", values1);// 1：1中控，
		map.put("values2", values2);// 2：2投影，
		map.put("values3", values3);// 3：3空调，
		map.put("values4", values4);// 4：4电箱
		return map;
	
	}
	
	
	
	
	
	@RequestMapping("insert")
	@ResponseBody
	public Object insert(HttpSession session,HttpServletRequest request,HttpServletResponse response,RunInfo runInfo){
		runInfo = new RunInfo();
		runInfo.setClassRoomId(5L);
		runInfo.setDeviceType("1");
		runInfo.setIsOpened("1");
		runInfo.setOpenTime(new Date());
		runInfo.setCreateDate(new Date());
		runInfo.setUpdateDate(new Date());
		runInfoService.saveRunInfo(runInfo);
		Map<String, Object> map = new HashMap<>();
		map.put("runData", "插入成功！");
		return map;
	
	}
	@RequestMapping("update")
	@ResponseBody
	public Object update(HttpSession session,HttpServletRequest request,HttpServletResponse response,RunInfo runInfo){
		
		runInfo = new RunInfo();
		runInfo.setClassRoomId(5L);
		runInfo.setDeviceType("1");
		List<RunInfo> toClose = runInfoService.findToClose(runInfo);
		for (RunInfo run : toClose) {
			runInfo = new RunInfo();
			runInfo.setRunInfoId(run.getRunInfoId());
			runInfo.setIsClosed("1");
			runInfo.setCloseTime(new Date());
			runInfo.setUpdateDate(new Date());
			runInfoService.updateRunInfo(runInfo);
		}
		runInfo=null;
		toClose.clear();
		toClose = null;
		Map<String, Object> map = new HashMap<>();
		map.put("runData", "修改成功！");
		return map;
	
	}
	
	// TODO 分页
	@RequestMapping("findPage")
	@ResponseBody
	public void findPage(HttpSession session,HttpServletRequest request,HttpServletResponse response,RunError runError){
		
		PageBean pageBean = runErrorService.findPage(runError);
		ResponseUtil.sendHtml(response, JsonUtil.toJson(pageBean));
		return ;
	
	}
	
	@RequestMapping("/openOrCloseAllAirSw")
	@ResponseBody
	public void openOrCloseAllAirSw(HttpServletRequest request,HttpServletResponse response,HttpSession session,Boolean isOpen){
		Map<String, Object> map = new HashMap<>();
		map.put("status", true);
		map.put("msg", "操作成功！");
		List<ClassesModel> lists = ClassesFactory.getAllClassModel();
		if (lists== null || lists.isEmpty()) {
			map.put("status", false);
			map.put("msg", "系统中暂无设备需要打开或关闭！");
			ResponseUtil.sendHtml(response, JsonUtil.toJson(map));
			return;
		}
		
		// 采用线程池处理
		if (isOpen) {
			for (ClassesModel model : lists) {
				if (model.getDevStatus().getAir_switch().equals("off")) {
					// 如果是全部开启空调电闸，则查询数据库，找出符合条件的数据，进行开启电闸
					FutureTask<ClassesModel> ft = new FutureTask<>(new ProcessThread(model,isOpen));
					threadPool.execute(ft);
				}
				map.put("msg", "空调电闸全部开启成功！");
			}
			
		} else {
			
			for (ClassesModel model : lists) {
				if (model.getDevStatus().getAir_switch().equals("on")) {
					// 如果是全部关闭空调店招，则查询数据库，找出符合条件的数据，进行关闭电闸
					FutureTask<ClassesModel> ft = new FutureTask<>(new ProcessThread(model,isOpen));
					threadPool.execute(ft);
				}
				map.put("msg", "空调电闸全部关闭成功！");
			}
		}
		
		ResponseUtil.sendHtml(response, JsonUtil.toJson(map));
	}
	
	// 处理线程实体类
	public static class ProcessThread implements Callable<ClassesModel>{
		private ClassesModel model;
		private Boolean isOpen;
		public ProcessThread(ClassesModel model,Boolean isOpen) {
			this.model = model;
			this.isOpen = isOpen;
		}

		@Override
		public ClassesModel call() throws Exception {
			if (isOpen) {
				model.openAirSwitch();
			} else {
				model.closeAirSwitch();
			}
			return model;
		}
		
	}
		
}