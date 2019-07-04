package ecapi.api;


import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;

/**
 * 编解码工厂类
 * @author chenguofang
 *
 */
class CodeFactory extends ObjectSerializationCodecFactory{
	//public static String STACKSTREAM = "stackstream";  2012-11-21 10：15注释
	@Override
	public ProtocolDecoder getDecoder(IoSession session) {
		return new CodecDecoder();
		//return super.getDecoder(session);
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession session) {
		return new CodecEncoder();		
		//return super.getEncoder(session);
	}

}
