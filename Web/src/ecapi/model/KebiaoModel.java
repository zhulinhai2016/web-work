package ecapi.model;

public class KebiaoModel {
	private String xqid;// 校区号
	private String jzwmc;// 教学楼
	private String SZLC;// 所在楼层
	private String jsid;// 教室ID
	private String jsh; //教室
	private String kkdlb;// 开课点类别,
	private String xnxq01id;// 学年学期ID,
	//节次 标记哪节课该教室有课
	private String kcsjmx;// 节次,
	private String KKZC;// 周期,
	private String zc;// 周次,
	private String MXRQ;// 日期,
	public String getXqid() {
		return xqid;
	}
	public void setXqid(String xqid) {
		this.xqid = xqid;
	}
	public String getJzwmc() {
		return jzwmc;
	}
	public void setJzwmc(String jzwmc) {
		this.jzwmc = jzwmc;
	}
	public String getSZLC() {
		return SZLC;
	}
	public void setSZLC(String sZLC) {
		SZLC = sZLC;
	}
	public String getJsid() {
		return jsid;
	}
	public void setJsid(String jsid) {
		this.jsid = jsid;
	}
	public String getJsh() {
		return jsh;
	}
	public void setJsh(String jsh) {
		this.jsh = jsh;
	}
	public String getKkdlb() {
		return kkdlb;
	}
	public void setKkdlb(String kkdlb) {
		this.kkdlb = kkdlb;
	}
	public String getXnxq01id() {
		return xnxq01id;
	}
	public void setXnxq01id(String xnxq01id) {
		this.xnxq01id = xnxq01id;
	}
	public String getKcsjmx() {
		return kcsjmx;
	}
	public void setKcsjmx(String kcsjmx) {
		this.kcsjmx = kcsjmx;
	}
	public String getKKZC() {
		return KKZC;
	}
	public void setKKZC(String kKZC) {
		KKZC = kKZC;
	}
	public String getZc() {
		return zc;
	}
	public void setZc(String zc) {
		this.zc = zc;
	}
	public String getMXRQ() {
		return MXRQ;
	}
	public void setMXRQ(String mXRQ) {
		MXRQ = mXRQ;
	}
}
