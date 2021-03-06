package com.corey.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoClient
{
	private static final Logger logger = LoggerFactory.getLogger(EchoClient.class);  
    
        public static void main(String[] args) throws Exception
        {
                // 初始化本地UDP的Socket
                LocalUDPSocketProvider.getInstance().initSocket();
                // 启动本地UDP监听（接收数据用的）
               // LocalUDPDataReciever.getInstance().startup();
                 
                // 循环发送数据给服务端
                while(true)
                {
                        // 要发送的数据
                        String toServer = "Hi，我是客户端，我的时间戳"+System.currentTimeMillis();
                        toServer = "add:{\"server_port\":8500,\"password\":\"youxin0\"}";
                        byte[] soServerBytes = toServer.getBytes("UTF-8");
                         
                        // 开始发送
                        boolean ok = UDPUtils.send(soServerBytes, soServerBytes.length);
                        if(ok)
                        	logger.debug( "发往服务端的信息已送出.");
                        else
                        	logger.error( "发往服务端的信息没有成功发出！！！");
                         
                        // 3000秒后进入下一次循环
                        Thread.sleep(3000);
                }
        }
}