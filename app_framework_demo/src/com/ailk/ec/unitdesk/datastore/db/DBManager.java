package com.ailk.ec.unitdesk.datastore.db;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.database.Cursor;

import com.ailk.ec.unitdesk.datastore.CommonApplication;
import com.ailk.ec.unitdesk.datastore.db.TableField.TableReUser;
import com.ailk.ec.unitdesk.datastore.db.TableField.TableSysAcctConfig;
import com.ailk.ec.unitdesk.datastore.db.TableField.TableUserArea;
import com.ailk.ec.unitdesk.datastore.db.TableField.TableUserOrg;
import com.ailk.ec.unitdesk.datastore.db.TableField.TableUserStyle;
import com.ailk.ec.unitdesk.datastore.db.TableField.TableWordPosts;
import com.ailk.ec.unitdesk.models.StyleModel;
import com.ailk.ec.unitdesk.models.desktop.PostsResourceInfo;
import com.ailk.ec.unitdesk.models.desktop.SysAcctInfo;
import com.ailk.ec.unitdesk.models.desktop.UserArea;
import com.ailk.ec.unitdesk.models.desktop.WordPostsInfo;
import com.ailk.ec.unitdesk.utils.StringUtils;

/**
 * 数据持久层封装
 * 
 * @author spoon
 * 
 */
public class DBManager {
	private static DBManager dbManager;

	public DBHelper dbHelp;
	private Object syncObject = new Object();

	private DBManager() {
		dbHelp = new DBHelper(CommonApplication.getInstance()
				.getApplicationContext());
	}

	public synchronized static DBManager getInstance() {
		if (dbManager == null) {
			dbManager = new DBManager();
		}
		return dbManager;
	}

	/**
	 * 保存词帖
	 * 
	 * @param postsList
	 */
	public void saveWordPosts(ArrayList<WordPostsInfo> postsList) {
		if (postsList == null || postsList.size() < 1) {
			return;
		}
		synchronized (syncObject) {
			for (WordPostsInfo posts : postsList) {
				ContentValues values = new ContentValues();
				values.put(TableWordPosts.SCREEN_INDEX, posts.screenIndex);
				values.put(TableWordPosts.TIME, posts.time);
				values.put(TableWordPosts.COL, posts.col);
				values.put(TableWordPosts.ROW, posts.row);
				values.put(TableWordPosts.INDEX, posts.index);
				// values.put(TableWordPosts.COLOR_ID, posts.colorId);
				// values.put(TableWordPosts.ICON_ID, posts.iconId);
				values.put(TableWordPosts.TITLE, posts.title);
				values.put(TableWordPosts.DESCRIPTION, posts.description);
				values.put(TableWordPosts.POSTS_ID, posts.postsId);
				values.put(TableWordPosts.TO_URL, posts.toUrl);
				dbHelp.insert(DBHelper.TABLE_WORDPOSTS, values);
			}
		}
	}

	public void saveWordPosts(WordPostsInfo posts) {
		if (posts == null) {
			return;
		}
		synchronized (syncObject) {
			ContentValues values = new ContentValues();
			values.put(TableWordPosts.SCREEN_INDEX, posts.screenIndex);
			values.put(TableWordPosts.TIME, posts.time);
			values.put(TableWordPosts.COL, posts.col);
			values.put(TableWordPosts.ROW, posts.row);
			values.put(TableWordPosts.INDEX, posts.index);
			// values.put(TableWordPosts.COLOR_ID, posts.colorId);
			// values.put(TableWordPosts.ICON_ID, posts.iconId);
			values.put(TableWordPosts.TITLE, posts.title);
			values.put(TableWordPosts.DESCRIPTION, posts.description);
			values.put(TableWordPosts.POSTS_ID, posts.postsId);
			values.put(TableWordPosts.TO_URL, posts.toUrl);
			dbHelp.insert(DBHelper.TABLE_WORDPOSTS, values);
		}
	}

	/**
	 * 获取历史词帖
	 * 
	 * @return
	 */
	public ArrayList<WordPostsInfo> obtainWordPostsList(
			HashMap<Integer, PostsResourceInfo> postsResIdMap) {
		ArrayList<WordPostsInfo> postsList = new ArrayList<WordPostsInfo>();
		WordPostsInfo posts = null;
		Cursor cursor = null;
		synchronized (syncObject) {
			try {
				cursor = dbHelp.query(DBHelper.TABLE_WORDPOSTS, null);
				if (cursor != null && cursor.getCount() > 0) {
					cursor.moveToFirst();
					do {
						posts = new WordPostsInfo();
						posts.index = cursor.getInt(cursor
								.getColumnIndex(TableWordPosts.SCREEN_INDEX));
						posts.time = cursor.getInt(cursor
								.getColumnIndex(TableWordPosts.TIME));
						posts.col = cursor.getInt(cursor
								.getColumnIndex(TableWordPosts.COL));
						posts.row = cursor.getInt(cursor
								.getColumnIndex(TableWordPosts.ROW));
						posts.index = cursor.getInt(cursor
								.getColumnIndex(TableWordPosts.INDEX));
						posts.title = cursor.getString(cursor
								.getColumnIndex(TableWordPosts.TITLE));
						posts.description = cursor.getString(cursor
								.getColumnIndex(TableWordPosts.DESCRIPTION));
						posts.toUrl = cursor.getString(cursor
								.getColumnIndex(TableWordPosts.TO_URL));
						posts.postsId = cursor.getInt(cursor
								.getColumnIndex(TableWordPosts.POSTS_ID));
						if (postsResIdMap != null
								&& postsResIdMap.containsKey(posts.postsId)) {
							posts.colorId = postsResIdMap.get(posts.postsId).postsColorId;
							posts.iconId = postsResIdMap.get(posts.postsId).postsIconId;
							posts.toUrl = postsResIdMap.get(posts.postsId).toUrl;
							posts.currentUrl = postsResIdMap.get(posts.postsId).currentUrl;
							posts.iconId2 = postsResIdMap.get(posts.postsId).postsIconId2;
							posts.iconId3 = postsResIdMap.get(posts.postsId).postsIconId3;
							posts.title2 = postsResIdMap.get(posts.postsId).title2;
							posts.title3 = postsResIdMap.get(posts.postsId).title3;
						}
						postsList.add(posts);
					} while (cursor.moveToNext());
				}
			} finally {
				if (cursor != null) {
					cursor.close();
				}
			}
		}
		return postsList;
	}

	public void removeWordPosts(WordPostsInfo posts) {

		synchronized (syncObject) {
			dbHelp.delete(
					DBHelper.TABLE_WORDPOSTS,
					new String[] { TableWordPosts.COL, TableWordPosts.ROW,
							TableWordPosts.TITLE },
					new String[] { String.valueOf(posts.col),
							String.valueOf(posts.row),
							String.valueOf(posts.title) });
		}
	}

	public void removeAllWordPosts() {

		synchronized (syncObject) {
			dbHelp.delete(DBHelper.TABLE_WORDPOSTS, null, null);
		}
	}

	public String qryOrgIdByUserName(String username) {
		String orgId = "";
		Cursor cursor = null;
		synchronized (syncObject) {
			try {
				cursor = dbHelp.query(DBHelper.TABLE_USER_ORG,
						TableUserOrg.USER_NAME + "=" + username);
				if (cursor != null && cursor.getCount() > 0) {
					cursor.moveToFirst();
					do {
						orgId = cursor.getString(cursor
								.getColumnIndex(TableUserOrg.ORG_ID));
					} while (cursor.moveToNext());
				}
			} finally {
				if (cursor != null) {
					cursor.close();
				}
			}
		}
		return orgId;
	}

	/**
	 * 获取统一桌面配置账号列表
	 * 
	 * @return
	 */
	public ArrayList<SysAcctInfo> obtainConfigList(String userName) {
		ArrayList<SysAcctInfo> acctList = new ArrayList<SysAcctInfo>();
		SysAcctInfo acct = null;
		Cursor cursor = null;
		synchronized (syncObject) {
			try {
				cursor = dbHelp.query(DBHelper.TABLE_SYS_ACCT_CONFIG,
						TableSysAcctConfig.SYS_ID + " != "
								+ SysAcctInfo.UN_DESK + " and "
								+ TableSysAcctConfig.RELA_UNI_ACCT + " = '"
								+ userName + "'");
				if (cursor != null && cursor.getCount() > 0) {
					cursor.moveToFirst();
					do {
						acct = new SysAcctInfo();
						acct.sysId = cursor.getInt(cursor
								.getColumnIndex(TableSysAcctConfig.SYS_ID));
						acct.sysName = cursor.getString(cursor
								.getColumnIndex(TableSysAcctConfig.SYS_NAME));
						acct.acctName = cursor.getString(cursor
								.getColumnIndex(TableSysAcctConfig.ACCT_NAME));
						acct.acctPwd = cursor.getString(cursor
								.getColumnIndex(TableSysAcctConfig.ACCT_PWD));
						acct.relaUniAcct = cursor
								.getString(cursor
										.getColumnIndex(TableSysAcctConfig.RELA_UNI_ACCT));
						acct.clientUri = cursor.getString(cursor
								.getColumnIndex(TableSysAcctConfig.CLIENT_URI));
						acct.bindAccountServiceCode = cursor
								.getString(cursor
										.getColumnIndex(TableSysAcctConfig.BIND_ACCOUNT_SERVICE_CODE));
						acct.appDownloadAddress = cursor
								.getString(cursor
										.getColumnIndex(TableSysAcctConfig.APP_DOWNLOAD_ADDRESS));
						acct.iconName = cursor.getString(cursor
								.getColumnIndex(TableSysAcctConfig.ICON_NAME));
						acctList.add(acct);
					} while (cursor.moveToNext());
				}
			} finally {
				if (cursor != null) {
					cursor.close();
				}
			}
		}
		return acctList;
	}

	/**
	 * 保存账号配置
	 * 
	 * @param info
	 */
	public void saveSysAcctConfig(SysAcctInfo info) {

		if (info == null) {
			return;
		}
		removeSysAcctBySysId(String.valueOf(info.sysId), info.relaUniAcct,
				info.clientUri);
		synchronized (syncObject) {
			ContentValues values = new ContentValues();
			values.put(TableSysAcctConfig.SYS_ID, info.sysId);
			values.put(TableSysAcctConfig.SYS_NAME, info.sysName);
			values.put(TableSysAcctConfig.ACCT_NAME, info.acctName);
			values.put(TableSysAcctConfig.ACCT_PWD, info.acctPwd);
			values.put(TableSysAcctConfig.RELA_UNI_ACCT, info.relaUniAcct);
			if (!StringUtils.isEmpty(info.sid)) {
				values.put(TableSysAcctConfig.SID, info.sid);
			}
			if (!StringUtils.isEmpty(info.clientUri)) {
				values.put(TableSysAcctConfig.CLIENT_URI, info.clientUri);
			}
			if (!StringUtils.isEmpty(info.bindAccountServiceCode)) {
				values.put(TableSysAcctConfig.BIND_ACCOUNT_SERVICE_CODE,
						info.bindAccountServiceCode);
			}
			if (!StringUtils.isEmpty(info.appDownloadAddress)) {
				values.put(TableSysAcctConfig.APP_DOWNLOAD_ADDRESS,
						info.appDownloadAddress);
			}
			if (!StringUtils.isEmpty(info.iconName)) {
				values.put(TableSysAcctConfig.ICON_NAME, info.iconName);
			}
			dbHelp.insert(DBHelper.TABLE_SYS_ACCT_CONFIG, values);
		}
	}

	/**
	 * 更新账号配置
	 * 
	 * @param info
	 */
	public void modifySysAcctConfig(SysAcctInfo info) {

		if (info == null) {
			return;
		}
		synchronized (syncObject) {
			ContentValues values = new ContentValues();
			values.put(TableSysAcctConfig.ACCT_NAME, info.acctName);
			values.put(TableSysAcctConfig.ACCT_PWD, info.acctPwd);
			if (!StringUtils.isEmpty(info.sid)) {
				values.put(TableSysAcctConfig.SID, info.sid);
			}
			dbHelp.update(DBHelper.TABLE_SYS_ACCT_CONFIG, values,
					new String[] { TableSysAcctConfig.SYS_ID },
					new String[] { String.valueOf(info.sysId) });
		}
	}

	/**
	 * 删除指定系统的账号
	 * 
	 * @param sysId
	 */
	public void removeSysAcctBySysId(String sysId, String userName,
			String clientUri) {
		synchronized (syncObject) {
			if (!StringUtils.isEmpty(sysId)) {
				dbHelp.delete(DBHelper.TABLE_SYS_ACCT_CONFIG, new String[] {
						TableSysAcctConfig.SYS_ID,
						TableSysAcctConfig.RELA_UNI_ACCT }, new String[] {
						sysId, userName });
			} else if (!StringUtils.isEmpty(clientUri)) {
				dbHelp.delete(DBHelper.TABLE_SYS_ACCT_CONFIG, new String[] {
						TableSysAcctConfig.CLIENT_URI,
						TableSysAcctConfig.RELA_UNI_ACCT }, new String[] {
						clientUri, userName });
			}
		}
	}

	public SysAcctInfo qryAcctBySysId(int sysId, String userName) {
		SysAcctInfo acct = null;
		Cursor cursor = null;
		synchronized (syncObject) {
			try {
				cursor = dbHelp.query(DBHelper.TABLE_SYS_ACCT_CONFIG,
						TableSysAcctConfig.SYS_ID + " = " + sysId + " and "
								+ TableSysAcctConfig.RELA_UNI_ACCT + " = '"
								+ userName + "'");
				if (cursor != null && cursor.getCount() > 0) {
					cursor.moveToFirst();
					do {
						acct = new SysAcctInfo();
						acct.sysId = cursor.getInt(cursor
								.getColumnIndex(TableSysAcctConfig.SYS_ID));
						acct.sysName = cursor.getString(cursor
								.getColumnIndex(TableSysAcctConfig.SYS_NAME));
						acct.acctName = cursor.getString(cursor
								.getColumnIndex(TableSysAcctConfig.ACCT_NAME));
						acct.acctPwd = cursor.getString(cursor
								.getColumnIndex(TableSysAcctConfig.ACCT_PWD));
						acct.sid = cursor.getString(cursor
								.getColumnIndex(TableSysAcctConfig.SID));
						acct.relaUniAcct = cursor
								.getString(cursor
										.getColumnIndex(TableSysAcctConfig.RELA_UNI_ACCT));
						acct.clientUri = cursor.getString(cursor
								.getColumnIndex(TableSysAcctConfig.CLIENT_URI));
						acct.bindAccountServiceCode = cursor
								.getString(cursor
										.getColumnIndex(TableSysAcctConfig.BIND_ACCOUNT_SERVICE_CODE));
						acct.appDownloadAddress = cursor
								.getString(cursor
										.getColumnIndex(TableSysAcctConfig.APP_DOWNLOAD_ADDRESS));
						acct.iconName = cursor.getString(cursor
								.getColumnIndex(TableSysAcctConfig.ICON_NAME));
					} while (cursor.moveToNext());
				}
			} finally {
				if (cursor != null) {
					cursor.close();
				}
			}
		}
		return acct;
	}

	public SysAcctInfo qryAcctBySysUri(String clientUri, String userName) {
		SysAcctInfo acct = null;
		Cursor cursor = null;
		synchronized (syncObject) {
			try {
				cursor = dbHelp.query(DBHelper.TABLE_SYS_ACCT_CONFIG,
						TableSysAcctConfig.CLIENT_URI + " = '" + clientUri
								+ "' and " + TableSysAcctConfig.RELA_UNI_ACCT
								+ " = '" + userName + "'");
				if (cursor != null && cursor.getCount() > 0) {
					cursor.moveToFirst();
					do {
						acct = new SysAcctInfo();
						acct.sysId = cursor.getInt(cursor
								.getColumnIndex(TableSysAcctConfig.SYS_ID));
						acct.sysName = cursor.getString(cursor
								.getColumnIndex(TableSysAcctConfig.SYS_NAME));
						acct.acctName = cursor.getString(cursor
								.getColumnIndex(TableSysAcctConfig.ACCT_NAME));
						acct.acctPwd = cursor.getString(cursor
								.getColumnIndex(TableSysAcctConfig.ACCT_PWD));
						acct.sid = cursor.getString(cursor
								.getColumnIndex(TableSysAcctConfig.SID));
						acct.relaUniAcct = cursor
								.getString(cursor
										.getColumnIndex(TableSysAcctConfig.RELA_UNI_ACCT));
						acct.clientUri = cursor.getString(cursor
								.getColumnIndex(TableSysAcctConfig.CLIENT_URI));
						acct.bindAccountServiceCode = cursor
								.getString(cursor
										.getColumnIndex(TableSysAcctConfig.BIND_ACCOUNT_SERVICE_CODE));
						acct.appDownloadAddress = cursor
								.getString(cursor
										.getColumnIndex(TableSysAcctConfig.APP_DOWNLOAD_ADDRESS));
						acct.iconName = cursor.getString(cursor
								.getColumnIndex(TableSysAcctConfig.ICON_NAME));
					} while (cursor.moveToNext());
				}
			} finally {
				if (cursor != null) {
					cursor.close();
				}
			}
		}
		return acct;
	}

	/**
	 * 查询所有数据
	 * 
	 * @return Cursor
	 */
	/*
	 * public Cursor selectByUrl(String url) { return dbHelp.selectByUrl(url); }
	 *//**
	 * 查询所有缓存
	 * 
	 * @return
	 */
	/*
	 * public Cursor selectAllCache() { ArrayList<String> urls = new
	 * ArrayList<String>(); return dbHelp.selectAllCache(); }
	 * 
	 * public long insert(String url, String filepath, String lastmodify, String
	 * etag, String expires, String contentlength, String expiresstring, String
	 * mimetype) {
	 * 
	 * return dbHelp.insert(url, filepath, lastmodify, etag, expires,
	 * contentlength, expiresstring, mimetype); }
	 */

	/**
	 * 保存用户当前地区
	 * 
	 * @param userArea
	 */
	public void saveUserArea(UserArea userArea) {
		if (userArea == null) {
			return;
		}
		removeUserArea(userArea.userName);
		synchronized (syncObject) {
			ContentValues values = new ContentValues();
			values.put(TableUserArea.USER_NAME, userArea.userName.toLowerCase());
			values.put(TableUserArea.AREA_CODE, userArea.areaCode);
			dbHelp.insert(DBHelper.TABLE_USER_AREA, values);
		}
	}

	/**
	 * 根据用户名查登录地区
	 * 
	 * @param username
	 * @return
	 */
	public String qryAreaByUserName(String username) {
		String areaCode = "";
		Cursor cursor = null;
		synchronized (syncObject) {
			try {
				cursor = dbHelp.query(
						DBHelper.TABLE_USER_AREA,
						TableUserArea.USER_NAME + " = '"
								+ username.toLowerCase() + "'");
				if (cursor != null && cursor.getCount() > 0) {
					cursor.moveToFirst();
					do {
						areaCode = cursor.getString(cursor
								.getColumnIndex(TableUserArea.AREA_CODE));
					} while (cursor.moveToNext());
				}
			} finally {
				if (cursor != null) {
					cursor.close();
				}
			}
		}
		return areaCode;
	}

	/**
	 * 删除用户地区
	 */
	public void removeUserArea(String userName) {

		synchronized (syncObject) {
			dbHelp.delete(DBHelper.TABLE_USER_AREA,
					new String[] { TableUserArea.USER_NAME },
					new String[] { userName.toLowerCase() });
		}
	}

	/**
	 * 保存应用当前风格
	 * 
	 * @param styleModel
	 */
	public void saveUserStyle(StyleModel styleModel) {
		if (styleModel == null) {
			return;
		}
		removeUserStyle(styleModel.userName);
		synchronized (syncObject) {
			ContentValues values = new ContentValues();
			values.put(TableUserStyle.USER_NAME,
					styleModel.userName.toLowerCase());
			values.put(TableUserStyle.STYLE_CODE, styleModel.styleCode);
			dbHelp.insert(DBHelper.TABLE_USER_STYLE, values);
		}
	}

	/**
	 * 根据用户名查应用风格
	 * 
	 * @param username
	 * @return
	 */
	public int qryStyleByUserName(String username) {
		int styleCode = 0;
		Cursor cursor = null;
		synchronized (syncObject) {
			try {
				cursor = dbHelp.query(
						DBHelper.TABLE_USER_STYLE,
						TableUserStyle.USER_NAME + " = '"
								+ username.toLowerCase() + "'");
				if (cursor != null && cursor.getCount() > 0) {
					cursor.moveToFirst();
					do {
						styleCode = cursor.getInt(cursor
								.getColumnIndex(TableUserStyle.STYLE_CODE));
					} while (cursor.moveToNext());
				}
			} finally {
				if (cursor != null) {
					cursor.close();
				}
			}
		}
		return styleCode;
	}

	/**
	 * 删除应用风格
	 */
	public void removeUserStyle(String userName) {

		synchronized (syncObject) {
			dbHelp.delete(DBHelper.TABLE_USER_STYLE,
					new String[] { TableUserStyle.USER_NAME },
					new String[] { userName.toLowerCase() });
		}
	}

	public boolean isReUser(String username) {
		boolean result = false;
		Cursor cursor = null;
		synchronized (syncObject) {
			try {
				cursor = dbHelp.query(DBHelper.TABLE_RE_USER,
						TableReUser.USER_NAME + "='" + username + "'");
				if (cursor != null && cursor.moveToNext()) {
					int count = cursor.getInt(0);
					if (count > 0) {
						result = true;
					}
				}
			} finally {
				if (cursor != null) {
					cursor.close();
				}
			}
		}
		return result;
	}

}