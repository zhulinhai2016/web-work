package ecapi.api;


import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;


/**
 * 集群服务端适配器
 * 
 * @author chenguofang
 *
 */
class ClusterServerHandler extends IoHandlerAdapter {

	/**
	 * 接受数据流
	 */
	public void messageReceived(IoSession session, Object msg) {	
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s = new String((byte[])msg);
		if(s.equals("0001010101")){
			System.out.println("服务端 开启空调电闸 ");
		}else if(s.equals("0001010100")){
			System.out.println("服务端 关闭空调电闸 ");
		}else if(s.equals("0001010201")){
			System.out.println("服务端 开启其他电器电闸 ");
		}else if(s.equals("0001010200")){
			System.out.println("服务端 关闭其他电器电闸 ");
		}else{
			// TODO Auto-generated method stub
			System.out.println("response:");
			for(byte a : (byte[])msg){
				System.out.print(Integer.toHexString(a & 0xff)+" ");
			}	
		}
//		System.out.println("s jiesjhou");
//		if(msg != null){	
//			System.out.println("response:");
//			for(byte a : (byte[])msg){
//				System.out.print(Integer.toHexString(a & 0xff)+" ");
//			}
//		}
	}
	
	/**
	 * Session关闭
	 */
	public void sessionClosed(IoSession session){
//		if(session.containsAttribute(User.UID_KEY)){
//			User.OffLine(session);	
//		}
//		PFLog.debug("Session created..."+session.getRemoteAddress());
	}
	/**
	 * Session创建
	 */
	public void sessionCreated(IoSession session) throws Exception {		
//		PFLog.debug("Session created..."+session.getRemoteAddress());
	}
}
