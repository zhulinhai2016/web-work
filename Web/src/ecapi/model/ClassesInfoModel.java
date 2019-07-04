package ecapi.model;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;

import org.json.JSONException;
import org.json.JSONObject;

import util.Functions;

public class ClassesInfoModel {
	/** 服务器IP */
	private String serverHost = "";
	/** 服务器端口 */
	private int serverPort = 485;
	/** 所在楼层 */
	private String floorId = "";
	/** 班级名称  */
	private String name = "";
	private String touyUrl = "";
	private String id = "";
	private String classId = "";
	private String buildId = "";
	private String versionNum="";
	/** 中控端口 */
	private int centerPort = 20000;
	/** 中控IP */
	private String centerHost = "";
	//投影类型 zan shi ding wei kaxi
		private String touyType = "A";
		//爱普生CB-X30开机码
		private String touyAOpenCode = "77 03 50 57 52 20 4F 4E 0D 23 4B";
		//爱普生CB-X30关机码
		private String touyACloseCode= "77 04 50 57 52 20 4F 46 46 0D 5A 1E";
		//投影卡明基开机码
		//private String touyAOpenCode = "77 03 0D 2A 70 6F 77 3D 6F 6E 23 0D F2 35 ";
		//投影卡明基关机码
		//private String touyACloseCode= "77 04 0D 2A 70 6F 77 3D 6F 66 66 23 0D 8E 9E";
		//投影NEC开机码
		private String touyBOpenCode = "77 03 02 00 00 00 00 02 B5 EA";
		//投影NEC关机码
		private String touyBCloseCode = "77 04 02 01 00 00 00 03 3F 2A";
		//投影松下PT-UX363C开机码
		private String touyCOpenCode = "77 03 02 50 4F 4E 03 30 82";
		//投影松下PT-UX363C关机码
		private String touyCCloseCode = "77 04 02 50 4F 46 03 36 F5";
		//投影松下PT-UX35关机码
		private String touyFOpenCode = "77 03 02 50 4F 4E 03 30 82";
		//投影松下PT-UX35关机码
		private String touyFCloseCode = "77 04 02 50 4F 46 03 36 F5";
		
		
		//投影三洋开机码
		private String touyDOpenCode = "77 03 43 30 30 0D 8E D2";
		//投影三洋关机码
		private String touyDCloseCode = "77 04 43 30 31 0D 3A 82";
		//投影卡西欧开机码
		private String touyEOpenCode = "77 03 28 50 57 52 31 29 96 06";
		//投影卡西欧关机码
		private String touyECloseCode = "77 04 28 50 57 52 30 29 E1 56";

		
		private String kongtType = "B";	
	//格力空调开关机码
	private String aCloseCode="66 01 02 2D 26 CC C6 80 80 01 00 8E A5 CC C6 01 00 8F 8C 8E A5 22 1C 00 40 40 12 8F DC 8F 8C 0E 7E 7F 8F A4 8F DC 01 00 90 8B 8F A4 12 00 00 05 0E 1E 06";
								                   
	//格力空调23度开机码					
	private String aOpenCode23 = "66 02 02 2D 26 CC C6 80 80 01 00 8D A6 CC C6 01 00 8F 8C 8D A6 22 14 07 40 40 12 8F DC 8F 8C 0E 7E 7F 8F A4 8F DC 01 00 90 8C 8F A4 12 00 00 0C 0E 28 F7";
								                  
	//格力空调26度开机码
	private String aOpenCode26 =  "66 03 02 49 26 CC C6 80 80 01 00 8F A4 CC C7 01 00 8E 8E 8F A4 03 04 90 8B 8E 8E 05 00 8F A4 90 8B 12 7A 5F 0F 8D 8E 8F A4 05 0A 8F 8B 8D 8E 01 00 8E A6 8F 8B 02 02 8D DC 8E A6 01 00 8E 8C 8D DC 0D 00 00 8D A5 8E 8C 13 7E 7F 01 0B 35 02";
	//格力空调16度码
	private String aOpenCode16 =  "66 04 02 2D 26 CC C6 80 80 01 00 8E A5 CC C7 01 00 8D 8E 8E A5 22 00 0A 40 40 12 8C DC 8D 8E 0E 7E 7F 8D A6 8C DC 01 00 8D 8E 8D A6 12 00 00 07 0B 49 55";

	
	//美的空调开关机码
	private String bCloseCode  ="66 01 02 38 26 C6 C6 80 80 01 00 8D A3 C6 C6 01 00 8D 8C 8D A3 2F 26 32 79 0F 00 70 1F 8D C7 8D 8C 01 00 C6 C6 8D C7 01 00 8D A3 C6 C6 01 00 8D 8C 8D A3 2F 26 32 79 0F 00 70 1F 0C 9A 4D";
								                  
	//美的空调23度开机码					
	private String bOpenCode23 =  "66 02 02 38 26 C6 C6 80 80 01 00 8D A3 C6 C7 01 00 8E 8B 8D A3 2F 26 32 79 0F 50 50 1E 8D C7 8E 8B 01 00 C6 C6 8D C7 01 00 8D A3 C6 C7 01 00 8D 8B 8D A3 2F 26 32 79 0F 50 50 1E 0D 7A 5E";
								                  
	//美的空调26度开机码
	private String bOpenCode26 =  "66 03 02 38 26 C6 C6 80 80 01 00 8E A2 C6 C7 01 00 8E 8C 8E A2 2F 26 32 79 0F 58 40 1E 8E C7 8E 8C 01 00 C6 C6 8E C8 01 00 8E A3 C6 C7 01 00 8D 8B 8E A3 2F 26 32 79 0F 58 40 1E 0D B9 27";
	//美的空调16度码
	private String bOpenCode16 =  "66 04 02 38 26 C6 C6 80 80 01 00 8D A3 C6 C6 01 00 8D 8C 8D A3 2F 26 32 3D 07 39 00 1F 8E C7 8D 8C 01 00 C6 C6 8E C8 01 00 8E A2 C6 C7 01 00 8E 8B 8E A2 2F 26 32 3D 07 39 00 1F 0D A2 2D";

		//格力空调开关机码
		private String cCloseCode="66 05 02 2D 26 CC C6 80 80 01 00 8E A5 CC C6 01 00 8F 8C 8E A5 22 1C 00 40 40 12 8F DC 8F 8C 0E 7E 7F 8F A4 8F DC 01 00 90 8B 8F A4 12 00 00 05 0E AF 73";
									                   
		//格力空调23度开机码					
		private String cOpenCode23 = "66 06 02 2D 26 CC C6 80 80 01 00 8D A6 CC C6 01 00 8F 8C 8D A6 22 14 07 40 40 12 8F DC 8F 8C 0E 7E 7F 8F A4 8F DC 01 00 90 8C 8F A4 12 00 00 0C 0E 99 82";
									                  
		//格力空调26度开机码
		private String cOpenCode26 =  "66 07 02 49 26 CC C6 80 80 01 00 8F A4 CC C7 01 00 8E 8E 8F A4 03 04 90 8B 8E 8E 05 00 8F A4 90 8B 12 7A 5F 0F 8D 8E 8F A4 05 0A 8F 8B 8D 8E 01 00 8E A6 8F 8B 02 02 8D DC 8E A6 01 00 8E 8C 8D DC 0D 00 00 8D A5 8E 8C 13 7E 7F 01 0B C7 85";
		//格力空调16度码
		private String cOpenCode16 =  "66 08 02 2D 26 CC C6 80 80 01 00 8E A5 CC C7 01 00 8D 8E 8E A5 22 00 0A 40 40 12 8C DC 8D 8E 0E 7E 7F 8D A6 8C DC 01 00 8D 8E 8D A6 12 00 00 07 0B 9A CB";

		
		//美的空调开关机码
		private String dCloseCode  =  "66 05 02 38 26 C6 C6 80 80 01 00 8D A3 C6 C6 01 00 8D 8C 8D A3 2F 26 32 79 0F 00 70 1F 8D C7 8D 8C 01 00 C6 C6 8D C7 01 00 8D A3 C6 C6 01 00 8D 8C 8D A3 2F 26 32 79 0F 00 70 1F 0C DA 48";
		
		//美的空调23度开机码					
		private String dOpenCode23 =  "66 06 02 38 26 C6 C6 80 80 01 00 8D A3 C6 C7 01 00 8E 8B 8D A3 2F 26 32 79 0F 50 50 1E 8D C7 8E 8B 01 00 C6 C6 8D C7 01 00 8D A3 C6 C7 01 00 8D 8B 8D A3 2F 26 32 79 0F 50 50 1E 0D 3A 5B";
									                  
		//美的空调26度开机码
		private String dOpenCode26 =  "66 07 02 38 26 C6 C6 80 80 01 00 8E A2 C6 C7 01 00 8E 8C 8E A2 2F 26 32 79 0F 58 40 1E 8E C7 8E 8C 01 00 C6 C6 8E C8 01 00 8E A3 C6 C7 01 00 8D 8B 8E A3 2F 26 32 79 0F 58 40 1E 0D F9 22";
		//美的空调16度码
		private String dOpenCode16 =  "66 08 02 38 26 C6 C6 80 80 01 00 8D A3 C6 C6 01 00 8D 8C 8D A3 2F 26 32 3D 07 39 00 1F 8E C7 8D 8C 01 00 C6 C6 8E C8 01 00 8E A2 C6 C7 01 00 8E 8B 8E A2 2F 26 32 3D 07 39 00 1F 0D 62 22";

		
		public String getTouyFOpenCode() {
			return touyFOpenCode;
		}

		public void setTouyFOpenCode(String touyFOpenCode) {
			this.touyFOpenCode = touyFOpenCode;
		}

		public String getTouyFCloseCode() {
			return touyFCloseCode;
		}

		public void setTouyFCloseCode(String touyFCloseCode) {
			this.touyFCloseCode = touyFCloseCode;
		}
		public String getcCloseCode() {
			return cCloseCode;
		}

		public void setcCloseCode(String cCloseCode) {
			this.cCloseCode = cCloseCode;
		}

		public String getcOpenCode23() {
			return cOpenCode23;
		}

		public void setcOpenCode23(String cOpenCode23) {
			this.cOpenCode23 = cOpenCode23;
		}

		public String getcOpenCode26() {
			return cOpenCode26;
		}

		public void setcOpenCode26(String cOpenCode26) {
			this.cOpenCode26 = cOpenCode26;
		}

		public String getcOpenCode16() {
			return cOpenCode16;
		}

		public void setcOpenCode16(String cOpenCode16) {
			this.cOpenCode16 = cOpenCode16;
		}

		public String getdCloseCode() {
			return dCloseCode;
		}

		public void setdCloseCode(String dCloseCode) {
			this.dCloseCode = dCloseCode;
		}

		public String getdOpenCode23() {
			return dOpenCode23;
		}

		public void setdOpenCode23(String dOpenCode23) {
			this.dOpenCode23 = dOpenCode23;
		}

		public String getdOpenCode26() {
			return dOpenCode26;
		}

		public void setdOpenCode26(String dOpenCode26) {
			this.dOpenCode26 = dOpenCode26;
		}

		public String getdOpenCode16() {
			return dOpenCode16;
		}

		public void setdOpenCode16(String dOpenCode16) {
			this.dOpenCode16 = dOpenCode16;
		}
		private String NECAirCode = "77 05 96 00 64 31";//NEO
		private String MJAirCode = "77 05 25 80 10 A1";//爱普生
		//private String MJAirCode = "77 05 01 C2 00 D0 67";//明基
		private String SXAirCode = "77 05 4B 00 3D 61";//松下
		private String SX1AirCode = "77 05 25 80 10 A1";//松下(UX-35)
		private String SYAirCode = "77 05 4B 00 3D 61";//三洋
		private String KXOAirCode = "77 05 4B 00 3D 61";//卡西欧
		public String getSX1AirCode() {
			return SX1AirCode;
		}

		public void setSX1AirCode(String sX1AirCode) {
			SX1AirCode = sX1AirCode;
		}
		
        public String getNECAirCode() {
			return NECAirCode;
		}

		public String getSXAirCode() {
			return SXAirCode;
		}
													
		public void setSXAirCode(String sXAirCode) {
			SXAirCode = sXAirCode;
		}
		
		public String getSYAirCode() {
			return SYAirCode;
		}
		
		public void setSYAirCode(String sYAirCode) {
			SYAirCode = sYAirCode;
		}
		
		public String getKXOAirCode() {
			return KXOAirCode;
		}
		
		public void setKXOAirCode(String kXOAirCode) {
			KXOAirCode = kXOAirCode;
		}

		public void setNECAirCode(String nECAirCode) {
			NECAirCode = nECAirCode;
		}

		public String getMJAirCode() {
			return MJAirCode;
		}

		public void setMJAirCode(String mJAirCode) {
			MJAirCode = mJAirCode;
		}

	public String getaOpenCode16() {
		return aOpenCode16;
	}

	public void setaOpenCode16(String aOpenCode16) {
		this.aOpenCode16 = aOpenCode16;
	}

	public String getaOpenCode23() {
		return aOpenCode23;
	}

	public void setaOpenCode23(String aOpenCode23) {
		this.aOpenCode23 = aOpenCode23;
	}

	public String getaOpenCode26() {
		return aOpenCode26;
	}

	public void setaOpenCode26(String aOpenCode26) {
		this.aOpenCode26 = aOpenCode26;
	}

	public String getaCloseCode() {
		return aCloseCode;
	}

	public void setaCloseCode(String aCloseCode) {
		this.aCloseCode = aCloseCode;
	}

	public String getbOpenCode16() {
		return bOpenCode16;
	}

	public void setbOpenCode16(String bOpenCode16) {
		this.bOpenCode16 = bOpenCode16;
	}

	public String getbOpenCode23() {
		return bOpenCode23;
	}

	public void setbOpenCode23(String bOpenCode23) {
		this.bOpenCode23 = bOpenCode23;
	}

	public String getbOpenCode26() {
		return bOpenCode26;
	}

	public void setbOpenCode26(String bOpenCode26) {
		this.bOpenCode26 = bOpenCode26;
	}

	public String getbCloseCode() {
		return bCloseCode;
	}

	public void setbCloseCode(String bCloseCode) {
		this.bCloseCode = bCloseCode;
	}

	
	
    public String getKongtType() {
		return kongtType;
	}

	public void setKongtType(String kongtType) {
		this.kongtType = kongtType;
	}

	
//	public ClassesInfoModel(String buildId, String floorId,String name,String id,String touyUrl,String serverHost,int serverPort){
//		setServerHost(serverHost);
//		setServerPort(serverPort);
//		setTouyUrl(touyUrl);
//		setBuildId(buildId);
//		setFloorId(floorId);
//		setName(name);
//		setId(id);
//	} --
	public ClassesInfoModel(){};
	public ClassesInfoModel(String buildId, String floorId,String name,String id,String touyUrl,String serverHost,int serverPort,String centerHost,int centerPort){
		setServerHost(serverHost);
		setServerPort(serverPort);
		setCenterHost(centerHost);
		setTouyUrl(touyUrl);
		setBuildId(buildId);
		setFloorId(floorId);
		setName(name);
		setId(id);
		setCenterPort(centerPort);

	}
	
	public LinkedHashMap<String, Object> getMap(){
		LinkedHashMap<String, Object> out = new LinkedHashMap<String, Object>();
		Field[] fields = this.getClass().getDeclaredFields();
		try {
			for(Field f : fields){
				out.put(f.getName(), f.get(this));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return out;
	}
	
	public JSONObject getJSONObject(){
		JSONObject out = new JSONObject(getMap());
		return out;
	}
	
	
	
	public int getCenterPort() {
		return centerPort;
	}


	public void setCenterPort(int centerPort) {
		this.centerPort = centerPort;
	}


	public String getCenterHost() {
		return centerHost;
	}


	public void setCenterHost(String centerHost) {
		this.centerHost = centerHost;
	}


	public String getJSONString(){		
		return getJSONObject().toString();
	}
		
//	public static ClassesInfoModel getClassesInfoModel(JSONObject o) throws JSONException{
//		Field[] fields = BuildModel.class.getDeclaredFields();
//		for(Field f : fields){
//			String k = f.getName();
//			if(!o.has(k) || null==o.get(k) || o.get(k).equals("")) return null;
//		}
//		return new ClassesInfoModel(o.getString("buildId"),o.getString("floorId"),o.getString("name"),o.getString("id"),o.getString("touyUrl"),o.getString("serverHost"),o.getInt("serverPort"));
//	}
	
	public static ClassesInfoModel getClassesInfoModel(JSONObject o) throws JSONException{
		Field[] fields = BuildModel.class.getDeclaredFields();
		for(Field f : fields){
			String k = f.getName();
			if(!o.has(k) || null==o.get(k) || o.get(k).equals("")) return null;
		}
		String buildId = (o.has("buildId")&&null!=o.get("buildId"))?o.getString("buildId"):"";
		String floorId = (o.has("buildId")&&null!=o.get("floorId"))?o.getString("floorId"):"";
		String name = (o.has("name")&&null!=o.get("name"))?o.getString("name"):"";
		String id = (o.has("id")&&null!=o.get("id"))?o.getString("id"):"";
		String touyUrl = (o.has("touyUrl")&&null!=o.get("touyUrl"))?o.getString("touyUrl"):"";
		String serverHost = (o.has("serverHost")&&null!=o.get("serverHost"))?o.getString("serverHost"):"";
		String centerHost = (o.has("centerHost")&&null!=o.get("centerHost"))?o.getString("centerHost"):"";
		int serverPort = (o.has("serverPort")&&null!=o.get("serverPort"))?o.getInt("serverPort"):485;
		int centerPort = (o.has("centerPort")&&null!=o.get("centerPort"))?o.getInt("centerPort"):20000;
		
		return new ClassesInfoModel(buildId,floorId,name,id,touyUrl,serverHost,serverPort,centerHost,centerPort);
	}
	
	public String getServerHost() {
		return serverHost;
	}
	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}
	public int getServerPort() {
		return serverPort;
	}
	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}
	public String getTouyCOpenCode() {
		return touyCOpenCode;
	}

	public void setTouyCOpenCode(String touyCOpenCode) {
		this.touyCOpenCode = touyCOpenCode;
	}

	public String getTouyCCloseCode() {
		return touyCCloseCode;
	}

	public void setTouyCCloseCode(String touyCCloseCode) {
		this.touyCCloseCode = touyCCloseCode;
	}

	public String getTouyDOpenCode() {
		return touyDOpenCode;
	}

	public void setTouyDOpenCode(String touyDOpenCode) {
		this.touyDOpenCode = touyDOpenCode;
	}

	public String getTouyDCloseCode() {
		return touyDCloseCode;
	}

	public void setTouyDCloseCode(String touyDCloseCode) {
		this.touyDCloseCode = touyDCloseCode;
	}

	public String getTouyEOpenCode() {
		return touyEOpenCode;
	}

	public void setTouyEOpenCode(String touyEOpenCode) {
		this.touyEOpenCode = touyEOpenCode;
	}

	public String getTouyECloseCode() {
		return touyECloseCode;
	}

	public void setTouyECloseCode(String touyECloseCode) {
		this.touyECloseCode = touyECloseCode;
	}

	public String getFloorId() {
		return floorId;
	}
	public void setFloorId(String floorId) {
		this.floorId = floorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTouyUrl() {
		return touyUrl;
	}
	public void setTouyUrl(String touyUrl) {
		this.touyUrl = touyUrl;
	}
	public String getId() {
		if(null==id || id.equals("")){
			setId(Functions.MD5(getServerHost()+"_"+getServerPort()));
		}
		return id;
	}
	public void setId(String id) {
		if(null==id || id.equals("")){
			id = Functions.MD5(getServerHost()+"_"+getServerPort());
		}
		this.id = id;
	}
	public String getBuildId() { 
		return buildId;
	}
	public void setBuildId(String buildId) {
		this.buildId = buildId;
	}
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getTouyAOpenCode() {
		return touyAOpenCode;
	}

	public void setTouyAOpenCode(String touyAOpenCode) {
		this.touyAOpenCode = touyAOpenCode;
	}

	public String getTouyACloseCode() {
		return touyACloseCode;
	}

	public void setTouyACloseCode(String touyACloseCode) {
		this.touyACloseCode = touyACloseCode;
	}

	public String getTouyBOpenCode() {
		return touyBOpenCode;
	}

	public void setTouyBOpenCode(String touyBOpenCode) {
		this.touyBOpenCode = touyBOpenCode;
	}

	public String getTouyBCloseCode() {
		return touyBCloseCode;
	}

	public void setTouyBCloseCode(String touyBCloseCode) {
		this.touyBCloseCode = touyBCloseCode;
	}

	public String getTouyType() {
		return touyType;
	}

	public void setTouyType(String touyType) {
		this.touyType = touyType;
	}

	public String getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}


}
