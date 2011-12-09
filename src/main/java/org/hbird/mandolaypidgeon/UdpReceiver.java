package org.hbird.mandolaypidgeon;

import java.net.InetSocketAddress;

import org.apache.camel.Header;
import org.apache.mina.common.IoSession;
import org.hbird.transport.commons.data.GenericPayload;

public class UdpReceiver {
	
	/**
	 * Processes a Camel Mina UDP datagram and turns it into a Camel generic payload object using
	 * the source IP address as the payload layout identifier.
	 * @param datagram UDP payload
	 * @param ioSession 
	 * @return
	 */
	public static GenericPayload fromIpBasedPayloadIdentifier(byte[] datagram, @Header("CamelMinaIoSession") IoSession ioSession) {
		InetSocketAddress sockadd = (InetSocketAddress)ioSession.getLocalAddress();
		String ipAddress = sockadd.getAddress().getHostAddress();
		GenericPayload packetPayload = new GenericPayload(datagram, ipAddress,  System.currentTimeMillis());
		return packetPayload;
	}
}