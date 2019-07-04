package com.iot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iot.model.ClassroomExample;
import com.iot.model.Floor;
import com.iot.model.FloorExample;
import com.iot.model.FloorExample.Criteria;
import com.iot.model.ResultVO;
import com.iot.service.ClassroomService;
import com.iot.service.FloorService;

import ecapi.api.ClassesFactory;
import net.sf.json.JSONObject;
/**
 * 楼层管理Action
 * @author jh
 *
 */
@Controller
public class FloorAction extends BaseAction {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FloorService floorService;
	@Autowired
	private ClassroomService classroomService;
	/**
	 *  楼层管理列表
	 * @throws Exception
	 */
	@RequestMapping(value="/selectAllFloor")	
	public void selectAllFloor() throws Exception
	{
        //当前页,page由分页工具负责传过来         
		int number = Integer.parseInt((limit == null || limit == "0") ? "10":limit);
		int intPage = Integer.parseInt((offset == null || offset == "0") ? "1":offset);          //每页显示条数         
		intPage =(intPage / number)+1;
		String name =getParameter("name","");
		name=new String(name.getBytes("iso-8859-1"),"UTF-8");
		String id =getParameter("id","");	
		String buildId =getParameter("buildId","");	
		
		FloorExample advExample = new FloorExample();
		Criteria criteria = advExample.createCriteria();
		if(StringUtils.isNotBlank(name)){
			criteria.andNameLike(name);
		}
		if(StringUtils.isNotBlank(id)){
			criteria.andIdEqualTo(Long.parseLong(id));
		}
		if(StringUtils.isNotBlank(buildId)){
			criteria.andBuildIdEqualTo(buildId);
		}
		List<Floor> list = floorService.selectByExample(advExample);
		ResultVO vo = new ResultVO();
		vo.total = (int)list.size();
		vo.page =number;
		vo.rows =list;
		JSONObject jo = JSONObject.fromObject(vo); 
		//   设置response的ContentType解决中文乱码  
	    response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(jo.toString());
	}
	@RequestMapping(value="/checkedByIP")	
	public void checkedByIP(String ip) throws Exception
	{
//		String ip =getParameter("ip","");
		
		boolean byIP = ClassesFactory.checkedByIP(ip,null,null);
		JSONObject jo = new JSONObject();
		if (StringUtils.isBlank(ip)) {
			jo.put("result", false);

		} else {
			jo.put("result", byIP);
			
		}
		//   设置response的ContentType解决中文乱码  
	    response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(jo.toString());
	}

	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/deleteFloor")	
	public void deleteFloor() throws Exception
	{
		String id =getParameter("id","0");
		Map mp=new HashMap();
		FloorExample example = new FloorExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(Long.parseLong(id));
		Boolean flag = floorService.deleteByExample(example)>0;
		if(flag){
			ClassroomExample cexample = new ClassroomExample();
			cexample.createCriteria().andFloorIdEqualTo(Long.valueOf(id));
			classroomService.deleteByExample(cexample);
		}
		mp.put("result", flag);
		JSONObject jo = JSONObject.fromObject(mp); 
		//   设置response的ContentType解决中文乱码  
	    response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(jo.toString());
	
	}
	
	@RequestMapping(value="/addOrUpdateFloor")		
	public void addOrUpdateFloor() throws Exception
	{  
		String id =getParameter("id","");
		String name =getParameter("floor","");
		String buildId =getParameter("build","");
		
		Floor adv = new Floor();
		adv.setName(name);
		adv.setBuildId(buildId);
		Map mp=new HashMap();
		Boolean flag =false;
		if(id.equals(""))
		{
			//staff.setPassword(mobile);
			flag = floorService.insert(adv)>0;
			
		}else
		{
			adv.setId(Long.parseLong(id));
			flag = floorService.updateByPrimaryKey(adv)>0;
		}
		mp.put("result", flag);
		mp.put("floorinfo", adv);
		JSONObject jo = JSONObject.fromObject(mp); 
		//   设置response的ContentType解决中文乱码  
	    response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(jo.toString());
	}	
	
}