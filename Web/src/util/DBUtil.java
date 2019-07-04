package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import ecapi.api.ClassesFactory;
import ecapi.model.ClassesInfoModel;
import ecapi.model.KebiaoModel;
/**
 * 
 * @author wjd
 *
 */
public class DBUtil {
	private static final String DBURL ="jdbc:oracle:thin:@//10.0.12.43:1521/jwxt";
	private static final String USER = "bjlgzhxy_jk_keshi";// 用户名,系统默认的账户名
	private static final String PASSWORD = "bjlgzhxy20160808";// 你安装时选设置的密码
	private static final String DBDRIVER = "oracle.jdbc.driver.OracleDriver";
/*	public static void main(String[] args) {
		Map m = new HashMap();
		List<KebiaoModel> list = getKB("2016-10-17",m);
		System.out.println(list.size());
	}*/
	/**
	 * 一个非常标准的连接Oracle数据库的示例代码
	 */
	public static List<KebiaoModel> getKB(String Date,Map<String,String> controlTimeMap) {
		Connection con = null;// 创建一个数据库连接
		PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
		ResultSet result = null;// 创建一个结果集对象
		List<KebiaoModel> kebiaoList = new ArrayList<KebiaoModel>();
		try {
			Class.forName(DBDRIVER);// 加载Oracle驱动程序
			MyLog.debug("开始尝试连接数据库！");
			
			con = DriverManager.getConnection(DBURL, USER, PASSWORD);// 获取连接
			MyLog.debug("连接成功！");
			String sql = "select * from s_jxzl_ks where mxrq=? order by jsh, kcsjmx";// 预编译语句，“？”代表参数
			pre = con.prepareStatement(sql);// 实例化预编译语句
			MyLog.debug("Data==------！"+Date);

			pre.setString(1, Date);// 设置参数，前面的1表示参数的索引，而不是表中列名的索引
			result = pre.executeQuery();// 执行查询，注意括号中不需要再加参数
			MyLog.debug("result size=----"+result.getRow());
			while (result.next()){
				MyLog.debug("zhanyong shijian==------！"+result.getString("kcsjmx"));
				//修改课表占用时间 ',101,102,'成为'0102'
				String kcsjmx = Pattern.compile(",[\\d]").matcher(result.getString("kcsjmx")).replaceAll("").replaceAll(",", "");
				if(!controlTimeMap.containsKey(kcsjmx)){
					continue;
				}
				MyLog.debug("jsid==------！"+result.getString("jsid"));
			/*	if("NMD00401".equals(result.getString("jsid"))
						||"NMD00402".equals(result.getString("jsid"))
						||"NMD00403".equals(result.getString("jsid"))
						||"NMD00404".equals(result.getString("jsid"))
						||"NMD00405".equals(result.getString("jsid"))
						||"NMD00406".equals(result.getString("jsid"))){
				KebiaoModel kebiaoModel = new KebiaoModel();
*/
				if("NMD00401".equals(result.getString("jsid"))//     从这里62注释到 100
						||"NMA00101".equals(result.getString("jsid"))
						||"NMA00102".equals(result.getString("jsid"))
						||"NMA00103".equals(result.getString("jsid"))
						||"NMA00104".equals(result.getString("jsid"))
						||"NMA00105".equals(result.getString("jsid"))
						||"NMA00106".equals(result.getString("jsid"))
						||"NMA00107".equals(result.getString("jsid"))
						||"NMA00108".equals(result.getString("jsid"))
						||"NJA00201".equals(result.getString("jsid"))
						||"NJA00202".equals(result.getString("jsid"))
						||"NJA00203".equals(result.getString("jsid"))
						||"NJA00204".equals(result.getString("jsid"))
						||"NJA00205".equals(result.getString("jsid"))
						||"NJA00206".equals(result.getString("jsid"))
						||"NMA00207".equals(result.getString("jsid"))
						||"NMA00301".equals(result.getString("jsid"))
						||"NMA00302".equals(result.getString("jsid"))
						||"NMA00305".equals(result.getString("jsid"))
						||"NMA00306".equals(result.getString("jsid"))
						||"NMA00307".equals(result.getString("jsid"))
						||"NMA00308".equals(result.getString("jsid"))
						||"NMA00401".equals(result.getString("jsid"))
						||"NMA00402".equals(result.getString("jsid"))
						||"NMA00403".equals(result.getString("jsid"))	
						||"NMA00404".equals(result.getString("jsid"))
						||"NMB00101".equals(result.getString("jsid"))
						||"NMB00102".equals(result.getString("jsid"))
						||"NMB00103".equals(result.getString("jsid"))
						||"NMB00104".equals(result.getString("jsid"))
						||"NMB00105".equals(result.getString("jsid"))
						||"NMB00106".equals(result.getString("jsid"))
						||"NMB00201".equals(result.getString("jsid"))
						||"NMB00202".equals(result.getString("jsid"))
						||"NMB00203".equals(result.getString("jsid"))
						||"NMB00204".equals(result.getString("jsid"))
						||"NMB00205".equals(result.getString("jsid"))
						||"NMB00206".equals(result.getString("jsid"))
						||"NMB00301".equals(result.getString("jsid"))
						||"NMB00302".equals(result.getString("jsid"))
						||"NMB00303".equals(result.getString("jsid"))
						||"NMB00304".equals(result.getString("jsid"))
						||"NMB00305".equals(result.getString("jsid"))
						||"NMB00306".equals(result.getString("jsid"))
						||"NMC00101".equals(result.getString("jsid"))
						||"NMC00102".equals(result.getString("jsid"))
						||"NMC00103".equals(result.getString("jsid"))
						||"NMC00104".equals(result.getString("jsid"))
						||"NMC00105".equals(result.getString("jsid"))
						||"NMC00106".equals(result.getString("jsid"))
						||"NMC00107".equals(result.getString("jsid"))
						||"NMC00108".equals(result.getString("jsid"))
						||"NMC00109".equals(result.getString("jsid"))
						||"NMC00201".equals(result.getString("jsid"))
						||"NMC00202".equals(result.getString("jsid"))
						||"NMC00203".equals(result.getString("jsid"))
						||"NMC00204".equals(result.getString("jsid"))
						||"NMC00205".equals(result.getString("jsid"))
						||"NMC00206".equals(result.getString("jsid"))	
						||"NMC00207".equals(result.getString("jsid"))
						||"NMC00208".equals(result.getString("jsid"))
						||"NMC00301".equals(result.getString("jsid"))
						||"NMC00302".equals(result.getString("jsid"))
						||"NMC00303".equals(result.getString("jsid"))
						||"NMC00304".equals(result.getString("jsid"))
						||"NMC00305".equals(result.getString("jsid"))
						||"NMC00306".equals(result.getString("jsid"))
						||"NMC00307".equals(result.getString("jsid"))
						||"NMC00308".equals(result.getString("jsid"))
						||"NMC00401".equals(result.getString("jsid"))
						||"NMC00402".equals(result.getString("jsid"))
						||"NMC00403".equals(result.getString("jsid"))
						||"NMC00404".equals(result.getString("jsid"))
						||"NMC00501".equals(result.getString("jsid"))
						||"NMC00502".equals(result.getString("jsid"))
						||"NMC00503".equals(result.getString("jsid"))
						||"NMC00504".equals(result.getString("jsid"))
						||"NMD00402".equals(result.getString("jsid"))
						||"NMD00403".equals(result.getString("jsid"))
						||"NMD00404".equals(result.getString("jsid"))
						||"NMD00405".equals(result.getString("jsid"))
						||"NMD00406".equals(result.getString("jsid"))
						||"NMD00302".equals(result.getString("jsid"))
						||"NMD00303".equals(result.getString("jsid"))
						||"NMD00304".equals(result.getString("jsid"))
						||"NMD00305".equals(result.getString("jsid"))
						||"NMD00306".equals(result.getString("jsid"))
						||"NMD00507".equals(result.getString("jsid"))
						||"NMD00508".equals(result.getString("jsid"))
						||"NMD00101".equals(result.getString("jsid"))
						||"NMD00102".equals(result.getString("jsid"))
						||"NMD00103".equals(result.getString("jsid"))
						||"NMD00104".equals(result.getString("jsid"))
						||"NMD00105".equals(result.getString("jsid"))
						||"NMD00106".equals(result.getString("jsid"))
						||"NMD00201".equals(result.getString("jsid"))
						||"NMD00202".equals(result.getString("jsid"))
						||"NMD00203".equals(result.getString("jsid"))
						||"NMD00204".equals(result.getString("jsid"))
						||"NMD00205".equals(result.getString("jsid"))
						||"NMD00206".equals(result.getString("jsid"))	
						
						){
				KebiaoModel kebiaoModel = new KebiaoModel();
				
				kebiaoModel.setJsh(result.getString("jsh"));
				kebiaoModel.setJsid(result.getString("jsid"));
				kebiaoModel.setJzwmc(result.getString("jzwmc"));
				kebiaoModel.setKcsjmx(result.getString("kcsjmx"));
				kebiaoModel.setKkdlb(result.getString("kkdlb"));
				kebiaoModel.setKKZC(result.getString("kKZC"));
				kebiaoModel.setMXRQ(result.getString("mXRQ"));
				kebiaoModel.setSZLC(result.getString("sZLC"));
				kebiaoModel.setXnxq01id(result.getString("xnxq01id"));
				kebiaoModel.setZc(result.getString("zc"));
				kebiaoList.add(kebiaoModel);
				// 当结果集不为空时
				MyLog.debug("教室号:" + result.getString("jsh")+"===="+kebiaoModel.getKcsjmx());
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
				// 注意关闭的顺序，最后使用的最先关闭
				if (result != null)
					result.close();
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
				MyLog.debug("数据库连接已关闭！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return kebiaoList;
	}
	/**
	 * 根据教室id和日期 查询该教室课表
	 */
	public static List<KebiaoModel> getKeBiaoByDateAndClassId(String Date,String classRoomId) {
		Connection con = null;// 创建一个数据库连接
		PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
		ResultSet result = null;// 创建一个结果集对象
		List<KebiaoModel> kebiaoList = new ArrayList<KebiaoModel>();
		//ClassesInfoModel classesInfo = ClassesFactory.getClassesInfo(classRoomId);
		try {
			Class.forName(DBDRIVER);// 加载Oracle驱动程序
			MyLog.debug("开始尝试连接数据库！");
			
			con = DriverManager.getConnection(DBURL, USER, PASSWORD);// 获取连接
			MyLog.debug("连接成功！");
			String sql = "select * from s_jxzl_ks where mxrq=? and jsid =? order by jsh, kcsjmx";// 预编译语句，“？”代表参数
			pre = con.prepareStatement(sql);// 实例化预编译语句
			pre.setString(1, Date);// 设置参数，前面的1表示参数的索引，而不是表中列名的索引
			pre.setString(2, classRoomId);// 设置参数，前面的2表示参数的索引，而不是表中列名的索引
			result = pre.executeQuery();// 执行查询，注意括号中不需要再加参数
			while (result.next()){
				//修改课表占用时间 ',101,102,'成为'0102'
				String kcsjmx = Pattern.compile(",[\\d]").matcher(result.getString("kcsjmx")).replaceAll("").replaceAll(",", "");
				KebiaoModel kebiaoModel = new KebiaoModel();

				kebiaoModel.setJsh(result.getString("jsh"));
				kebiaoModel.setJsid(result.getString("jsid"));
				kebiaoModel.setJzwmc(result.getString("jzwmc"));
				kebiaoModel.setKcsjmx(kcsjmx);
				kebiaoModel.setKkdlb(result.getString("kkdlb"));
				kebiaoModel.setKKZC(result.getString("kKZC"));
				kebiaoModel.setMXRQ(result.getString("mXRQ"));
				kebiaoModel.setSZLC(result.getString("sZLC"));
				kebiaoModel.setXnxq01id(result.getString("xnxq01id"));
				kebiaoModel.setZc(result.getString("zc"));
				kebiaoList.add(kebiaoModel);
				// 当结果集不为空时
				MyLog.debug("教室号:" + result.getString("jsh")+"===="+kebiaoModel.getKcsjmx());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
				// 注意关闭的顺序，最后使用的最先关闭
				if (result != null)
					result.close();
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
				MyLog.debug("数据库连接已关闭！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return kebiaoList;
	}
	
}
