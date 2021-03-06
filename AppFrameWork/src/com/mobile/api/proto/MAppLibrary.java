package com.mobile.api.proto;

import java.util.*;
import com.mobile.base.model.Bean2Json;
import org.json.*;

public class MAppLibrary {

	static public class MBookList extends Bean2Json<MBookList> {
		private static final long serialVersionUID = 1L;
		private List<MBook> news = new ArrayList<MBook>();
		private int cnt;
		private String img;
		public List<MBook> getNews() {
			return this.news;
		}
		public void setNews(List<MBook> news) {
			this.news=news;
		}
		public int getCnt() {
			return this.cnt;
		}
		public void setCnt(int cnt) {
			this.cnt=cnt;
		}
		public String getImg() {
			return this.img;
		}
		public void setImg(String img) {
			this.img=img;
		}
		public MBookList build() throws Exception {
			JSONArray janews = data.getJSONArray("news_");
			for(int i = 0, len = janews.length(); i < len; i++) {
				MBook n = new MBook();n.setData(janews.getJSONObject(i));this.news.add(n);
			}
			this.setCnt(data.getInt("cnt_"));
			this.setImg(data.getString("img_"));
			return this;
		}
		public void build(Bean2Json data) {
			this.setNews(((MBookList)data).getNews());
			this.setCnt(((MBookList)data).getCnt());
			this.setImg(((MBookList)data).getImg());
		}

	}

	static public class MBook extends Bean2Json<MBook> {
		private static final long serialVersionUID = 1L;
		private String id;
		private String title;
		private String author;
		private String publish;
		private int total;
		private int canBorrow;
		private String borrowTime;
		private String backTime;
		private List<MBookDetail> details = new ArrayList<MBookDetail>();
		public String getId() {
			return this.id;
		}
		public void setId(String id) {
			this.id=id;
		}
		public String getTitle() {
			return this.title;
		}
		public void setTitle(String title) {
			this.title=title;
		}
		public String getAuthor() {
			return this.author;
		}
		public void setAuthor(String author) {
			this.author=author;
		}
		public String getPublish() {
			return this.publish;
		}
		public void setPublish(String publish) {
			this.publish=publish;
		}
		public int getTotal() {
			return this.total;
		}
		public void setTotal(int total) {
			this.total=total;
		}
		public int getCanBorrow() {
			return this.canBorrow;
		}
		public void setCanBorrow(int canBorrow) {
			this.canBorrow=canBorrow;
		}
		public String getBorrowTime() {
			return this.borrowTime;
		}
		public void setBorrowTime(String borrowTime) {
			this.borrowTime=borrowTime;
		}
		public String getBackTime() {
			return this.backTime;
		}
		public void setBackTime(String backTime) {
			this.backTime=backTime;
		}
		public List<MBookDetail> getDetails() {
			return this.details;
		}
		public void setDetails(List<MBookDetail> details) {
			this.details=details;
		}
		public MBook build() throws Exception {
			this.setId(data.getString("id_"));
			this.setTitle(data.getString("title_"));
			this.setAuthor(data.getString("author_"));
			this.setPublish(data.getString("publish_"));
			this.setTotal(data.getInt("total_"));
			this.setCanBorrow(data.getInt("canBorrow_"));
			this.setBorrowTime(data.getString("borrowTime_"));
			this.setBackTime(data.getString("backTime_"));
			JSONArray jadetails = data.getJSONArray("details_");
			for(int i = 0, len = jadetails.length(); i < len; i++) {
				MBookDetail n = new MBookDetail();n.setData(jadetails.getJSONObject(i));this.details.add(n);
			}
			return this;
		}
		public void build(Bean2Json data) {
			this.setId(((MBook)data).getId());
			this.setTitle(((MBook)data).getTitle());
			this.setAuthor(((MBook)data).getAuthor());
			this.setPublish(((MBook)data).getPublish());
			this.setTotal(((MBook)data).getTotal());
			this.setCanBorrow(((MBook)data).getCanBorrow());
			this.setBorrowTime(((MBook)data).getBorrowTime());
			this.setBackTime(((MBook)data).getBackTime());
			this.setDetails(((MBook)data).getDetails());
		}

	}

	static public class MBookDetail extends Bean2Json<MBookDetail> {
		private static final long serialVersionUID = 1L;
		private String num;
		private String code;
		private String address;
		private String state;
		public String getNum() {
			return this.num;
		}
		public void setNum(String num) {
			this.num=num;
		}
		public String getCode() {
			return this.code;
		}
		public void setCode(String code) {
			this.code=code;
		}
		public String getAddress() {
			return this.address;
		}
		public void setAddress(String address) {
			this.address=address;
		}
		public String getState() {
			return this.state;
		}
		public void setState(String state) {
			this.state=state;
		}
		public MBookDetail build() throws Exception {
			this.setNum(data.getString("num_"));
			this.setCode(data.getString("code_"));
			this.setAddress(data.getString("address_"));
			this.setState(data.getString("state_"));
			return this;
		}
		public void build(Bean2Json data) {
			this.setNum(((MBookDetail)data).getNum());
			this.setCode(((MBookDetail)data).getCode());
			this.setAddress(((MBookDetail)data).getAddress());
			this.setState(((MBookDetail)data).getState());
		}

	}


}
