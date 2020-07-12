package com.yml.thread.demo.test2;


import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class BufferTest {

    public static void main(String[] args) throws UnsupportedEncodingException, UnknownHostException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        buffer.put("yang".getBytes());
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());
        System.out.println(buffer.position());
        byte[] bytes = new byte[1024];
        bytes[0]=buffer.get(0);
        System.out.println(new String(bytes));

        buffer.flip();

        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());
        System.out.println(buffer.position());

        byte[] bytes2 = new byte[1024];
        bytes2[0]=buffer.get();
        System.out.println(new String(bytes2));

        System.out.println(buffer.position());
        buffer.get();
        buffer.get();

        System.out.println(buffer.position());
        buffer.get();

        System.out.println(buffer.position());

        /*buffer.rewind();
        System.out.println(buffer.position());*/

        buffer.clear();
        System.out.println(buffer.position());


        System.out.println((char) buffer.get(3));
        System.out.println("IP:"+InetAddress.getLocalHost().getHostAddress());
    }
}
