package ecapi.api;


import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
/**
 * 过滤器编码
 * @author chenguofang
 *
 */
class CodecEncoder implements ProtocolEncoder {

	public void encode(IoSession session, Object message, ProtocolEncoderOutput out)
			throws Exception {	
		byte[] tmp = (byte[])message;
		IoBuffer buf = IoBuffer.allocate(tmp.length);//IoBuffer.wrap(tmp);
		buf.put(tmp);
		buf.flip();
		out.write(buf);
		out.flush();
	}

	@Override
	public void dispose(IoSession arg0) throws Exception {
		
	}

}
