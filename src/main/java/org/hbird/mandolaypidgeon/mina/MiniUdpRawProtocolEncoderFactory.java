package org.hbird.mandolaypidgeon.mina;

import org.apache.mina.common.ByteBuffer;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.hbird.transport.commons.util.BytesUtility;

public class MiniUdpRawProtocolEncoderFactory implements ProtocolCodecFactory {
	
	@Override
	public ProtocolEncoder getEncoder() throws Exception {
		return new ProtocolEncoder() {
			
			@Override
			public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {

				if (message instanceof byte[]) {
					byte[] rawData = (byte[]) message;
					System.out.println("Encode: " + BytesUtility.decimalDump(rawData));
					ByteBuffer buf = ByteBuffer.allocate(rawData.length);
					buf.setAutoExpand(false);
					buf.put(rawData);
					
//		        int objectSize = buf.position() - 4;
//		        if (objectSize > maxObjectSize) {
//		            buf.release();
//		            throw new IllegalArgumentException(
//		                    "The encoded object is too big: " + objectSize + " (> "
//		                            + maxObjectSize + ')');
//		        }
					
					buf.flip();
					out.write(buf);
				} else {
					throw new InvalidMiniUdpException();
				}
			}
			
			@Override
			public void dispose(IoSession session) throws Exception {
				// TODO Auto-generated method stub
				
			}
		};
	}

	@Override
	public ProtocolDecoder getDecoder() throws Exception {
		return new ProtocolDecoder() {
			
			@Override
			public void finishDecode(IoSession session, ProtocolDecoderOutput out) throws Exception {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void dispose(IoSession session) throws Exception {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void decode(IoSession session, ByteBuffer in, ProtocolDecoderOutput out) throws Exception {
//				System.out.println("Decode: " + BytesUtility.decimalDump(in.array()));
//				out.write(in.array());
			}
		};
	}

}
