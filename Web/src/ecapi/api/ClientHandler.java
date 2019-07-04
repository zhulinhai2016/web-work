package ecapi.api;

import java.net.SocketAddress;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import util.Functions;


/**
 * 集群服务客户端适配器
 * @author chenguofang
 *
 */
class ClientHandler extends IoHandlerAdapter {
	public ApiListen callback;
	
	public ClientHandler(ApiListen c){
		callback = c;
		/*try {
			this.sessionOpened(null);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
	
	public void sessionOpened(IoSession session) throws Exception {
		//PFLog.debug("connect");
		System.out.println("session opened");
		if(null!=callback)
		callback.connected();
		//session.write("5501100000000066".getBytes());
	}

	public void sessionClosed(IoSession session) throws Exception {
		//PFLog.debug("disconnect");
		System.out.println("session closed");
		System.err.println("------//////******"+session.getServiceAddress().toString());
		if(null!=callback)
		callback.closed();
	}
	
	public void messageReceived(IoSession session, Object message)
			throws Exception {		
		System.err.println("****** ip："+session.getRemoteAddress().toString());
		System.err.println("****** ip："+session.getServiceAddress().toString());
		if(message != null){
			//TODO 在这里打印出来了
			
			if(null!=callback){
				
				// 这个message是安卓返回来的，如果他返回来就能打印出来
				callback.response((byte[])message);
				System.err.println("******："+Functions.byteToHexString((byte[])message));
			}
		}
		
	}	
		
}