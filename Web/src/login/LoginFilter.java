package login;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
		HttpServletResponse httpRes = (HttpServletResponse) servletResponse;
		HttpSession session = httpReq.getSession();
		String userId = null;

		String sessionId = session.getId();
		
		String requestURI = httpReq.getRequestURI();
		
		userId = (String) httpReq.getSession().getAttribute("userName");
		
		if(null == userId && true == requestURI.startsWith(httpReq.getContextPath() + "/login/")){
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}

		if ( !true == requestURI.startsWith(httpReq.getContextPath() + "/login/") ) {

			
			if(null == userId){
				httpRes.sendRedirect(httpReq.getContextPath() + "/login/index.jsp");
				return;
			}
			
			String lastSessionId = session.getId();
			if (false == sessionId.equals(lastSessionId)) {
				
					if(true == requestURI.startsWith(httpReq.getContextPath() + "/zjz/") 
							|| true == requestURI.startsWith(httpReq.getContextPath() + "/logins/")
							|| true == requestURI.startsWith(httpReq.getContextPath() + "/page/")
							|| true == requestURI.startsWith(httpReq.getContextPath() + "/public/")){
						httpRes.sendRedirect(httpReq.getContextPath() + "/login/index.jsp");
						return;
					}else{
						//登出跳页面
						httpReq.getSession().setAttribute("userName", null);
						httpReq.getSession().setAttribute("password", null);
						httpRes.sendRedirect(httpReq.getContextPath() + "/login/index.jsp");
						return;
					}
			}
		}
		filterChain.doFilter(servletRequest, servletResponse);
		return;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
