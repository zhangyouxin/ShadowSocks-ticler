/**
 * 
 */
package com.corey.client;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * @author zhangyx
 * 
 * @version 2016年10月26日
 */
public class ConfigEntity {

	public static String serverIP = "95.85.54.29";
	public static SocketAddress localUDPPort = new InetSocketAddress("localhost", 8888);
	public static int serverUDPPort = 6001;

}
