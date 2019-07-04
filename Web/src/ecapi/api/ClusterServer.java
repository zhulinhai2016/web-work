package ecapi.api;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.executor.OrderedThreadPoolExecutor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * 集群服务端
 * @author chenguofang
 *
 */
class ClusterServer {	
	/** 服务线程池 */
	private static ExecutorService filterExecutor = new OrderedThreadPoolExecutor();
//	/** 空闲时间 */
//	private static final int IDELTIMEOUT = 60;//5秒后超时
//	/** 每次发送心跳包的时间 */
//	private static final int HEARTBEATRATE = 30;//2秒发送一次心跳包
	
	public static void main(String[] args){
		new ClusterServer();
	}
	
	public ClusterServer(){
		startServer();
	}
	
	public void startServer() {
		NioSocketAcceptor accept = new NioSocketAcceptor();
		accept.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new CodeFactory()));
		accept.setReuseAddress(true);
		accept.getFilterChain().addLast("threadPool", new ExecutorFilter(filterExecutor));
		
		accept.setHandler(new ClusterServerHandler());
		try {
			accept.bind(new InetSocketAddress("127.0.0.1",20000));
			System.out.println("server start ok");
		} catch (IOException e) {
			e.printStackTrace();
			//System.out.println("server start fail");
			System.exit(0);
		}
	}
}
