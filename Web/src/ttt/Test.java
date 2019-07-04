package ttt;

import java.util.Calendar;
import java.util.Date;

import ecapi.api.Api;
import ecapi.api.ApiListen;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calendar c = Calendar.getInstance(); 
		Date date = new Date();	
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE);	
		System.out.println("hour:"+hour+" min:"+min );
	}

	public static class ApiThread extends Thread{
		private Api api;
		
		public ApiThread(String host, int port, MysListen callback){
			if(null == api) api = new Api(host, port, callback);			
		}		
		
		public void run(){
			if(null!=api && api.init()){
				//api.cmd1();//开启空调电闸
			    api.cmd2();//开启空调电闸
			}else{
				//连接失败
			}			
		}
		
	}
	
	public static class MysListen extends ApiListen{		
		
		@Override
		public void response(byte[] arg0) {
			// TODO Auto-generated method stub
			//System.out.println("cc");
			System.out.println("response:"+new String(arg0));			
			StringBuffer buf = new StringBuffer();
			for(byte b : arg0) buf.append(String.format("%02x", b));
			System.out.print(" 16jinzhi:"+buf.toString());
		}

		@Override
		public void connected() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void closed() {
			// TODO Auto-generated method stub
			
		}
		
	}
}
