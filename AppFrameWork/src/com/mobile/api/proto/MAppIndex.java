package com.mobile.api.proto;

import java.util.*;
import com.mobile.base.model.Bean2Json;
import org.json.*;

public class MAppIndex {

	static public class MIndex extends Bean2Json<MIndex> {
		private static final long serialVersionUID = 1L;
		private List<MFocus> focus = new ArrayList<MFocus>();
		private List<MMdoule> module = new ArrayList<MMdoule>();
		public List<MFocus> getFocus() {
			return this.focus;
		}
		public void setFocus(List<MFocus> focus) {
			this.focus=focus;
		}
		public List<MMdoule> getModule() {
			return this.module;
		}
		public void setModule(List<MMdoule> module) {
			this.module=module;
		}
		public MIndex build() throws Exception {
			JSONArray jafocus = data.getJSONArray("focus_");
			for(int i = 0, len = jafocus.length(); i < len; i++) {
				MFocus n = new MFocus();n.setData(jafocus.getJSONObject(i));this.focus.add(n);
			}
			JSONArray jamodule = data.getJSONArray("module_");
			for(int i = 0, len = jamodule.length(); i < len; i++) {
				MMdoule n = new MMdoule();n.setData(jamodule.getJSONObject(i));this.module.add(n);
			}
			return this;
		}
		public void build(Bean2Json data) {
			this.setFocus(((MIndex)data).getFocus());
			this.setModule(((MIndex)data).getModule());
		}

	}

	static public class MFocus extends Bean2Json<MFocus> {
		private static final long serialVersionUID = 1L;
		private String id;
		private String img;
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
		public MFocus build() throws Exception {
			this.setId(data.getString("id_"));
			this.setImg(data.getString("img_"));
			return this;
		}
		public void build(Bean2Json data) {
			this.setId(((MFocus)data).getId());
			this.setImg(((MFocus)data).getImg());
		}

	}

	static public class MMdoule extends Bean2Json<MMdoule> {
		private static final long serialVersionUID = 1L;
		private String id;
		private String img;
		private String name;
		private String desc;
		private String code;
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
		public String getName() {
			return this.name;
		}
		public void setName(String name) {
			this.name=name;
		}
		public String getDesc() {
			return this.desc;
		}
		public void setDesc(String desc) {
			this.desc=desc;
		}
		public String getCode() {
			return this.code;
		}
		public void setCode(String code) {
			this.code=code;
		}
		public MMdoule build() throws Exception {
			this.setId(data.getString("id_"));
			this.setImg(data.getString("img_"));
			this.setName(data.getString("name_"));
			this.setDesc(data.getString("desc_"));
			this.setCode(data.getString("code_"));
			return this;
		}
		public void build(Bean2Json data) {
			this.setId(((MMdoule)data).getId());
			this.setImg(((MMdoule)data).getImg());
			this.setName(((MMdoule)data).getName());
			this.setDesc(((MMdoule)data).getDesc());
			this.setCode(((MMdoule)data).getCode());
		}

	}

	static public class MUnread extends Bean2Json<MUnread> {
		private static final long serialVersionUID = 1L;
		private int module1;
		private int module2;
		private int module3;
		private int module4;
		private int module5;
		private int module6;
		public int getModule1() {
			return this.module1;
		}
		public void setModule1(int module1) {
			this.module1=module1;
		}
		public int getModule2() {
			return this.module2;
		}
		public void setModule2(int module2) {
			this.module2=module2;
		}
		public int getModule3() {
			return this.module3;
		}
		public void setModule3(int module3) {
			this.module3=module3;
		}
		public int getModule4() {
			return this.module4;
		}
		public void setModule4(int module4) {
			this.module4=module4;
		}
		public int getModule5() {
			return this.module5;
		}
		public void setModule5(int module5) {
			this.module5=module5;
		}
		public int getModule6() {
			return this.module6;
		}
		public void setModule6(int module6) {
			this.module6=module6;
		}
		public MUnread build() throws Exception {
			this.setModule1(data.getInt("module1_"));
			this.setModule2(data.getInt("module2_"));
			this.setModule3(data.getInt("module3_"));
			this.setModule4(data.getInt("module4_"));
			this.setModule5(data.getInt("module5_"));
			this.setModule6(data.getInt("module6_"));
			return this;
		}
		public void build(Bean2Json data) {
			this.setModule1(((MUnread)data).getModule1());
			this.setModule2(((MUnread)data).getModule2());
			this.setModule3(((MUnread)data).getModule3());
			this.setModule4(((MUnread)data).getModule4());
			this.setModule5(((MUnread)data).getModule5());
			this.setModule6(((MUnread)data).getModule6());
		}

	}


}
