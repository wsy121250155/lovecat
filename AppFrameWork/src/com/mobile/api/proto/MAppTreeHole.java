package com.mobile.api.proto;

import java.util.*;
import com.mobile.base.model.Bean2Json;
import org.json.*;

public class MAppTreeHole {

	static public class MTreeHole extends Bean2Json<MTreeHole> {
		private static final long serialVersionUID = 1L;
		private List<MTopic> topics = new ArrayList<MTopic>();
		public List<MTopic> getTopics() {
			return this.topics;
		}
		public void setTopics(List<MTopic> topics) {
			this.topics=topics;
		}
		public MTreeHole build() throws Exception {
			JSONArray jatopics = data.getJSONArray("topics_");
			for(int i = 0, len = jatopics.length(); i < len; i++) {
				MTopic n = new MTopic();n.setData(jatopics.getJSONObject(i));this.topics.add(n);
			}
			return this;
		}
		public void build(Bean2Json data) {
			this.setTopics(((MTreeHole)data).getTopics());
		}

	}

	static public class MTopic extends Bean2Json<MTopic> {
		private static final long serialVersionUID = 1L;
		private String id;
		private String tagid;
		private String tag;
		private String content;
		private String time;
		private String img;
		private int praiseCnt;
		private int commentCnt;
		private int hasPraise;
		private int isHot;
		private int isTop;
		private String author;
		private String createTime;
		public String getId() {
			return this.id;
		}
		public void setId(String id) {
			this.id=id;
		}
		public String getTagid() {
			return this.tagid;
		}
		public void setTagid(String tagid) {
			this.tagid=tagid;
		}
		public String getTag() {
			return this.tag;
		}
		public void setTag(String tag) {
			this.tag=tag;
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
		public String getImg() {
			return this.img;
		}
		public void setImg(String img) {
			this.img=img;
		}
		public int getPraiseCnt() {
			return this.praiseCnt;
		}
		public void setPraiseCnt(int praiseCnt) {
			this.praiseCnt=praiseCnt;
		}
		public int getCommentCnt() {
			return this.commentCnt;
		}
		public void setCommentCnt(int commentCnt) {
			this.commentCnt=commentCnt;
		}
		public int getHasPraise() {
			return this.hasPraise;
		}
		public void setHasPraise(int hasPraise) {
			this.hasPraise=hasPraise;
		}
		public int getIsHot() {
			return this.isHot;
		}
		public void setIsHot(int isHot) {
			this.isHot=isHot;
		}
		public int getIsTop() {
			return this.isTop;
		}
		public void setIsTop(int isTop) {
			this.isTop=isTop;
		}
		public String getAuthor() {
			return this.author;
		}
		public void setAuthor(String author) {
			this.author=author;
		}
		public String getCreateTime() {
			return this.createTime;
		}
		public void setCreateTime(String createTime) {
			this.createTime=createTime;
		}
		public MTopic build() throws Exception {
			this.setId(data.getString("id_"));
			this.setTagid(data.getString("tagid_"));
			this.setTag(data.getString("tag_"));
			this.setContent(data.getString("content_"));
			this.setTime(data.getString("time_"));
			this.setImg(data.getString("img_"));
			this.setPraiseCnt(data.getInt("praiseCnt_"));
			this.setCommentCnt(data.getInt("commentCnt_"));
			this.setHasPraise(data.getInt("hasPraise_"));
			this.setIsHot(data.getInt("isHot_"));
			this.setIsTop(data.getInt("isTop_"));
			this.setAuthor(data.getString("author_"));
			this.setCreateTime(data.getString("createTime_"));
			return this;
		}
		public void build(Bean2Json data) {
			this.setId(((MTopic)data).getId());
			this.setTagid(((MTopic)data).getTagid());
			this.setTag(((MTopic)data).getTag());
			this.setContent(((MTopic)data).getContent());
			this.setTime(((MTopic)data).getTime());
			this.setImg(((MTopic)data).getImg());
			this.setPraiseCnt(((MTopic)data).getPraiseCnt());
			this.setCommentCnt(((MTopic)data).getCommentCnt());
			this.setHasPraise(((MTopic)data).getHasPraise());
			this.setIsHot(((MTopic)data).getIsHot());
			this.setIsTop(((MTopic)data).getIsTop());
			this.setAuthor(((MTopic)data).getAuthor());
			this.setCreateTime(((MTopic)data).getCreateTime());
		}

	}

	static public class MTag extends Bean2Json<MTag> {
		private static final long serialVersionUID = 1L;
		private String id;
		private String title;
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
		public MTag build() throws Exception {
			this.setId(data.getString("id_"));
			this.setTitle(data.getString("title_"));
			return this;
		}
		public void build(Bean2Json data) {
			this.setId(((MTag)data).getId());
			this.setTitle(((MTag)data).getTitle());
		}

	}

	static public class MTagList extends Bean2Json<MTagList> {
		private static final long serialVersionUID = 1L;
		private List<MTag> tags = new ArrayList<MTag>();
		public List<MTag> getTags() {
			return this.tags;
		}
		public void setTags(List<MTag> tags) {
			this.tags=tags;
		}
		public MTagList build() throws Exception {
			JSONArray jatags = data.getJSONArray("tags_");
			for(int i = 0, len = jatags.length(); i < len; i++) {
				MTag n = new MTag();n.setData(jatags.getJSONObject(i));this.tags.add(n);
			}
			return this;
		}
		public void build(Bean2Json data) {
			this.setTags(((MTagList)data).getTags());
		}

	}

	static public class MComment extends Bean2Json<MComment> {
		private static final long serialVersionUID = 1L;
		private String id;
		private int floor;
		private String userid;
		private int replyFloor;
		private String replyid;
		private String content;
		private String time;
		private int isLz;
		private String createTime;
		public String getId() {
			return this.id;
		}
		public void setId(String id) {
			this.id=id;
		}
		public int getFloor() {
			return this.floor;
		}
		public void setFloor(int floor) {
			this.floor=floor;
		}
		public String getUserid() {
			return this.userid;
		}
		public void setUserid(String userid) {
			this.userid=userid;
		}
		public int getReplyFloor() {
			return this.replyFloor;
		}
		public void setReplyFloor(int replyFloor) {
			this.replyFloor=replyFloor;
		}
		public String getReplyid() {
			return this.replyid;
		}
		public void setReplyid(String replyid) {
			this.replyid=replyid;
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
		public int getIsLz() {
			return this.isLz;
		}
		public void setIsLz(int isLz) {
			this.isLz=isLz;
		}
		public String getCreateTime() {
			return this.createTime;
		}
		public void setCreateTime(String createTime) {
			this.createTime=createTime;
		}
		public MComment build() throws Exception {
			this.setId(data.getString("id_"));
			this.setFloor(data.getInt("floor_"));
			this.setUserid(data.getString("userid_"));
			this.setReplyFloor(data.getInt("replyFloor_"));
			this.setReplyid(data.getString("replyid_"));
			this.setContent(data.getString("content_"));
			this.setTime(data.getString("time_"));
			this.setIsLz(data.getInt("isLz_"));
			this.setCreateTime(data.getString("createTime_"));
			return this;
		}
		public void build(Bean2Json data) {
			this.setId(((MComment)data).getId());
			this.setFloor(((MComment)data).getFloor());
			this.setUserid(((MComment)data).getUserid());
			this.setReplyFloor(((MComment)data).getReplyFloor());
			this.setReplyid(((MComment)data).getReplyid());
			this.setContent(((MComment)data).getContent());
			this.setTime(((MComment)data).getTime());
			this.setIsLz(((MComment)data).getIsLz());
			this.setCreateTime(((MComment)data).getCreateTime());
		}

	}

	static public class MCommentList extends Bean2Json<MCommentList> {
		private static final long serialVersionUID = 1L;
		private List<MComment> comments = new ArrayList<MComment>();
		public List<MComment> getComments() {
			return this.comments;
		}
		public void setComments(List<MComment> comments) {
			this.comments=comments;
		}
		public MCommentList build() throws Exception {
			JSONArray jacomments = data.getJSONArray("comments_");
			for(int i = 0, len = jacomments.length(); i < len; i++) {
				MComment n = new MComment();n.setData(jacomments.getJSONObject(i));this.comments.add(n);
			}
			return this;
		}
		public void build(Bean2Json data) {
			this.setComments(((MCommentList)data).getComments());
		}

	}

	static public class MAddTopic extends Bean2Json<MAddTopic> {
		private static final long serialVersionUID = 1L;
		private String tagId;
		private String content;
		private String img;
		public String getTagId() {
			return this.tagId;
		}
		public void setTagId(String tagId) {
			this.tagId=tagId;
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
		public MAddTopic build() throws Exception {
			this.setTagId(data.getString("tagId_"));
			this.setContent(data.getString("content_"));
			this.setImg(data.getString("img_"));
			return this;
		}
		public void build(Bean2Json data) {
			this.setTagId(((MAddTopic)data).getTagId());
			this.setContent(((MAddTopic)data).getContent());
			this.setImg(((MAddTopic)data).getImg());
		}

	}

	static public class MMsgCount extends Bean2Json<MMsgCount> {
		private static final long serialVersionUID = 1L;
		private int comment;
		private int chat;
		public int getComment() {
			return this.comment;
		}
		public void setComment(int comment) {
			this.comment=comment;
		}
		public int getChat() {
			return this.chat;
		}
		public void setChat(int chat) {
			this.chat=chat;
		}
		public MMsgCount build() throws Exception {
			this.setComment(data.getInt("comment_"));
			this.setChat(data.getInt("chat_"));
			return this;
		}
		public void build(Bean2Json data) {
			this.setComment(((MMsgCount)data).getComment());
			this.setChat(((MMsgCount)data).getChat());
		}

	}

	static public class MTopicMini extends Bean2Json<MTopicMini> {
		private static final long serialVersionUID = 1L;
		private String id;
		private String tag;
		private String content;
		private int unreadCnt;
		private String createTime;
		public String getId() {
			return this.id;
		}
		public void setId(String id) {
			this.id=id;
		}
		public String getTag() {
			return this.tag;
		}
		public void setTag(String tag) {
			this.tag=tag;
		}
		public String getContent() {
			return this.content;
		}
		public void setContent(String content) {
			this.content=content;
		}
		public int getUnreadCnt() {
			return this.unreadCnt;
		}
		public void setUnreadCnt(int unreadCnt) {
			this.unreadCnt=unreadCnt;
		}
		public String getCreateTime() {
			return this.createTime;
		}
		public void setCreateTime(String createTime) {
			this.createTime=createTime;
		}
		public MTopicMini build() throws Exception {
			this.setId(data.getString("id_"));
			this.setTag(data.getString("tag_"));
			this.setContent(data.getString("content_"));
			this.setUnreadCnt(data.getInt("unreadCnt_"));
			this.setCreateTime(data.getString("createTime_"));
			return this;
		}
		public void build(Bean2Json data) {
			this.setId(((MTopicMini)data).getId());
			this.setTag(((MTopicMini)data).getTag());
			this.setContent(((MTopicMini)data).getContent());
			this.setUnreadCnt(((MTopicMini)data).getUnreadCnt());
			this.setCreateTime(((MTopicMini)data).getCreateTime());
		}

	}

	static public class MTopicMiniList extends Bean2Json<MTopicMiniList> {
		private static final long serialVersionUID = 1L;
		private List<MTopicMini> topics = new ArrayList<MTopicMini>();
		public List<MTopicMini> getTopics() {
			return this.topics;
		}
		public void setTopics(List<MTopicMini> topics) {
			this.topics=topics;
		}
		public MTopicMiniList build() throws Exception {
			JSONArray jatopics = data.getJSONArray("topics_");
			for(int i = 0, len = jatopics.length(); i < len; i++) {
				MTopicMini n = new MTopicMini();n.setData(jatopics.getJSONObject(i));this.topics.add(n);
			}
			return this;
		}
		public void build(Bean2Json data) {
			this.setTopics(((MTopicMiniList)data).getTopics());
		}

	}


}
