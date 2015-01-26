package com.mobile.zsdx.chat;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mobile.base.MFragment;
import com.mobile.base.R;
import com.mobile.base.im.XmppConnection;

public class ChatFragment extends MFragment {
	private EditText targetInput;
	private EditText messageInput;
	private Button submitbtn;
	private TextView messageShow;
	private XmppConnection chatClient;
	
	private String username = "admin";
	private String password = "konghuan632";
	
	//创建一个Handler
	private Handler messageHandler = new Handler() {

        @Override
        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {
            case 0: {
                String result = msg.getData().getString("msg");
                messageShow.setText(messageShow.getText() + "\n" + result);
            }
                break;
            default:
                break;
            }
        }
    };
	
	@Override
	protected void create(Bundle savedInstanceState) {
		super.create(savedInstanceState);
		
		setContentView(R.layout.frg_chatdetail); //填充UI布局
		
		targetInput = (EditText)findViewById(R.id.targetinput);
		messageInput = (EditText)findViewById(R.id.chatinput);
		submitbtn = (Button)findViewById(R.id.chatsubmit);
		messageShow = (TextView)findViewById(R.id.messageshow);
		//登录
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				chatClient = new XmppConnection(username, password, getActivity(), messageHandler);
			}
		}).start();
		
		submitbtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String targetUser = targetInput.getText().toString();
				String message = messageInput.getText().toString();
				if(chatClient != null)
					chatClient.sendMessage(targetUser, message);
			}
		});
		
		
		
	}
}
