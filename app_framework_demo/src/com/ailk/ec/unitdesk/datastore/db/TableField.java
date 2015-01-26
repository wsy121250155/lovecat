package com.ailk.ec.unitdesk.datastore.db;

/**
 * 数据库表字段
 * 
 * @author spoon
 * 
 */
public class TableField {

	/**
	 * 词帖表字段
	 * 
	 * @author spoon
	 * 
	 */
	public static class TableWordPosts {

		// 屏幕下标
		public static final String SCREEN_INDEX = "screen_index";
		// 大小倍率
		public static final String TIME = "time";

		// 列下标(x)
		public static final String COL = "col";

		// 行下标(y)
		public static final String ROW = "row";

		// 在整个词帖队列中下标
		public static final String INDEX = "table_index";

		// 词帖背景色资源ID
		public static final String COLOR_ID = "colorId";

		// 帖子icon资源ID
		public static final String ICON_ID = "iconId";

		// 点击词帖跳转的url
		public static final String TO_URL = "toUrl";

		// 词帖标题
		public static final String TITLE = "title";

		// 词帖描述
		public static final String DESCRIPTION = "description";

		public static final String POSTS_ID = "postsId";
	}

	/**
	 * 统一桌面账号配置表
	 * 
	 * @author admini
	 * 
	 */
	public static class TableSysAcctConfig {

		/**
		 * 系统id
		 */
		public static final String SYS_ID = "sys_id";

		/**
		 * 系统名称
		 */
		public static final String SYS_NAME = "sys_name";

		/**
		 * 账号
		 */
		public static final String ACCT_NAME = "acct_name";

		/**
		 * 密码
		 */
		public static final String ACCT_PWD = "acct_pwd";

		/**
		 * 关联的统一桌面账号
		 */
		public static final String RELA_UNI_ACCT = "rela_uni_acct";

		/**
		 * sid
		 */
		public static final String SID = "sid";

		/**
		 * clientUri
		 */
		public static final String CLIENT_URI = "client_uri";

		/**
		 * bindAccountServiceCode
		 */
		public static final String BIND_ACCOUNT_SERVICE_CODE = "bind_account_servicecode";

		/**
		 * appDownloadAddress
		 */
		public static final String APP_DOWNLOAD_ADDRESS = "app_download_address";

		/**
		 * iconName
		 */
		public static final String ICON_NAME = "icon_name";

	}

	/**
	 * 用户组织表(实时查询接口需要)
	 * 
	 * @author admini
	 * 
	 */
	public static class TableUserOrg {

		/**
		 * 用户名(全小写)
		 */
		public static final String USER_NAME = "user_name";

		/**
		 * 组织id
		 */
		public static final String ORG_ID = "org_id";

	}

	/**
	 * 用户地区表
	 * 
	 * @author admini
	 * 
	 */
	public static class TableUserArea {

		/**
		 * 用户名(全小写)
		 */
		public static final String USER_NAME = "user_name";

		/**
		 * 用户地区
		 */
		public static final String AREA_CODE = "area_code";

	}

	/**
	 * 应用风格
	 * 
	 * @author Spoon
	 * @date 2014-5-6
	 * @version 1.0
	 */
	public static class TableUserStyle {

		/**
		 * 用户名(全小写)
		 */
		public static final String USER_NAME = "user_name";

		/**
		 * 应用风格
		 */
		public static final String STYLE_CODE = "style_code";

	}

	/**
	 * 重复工号
	 * 
	 * @author Spoon
	 * @date 2014年7月23日
	 * @version 1.0
	 */
	public static class TableReUser {
		// 工号
		public static final String USER_NAME = "user_name";

	}

}
