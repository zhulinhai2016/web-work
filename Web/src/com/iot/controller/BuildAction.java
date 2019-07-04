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

import com.iot.model.Build;
import com.iot.model.BuildExample;
import com.iot.model.BuildExample.Criteria;
import com.iot.model.ClassroomExample;
import com.iot.model.FloorExample;
import com.iot.model.ResultVO;
import com.iot.service.BuildService;
import com.iot.service.ClassroomService;
import com.iot.service.FloorService;

import net.sf.json.JSONObject;
/**
 * 大楼管理Action
 * @author jh
 *
 */
@Controller
public class BuildAction extends BaseAction {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BuildService buildService;
	@Autowired
	private FloorService floorService;
	@Autowired
	private ClassroomService classroomService;

	/**
	 *  大楼管理列表
	 * @throws Exception
	 */
	@RequestMapping(value="/selectAllBuild")	
	public void selectAllBuild() throws Exception
	{
        //当前页,page由分页工具负责传过来         
		int number = Integer.parseInt((limit == null || limit == "0") ? "10":limit);
		int intPage = Integer.parseInt((offset == null || offset == "0") ? "1":offset);          //每页显示条数         
		intPage =(intPage / number)+1;
		String name =getParameter("name","");
		name=new String(name.getBytes("iso-8859-1"),"UTF-8");
		String id =getParameter("id","");	
		
		BuildExample advExample = new BuildExample();
		Criteria criteria = advExample.createCriteria();
		if(StringUtils.isNotBlank(name)){
			criteria.andNameLike(name);
		}
		if(StringUtils.isNotBlank(id)){
			criteria.andIdEqualTo(Long.parseLong(id));
		}
		List<Build> list = buildService.selectByExample(advExample);
		ResultVO vo = new ResultVO();
		vo.total = (int)list.size();
		vo.page =number;
		vo.rows =list;
		JSONObject jo = JSONObject.fromObject(vo); 
	//   设置response的ContentType解决中文乱码  
	    response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(jo.toString());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/deleteBuild")	
	public void deleteBuild() throws Exception
	{
		String id =getParameter("id","0");
		Map mp=new HashMap();
		BuildExample example = new BuildExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(Long.parseLong(id));
		Boolean flag = buildService.deleteByExample(example)>0;
		if(flag){
			FloorExample fexample = new FloorExample();
			fexample.createCriteria().andBuildIdEqualTo(id);
			boolean delFloorFlag = floorService.deleteByExample(fexample)>0;
			if(delFloorFlag){
				ClassroomExample cexample = new ClassroomExample();
				cexample.createCriteria().andBuildIdEqualTo(Long.valueOf(id));
				classroomService.deleteByExample(cexample);
			}
		}
		mp.put("result", flag);
		JSONObject jo = JSONObject.fromObject(mp);
		//   设置response的ContentType解决中文乱码  
	    response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(jo.toString());
	
	}
	
	@RequestMapping(value="/addOrUpdateBuild")		
	public void addOrUpdateBuild() throws Exception
	{  
		String id =getParameter("id","");
		String name =getParameter("name","");
		String picture =getParameter("picture","");
		String sex =getParameter("sex","");
		String mobile=getParameter("phone","");
		String idno=getParameter("idno","0");
		String usertype="2";
		//String storeid=getParameter("storeid","0");
		String carwash =getParameter("carwash","0");
		String maintain =getParameter("maintain","0");
		String repair =getParameter("repair","0");
		String beauty=getParameter("beauty","0");		
		String bak1=getParameter("bak1","");
		String bak2=getParameter("bak2","");
		String bak3=getParameter("bak3","");
		
		Build adv = new Build();
		adv.setName(name);
			 
		Map mp=new HashMap();
		Boolean flag =false;
		if(id.equals(""))
		{
			//staff.setPassword(mobile);
			flag = buildService.insert(adv)>0;
			
		}else
		{
			adv.setId(Long.parseLong(id));
			flag = buildService.updateByPrimaryKey(adv)>0;
		}
		mp.put("result", flag);
		mp.put("buildinfo", adv);
		JSONObject jo = JSONObject.fromObject(mp); 
		//   设置response的ContentType解决中文乱码  
	    response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(jo.toString());
	}
	
	
}