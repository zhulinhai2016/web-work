package schedule;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.TimerUtil;

/**
 * Servlet implementation class ScheduleServlet
 */
@WebServlet(value="/ScheduleServlet",loadOnStartup=1,initParams={  
        //@WebInitParam(name = "first", value = "15:30:00"),     
        //@WebInitParam(name = "second", value = "19:50:00")
        @WebInitParam(name = "first", value = "00:10:00"),     
        @WebInitParam(name = "second", value = "17:40:00")
        
	})
public class ScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ScheduleServlet() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		      
		        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
		        long oneDay = 24 * 60 * 60 * 1000;
		        // 每天第一次同步课表
		        long firstDelay  = TimerUtil.getTimeMillis(config.getInitParameter(TimerUtil.FIRST)) - System.currentTimeMillis();  
		        firstDelay = firstDelay > 0 ? firstDelay : oneDay + firstDelay;  
		        
		        service.scheduleAtFixedRate(  
		        		new SynchronizeKebiaoSchedule(TimerUtil.FIRST), 
		        		firstDelay,  
		                oneDay,  
		                TimeUnit.MILLISECONDS); 
		        //每天第二次同步课表
		        long secondDelay  = TimerUtil.getTimeMillis(config.getInitParameter(TimerUtil.SECOND)) - System.currentTimeMillis();  
		        secondDelay = secondDelay > 0 ? secondDelay : oneDay + secondDelay;  
		        
		        service.scheduleAtFixedRate(  
		        		new SynchronizeKebiaoSchedule(TimerUtil.SECOND),  
		        		secondDelay,  
		        		oneDay,  
		        		TimeUnit.MILLISECONDS);  
		    }  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
