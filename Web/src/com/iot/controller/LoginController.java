package com.iot.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.ModelAndView;

import com.iot.model.LoginUser;

@Controller
@RequestMapping("/login")
public class LoginController {
	@RequestMapping("/login")
	@ResponseBody
	public void login(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException, ServletException {
		ModelAndView mav = new ModelAndView();
		response. setContentType ( "text/html" ) ;   
        String userName = request.getParameter("userName"); 
        String password = request.getParameter("password");
        System.out.println("username:"+userName+"\r\n"+"password:"+password);  
        if(null != userName && !"".equals(userName) && null != password && !"".equals(password) &&
        		((userName.equals("admin")&&password.equals("dianjiao"))||(userName.equals("user")&&password.equals("user"))))
        {
        	LoginUser user = new LoginUser(userName, password);
        	session.setAttribute("loginUser", user);
        	request.getSession().setAttribute("userName", userName);
            request.getSession().setAttribute("password", password.hashCode());
            
            
            //创建Cookie，将用户名存到叫username的cookie中
            //Cookie 对象携带需要保存的数据，name=value，都是字符串类型
            //每个cookie保存一个数据，如果需要多个，创建多个cookie对象
            Cookie cookie = new Cookie("userName", userName);
            cookie.setPath("/");
            response.addCookie(cookie);
            
            
            request.getRequestDispatcher("login_success.jsp").forward(request, response);
            // response.sendRedirect("login_success.jsp");
        	//return new ModelAndView("login_success");
        }  
        else {
        	request.getSession().setAttribute("userName", null);
            request.getSession().setAttribute("password", null);
            response.sendRedirect("login_failure.jsp");
            session.setAttribute("loginUser", null);
            //return new ModelAndView("login_failure");
        }
	}

}
