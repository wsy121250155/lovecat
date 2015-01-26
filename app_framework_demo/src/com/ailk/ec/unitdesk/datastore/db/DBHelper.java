package com.ailk.ec.unitdesk.datastore.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ailk.ec.unitdesk.R;
import com.ailk.ec.unitdesk.datastore.db.TableField.TableReUser;
import com.ailk.ec.unitdesk.datastore.db.TableField.TableSysAcctConfig;
import com.ailk.ec.unitdesk.datastore.db.TableField.TableUserArea;
import com.ailk.ec.unitdesk.datastore.db.TableField.TableUserStyle;
import com.ailk.ec.unitdesk.datastore.db.TableField.TableWordPosts;
import com.ailk.ec.unitdesk.utils.Log;

/**
 * 数据库操作类
 * 
 * @author spoon
 * 
 */
public class DBHelper extends SQLiteOpenHelper {

	// 打印日志时日志信息中的标识符
	private static final String TAG = "DBHelper";

	// 数据库的名称
	private static final String DATABASE_NAME = "DESKTOP.db";

	// webviewCache.db
	private static final String CACHE_WEBVIEW_DB_NAME = "webviewCache.db";

	// 数据库的版本号，默认情况下是1,初始版本1
	private static final int DATABASE_VERSION = 8;

	// 词帖表
	public static final String TABLE_WORDPOSTS = "table_wordposts";

	// 用户组织表
	public static final String TABLE_USER_ORG = "table_user_org";

	// 账号配置表
	public static final String TABLE_SYS_ACCT_CONFIG = "table_sys_acct_config";
	// 用户地区表
	public static final String TABLE_USER_AREA = "table_user_area";
	// 应用风格表
	public static final String TABLE_USER_STYLE = "table_user_style";
	// cache表
	public static final String TABLE_CACHE_WEBVIEW = "cache";
	// 重复工号表
	public static final String TABLE_RE_USER = "table_re_user";

	// 用来操作的数据库的实例
	SQLiteDatabase db = null;
	// SQLiteDatabase mCacheDb = null;

	private Context ctx;

	/**
	 * 
	 * @param context
	 */
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.ctx = context;

		// mCacheDb = context.openOrCreateDatabase(CACHE_WEBVIEW_DB_NAME, 0,
		// null);

	}

	/**
	 * 数据库在建立的时候执行的操作，主要是新建系统所需的数据表
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.beginTransaction();
		try {

			// 词帖表(v1)
			db.execSQL("create table if not exists " + TABLE_WORDPOSTS
					+ "(id Integer primary key autoincrement,"
					+ TableWordPosts.SCREEN_INDEX + " Integer,"
					+ TableWordPosts.TIME + " Integer," + TableWordPosts.COL
					+ " Integer," + TableWordPosts.ROW + " Integer,"
					+ TableWordPosts.INDEX + " Integer,"
					+ TableWordPosts.COLOR_ID + " Integer,"
					+ TableWordPosts.ICON_ID + " Integer,"
					+ TableWordPosts.TITLE + " text,"
					+ TableWordPosts.DESCRIPTION + " text,"
					+ TableWordPosts.POSTS_ID + " Integer,"
					+ TableWordPosts.TO_URL + " text)");

			// 系统账号配置表(v2)
			db.execSQL("create table if not exists " + TABLE_SYS_ACCT_CONFIG
					+ "(id Integer primary key autoincrement,"
					+ TableSysAcctConfig.SYS_ID + " Integer,"
					+ TableSysAcctConfig.SYS_NAME + " text,"
					+ TableSysAcctConfig.ACCT_NAME + " text,"
					+ TableSysAcctConfig.ACCT_PWD + " text,"
					+ TableSysAcctConfig.SID + " text,"
					+ TableSysAcctConfig.RELA_UNI_ACCT + " text,"
					+ TableSysAcctConfig.CLIENT_URI + " text,"
					+ TableSysAcctConfig.BIND_ACCOUNT_SERVICE_CODE + " text,"
					+ TableSysAcctConfig.APP_DOWNLOAD_ADDRESS + " text,"
					+ TableSysAcctConfig.ICON_NAME + " text)");
			// 用户地区表(v3)
			db.execSQL("create table if not exists " + TABLE_USER_AREA
					+ "(id Integer primary key autoincrement,"
					+ TableUserArea.USER_NAME + " text,"
					+ TableUserArea.AREA_CODE + " text)");
			// 应用风格表
			db.execSQL("create table if not exists " + TABLE_USER_STYLE
					+ "(id Integer primary key autoincrement,"
					+ TableUserStyle.USER_NAME + " text,"
					+ TableUserStyle.STYLE_CODE + " text)");

			// 重复工号表
			db.execSQL("create table if not exists " + TABLE_RE_USER + "("
					+ "id Integer primary key autoincrement,"
					+ TableReUser.USER_NAME + " text)");
			db.setTransactionSuccessful();
			if (!isReUserExistData(db)) {
				try {
					initReUserInsert(db);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
		} finally {
			db.endTransaction();
		}
	}

	/**
	 * 数据库在变动的时候执行的操作，主要是重新建立数据库的时候
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		switch (oldVersion) {
		case 2:
			/**
			 * version1升级到版本version5
			 */
		case 3:
		case 4:
			// upgradeToVersion5(db, oldVersion);
			// break;
		case 5:
		case 6:
		case 7:
			upgradeToLastestVersion(db, oldVersion);
			break;
		default:
			break;
		}
	}

	private void upgradeToLastestVersion(SQLiteDatabase db, int oldVersion) {
		db.beginTransaction();
		try {

			switch (oldVersion) {
			case 2:
			case 3:
			case 4:
			case 7:
				// 重复工号表
				db.execSQL("create table if not exists " + TABLE_RE_USER + "("
						+ "id Integer primary key autoincrement,"
						+ TableReUser.USER_NAME + " text)");
				break;
			default:
				break;
			}
			db.setTransactionSuccessful();
			if (!isReUserExistData(db)) {
				try {
					initReUserInsert(db);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
		} finally {
			db.endTransaction();
		}
	}

	// 判断数据库表中是否有数据
	public boolean isReUserExistData(SQLiteDatabase db) {
		boolean result = false;
		String sql = "select count(*) from " + TABLE_RE_USER + ";";
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.moveToNext()) {
			int count = cursor.getInt(0);
			if (count > 0) {// 存在数据
				result = true;
			}
			cursor.close();
			// db.close();
		}
		return result;
	}

	/**
	 * 通过读取文件，插入初始化数据
	 * 
	 * @param db
	 * @throws IOException
	 */
	public void initReUserInsert(SQLiteDatabase db) throws IOException {
		InputStreamReader reader = new InputStreamReader(ctx.getResources()
				.openRawResource(R.raw.re_user), "utf-8");
		BufferedReader br = new BufferedReader(reader);
		String sql = "";
		// 通过事务，进行批量插入
		// db.beginTransaction();
		while ((sql = br.readLine()) != null) {
			Log.d(TAG, sql);
			db.execSQL(sql);
		}
		db.setTransactionSuccessful();
		// db.endTransaction();
		br.close();
		reader.close();
	}

	/**
	 * 数据库表机构发生变化时调用
	 * 
	 * @param db
	 *            数据库操作对象
	 * @param tableName
	 *            表名
	 * @param colmnDef
	 *            新表列限制条件 如:timestamp text,temp text
	 * @param fromColmns
	 *            需要复制表的字段
	 * @param toColmns
	 *            插入新表中对应的字段
	 */
	protected void onUpgradeColmn(SQLiteDatabase db, String tableName,
			String colmnDef, String fromColmns, String toColmns) {
		db.execSQL("create table if not exists table_test_temp as select * from "
				+ tableName);
		db.execSQL("drop table if exists " + tableName);

		db.execSQL("create table if not exists " + tableName + "(" + colmnDef
				+ ")");
		db.execSQL("insert into " + tableName + " (" + toColmns + ") select "
				+ fromColmns + " from table_test_temp");
		db.execSQL("drop table if exists " + "table_test_temp");
	}

	/**
	 * 插入数据
	 * 
	 * @param tableName
	 *            要插入的表格的名称
	 * @param values
	 *            要插入的数据由数据名称和数据值组成的键值对
	 * 
	 */
	public long insert(String tableName, ContentValues values) {
		db = this.getWritableDatabase();

		db.beginTransaction();

		long id = -1;
		try {
			id = db.insert(tableName, null, values);
			db.setTransactionSuccessful();
		} catch (Exception e) {
			return -1;
		} finally {
			db.endTransaction();
			close(db);
		}
		return id;
	}

	/**
	 * 对数据库中数据的删除操作
	 * 
	 * @param tableName
	 *            删除操作中要操作的数据表的名称
	 * @param whereArgs
	 *            要删除的数据中提供的条件参数的名称
	 * @param whereArgsValues
	 *            要删除的数据中提供的条件参数的值
	 */
	public void delete(String tableName, String[] whereArgs,
			String[] whereArgsValues) {
		db = this.getWritableDatabase();

		db.beginTransaction();
		try {
			// 当传入的参数为空时，表示操作所有的记录
			if (whereArgs == null) {
				db.delete(tableName, null, null);
			} else {
				// 当传入的参数为一个时
				if (whereArgs.length == 1) {
					// 当传入的参数和参数值分别为一个时
					if (whereArgsValues.length == 1) {
						db.delete(tableName, whereArgs[0] + " = ?",
								whereArgsValues);
					}
					// 当传入的参数为一个，参数值为多个时
					else {
						db.execSQL(del_SQL(
								tableName,
								createSQL(whereArgs, whereArgsValues,
										whereArgsValues.length)));
					}
				}
				// 当传入的参数和参数值分别为多个时，并且参数和参数值是一一对应的
				else {
					db.execSQL(del_SQL(
							tableName,
							createSQL(whereArgs, whereArgsValues,
									whereArgs.length)));
				}
			}
			db.setTransactionSuccessful();
		} catch (Exception e) {
		} finally {
			db.endTransaction();
			close(db);
		}
	}

	/**
	 * 将用户提供的参数拼接成一条完整的删除数据库的SQL语句
	 * 
	 * @param tableName
	 *            要操作的表的名称
	 * @param whereArgs
	 *            删除数据的条件参数
	 * @param whereArgsValues
	 *            删除数据的条件参数的值
	 * @param num
	 *            提供的条件中参数的个数
	 * @return 拼接完成后的删除SQL语句
	 */
	private String createSQL(String[] whereArgs, String[] whereArgsValues,
			int length) {
		StringBuffer sql = new StringBuffer(" ");
		// 当传入的参数为一个时
		if (whereArgs.length == 1) {
			for (int i = 0; i < length; i++) {
				sql.append(whereArgs[0] + " = '" + whereArgsValues[i] + "'");
				if (i < length - 1) {
					sql.append(" or ");
				}
			}
		}
		// 当传入的参数和参数值分别为多个时，并且参数和参数值是一一对应的
		else {
			for (int i = 0; i < length; i++) {
				sql.append(whereArgs[i] + " = '" + whereArgsValues[i] + "'");
				if (i < length - 1) {
					sql.append(" and ");
				}
			}
		}
		return sql.toString();
	}

	/**
	 * 生成删除数据的sql语句
	 * 
	 * @param tableName
	 *            要操作的数据表的名称
	 * @param str_sql
	 *            where语句部分
	 * @return
	 */
	private String del_SQL(String tableName, String str_sql) {
		return new StringBuffer("delete from " + tableName + " where "
				+ str_sql).toString();
	}

	/**
	 * 对数据进行的查询操作
	 * 
	 * @param tableName
	 *            需要操作的表名
	 * @return 查找的数据集的指针
	 * @param selection
	 *            查询数据的条件语句
	 */
	public Cursor query(String tableName, String selection) {
		Cursor cursor = null;
		db = this.getReadableDatabase();

		db.beginTransaction();
		try {
			// 当传入的参数为空时，表示操作所有的记录
			cursor = db.query(tableName, null, selection, null, null, null,
					null);
			db.setTransactionSuccessful();
		} catch (Exception e) {
		} finally {
			db.endTransaction();

		}
		return cursor;
	}

	/**
	 * 对数据进行的查询操作
	 * 
	 * @param tableName
	 *            需要操作的表名
	 * @param whereArgs
	 *            要查询的数据中提供的条件参数的名称
	 * @param whereArgsValues
	 *            要查询的数据中提供的条件参数的值
	 * @param column
	 *            控制哪些字段返回结果（传null是返回所有字段的结果集）
	 * @param orderBy
	 *            是否对某一字段进行排序（传null不进行排序）
	 * @return 查找的数据集的指针
	 */
	public Cursor query(String tableName, String[] whereArgs,
			String[] whereArgsValues, String[] column, String orderBy) {
		Cursor cursor = null;
		db = this.getReadableDatabase();

		db.beginTransaction();
		try {
			// 当传入的参数为空时，表示操作所有的记录
			if (whereArgs == null) {
				cursor = db.query(tableName, column, null, null, null, null,
						orderBy);
			} else {
				// 当传入的参数为一个时
				if (whereArgs.length == 1) {
					// 当传入的参数和参数值分别为一个时
					if (whereArgsValues.length == 1) {
						cursor = db.query(tableName, column, whereArgs[0]
								+ "= ?", whereArgsValues, null, null, orderBy);
					}
					// 当传入的参数为一个，参数值为多个时
					else {
						cursor = db.query(
								tableName,
								column,
								createSQL(whereArgs, whereArgsValues,
										whereArgsValues.length), null, null,
								null, orderBy);
					}
				}
				// 当传入的参数和参数值分别为多个时，并且参数和参数值是一一对应的
				else {
					cursor = db.query(
							tableName,
							column,
							createSQL(whereArgs, whereArgsValues,
									whereArgs.length), null, null, null,
							orderBy);
				}
			}
			db.setTransactionSuccessful();
		} catch (Exception e) {
		} finally {
			db.endTransaction();

		}
		return cursor;
	}

	/**
	 * 对数据的更新操作
	 * 
	 * @param tableName
	 *            是所对应的操作表
	 * @param values
	 *            需要更新的数据名称和值组成的键值对
	 * @param whereArgs
	 *            要更新的数据集的条件参数
	 * @param whereArgsValues
	 *            要更新的数据集的条件参数的值
	 */
	public void update(String tableName, ContentValues values,
			String[] whereArgs, String[] whereArgsValues) {
		db = this.getWritableDatabase();

		db.beginTransaction();
		try {
			if (whereArgs == null) {
				db.update(tableName, values, null, null);
			} else {
				// 当传入的参数为一个时
				if (whereArgs.length == 1) {
					// 当传入的参数和参数值分别为一个时
					if (whereArgsValues.length == 1) {
						db.update(tableName, values, whereArgs[0] + "='"
								+ whereArgsValues[0] + "'", null);
					}
					// 当传入的参数为一个，参数值为多个时
					else {
						db.update(
								tableName,
								values,
								createSQL(whereArgs, whereArgsValues,
										whereArgsValues.length), null);
					}
				}
				// 当传入的参数和参数值分别为多个时，并且参数和参数值是一一对应的
				else {
					db.update(
							tableName,
							values,
							createSQL(whereArgs, whereArgsValues,
									whereArgs.length), null);
				}
			}
			db.setTransactionSuccessful();
		} catch (Exception e) {
		} finally {
			db.endTransaction();
			close(db);
		}
	}

	/**
	 * 关闭所有链接中的数据库
	 */
	public void closeDatabase() {
		try {
			close();
		} catch (Exception e) {
		}
	}

	/**
	 * 关闭当前正在使用的数据库
	 * 
	 * @param db
	 *            正处于打开状态的数据库
	 */
	public void close(SQLiteDatabase db) {
		try {
			if (db != null) {
				if (db.isOpen()) {
					db.close();
					db = null;
				}
			}
		} catch (Exception e) {
		} finally {
			if (db != null) {
				db.close();
			}
		}
	}

	// 判断数据库表中是否有数据
	public boolean isAreaExist(SQLiteDatabase db) {
		boolean result = true;
		String sql = "select count(*) from table_area;";
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.moveToNext()) {
			int count = cursor.getInt(0);
			if (count > 0) {// 存在数据
				result = false;
			}
			cursor.close();
			// db.close();
		}
		return result;
	}

	/*
	 * public Cursor selectByUrl(String url) { Cursor cursor = null; // if
	 * (mCacheDb == null) { // mCacheDb =
	 * ctx.openOrCreateDatabase(CACHE_WEBVIEW_DB_NAME, 0, null); // }
	 * mCacheDb.beginTransaction(); try { // 当传入的参数为空时，表示操作所有的记录 cursor =
	 * mCacheDb.query(TABLE_CACHE_WEBVIEW, new String[] { "_id", "url",
	 * "filepath", "lastmodify", "etag", "expires", "mimetype" }, "url='" + url
	 * + "'", null, null, null, "_id desc");
	 * mCacheDb.setTransactionSuccessful(); } catch (Exception e) { } finally {
	 * mCacheDb.endTransaction();
	 * 
	 * } return cursor; }
	 * 
	 * public Cursor selectAllCache() { Cursor cursor = null;
	 * mCacheDb.beginTransaction(); try { // 当传入的参数为空时，表示操作所有的记录 cursor =
	 * mCacheDb.query(TABLE_CACHE_WEBVIEW, new String[] { "_id", "url",
	 * "filepath", "lastmodify", "etag", "expires", "mimetype" }, null, null,
	 * null, null, "_id desc"); mCacheDb.setTransactionSuccessful(); } catch
	 * (Exception e) { } finally { mCacheDb.endTransaction(); } return cursor; }
	 * 
	 * public long insert(String url, String filepath, String lastmodify, String
	 * etag, String expires, String contentlength, String expiresstring, String
	 * mimetype) { mCacheDb.beginTransaction();
	 * 
	 * long id = -1; try { ContentValues contentValues = new ContentValues();
	 * contentValues.put("url", url); contentValues.put("filepath", filepath);
	 * contentValues.put("lastmodify", lastmodify); contentValues.put("etag",
	 * etag); contentValues.put("expires", expires);
	 * contentValues.put("expiresstring", expiresstring);
	 * contentValues.put("contentlength", contentlength);
	 * contentValues.put("mimetype", mimetype); id =
	 * mCacheDb.insert(TABLE_CACHE_WEBVIEW, null, contentValues);
	 * mCacheDb.setTransactionSuccessful(); } catch (Exception e) { return -1; }
	 * finally { mCacheDb.endTransaction(); }
	 * 
	 * return id; }
	 */
}
