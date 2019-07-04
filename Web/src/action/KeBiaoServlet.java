package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;

import ecapi.model.KebiaoModel;
import schedule.ControlTime;
import util.DBUtil;
import util.DBUtil1;

/**
 * Servlet implementation class Classes
 */
@WebServlet("/KeBiao")
public class KeBiaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KeBiaoServlet() {
        super();       
    }
    
    public void getKebiaoDetail(String classRoomId) throws JSONException{
    	JSONArray clsOut = new JSONArray();
    	//生产环境的代码 
    	List<KebiaoModel> keBiaoDetailList = DBUtil.getKeBiaoByDateAndClassId(
    	new	 SimpleDateFormat("yyyy-MM-dd").format(new Date()), classRoomId);
    	//该代码是测试环境用的 
    	//List<KebiaoModel> keBiaoDetailList = DBUtil1.getKB(null);
    	if(null!=keBiaoDetailList){
    		for(KebiaoModel c : keBiaoDetailList){
    			clsOut.put(ControlTime.keBiaoMap.get(c.getKcsjmx()));
    		}
    	}
    	try {
			PrintWriter out = response.getWriter();
			out.print(clsOut.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private String getPm(String key){
    	String pm = request.getParameter(key);
    	if(null!=pm && !pm.trim().equals("")) return pm.trim();
    	return "";
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-Type", "text/json;charset=utf-8");
		try {
			this.getKebiaoDetail(getPm("classRoomId"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
