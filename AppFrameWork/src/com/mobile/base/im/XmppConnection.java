package com.mobile.base.im;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Message.Type;
import org.jivesoftware.smack.packet.Presence;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import com.mobile.base.util.Helper;
import com.mobile.base.util.MLog;
 

public class XmppConnection {
 
    private Connection connection;
    private ConnectionConfiguration config;
    private Chat activeChat;
    private String activeTaget = "";

    private final static String HOSTNAME = "172.26.32.147";
    private final static int PORT = 5222;
    
    private String username;
    private String password;
    
    private Context context;
    
    private Handler handler;
    
    private boolean isLogin = false;
    
    
    
    public XmppConnection(String username, String password, Context context, Handler handler) {
		super();
		this.username = username;
		this.password = password;
		this.context = context;
		this.handler = handler;
		init();
		//登录
		login(username, password, 1);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
     * 初始化，客户端
     */
    public void init() {
        try {
            config = new ConnectionConfiguration(HOSTNAME, PORT);

            /** 是否启用压缩 */ 
            config.setCompressionEnabled(false);
            /** 是否启用安全验证 */
            config.setSASLAuthenticationEnabled(false);
            /** 是否启用调试 */
            config.setDebuggerEnabled(false);
            config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
            
            connection = new XMPPConnection(config);
            /** 建立连接 */
            connection.connect();
        } catch (XMPPException e) {
        	MLog.e(e);
        }
    }
    
   
    public void destory() {
        if (connection != null) {
            connection.disconnect();
            connection = null;
        }
    }
    
    /**
     * 创建用户
     * @param username
     * @param password
     */
    public void createUser(String username, String password) {
        AccountManager accountManager = connection.getAccountManager();
 
        try {
        	accountManager.createAccount(username, password);
        } catch (XMPPException e) {
           MLog.e(e);
        }
    }
    
    /**
     * 修改密码
     * @param newPassword
     */
    public void changePassword(String newPassword) {
    	AccountManager accountManager = connection.getAccountManager();
        try {
			accountManager.changePassword(newPassword);
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			MLog.e(e);
		}
    }
    
    /**
     * 用户登录
     */
    public void login(String username, String password, int type) {
        try {
        	org.jivesoftware.smack.packet.Presence.Type TYPE = null;
        	String status = "";
        	if(!isLogin)
        		connection.login(username, password);
        	 /** 更改用户状态，available=true表示在线，false表示离线，status状态签名；当你登陆后，在Spark客户端软件中就可以看到你登陆的状态 */
            switch(type) {
            	case 1: TYPE = Presence.Type.available; status = "Q我吧"; break;
            	default: TYPE = Presence.Type.available; status = "Q我吧";
            }
        	Presence presence = new Presence(TYPE);
        	presence.setStatus(status);
            connection.sendPacket(presence);
            isLogin = true;
        } catch (XMPPException e) {
            MLog.e(e);
        }
    }
    
    /**
     * 发送消息
     */
    public void sendMessage(String target, String text) {
        if(!isLogin) { //检查是否登录
        	login(this.username, this.password, 1);
        }

        /** Type.chat 表示聊天，groupchat多人聊天，error错误，headline在线用户； */
        final Message message = new Message(target + "@" + HOSTNAME, Type.chat);
        message.setBody(text);
        //打开与目标用户的消息管理器
        if(!activeTaget.equals(target))
        	chatManager(target);
        
        
        	new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						activeChat.sendMessage(message);
					} catch (XMPPException e) {
						// TODO Auto-generated catch block
						MLog.e(e);
					}
				}
			});
			
		
    }
    
    /**
     * 消息管理器
     */
    public void chatManager(String target) {
    	if(!isLogin) { //检查是否登录
        	login(this.username, this.password, 1);
        }
        
        /** 获取当前登陆用户的聊天管理器 */
        ChatManager chatManager = connection.getChatManager();
        /** 为指定用户创建一个chat，MyMessageListeners用于监听对方发过来的消息  */
        activeChat = chatManager.createChat(target + "@" + HOSTNAME, null);
        chatManager.addChatListener(new ChatManagerListenerEx());
        this.activeTaget = target;
    }
    
    public class ChatManagerListenerEx implements ChatManagerListener {

        @Override
        public void chatCreated(Chat chat, boolean arg1) {
            // TODO Auto-generated method stub
            chat.addMessageListener(new MyMessageListeners());
        }

    }
    
    /**
     * 监听对方的发过来的消息
     * @author yjh
     *
     */
    class MyMessageListeners implements MessageListener {
        public void processMessage(Chat chat, Message message) {
            try {
            	
                //显示消息
            	 String result = message.getFrom() + ":" + message.getBody();
            	 MLog.d(result);
            	 android.os.Message msg = new android.os.Message();
                 msg.what = 0;
                 Bundle bd = new Bundle();
                 bd.putString("msg", result);
                 msg.setData(bd);
                 handler.sendMessage(msg);
            } catch (Exception e) {
               MLog.e(e);
            }
        }
    }
    
    
}