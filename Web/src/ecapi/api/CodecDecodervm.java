package ecapi.api;


import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * 过滤器解码
 * @author chenguofang
 *
 */
class CodecDecodervm implements ProtocolDecoder {	
	@Override
	public void decode(IoSession session, IoBuffer in,ProtocolDecoderOutput out) throws Exception {
		byte[] buff = null;
		int pos = 0;
		while (in.hasRemaining()) {
			if(null == buff) buff = new byte[in.limit()];
			buff[pos++] = in.get();
		}	
		if(null!=buff && buff.length>0){
			out.write(buff);
			buff = null;
			pos = 0;
		}
	}

	@Override
	public void dispose(IoSession arg0) throws Exception {
		
	}

	@Override
	public void finishDecode(IoSession arg0, ProtocolDecoderOutput arg1)
			throws Exception {
		
	}
}
