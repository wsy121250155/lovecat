package com.mobile.api.proto;

import java.util.*;
import com.mobile.base.model.Bean2Json;
import org.json.*;

public class MLost {

	static public class MLostAndFoundList extends Bean2Json<MLostAndFoundList> {
		private static final long serialVersionUID = 1L;
		private List<MLostAndFound> lf = new ArrayList<MLostAndFound>();
		public List<MLostAndFound> getLf() {
			return this.lf;
		}
		public void setLf(List<MLostAndFound> lf) {
			this.lf=lf;
		}
		public MLostAndFoundList build() throws Exception {
			JSONArray jalf = data.getJSONArray("lf_");
			for(int i = 0, len = jalf.length(); i < len; i++) {
				MLostAndFound n = new MLostAndFound();n.setData(jalf.getJSONObject(i));this.lf.add(n);
			}
			return this;
		}
		public void build(Bean2Json data) {
			this.setLf(((MLostAndFoundList)data).getLf());
		}

	}

	static public class MLostAndFound extends Bean2Json<MLostAndFound> {
		private static final long serialVersionUID = 1L;
		private String id;
		private String address;
		private String desc;
		private String userid;
		private String nickname;
		private String contact;
		private String time;
		private String[] img;
		public String getId() {
			return this.id;
		}
		public void setId(String id) {
			this.id=id;
		}
		public String getAddress() {
			return this.address;
		}
		public void setAddress(String address) {
			this.address=address;
		}
		public String getDesc() {
			return this.desc;
		}
		public void setDesc(String desc) {
			this.desc=desc;
		}
		public String getUserid() {
			return this.userid;
		}
		public void setUserid(String userid) {
			this.userid=userid;
		}
		public String getNickname() {
			return this.nickname;
		}
		public void setNickname(String nickname) {
			this.nickname=nickname;
		}
		public String getContact() {
			return this.contact;
		}
		public void setContact(String contact) {
			this.contact=contact;
		}
		public String getTime() {
			return this.time;
		}
		public void setTime(String time) {
			this.time=time;
		}
		public String[] getImg() {
			return this.img;
		}
		public void setImg(String[] img) {
			this.img=img;
		}
		public MLostAndFound build() throws Exception {
			this.setId(data.getString("id_"));
			this.setAddress(data.getString("address_"));
			this.setDesc(data.getString("desc_"));
			this.setUserid(data.getString("userid_"));
			this.setNickname(data.getString("nickname_"));
			this.setContact(data.getString("contact_"));
			this.setTime(data.getString("time_"));
			return this;
		}
		public void build(Bean2Json data) {
			this.setId(((MLostAndFound)data).getId());
			this.setAddress(((MLostAndFound)data).getAddress());
			this.setDesc(((MLostAndFound)data).getDesc());
			this.setUserid(((MLostAndFound)data).getUserid());
			this.setNickname(((MLostAndFound)data).getNickname());
			this.setContact(((MLostAndFound)data).getContact());
			this.setTime(((MLostAndFound)data).getTime());
			this.setImg(((MLostAndFound)data).getImg());
		}

	}

	static public class MAddLostOrFound extends Bean2Json<MAddLostOrFound> {
		private static final long serialVersionUID = 1L;
		private int type;
		private String address;
		private String desc;
		private String contact;
		private String time;
		private byte[] img1;
		private byte[] img2;
		private byte[] img3;
		private byte[] img4;
		public int getType() {
			return this.type;
		}
		public void setType(int type) {
			this.type=type;
		}
		public String getAddress() {
			return this.address;
		}
		public void setAddress(String address) {
			this.address=address;
		}
		public String getDesc() {
			return this.desc;
		}
		public void setDesc(String desc) {
			this.desc=desc;
		}
		public String getContact() {
			return this.contact;
		}
		public void setContact(String contact) {
			this.contact=contact;
		}
		public String getTime() {
			return this.time;
		}
		public void setTime(String time) {
			this.time=time;
		}
		public byte[] getImg1() {
			return this.img1;
		}
		public void setImg1(byte[] img1) {
			this.img1=img1;
		}
		public byte[] getImg2() {
			return this.img2;
		}
		public void setImg2(byte[] img2) {
			this.img2=img2;
		}
		public byte[] getImg3() {
			return this.img3;
		}
		public void setImg3(byte[] img3) {
			this.img3=img3;
		}
		public byte[] getImg4() {
			return this.img4;
		}
		public void setImg4(byte[] img4) {
			this.img4=img4;
		}
		public MAddLostOrFound build() throws Exception {
			this.setType(data.getInt("type_"));
			this.setAddress(data.getString("address_"));
			this.setDesc(data.getString("desc_"));
			this.setContact(data.getString("contact_"));
			this.setTime(data.getString("time_"));
			return this;
		}
		public void build(Bean2Json data) {
			this.setType(((MAddLostOrFound)data).getType());
			this.setAddress(((MAddLostOrFound)data).getAddress());
			this.setDesc(((MAddLostOrFound)data).getDesc());
			this.setContact(((MAddLostOrFound)data).getContact());
			this.setTime(((MAddLostOrFound)data).getTime());
			this.setImg1(((MAddLostOrFound)data).getImg1());
			this.setImg2(((MAddLostOrFound)data).getImg2());
			this.setImg3(((MAddLostOrFound)data).getImg3());
			this.setImg4(((MAddLostOrFound)data).getImg4());
		}

	}


}
