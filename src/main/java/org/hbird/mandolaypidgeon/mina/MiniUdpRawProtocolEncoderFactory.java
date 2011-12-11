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
					buf.flip();
					out.write(buf);
				}
				else {
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
		throw new UnsupportedOperationException();
	}

}
