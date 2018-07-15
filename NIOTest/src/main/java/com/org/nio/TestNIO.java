package com.org.nio;

/**
 * Created by admin on 2018/7/15.
 */

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class TestNIO {
    private static final String MSG = "hello, I must be going \n";

    public static void main(String[] args) throws Exception {

        int port = 8989;
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ServerSocket ss = ssc.socket();
        ss.bind(new InetSocketAddress(port));
        // set no blocking
        ssc.configureBlocking(false);

        ByteBuffer buffer = ByteBuffer.wrap(MSG.getBytes());

        while (true) {
            System.out.println("wait for connection ……");

            Socket socket=ss.accept();
            SocketChannel sc = ssc.accept();
            if (sc == null) {
                // no connections, snooze a while ...
                Thread.sleep(1000);
            } else {
                System.out.println("Incoming connection from " + sc.socket().getRemoteSocketAddress());
                buffer.rewind();
                //write msg to client
                sc.write(buffer);
                sc.close();
            }
        }
    }
}
