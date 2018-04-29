package com.robot.charles.hexapod;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ClientThread implements Runnable
{
    private Socket s;
    // 定义向UI线程发送消息的Handler对象
    private Handler handler;
    // 定义接收UI线程的消息的Handler对象
    public Handler revHandler;
    // 该线程所处理的Socket所对应的输入流
    BufferedReader br = null;
    OutputStream os = null;
    private String ip = null;
    private int port = 0;

    public ClientThread(Handler handler,String ip,int port)
    {
        this.handler = handler;
        this.ip=ip;
        this.port=port;
    }
    public void run()
    {
        try
        {
            s = new Socket(ip,port);
            br = new BufferedReader(new InputStreamReader(
                    s.getInputStream()));
            os = s.getOutputStream();
            new Thread()
            {
                @Override
                public void run()
                {
                    String content = null;
                    // 不断读取Socket输入流中的内容。
                    try
                    {
                        while ((content = br.readLine()) != null)
                        {
                            // 每当读到来自服务器的数据之后，发送消息通知程序界面显示该数据
                            Message msg = new Message();
                            msg.obj = content;
                            handler.sendMessage(msg);
                            //System.out.println(msg.obj.toString());
                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }.start();
            // 为当前线程初始化Looper
            Looper.prepare();
            // 创建revHandler对象
            revHandler = new Handler()
            {
                @SuppressLint("HandlerLeak")
                @Override
                public void handleMessage(Message msg)
                {
                    // 接收到UI线程中用户输入的数据
                    // 将用户在文本框内输入的内容写入网络
                    try
                    {
                        System.out.println("Hello "+msg.obj.toString());
                        os.write((msg.obj.toString())
                                .getBytes("utf-8"));
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            };
            // 启动Looper
            Looper.loop();
        }
        catch (SocketTimeoutException e1)
        {
            System.out.println("网络连接超时！！");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}