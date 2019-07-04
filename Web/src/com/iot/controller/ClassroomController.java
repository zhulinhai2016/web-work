package com.iot.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iot.model.Classroom;
import com.iot.model.ClassroomExample;
import com.iot.model.ClassroomExample.Criteria;
import com.iot.model.ResultVO;
import com.iot.service.ClassroomService;

import action.Cmd;
import ecapi.api.ClassesFactory;

@Controller

public class ClassroomController extends BaseAction {
	@Autowired
	private ClassroomService buildService;

	/**
	 * 教室管理列表
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectAllClassroom")
	public void selectAllClassroom() throws Exception {
		// 当前页,page由分页工具负责传过来
		int number = Integer.parseInt((limit == null || limit == "0") ? "10" : limit);
		int intPage = Integer.parseInt((offset == null || offset == "0") ? "1" : offset); // 每页显示条数
		intPage = (intPage / number) + 1;
		String name = getParameter("name", "");
		name = new String(name.getBytes("iso-8859-1"), "UTF-8");
		String id = getParameter("id", "");
		String floorId = getParameter("floorId", "");

		ClassroomExample advExample = new ClassroomExample();
		Criteria criteria = advExample.createCriteria();
		if (StringUtils.isNotBlank(name)) {
			criteria.andNameLike(name);
		}
		if (StringUtils.isNotBlank(id)) {
			criteria.andIdEqualTo(Long.parseLong(id));
		}
		if (StringUtils.isNotBlank(floorId)) {
			criteria.andFloorIdEqualTo(Long.parseLong(floorId));
		}
		List<Classroom> list = buildService.selectByExample(advExample);
		ResultVO vo = new ResultVO();
		vo.total = (int) list.size();
		vo.page = number;
		vo.rows = list;
		JSONObject jo = JSONObject.fromObject(vo);
		// 设置response的ContentType解决中文乱码
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(jo.toString());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/deleteClassroom")
	public void deleteClassroom() throws Exception {
		String id = getParameter("id", "0");
		Map mp = new HashMap();
		ClassroomExample example = new ClassroomExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(Long.parseLong(id));
		Boolean flag = buildService.deleteByExample(example) > 0;
		if (flag) {
			// 如果删除教室成功 同时同步缓存数据
			buildService.syncPool(Long.parseLong(id));
		}
		mp.put("result", flag);
		JSONObject jo = JSONObject.fromObject(mp);
		// 设置response的ContentType解决中文乱码
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(jo.toString());

	}

	@RequestMapping(value = "/sendTouyText")
	public void sendTouyText() throws IOException {
		String sendText = getParameter("sendText", "");
		Cmd cmd = new Cmd();
		String result = cmd.sendTouyText(sendText, getParameter("class_id", ""));
		Map<String, String> mp = new HashMap<>();
		mp.put("result", result);
		JSONObject jo = JSONObject.fromObject(mp);

		// 设置response的ContentType解决中文乱码
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(jo.toString());

	}

	@RequestMapping(value = "/addOrUpdateClassroom")
	public void addOrUpdateClassroom() throws Exception {
		boolean flagUsed = false;
		String id = getParameter("id", null);
		String name = getParameter("class_name", null);
		String floorId = getParameter("class_floor", null);
		String class_ip = getParameter("class_ip", null);
		String class_center_ip = getParameter("class_center_ip", null);// 云中控
		String class_touy = getParameter("class_touy", null);
		String class_id = getParameter("class_id", null);
		String class_build = getParameter("class_build", null);

		Classroom adv = new Classroom();
		// 校验所填的教室名称是否在系统中已经存在
		List<Classroom> classroomList = new ArrayList<>();
		if (name != null) {
			classroomList = buildService.selectByName(name);
		}
		Map mp = new HashMap();
		JSONObject jo = new JSONObject();
		if (classroomList.size() > 0) {
			// JOptionPane.showMessageDialog(null, name+"已经存在!", "提示",
			// JOptionPane.ERROR_MESSAGE);
			// JOptionPane.showMessageDialog(null, name+"教室名已使用!");
			mp.put("result", 0);
			mp.put("message", name + "教室名已使用!");
			mp.put("classinfo", adv);
			jo = JSONObject.fromObject(mp);
			flagUsed = true;
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(jo.toString());
			return;
		}
		// 校验IP释放重复
		if (class_ip != null && buildService.selectByIP(class_ip).size() > 0) {
			// List<Classroom> list = buildService.selectByIP(class_ip);
			if (ClassesFactory.checkedByIP(class_ip,class_id,id)) {
				mp.put("result", 1);
			} else {
				mp.put("result", 0);
				mp.put("message", "教室IP:" + class_ip + "已被其他教室占用!");
				mp.put("classinfo", adv);
				flagUsed = true;

			}
			jo = JSONObject.fromObject(mp);
			if (flagUsed) {
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().print(jo.toString());
				return;
			}
		}
		if (class_center_ip != null) {
			if (ClassesFactory.checkedByIP(class_center_ip,class_id,id)) {
				mp.put("result", 1);
			} else {
				mp.put("result", 0);
				mp.put("message", "中控IP:" + class_center_ip + "已被其他教室占用!");
				mp.put("classinfo", adv);
				flagUsed = true;

			}
			jo = JSONObject.fromObject(mp);
			if (flagUsed) {
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().print(jo.toString());
				return;
			}
		}
		if (class_touy != null) {
			if (ClassesFactory.checkedByIP(class_touy,class_id,id)) {
				mp.put("result", 1);
			} else {
				mp.put("classinfo", adv);
				mp.put("result", 0);
				mp.put("message", "监控IP:" + class_touy + "已被其他教室占用!");
				flagUsed = true;

			}
			jo = JSONObject.fromObject(mp);
			if (flagUsed) {
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().print(jo.toString());
				return;
			}
		}

		adv.setName(name);
		if (StringUtils.isNotBlank(floorId)) {
			adv.setFloorId(Long.parseLong(floorId));
		}
		if (StringUtils.isNotBlank(class_build)) {
			adv.setBuildId(Long.parseLong(class_build));
		}
		if (class_ip != null) {
			adv.setClassIp(class_ip);
		}
		if (class_center_ip != null) {
			adv.setClassCenterIp(class_center_ip);
		}
		if (class_touy != null) {
			adv.setClassTouy(class_touy);
		}
		adv.setClassId(class_id);
		// Map mp=new HashMap();
		Boolean flag = false;
		if (id == null) {
			// staff.setPassword(mobile);
			flag = buildService.insert(adv) > 0;

		} else {
			adv.setId(Long.parseLong(id));
			flag = buildService.updateByPrimaryKeySelective(adv) > 0;
			if (flag) {
				// 如果更新教室成功 同时同步缓存数据
				buildService.syncPool(Long.parseLong(id));
			}
		}
		mp.put("result", flag);
		mp.put("classinfo", adv);
		jo = JSONObject.fromObject(mp);

		// 设置response的ContentType解决中文乱码
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(jo.toString());

	}
}
