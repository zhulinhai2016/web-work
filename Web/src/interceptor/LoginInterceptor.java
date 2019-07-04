package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.iot.model.LoginUser;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView view)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		
		String url = request.getRequestURI();
		String path = request.getContextPath();
	    if(url.contains("loginOut")){
	    	return true;
	    }
	    if (url.contains("login"))  {
//	    	response.sendRedirect(path + "/login/index.jsp");
	    	return true;
	    }
	    if (url.contains("selectAllBuild")) {
			return true;
		}
	    LoginUser user = (LoginUser) request.getSession().getAttribute("loginUser");
	    if (user == null) {
	    	response.sendRedirect(path + "/login/index.jsp");
	    	return false;
		} else {
			return true;
		}
	}

	

}
