/**
 * 
 */
package com.corey.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalUDPDataReciever
{
        private static final String TAG = LocalUDPDataReciever.class.getSimpleName();
        private static LocalUDPDataReciever instance = null;
        private Thread thread = null;
        private static final Logger logger = LoggerFactory.getLogger(LocalUDPDataReciever.class);  
        
 
        public static LocalUDPDataReciever getInstance()
        {
                if (instance == null)
                        instance = new LocalUDPDataReciever();
                return instance;
        }
 
        public void startup()
        {
                this.thread = new Thread(new Runnable()
                {
                        public void run()
                        {
                                try
                                {
                                	logger.debug( "本地UDP端口侦听中，端口=" + ConfigEntity.localUDPPort + "...");
 
                                        //开始侦听
                                        LocalUDPDataReciever.this.udpListeningImpl();
                                }
                                catch (Exception eee)
                                {
                                	logger.error( "本地UDP监听停止了(socket被关闭了?)," + eee.getMessage(), eee);
                                }
                        }
                });
                this.thread.start();
        }
 
        private void udpListeningImpl() throws Exception
        {
                while (true)
                {
                        byte[] data = new byte[1024];
                        // 接收数据报的包
                        DatagramPacket packet = new DatagramPacket(data, data.length);
 
                        DatagramSocket localUDPSocket = LocalUDPSocketProvider.getInstance().getLocalUDPSocket();
                        if ((localUDPSocket == null) || (localUDPSocket.isClosed()))
                                continue;
                         
                        // 阻塞直到收到数据
                        localUDPSocket.receive(packet);
                         
                        // 解析服务端发过来的数据
                        String pFromServer = new String(packet.getData(), 0 , packet.getLength(), "UTF-8");
                        logger.debug( "【NOTE】>>>>>> 收到服务端的消息："+pFromServer);
                }
        }
}