package com.mobile.api.proto;

import java.util.*;
import com.mobile.base.model.Bean2Json;
import org.json.*;

public class MAppUser {

	static public class MUser extends Bean2Json<MUser> {
		private static final long serialVersionUID = 1L;
		private String id;
		private String account;
		private String headImg;
		private String nickname;
		private String verify;
		private int sex;
		private String birthday;
		private String[] tags;
		private int flower;
		private String belong;
		private int isV;
		public String getId() {
			return this.id;
		}
		public void setId(String id) {
			this.id=id;
		}
		public String getAccount() {
			return this.account;
		}
		public void setAccount(String account) {
			this.account=account;
		}
		public String getHeadImg() {
			return this.headImg;
		}
		public void setHeadImg(String headImg) {
			this.headImg=headImg;
		}
		public String getNickname() {
			return this.nickname;
		}
		public void setNickname(String nickname) {
			this.nickname=nickname;
		}
		public String getVerify() {
			return this.verify;
		}
		public void setVerify(String verify) {
			this.verify=verify;
		}
		public int getSex() {
			return this.sex;
		}
		public void setSex(int sex) {
			this.sex=sex;
		}
		public String getBirthday() {
			return this.birthday;
		}
		public void setBirthday(String birthday) {
			this.birthday=birthday;
		}
		public String[] getTags() {
			return this.tags;
		}
		public void setTags(String[] tags) {
			this.tags=tags;
		}
		public int getFlower() {
			return this.flower;
		}
		public void setFlower(int flower) {
			this.flower=flower;
		}
		public String getBelong() {
			return this.belong;
		}
		public void setBelong(String belong) {
			this.belong=belong;
		}
		public int getIsV() {
			return this.isV;
		}
		public void setIsV(int isV) {
			this.isV=isV;
		}
		public MUser build() throws Exception {
			this.setId(data.getString("id_"));
			this.setAccount(data.getString("account_"));
			this.setHeadImg(data.getString("headImg_"));
			this.setNickname(data.getString("nickname_"));
			this.setVerify(data.getString("verify_"));
			this.setSex(data.getInt("sex_"));
			this.setBirthday(data.getString("birthday_"));
			this.setFlower(data.getInt("flower_"));
			this.setBelong(data.getString("belong_"));
			this.setIsV(data.getInt("isV_"));
			return this;
		}
		public void build(Bean2Json data) {
			this.setId(((MUser)data).getId());
			this.setAccount(((MUser)data).getAccount());
			this.setHeadImg(((MUser)data).getHeadImg());
			this.setNickname(((MUser)data).getNickname());
			this.setVerify(((MUser)data).getVerify());
			this.setSex(((MUser)data).getSex());
			this.setBirthday(((MUser)data).getBirthday());
			this.setTags(((MUser)data).getTags());
			this.setFlower(((MUser)data).getFlower());
			this.setBelong(((MUser)data).getBelong());
			this.setIsV(((MUser)data).getIsV());
		}

	}


}
