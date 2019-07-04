package com.iot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iot.model.RunCountData;
import com.iot.model.RunError;
import com.iot.model.RunErrorCountData;
import com.iot.model.RunInfo;
import com.iot.service.RunErrorService;
import com.iot.service.RunInfoService;

import ecapi.api.ClassesFactory;
import ecapi.model.ClassesDevModel;
import ecapi.model.ClassesModel;
import util.Constans;
import util.JsonUtil;
import util.ResponseUtil;

/**
 * 对外接口类
 * @author LinHaiZhu
 *
 */
@RequestMapping("/restFul/service/runData")
@Controller
public class InterFaceController {

	@Autowired
	private RunInfoService runInfoService;
	@Autowired
	private RunErrorService runErrorService;
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param classroom
	 * @return
	 * return_type Object
	 */
	@RequestMapping(value="/findStatus")
	@ResponseBody
	public void findStatus(HttpServletRequest request, HttpServletResponse response, RunInfo info) {
		Map<String, Object> map = new HashMap<>();
		map.put("successCode", 0);
		map.put("responseMsg", "请求成功！");
		map.put("data", null);
		try {
			if (info.getClassRoomId() == null) {

				map.put("successCode", 1);
				map.put("responseMsg", "参数错误！");
				ResponseUtil.sendHtml(response, JsonUtil.toJson(map));
				return;
			}
			
			ClassesModel m = ClassesFactory.getClassesObj(info.getClassRoomId().toString());
			if (null != m) {
				ClassesDevModel status = m.getDevStatus();
				map.put("data", status.getMap());
			}
			ResponseUtil.sendHtml(response, JsonUtil.toJson(map));
			return;
		} catch (Exception e) {
			map.put("successCode", 2);
			map.put("responseMsg", "请求失败！");
			e.printStackTrace();
		}
		ResponseUtil.sendHtml(response, JsonUtil.toJson(map));
		return;
	}
	
	/**
	 * 运行数据接口
	 * @param request
	 * @param response
	 * @param classroom
	 * @return
	 * return_type Object
	 */
	@RequestMapping(value="/findRunData")
	@ResponseBody
	public void findRunData(HttpServletRequest request, HttpServletResponse response, RunInfo info) {
		Map<String, Object> map = new HashMap<>();
		map.put("successCode", 0);
		map.put("responseMsg", "请求成功！");
		map.put("data", null);
		try {
			if (info.getClassRoomId()  == null) {
				map.put("successCode", 1);
				map.put("responseMsg", "参数错误！");
				ResponseUtil.sendHtml(response, JsonUtil.toJson(map));
				return ;
			}
			RunInfo runInfo = new RunInfo();
			runInfo.setClassRoomId(info.getClassRoomId());
			// 查询教室最新数据
			List<RunInfo> findNewestData = runInfoService.findNewestData(runInfo);
			Map<String, Object> runData = new HashMap<>();
			runData.put("newestData", findNewestData);
			// 统计一个教室各个设备的累积运行时长
			List<RunCountData> runDataCount = runInfoService.runDataCount(runInfo);
			runData.put("countData", runDataCount);
			map.put("data", runData);
			ResponseUtil.sendHtml(response, JsonUtil.toJson(map));
			return ;
		} catch (Exception e) {
			map.put("successCode", 2);
			map.put("responseMsg", "请求失败！");
			e.printStackTrace();
		}
		ResponseUtil.sendHtml(response, JsonUtil.toJson(map));
		return ;
	}
	
	/**
	 * 异常数据接口
	 * @param request
	 * @param response
	 * @param classroom
	 * @return
	 * return_type Object
	 */
	@RequestMapping(value="/findErrorData")
	@ResponseBody
	public void findErrorData(HttpServletRequest request, HttpServletResponse response, RunError error) {
		Map<String, Object> map = new HashMap<>();
		map.put("successCode", 0);
		map.put("responseMsg", "请求成功！");
		map.put("data", null);
		try {
			
			RunError error1 = new RunError();
			RunError error2 = new RunError();
			RunError error3 = new RunError();
			RunError error4 = new RunError();
			if (error.getClassRoomId()  != null) {
				error1.setClassRoomId(error.getClassRoomId());
				error2.setClassRoomId(error.getClassRoomId());
				error3.setClassRoomId(error.getClassRoomId());
				error4.setClassRoomId(error.getClassRoomId());
			}
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
			}
			List<RunErrorCountData> errorInfo1 = runErrorService.getErrorDataByInfo(error1);
			List<RunErrorCountData> errorInfo2 = runErrorService.getErrorDataByInfo(error2);
			List<RunErrorCountData> errorInfo3 = runErrorService.getErrorDataByInfo(error3);
			List<RunErrorCountData> errorInfo4 = runErrorService.getErrorDataByInfo(error4);
			Map<String, List<RunErrorCountData>> dataMap = new HashMap<>();
			dataMap.put("cloudTerminal", errorInfo1);// 中控
			dataMap.put("touy", errorInfo2);// 投影
			dataMap.put("airConditioner", errorInfo3);// 空调
			dataMap.put("electricBox", errorInfo4);// 电箱
			map.put("data", dataMap);
			ResponseUtil.sendHtml(response, JsonUtil.toJson(map));
			return;
		} catch (Exception e) {
			map.put("successCode", 2);
			map.put("responseMsg", "请求失败！");
			e.printStackTrace();
		}
		ResponseUtil.sendHtml(response, JsonUtil.toJson(map));
		return;
	}
}
