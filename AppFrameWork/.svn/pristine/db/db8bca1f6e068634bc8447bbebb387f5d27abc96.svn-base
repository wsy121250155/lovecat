package com.mobile.api.proto;

import java.util.*;
import com.mobile.base.model.Bean2Json;
import org.json.*;

public class MAppNews {

	static public class MNewsList extends Bean2Json<MNewsList> {
		private static final long serialVersionUID = 1L;
		private List<MNews> news = new ArrayList<MNews>();
		public List<MNews> getNews() {
			return this.news;
		}
		public void setNews(List<MNews> news) {
			this.news=news;
		}
		public MNewsList build() throws Exception {
			JSONArray janews = data.getJSONArray("news_");
			for(int i = 0, len = janews.length(); i < len; i++) {
				MNews n = new MNews();n.setData(janews.getJSONObject(i));this.news.add(n);
			}
			return this;
		}
		public void build(Bean2Json data) {
			this.setNews(((MNewsList)data).getNews());
		}

	}

	static public class MNews extends Bean2Json<MNews> {
		private static final long serialVersionUID = 1L;
		private String id;
		private String img;
		private String source;
		private String title;
		private String content;
		private String time;
		private String url;
		private String author;
		private int comment;
		public String getId() {
			return this.id;
		}
		public void setId(String id) {
			this.id=id;
		}
		public String getImg() {
			return this.img;
		}
		public void setImg(String img) {
			this.img=img;
		}
		public String getSource() {
			return this.source;
		}
		public void setSource(String source) {
			this.source=source;
		}
		public String getTitle() {
			return this.title;
		}
		public void setTitle(String title) {
			this.title=title;
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
		public String getUrl() {
			return this.url;
		}
		public void setUrl(String url) {
			this.url=url;
		}
		public String getAuthor() {
			return this.author;
		}
		public void setAuthor(String author) {
			this.author=author;
		}
		public int getComment() {
			return this.comment;
		}
		public void setComment(int comment) {
			this.comment=comment;
		}
		public MNews build() throws Exception {
			this.setId(data.getString("id_"));
			this.setImg(data.getString("img_"));
			this.setSource(data.getString("source_"));
			this.setTitle(data.getString("title_"));
			this.setContent(data.getString("content_"));
			this.setTime(data.getString("time_"));
			this.setUrl(data.getString("url_"));
			this.setAuthor(data.getString("author_"));
			this.setComment(data.getInt("comment_"));
			return this;
		}
		public void build(Bean2Json data) {
			this.setId(((MNews)data).getId());
			this.setImg(((MNews)data).getImg());
			this.setSource(((MNews)data).getSource());
			this.setTitle(((MNews)data).getTitle());
			this.setContent(((MNews)data).getContent());
			this.setTime(((MNews)data).getTime());
			this.setUrl(((MNews)data).getUrl());
			this.setAuthor(((MNews)data).getAuthor());
			this.setComment(((MNews)data).getComment());
		}

	}

	static public class MRssList extends Bean2Json<MRssList> {
		private static final long serialVersionUID = 1L;
		private List<MRss> list = new ArrayList<MRss>();
		public List<MRss> getList() {
			return this.list;
		}
		public void setList(List<MRss> list) {
			this.list=list;
		}
		public MRssList build() throws Exception {
			JSONArray jalist = data.getJSONArray("list_");
			for(int i = 0, len = jalist.length(); i < len; i++) {
				MRss n = new MRss();n.setData(jalist.getJSONObject(i));this.list.add(n);
			}
			return this;
		}
		public void build(Bean2Json data) {
			this.setList(((MRssList)data).getList());
		}

	}

	static public class MRss extends Bean2Json<MRss> {
		private static final long serialVersionUID = 1L;
		private String id;
		private String img;
		private String title;
		private String content;
		private int state;
		private int count;
		public String getId() {
			return this.id;
		}
		public void setId(String id) {
			this.id=id;
		}
		public String getImg() {
			return this.img;
		}
		public void setImg(String img) {
			this.img=img;
		}
		public String getTitle() {
			return this.title;
		}
		public void setTitle(String title) {
			this.title=title;
		}
		public String getContent() {
			return this.content;
		}
		public void setContent(String content) {
			this.content=content;
		}
		public int getState() {
			return this.state;
		}
		public void setState(int state) {
			this.state=state;
		}
		public int getCount() {
			return this.count;
		}
		public void setCount(int count) {
			this.count=count;
		}
		public MRss build() throws Exception {
			this.setId(data.getString("id_"));
			this.setImg(data.getString("img_"));
			this.setTitle(data.getString("title_"));
			this.setContent(data.getString("content_"));
			this.setState(data.getInt("state_"));
			this.setCount(data.getInt("count_"));
			return this;
		}
		public void build(Bean2Json data) {
			this.setId(((MRss)data).getId());
			this.setImg(((MRss)data).getImg());
			this.setTitle(((MRss)data).getTitle());
			this.setContent(((MRss)data).getContent());
			this.setState(((MRss)data).getState());
			this.setCount(((MRss)data).getCount());
		}

	}

	static public class MMyRssList extends Bean2Json<MMyRssList> {
		private static final long serialVersionUID = 1L;
		private List<MMyRss> list = new ArrayList<MMyRss>();
		public List<MMyRss> getList() {
			return this.list;
		}
		public void setList(List<MMyRss> list) {
			this.list=list;
		}
		public MMyRssList build() throws Exception {
			JSONArray jalist = data.getJSONArray("list_");
			for(int i = 0, len = jalist.length(); i < len; i++) {
				MMyRss n = new MMyRss();n.setData(jalist.getJSONObject(i));this.list.add(n);
			}
			return this;
		}
		public void build(Bean2Json data) {
			this.setList(((MMyRssList)data).getList());
		}

	}

	static public class MMyRss extends Bean2Json<MMyRss> {
		private static final long serialVersionUID = 1L;
		private String id;
		private String title;
		private List<MNews> news = new ArrayList<MNews>();
		private int count;
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
		public List<MNews> getNews() {
			return this.news;
		}
		public void setNews(List<MNews> news) {
			this.news=news;
		}
		public int getCount() {
			return this.count;
		}
		public void setCount(int count) {
			this.count=count;
		}
		public MMyRss build() throws Exception {
			this.setId(data.getString("id_"));
			this.setTitle(data.getString("title_"));
			JSONArray janews = data.getJSONArray("news_");
			for(int i = 0, len = janews.length(); i < len; i++) {
				MNews n = new MNews();n.setData(janews.getJSONObject(i));this.news.add(n);
			}
			this.setCount(data.getInt("count_"));
			return this;
		}
		public void build(Bean2Json data) {
			this.setId(((MMyRss)data).getId());
			this.setTitle(((MMyRss)data).getTitle());
			this.setNews(((MMyRss)data).getNews());
			this.setCount(((MMyRss)data).getCount());
		}

	}


}
