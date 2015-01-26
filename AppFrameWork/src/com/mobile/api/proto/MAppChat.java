package com.mobile.api.proto;

import java.util.*;
import com.mobile.base.model.Bean2Json;
import org.json.*;

public class MAppChat {

	static public class MChatList extends Bean2Json<MChatList> {
		private static final long serialVersionUID = 1L;
		private List<MChatIndex> chat = new ArrayList<MChatIndex>();
		public List<MChatIndex> getChat() {
			return this.chat;
		}
		public void setChat(List<MChatIndex> chat) {
			this.chat=chat;
		}
		public MChatList build() throws Exception {
			JSONArray jachat = data.getJSONArray("chat_");
			for(int i = 0, len = jachat.length(); i < len; i++) {
				MChatIndex n = new MChatIndex();n.setData(jachat.getJSONObject(i));this.chat.add(n);
			}
			return this;
		}
		public void build(Bean2Json data) {
			this.setChat(((MChatList)data).getChat());
		}

	}

	static public class MChatIndex extends Bean2Json<MChatIndex> {
		private static final long serialVersionUID = 1L;
		private String id;
		private String targetid;
		private int headImg;
		private int total;
		private String content;
		private String time;
		private String speaker;
		public String getId() {
			return this.id;
		}
		public void setId(String id) {
			this.id=id;
		}
		public String getTargetid() {
			return this.targetid;
		}
		public void setTargetid(String targetid) {
			this.targetid=targetid;
		}
		public int getHeadImg() {
			return this.headImg;
		}
		public void setHeadImg(int headImg) {
			this.headImg=headImg;
		}
		public int getTotal() {
			return this.total;
		}
		public void setTotal(int total) {
			this.total=total;
		}
		public String getContent() {
			return this.content;
		}
		public void setContent(String content) {
			this.content=content;
		}
		public String getTime() {
			return this.time;
		}
		public void setTime(String time) {
			this.time=time;
		}
		public String getSpeaker() {
			return this.speaker;
		}
		public void setSpeaker(String speaker) {
			this.speaker=speaker;
		}
		public MChatIndex build() throws Exception {
			this.setId(data.getString("id_"));
			this.setTargetid(data.getString("targetid_"));
			this.setHeadImg(data.getInt("headImg_"));
			this.setTotal(data.getInt("total_"));
			this.setContent(data.getString("content_"));
			this.setTime(data.getString("time_"));
			this.setSpeaker(data.getString("speaker_"));
			return this;
		}
		public void build(Bean2Json data) {
			this.setId(((MChatIndex)data).getId());
			this.setTargetid(((MChatIndex)data).getTargetid());
			this.setHeadImg(((MChatIndex)data).getHeadImg());
			this.setTotal(((MChatIndex)data).getTotal());
			this.setContent(((MChatIndex)data).getContent());
			this.setTime(((MChatIndex)data).getTime());
			this.setSpeaker(((MChatIndex)data).getSpeaker());
		}

	}

	static public class MChats extends Bean2Json<MChats> {
		private static final long serialVersionUID = 1L;
		private List<MChat> chat = new ArrayList<MChat>();
		private String targetid;
		private int headImg;
		private String pushId;
		public List<MChat> getChat() {
			return this.chat;
		}
		public void setChat(List<MChat> chat) {
			this.chat=chat;
		}
		public String getTargetid() {
			return this.targetid;
		}
		public void setTargetid(String targetid) {
			this.targetid=targetid;
		}
		public int getHeadImg() {
			return this.headImg;
		}
		public void setHeadImg(int headImg) {
			this.headImg=headImg;
		}
		public String getPushId() {
			return this.pushId;
		}
		public void setPushId(String pushId) {
			this.pushId=pushId;
		}
		public MChats build() throws Exception {
			JSONArray jachat = data.getJSONArray("chat_");
			for(int i = 0, len = jachat.length(); i < len; i++) {
				MChat n = new MChat();n.setData(jachat.getJSONObject(i));this.chat.add(n);
			}
			this.setTargetid(data.getString("targetid_"));
			this.setHeadImg(data.getInt("headImg_"));
			this.setPushId(data.getString("pushId_"));
			return this;
		}
		public void build(Bean2Json data) {
			this.setChat(((MChats)data).getChat());
			this.setTargetid(((MChats)data).getTargetid());
			this.setHeadImg(((MChats)data).getHeadImg());
			this.setPushId(((MChats)data).getPushId());
		}

	}

	static public class MChat extends Bean2Json<MChat> {
		private static final long serialVersionUID = 1L;
		private String id;
		private String userid;
		private String content;
		private String img;
		private String time;
		private String createtime;
		private String size;
		public String getId() {
			return this.id;
		}
		public void setId(String id) {
			this.id=id;
		}
		public String getUserid() {
			return this.userid;
		}
		public void setUserid(String userid) {
			this.userid=userid;
		}
		public String getContent() {
			return this.content;
		}
		public void setContent(String content) {
			this.content=content;
		}
		public String getImg() {
			return this.img;
		}
		public void setImg(String img) {
			this.img=img;
		}
		public String getTime() {
			return this.time;
		}
		public void setTime(String time) {
			this.time=time;
		}
		public String getCreatetime() {
			return this.createtime;
		}
		public void setCreatetime(String createtime) {
			this.createtime=createtime;
		}
		public String getSize() {
			return this.size;
		}
		public void setSize(String size) {
			this.size=size;
		}
		public MChat build() throws Exception {
			this.setId(data.getString("id_"));
			this.setUserid(data.getString("userid_"));
			this.setContent(data.getString("content_"));
			this.setImg(data.getString("img_"));
			this.setTime(data.getString("time_"));
			this.setCreatetime(data.getString("createtime_"));
			this.setSize(data.getString("size_"));
			return this;
		}
		public void build(Bean2Json data) {
			this.setId(((MChat)data).getId());
			this.setUserid(((MChat)data).getUserid());
			this.setContent(((MChat)data).getContent());
			this.setImg(((MChat)data).getImg());
			this.setTime(((MChat)data).getTime());
			this.setCreatetime(((MChat)data).getCreatetime());
			this.setSize(((MChat)data).getSize());
		}

	}

	static public class MMatch extends Bean2Json<MMatch> {
		private static final long serialVersionUID = 1L;
		private String userid;
		private int headImg;
		private int flower;
		private String school;
		private String belong;
		public String getUserid() {
			return this.userid;
		}
		public void setUserid(String userid) {
			this.userid=userid;
		}
		public int getHeadImg() {
			return this.headImg;
		}
		public void setHeadImg(int headImg) {
			this.headImg=headImg;
		}
		public int getFlower() {
			return this.flower;
		}
		public void setFlower(int flower) {
			this.flower=flower;
		}
		public String getSchool() {
			return this.school;
		}
		public void setSchool(String school) {
			this.school=school;
		}
		public String getBelong() {
			return this.belong;
		}
		public void setBelong(String belong) {
			this.belong=belong;
		}
		public MMatch build() throws Exception {
			this.setUserid(data.getString("userid_"));
			this.setHeadImg(data.getInt("headImg_"));
			this.setFlower(data.getInt("flower_"));
			this.setSchool(data.getString("school_"));
			this.setBelong(data.getString("belong_"));
			return this;
		}
		public void build(Bean2Json data) {
			this.setUserid(((MMatch)data).getUserid());
			this.setHeadImg(((MMatch)data).getHeadImg());
			this.setFlower(((MMatch)data).getFlower());
			this.setSchool(((MMatch)data).getSchool());
			this.setBelong(((MMatch)data).getBelong());
		}

	}


}
