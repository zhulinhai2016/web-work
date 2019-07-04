package com.iot.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

@Controller
@RequestMapping("/frame")
public class IndexController {

	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mav = new ModelAndView();
		response. setContentType ( "text/html" ) ;   
        String userName = (String) session.getAttribute("userName"); 
        Object object = session.getAttribute("password");
        mav.addObject("userName", userName);	
//       response.sendRedirect("index.jsp");
//       return null;
        mav.setViewName("frame/index");
       return mav;
	}
}
