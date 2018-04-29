package com.robot.charles.hexapod;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.os.Message;
import android.view.View;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    //控件变量
    TextView tvServer;
    EditText etInput;
    Button   btnStart;
    Button   btnEnd;
    Button   btnUp;
    Button   btnDown;
    Button   btnLeft;
    Button   btnRight;
    Button   btnX;
    Button   btnY;
    Button   btnA;
    Button   btnB;
    ClientThread clientThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        setListener();

        //隐藏虚拟键
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav
                        // bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    //初始化控件
    private void findView() {
        tvServer  = (TextView) this.findViewById(R. id. tv_server);
        etInput   = (EditText) this.findViewById(R. id. et_input);
        btnStart  = (Button) this.findViewById(R. id. btn_start);
        btnEnd    = (Button) this.findViewById(R. id. btn_end);
        btnUp     = (Button) this.findViewById(R. id. btn_up);
        btnDown   = (Button) this.findViewById(R. id. btn_down);
        btnLeft   = (Button) this.findViewById(R. id. btn_left);
        btnRight  = (Button) this.findViewById(R. id. btn_right);
        btnX      = (Button) this.findViewById(R. id. btn_x);
        btnY      = (Button) this.findViewById(R. id. btn_y);
        btnA      = (Button) this.findViewById(R. id. btn_a);
        btnB      = (Button) this.findViewById(R. id. btn_b);
    }

    //连接server
    Handler handler = new Handler() //①
    {
        public void handleMessage(Message msg)
        {
            // 如果消息来自于子线程
            // 将读取的内容追加显示在文本框中
            //tvServer.append(msg.obj.toString()+'\n');
        }
    };

    //监听器
    private void setListener() {
        btnStart.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                if(etInput.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this,"请先设置IP地址以及端口号 格式为  IP:PORT",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String msg_all=null;
                    String msg_ip=null;
                    String msg_port_string=null;
                    int msg_port_int=0;
                    try {
                        msg_all=etInput.getText().toString();
                        StringTokenizer msg_socket = new StringTokenizer(msg_all,":");
                        msg_ip=msg_socket.nextToken();
                        msg_port_string=msg_socket.nextToken();
                        msg_port_int=Integer.parseInt(msg_port_string);
                        Toast.makeText(MainActivity.this,"IP："+msg_ip+"  端口："+msg_port_int,Toast.LENGTH_SHORT).show();
                    } catch (Exception e1) {}
                    clientThread = new ClientThread(handler,msg_ip,msg_port_int);
                    // 客户端启动ClientThread线程创建网络连接、读取来自服务器的数据
                    new Thread(clientThread).start();
                }//①
            }
        });
        btnEnd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try
                {
                    Message msg = new Message();
                    msg.obj = "exit";
                    clientThread.revHandler.sendMessage(msg);
                    //input_msg.setText("");
                    Toast.makeText(MainActivity.this,"与机器人连接断开", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        btnUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try
                {
                    Message msg = new Message();
                    msg.obj = "up";
                    clientThread.revHandler.sendMessage(msg);
                    //input_msg.setText("");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        btnDown.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try
                {
                    Message msg = new Message();
                    msg.obj = "down";
                    clientThread.revHandler.sendMessage(msg);
                    //input_msg.setText("");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        btnLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try
                {
                    Message msg = new Message();
                    msg.obj = "left";
                    clientThread.revHandler.sendMessage(msg);
                    //input_msg.setText("");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        btnRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try
                {
                    Message msg = new Message();
                    msg.obj = "right";
                    clientThread.revHandler.sendMessage(msg);
                    //input_msg.setText("");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        btnA.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try
                {
                    Message msg = new Message();
                    msg.obj = "A";
                    clientThread.revHandler.sendMessage(msg);
                    //input_msg.setText("");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try
                {
                    Message msg = new Message();
                    msg.obj = "B";
                    clientThread.revHandler.sendMessage(msg);
                    //input_msg.setText("");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        btnX.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try
                {
                    Message msg = new Message();
                    msg.obj = "X";
                    clientThread.revHandler.sendMessage(msg);
                    //input_msg.setText("");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        btnY.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try
                {
                    Message msg = new Message();
                    msg.obj = "Y";
                    clientThread.revHandler.sendMessage(msg);
                    //input_msg.setText("");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}
