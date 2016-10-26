/**
 * 
 */
package com.corey.client;

import java.io.IOException;
import java.net.DatagramPacket;

/**
 * @author zhangyx
 * 
 * @version 2016年10月26日
 */
public class UDPUtils {

	/**@param 
	 * @param soServerBytes
	 * @param length
	 * @return
	 * @throws IOException 
	 */
	public static boolean send(byte[] soServerBytes, int length) throws IOException {
		DatagramPacket dp = new DatagramPacket(soServerBytes, length);
		LocalUDPSocketProvider.getInstance().getLocalUDPSocket().send(dp);
		return false;
	}

}
