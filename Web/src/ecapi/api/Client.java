package ecapi.api;


import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.filterchain.IoFilterChain;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.future.ReadFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.proxy.utils.IoBufferDecoder;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import util.Functions;


class Client {
	private IoSession session;
	public boolean complete = false;

	private static final int IDELTIMEOUT = 0;//30;//30秒后超时
	public NioSocketConnector connector;
	private String host = "127.0.0.1";
	private int port = 10086;
	private ApiListen callback;
	public static void main(String[] args){
//		MyListen l = new MyListen();
//		Api a=new Api("127.0.0.1", 10086, l);
//		a.cmd("1000101");
	}
	
	public boolean isConnected(){
		if(null!=session ) return session.isConnected();
		return false;
	}
	
	public boolean write(byte[] msg){
		if(null!=session && session.isConnected()){
			WriteFuture r = session.write(msg);
			r.awaitUninterruptibly();
			return r.isWritten();
		}
		return false;
	}
	/**
	 * zhi jie fa song xiao xi zifuchuan
	 * @param msg 
	 * @return
	 */
	public boolean writeStr(String msg){
		if(null!=session && session.isConnected()){
			WriteFuture r = session.write(msg);
			r.awaitUninterruptibly();
			return r.isWritten();
		}
		return false;
	}
	
	public Client(String host, int port ,ApiListen callback){
		this.host = host;
		this.port = port;
		this.callback = callback;
	}
	
	public boolean connect(){	
		if(null!=connector) Close();

		connector = new NioSocketConnector();
		connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new CodeFactory()));		
		connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,	IDELTIMEOUT);
		connector.getSessionConfig().setKeepAlive(true);
		/** *********** */
		connector.setHandler(new ClientHandler(callback));
		connector.setConnectTimeoutMillis(5000);
		ConnectFuture cf = connector.connect(new InetSocketAddress(host, port));
		complete = false;			
		cf.addListener(new IoFutureListener<ConnectFuture>() {   
            public void operationComplete(ConnectFuture future) { 
            	complete = true; 
                if(future.isConnected()){
                	session = future.getSession();      
                }
             }   
         });	
		int retryTimes =0;
		//阻塞，
		
		while(!complete){
			if(retryTimes==3){
				break;
			}
			retryTimes++;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
		}
		if(null==session || !session.isConnected()) {
			if(cf.isConnected()){
				session = cf.getSession();
			}
			if(null==session || !session.isConnected()){
				callback.closed();
				return false;
			} else {
				callback.connected();
				return true;
			}
		}else{
			callback.connected();
			return true;
		}
	}
		
	public void Close(){
		if(session != null){
			session.close(false);		
		}
		if(connector != null){
			connector.dispose();
		}
	}
}
