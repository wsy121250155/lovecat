/**
 * 
 */
package com.ailk.ec.unitdesk.net;

import java.util.Map;

import android.os.Handler;

import com.ailk.ec.unitdesk.models.http.param.SmsParam;
import com.ailk.ec.unitdesk.net.logic.AddCategoryRequest;
import com.ailk.ec.unitdesk.net.logic.AddFuncTempLogRequest;
import com.ailk.ec.unitdesk.net.logic.AddGroupInstRequest;
import com.ailk.ec.unitdesk.net.logic.AddPushLogRequest;
import com.ailk.ec.unitdesk.net.logic.ApplyPushRequest;
import com.ailk.ec.unitdesk.net.logic.BindingAcctRequest;
import com.ailk.ec.unitdesk.net.logic.DelCategoryRequest;
import com.ailk.ec.unitdesk.net.logic.DelGroupRequest;
import com.ailk.ec.unitdesk.net.logic.DelInstRequest;
import com.ailk.ec.unitdesk.net.logic.EmVerifyNumPwdRequest;
import com.ailk.ec.unitdesk.net.logic.ErrorLogRequest;
import com.ailk.ec.unitdesk.net.logic.GetMsgNumRequest;
import com.ailk.ec.unitdesk.net.logic.GetNtNumRequest;
import com.ailk.ec.unitdesk.net.logic.GetTemplateListRequest;
import com.ailk.ec.unitdesk.net.logic.GetTodoNumRequest;
import com.ailk.ec.unitdesk.net.logic.GetTokenRequest;
import com.ailk.ec.unitdesk.net.logic.GetUATicketRequest;
import com.ailk.ec.unitdesk.net.logic.GetUnameRequest;
import com.ailk.ec.unitdesk.net.logic.GetUserAreaRequest;
import com.ailk.ec.unitdesk.net.logic.ModifyPwdRequest;
import com.ailk.ec.unitdesk.net.logic.PortalLoginVerifyRequest;
import com.ailk.ec.unitdesk.net.logic.QueryAllPostsRequest;
import com.ailk.ec.unitdesk.net.logic.QueryPushInfoRequest;
import com.ailk.ec.unitdesk.net.logic.QueryTaskListRequest;
import com.ailk.ec.unitdesk.net.logic.QueryTempListRequest;
import com.ailk.ec.unitdesk.net.logic.QueryTempTypeRequest;
import com.ailk.ec.unitdesk.net.logic.QueryWeAgentTaskNumRq;
import com.ailk.ec.unitdesk.net.logic.ResetPwd1Request;
import com.ailk.ec.unitdesk.net.logic.ResetPwd2Request;
import com.ailk.ec.unitdesk.net.logic.SaveUserKeyRequest;
import com.ailk.ec.unitdesk.net.logic.SearchFuncTempRequest;
import com.ailk.ec.unitdesk.net.logic.SendSMSRequest;
import com.ailk.ec.unitdesk.net.logic.SimplePortalRequest;
import com.ailk.ec.unitdesk.net.logic.UpdateCategoryRequest;
import com.ailk.ec.unitdesk.net.logic.UpdateGroupRequest;
import com.ailk.ec.unitdesk.net.logic.UpdateInstRequest;
import com.ailk.ec.unitdesk.net.logic.UpdateOrDelCFGRequest;
import com.ailk.ec.unitdesk.net.logic.UpdatePageStyleRequest;
import com.ailk.ec.unitdesk.net.logic.UpgradeRequest;
import com.ailk.ec.unitdesk.net.logic.UserLoginLogRequest;
import com.ailk.ec.unitdesk.net.logic.VisitTemplateLogRequest;

/**
 * 接口调用逻辑层
 * 
 * @author spoon
 * 
 */
public class ApiClient {

	/**
	 * 拼装url
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	private static String makeURL(String url, Map<String, Object> params) {
		StringBuilder mUrl = new StringBuilder(url);
		if (mUrl.indexOf("?") < 0)
			mUrl.append('?');

		for (String name : params.keySet()) {
			mUrl.append('&');
			mUrl.append(name);
			mUrl.append('=');
			mUrl.append(String.valueOf(params.get(name)));
		}

		return url.toString().replace("?&", "?");
	}

	/**
	 * 获取UATicket
	 * 
	 * @param handler
	 * @param wwhat
	 * @param slacct
	 * @param systemId
	 * @param areaId
	 * @param imageCode
	 * @param pwd
	 * @param areaId
	 * @param url
	 */
	public static void getUATicket(Handler handler, int wwhat, String Pracct,
			String systemId, String imageCode, String Pwd, String areaCode,
			String url) {
		new GetUATicketRequest(handler, wwhat, Pracct, systemId, imageCode,
				Pwd, areaCode, url).sendGetRequest();
	}

	/**
	 * 
	 * @param handler
	 * @param wwhat
	 * @param Pracct
	 * @param systemId
	 * @param imageCode
	 * @param Pwd
	 * @param url
	 */
	public static void getUATicket(Handler handler, int wwhat, String Pracct,
			String systemId, String imageCode, String Pwd, String url) {
		new GetUATicketRequest(handler, wwhat, Pracct, systemId, imageCode,
				Pwd, null, url).sendGetRequest();
	}

	/**
	 * 获取token
	 * 
	 * @param handler
	 * @param wwhat
	 * @param uATicket
	 * @param url
	 */
	public static void getToken(Handler handler, int wwhat, String uATicket,
			String areaCode, String actionCode) {
		new GetTokenRequest(handler, wwhat, uATicket, areaCode, actionCode)
				.sendPostRequest();
	}

	/**
	 * 获取桌面所有的磁铁
	 * 
	 * @param handler
	 * @param wwhat
	 * @param userId
	 * @param url
	 */
	public static void getAllPosts(Handler handler, int wwhat, String userId,
			String areaCode, String deviceType, String actionCode) {
		// new GetAllPostsRequest(handler, wwhat, userId, areaCode, deviceType,
		// url).sendPostRequest();
		new QueryAllPostsRequest(handler, wwhat, userId, areaCode, deviceType,
				actionCode).sendPostRequest();
	}

	/**
	 * 获取分类所有的磁铁
	 * 
	 * @param handler
	 * @param wwhat
	 * @param userId
	 * @param areaCode
	 * @param deviceType
	 * @param cateId
	 * @param actionCode
	 */
	public static void getPostsByCateId(Handler handler, int wwhat,
			String userId, String areaCode, String deviceType, long cateId,
			String actionCode) {
		new QueryAllPostsRequest(handler, wwhat, userId, areaCode, deviceType,
				cateId, actionCode).sendPostRequest();
	}

	/**
	 * /** 添加分类
	 * 
	 * @param handler
	 * @param wwhat
	 * @param userId
	 * @param areaCode
	 * @param deviceType
	 * @param strCategory
	 * @param url
	 */
	public static void addCategory(Handler handler, int wwhat, String userId,
			String areaCode, String deviceType, String strCategory,
			String actionCode) {
		new AddCategoryRequest(handler, wwhat, userId, areaCode, deviceType,
				strCategory, actionCode).sendPostRequest();

	}

	/**
	 * 查询代办任务数量
	 * 
	 * @param handler
	 * @param wwhat
	 * @param opera
	 */
	public static void queryTodoThingNum(Handler handler, int wwhat,
			String Uname, String statusStr, String taskTypeIdStr,
			String newRecordCount, long instId, String actionCode) {
		// new GetTodoNumRequest(handler, wwhat, Pname1, instId, url)
		// .sendGetRequest();
		new QueryTaskListRequest(handler, wwhat, Uname, statusStr,
				taskTypeIdStr, newRecordCount, instId, actionCode)
				.sendPostRequest();

	}

	/**
	 * 任务列表
	 * 
	 * @param handler
	 * @param wwhat
	 * @param Pname1
	 * @param statusStr
	 * @param taskTypeIdStr
	 * @param newRecordCount
	 * @param instId
	 * @param actionCode
	 */
	public static void queryTaskList(Handler handler, int wwhat, String Uname,
			String statusStr, String taskTypeIdStr, String newRecordCount,
			long instId, String actionCode) {
		new QueryTaskListRequest(handler, wwhat, Uname, statusStr,
				taskTypeIdStr, newRecordCount, instId, actionCode)
				.sendPostRequest();
	}

	/**
	 * 查询公告数量
	 * 
	 * @param handler
	 * @param wwhat
	 * @param pracct
	 *            用户名
	 */
	public static void queryNtNum(Handler handler, int wwhat, String pracct,
			long instId, String action) {
		new GetNtNumRequest(handler, wwhat, pracct, instId, action)
				.sendPostRequest();
	}

	/**
	 * 查询未读消息数量
	 * 
	 * @param handler
	 * @param wwhat
	 * @param useracct
	 *            oa用户名
	 * @param areacode
	 *            地区编码
	 */
	public static void queryMsgNum(Handler handler, int wwhat, String Uname,
			long instId, String action) {
		new GetMsgNumRequest(handler, wwhat, Uname, instId, action)
				.sendPostRequest();
	}

	/**
	 * 绑定OA账号
	 * 
	 * @param handler
	 * @param wwhat
	 * @param username
	 * @param password
	 * @param actionCode
	 * @param url
	 */
	public static void bindingOA(Handler handler, int wwhat, String username,
			String password, String actionCode) {
		new PortalLoginVerifyRequest(handler, wwhat, username, password,
				actionCode).sendPostRequest();
	}

	/**
	 * 查询微代理可接受任务数量
	 * 
	 * @param handler
	 * @param wwhat
	 * @param userName
	 *            微代理用户名
	 */
	public static void queryWeAgentTaskNum(Handler handler, int wwhat,
			String userName, long instId, String action) {
		new QueryWeAgentTaskNumRq(handler, wwhat, userName, instId, action)
				.sendPostRequest();

	}

	/**
	 * 查询邮件数量
	 * 
	 * @param handler
	 * @param wwhat
	 * @param sid
	 *            oa Sid
	 */
	public static void GetMailNumRequest(Handler handler, int wwhat,
			String sid, long instId, String action) {
		new com.ailk.ec.unitdesk.net.logic.GetMailNumRequest(handler, wwhat,
				sid, instId, action).sendPostRequest();

	}

	/**
	 * 查询邮件列表
	 * 
	 * @param handler
	 * @param wwhat
	 * @param sid
	 *            oa Sid
	 */
	public static void GetMailListRequest(Handler handler, int wwhat,
			String sid, String pageSize, String action) {
		new com.ailk.ec.unitdesk.net.logic.GetMailListRequest(handler, wwhat,
				sid, pageSize, action).sendPostRequest();

	}

	/**
	 * 删除分类
	 * 
	 * @param handler
	 * @param wwhat
	 * @param categoryId
	 * @param deviceType
	 * @param areaCode
	 * @param url
	 */
	public static void delCategory(Handler handler, int wwhat, String userId,
			String deviceType, String areaCode, String ArrCategoryForDelete,
			String url) {
		new DelCategoryRequest(handler, wwhat, userId, areaCode, deviceType,
				ArrCategoryForDelete, url).sendPostRequest();
	}

	/**
	 * 更新分类
	 * 
	 * @param handler
	 * @param wwhat
	 * @param userId
	 * @param categoryId
	 * @param deviceType
	 * @param areaCode
	 * @param ArrCategoryForUpdate
	 * @param url
	 */
	public static void updateCategory(Handler handler, int wwhat,
			String userId, String deviceType, String areaCode,
			String ArrCategoryForUpdate, String url) {
		new UpdateCategoryRequest(handler, wwhat, userId, areaCode, deviceType,
				ArrCategoryForUpdate, url).sendPostRequest();
	}

	/**
	 * 获取模板列表
	 * 
	 * @param handler
	 * @param wwhat
	 * @param deviceType
	 * @param areaCode
	 * @param url
	 */
	public static void getTemplateList(Handler handler, int wwhat,
			String userId, String deviceType, String areaCode, String url) {
		new GetTemplateListRequest(handler, wwhat, userId, areaCode,
				deviceType, url).sendGetRequest();
	}

	/**
	 * 获取模板类别
	 * 
	 * @param handler
	 * @param wwhat
	 * @param userId
	 * @param deviceType
	 * @param areaCode
	 * @param actionCode
	 */
	public static void getTemplateType(Handler handler, int wwhat,
			String userId, String deviceType, String areaCode, String actionCode) {
		new QueryTempTypeRequest(handler, wwhat, userId, areaCode, deviceType,
				actionCode).sendPostRequest();
	}

	/**
	 * 获取模板实例列表
	 * 
	 * @param handler
	 * @param wwhat
	 * @param userId
	 * @param deviceType
	 * @param areaCode
	 * @param tempTypeId
	 * @param actionCode
	 */
	public static void getTemplateListByTempId(Handler handler, int wwhat,
			String userId, String deviceType, String areaCode,
			String tempTypeId, String actionCode) {
		new QueryTempListRequest(handler, wwhat, userId, areaCode, deviceType,
				tempTypeId, actionCode).sendPostRequest();
	}

	/**
	 * 添加磁铁
	 * 
	 * @param handler
	 * @param wwhat
	 * @param userId
	 * @param deviceType
	 * @param areaCode
	 * @param strCategory
	 * @param strInst
	 * @param strGroup
	 * @param url
	 */
	public static void addGroupInst(Handler handler, int wwhat, String userId,
			String deviceType, String areaCode, String strCategory,
			String strInst, String strGroup, String actionCode) {
		new AddGroupInstRequest(handler, wwhat, userId, areaCode, deviceType,
				strCategory, strInst, strGroup, actionCode).sendPostRequest();
	}

	/**
	 * 删除磁铁
	 * 
	 * @param handler
	 * @param wwhat
	 * @param userId
	 * @param categoryId
	 * @param deviceType
	 * @param areaCode
	 * @param ArrInstForDelete
	 * @param url
	 */
	public static void delInst(Handler handler, int wwhat, String userId,
			String deviceType, String areaCode, String ArrInstForDelete,
			String url) {
		new DelInstRequest(handler, wwhat, userId, areaCode, deviceType,
				ArrInstForDelete, url).sendPostRequest();
	}

	/**
	 * 更新磁铁位置
	 * 
	 * @param handler
	 * @param wwhat
	 * @param userId
	 * @param deviceType
	 * @param areaCode
	 * @param ArrInstForUpdate
	 * @param url
	 */
	public static void updateInst(Handler handler, int wwhat, String userId,
			String deviceType, String areaCode, String ArrInstForUpdate,
			String url) {
		new UpdateInstRequest(handler, wwhat, userId, areaCode, deviceType,
				ArrInstForUpdate, url).sendPostRequest();
	}

	/**
	 * 更新分组位置
	 * 
	 * @param handler
	 * @param wwhat
	 * @param userId
	 * @param deviceType
	 * @param areaCode
	 * @param ArrGroupForUpdate
	 * @param url
	 */
	public static void updateGroup(Handler handler, int wwhat, String userId,
			String deviceType, String areaCode, String ArrGroupForUpdate,
			String url) {
		new UpdateGroupRequest(handler, wwhat, userId, areaCode, deviceType,
				ArrGroupForUpdate, url).sendPostRequest();
	}

	/**
	 * 查询用户地区
	 * 
	 * @param handler
	 * @param wwhat
	 * @param pracctName
	 * @param SystemId
	 * @param querySystemId
	 * @param url
	 */
	public static void getUserArea(Handler handler, int wwhat, String staffId,
			String url) {
		new GetUserAreaRequest(handler, wwhat, staffId, url).sendGetRequest();
	}

	/**
	 * 获取验证码
	 * 
	 * @param handler
	 * @param wwhat
	 * @param pracctPwd1
	 * @param url
	 */
	public static void resetPwd1(Handler handler, int wwhat, String staffId,
			String url) {
		new ResetPwd1Request(handler, wwhat, staffId, null, url)
				.sendPostRequest();

	}

	/**
	 * 
	 * @param handler
	 * @param wwhat
	 * @param staffId
	 * @param areaCode
	 * @param url
	 */
	public static void resetPwd1(Handler handler, int wwhat, String staffId,
			String areaCode, String url) {
		new ResetPwd1Request(handler, wwhat, staffId, areaCode, url)
				.sendPostRequest();

	}

	/**
	 * 重置密码
	 * 
	 * @param handler
	 * @param wwhat
	 * @param staffId
	 * @param staffPwd
	 * @param validatecode
	 * @param url
	 */
	public static void resetPwd2(Handler handler, int wwhat, String staffId,
			String staffPwd, String validatecode, String url) {
		new ResetPwd2Request(handler, wwhat, staffId, staffPwd, validatecode,
				null, url).sendPostRequest();

	}

	/**
	 * 
	 * @param handler
	 * @param wwhat
	 * @param staffId
	 * @param staffPwd
	 * @param validatecode
	 * @param areaCode
	 * @param url
	 */
	public static void resetPwd2(Handler handler, int wwhat, String staffId,
			String staffPwd, String validatecode, String areaCode, String url) {
		new ResetPwd2Request(handler, wwhat, staffId, staffPwd, validatecode,
				areaCode, url).sendPostRequest();

	}

	/**
	 * 修改密码
	 * 
	 * @param handler
	 * @param wwhat
	 * @param staffId
	 * @param staffPwd
	 * @param orgPwd
	 * @param url
	 */
	public static void modifyPwd(Handler handler, int wwhat, String staffId,
			String staffPwd, String orgPwd, String url) {
		new ModifyPwdRequest(handler, wwhat, staffId, staffPwd, orgPwd, null,
				url).sendPostRequest();
	}

	/**
	 * 
	 * @param handler
	 * @param wwhat
	 * @param staffId
	 * @param staffPwd
	 * @param orgPwd
	 * @param areaCode
	 * @param url
	 */
	public static void modifyPwd(Handler handler, int wwhat, String staffId,
			String staffPwd, String orgPwd, String areaCode, String url) {
		new ModifyPwdRequest(handler, wwhat, staffId, staffPwd, orgPwd,
				areaCode, url).sendPostRequest();
	}

	/**
	 * 用户登录日志记录
	 * 
	 * @param handler
	 * @param wwhat
	 * @param userId
	 * @param areaId
	 * @param deviceType
	 * @param actionCode
	 */
	public static void userLoginLog(Handler handler, int wwhat, String userId,
			String areaId, String deviceType, String systemVersion,
			String appVersionCode, String appVersionName, String model,
			String actionCode) {
		new UserLoginLogRequest(handler, wwhat, userId, areaId, deviceType,
				systemVersion, appVersionCode, appVersionName, model,
				actionCode).sendPostRequest();
	}

	/**
	 * 访问磁铁日志记录
	 * 
	 * @param handler
	 * @param wwhat
	 * @param userId
	 * @param areaId
	 * @param tempInstId
	 * @param modelId
	 * @param tempName
	 * @param serviceCode
	 * @param serviceParam
	 * @param instTypeId
	 * @param actionCode
	 */
	public static void visitTemplateLogRequest(Handler handler, int wwhat,
			String userId, String areaId, String tempInstId, String modelId,
			String tempName, String serviceCode, String serviceParam,
			String instTypeId, String deviceType, String systemVersion,
			String appVersionCode, String appVersionName, String model,
			String actionCode) {
		new VisitTemplateLogRequest(handler, wwhat, userId, areaId, tempInstId,
				modelId, tempName, serviceCode, serviceParam, instTypeId,
				deviceType, systemVersion, appVersionCode, appVersionName,
				model, actionCode).sendPostRequest();
	}

	/**
	 * 错误日志记录
	 * 
	 * @param handler
	 * @param wwhat
	 * @param brand
	 * @param systemVersion
	 * @param deviceId
	 * @param product
	 * @param stackTrace
	 * @param packageName
	 * @param crashDate
	 * @param userName
	 * @param appVersionCode
	 * @param appVersionName
	 * @param model
	 * @param actionCode
	 */
	public static void errorLog(Handler handler, int wwhat, String brand,
			String systemVersion, String deviceId, String product,
			String stackTrace, String packageName, String crashDate,
			String userName, String appVersionCode, String appVersionName,
			String model, String actionCode) {
		new ErrorLogRequest(handler, wwhat, brand, systemVersion, deviceId,
				product, stackTrace, packageName, crashDate, userName,
				appVersionCode, appVersionName, model, actionCode)
				.sendPostRequest();
	}

	/**
	 * 绑定手机开店账号
	 * 
	 * @param handler
	 * @param wwhat
	 * @param account
	 * @param password
	 * @param city
	 * @param actionCode
	 */
	public static void emVerifyNumPwd(Handler handler, int wwhat,
			String account, String password, String city, String actionCode) {
		new EmVerifyNumPwdRequest(handler, wwhat, account, password, city,
				actionCode).sendPostRequest();
	}

	/**
	 * 配置信息更新，删除
	 * 
	 * @param handler
	 * @param wwhat
	 * @param userId
	 * @param areaCode
	 * @param deviceType
	 * @param ArrGroupForDelete
	 * @param ArrInstForUpdate
	 * @param actionCode
	 */
	public static void updateOrDelCFG(Handler handler, int wwhat,
			String userId, String areaCode, String deviceType,
			String ArrCategoryForDelete, String ArrCategoryForUpdate,
			String ArrGroupForDelete, String ArrGroupForUpdate,
			String ArrInstForDelete, String ArrInstForUpdate, String actionCode) {
		new UpdateOrDelCFGRequest(handler, wwhat, userId, areaCode, deviceType,
				ArrCategoryForDelete, ArrCategoryForUpdate, ArrGroupForDelete,
				ArrGroupForUpdate, ArrInstForDelete, ArrInstForUpdate,
				actionCode).sendPostRequest();
	}

	/**
	 * 删除分组
	 * 
	 * @param handler
	 * @param wwhat
	 * @param userId
	 * @param deviceType
	 * @param areaCode
	 * @param ArrGroupForDelete
	 * @param url
	 */
	public static void delGroup(Handler handler, int wwhat, String userId,
			String deviceType, String areaCode, String ArrGroupForDelete,
			String url) {
		new DelGroupRequest(handler, wwhat, userId, areaCode, deviceType,
				ArrGroupForDelete, url).sendPostRequest();
	}

	/**
	 * 搜索磁铁模板
	 * 
	 * @param handler
	 * @param wwhat
	 * @param userId
	 * @param areaCode
	 * @param deviceType
	 * @param key
	 * @param actionCode
	 */
	public static void searchFuncTemp(Handler handler, int wwhat,
			String userId, String areaCode, String deviceType, String key,
			String actionCode) {
		new SearchFuncTempRequest(handler, wwhat, userId, areaCode, deviceType,
				key, actionCode).sendPostRequest();
	}

	/**
	 * 标记已读
	 * 
	 * @param handler
	 * @param wwhat
	 * @param userId
	 * @param areaCode
	 * @param deviceType
	 * @param ArrFuncTempLog
	 * @param actionCode
	 */
	public static void addFuncTempLog(Handler handler, int wwhat,
			String userId, String areaCode, String deviceType,
			String ArrFuncTempLog, String actionCode) {
		new AddFuncTempLogRequest(handler, wwhat, userId, areaCode, deviceType,
				ArrFuncTempLog, actionCode).sendPostRequest();
	}

	/**
	 * 查询推送磁贴
	 * 
	 * @param handler
	 * @param wwhat
	 * @param userId
	 * @param areaCode
	 * @param deviceType
	 * @param actionCode
	 */
	public static void queryPushInfo(Handler handler, int wwhat, String userId,
			String areaCode, String deviceType, String actionCode) {
		new QueryPushInfoRequest(handler, wwhat, userId, areaCode, deviceType,
				actionCode).sendPostRequest();
	}

	/**
	 * 删除推荐磁贴
	 * 
	 * @param handler
	 * @param wwhat
	 * @param ArrPushLog
	 * @param actionCode
	 */
	public static void addPushLog(Handler handler, int wwhat,
			String deviceType, String userId, String areaCode,
			String ArrPushLog, String actionCode) {
		new AddPushLogRequest(handler, wwhat, deviceType, userId, areaCode,
				ArrPushLog, actionCode).sendPostRequest();
		// new AddPushLogSRequest(handler, wwhat, userId, areaCode, deviceType,
		// ArrPushLog, actionCode).sendPostRequest();
	}

	/**
	 * 应用磁贴
	 * 
	 * @param handler
	 * @param wwhat
	 * @param userId
	 * @param areaCode
	 * @param deviceType
	 * @param ArrPushLog
	 * @param url
	 */
	public static void applyPush(Handler handler, int wwhat, String userId,
			String areaCode, String deviceType, String ArrGroup,
			String actionCode) {
		// new ApplyPushSRequest(handler, wwhat, userId, areaCode, deviceType,
		// ArrGroup, actionCode).sendPostRequest();
		new ApplyPushRequest(handler, wwhat, userId, areaCode, deviceType,
				ArrGroup, actionCode).sendPostRequest();
	}

	/**
	 * 绑定账号
	 * 
	 * @param handler
	 * @param wwhat
	 * @param token
	 * @param SystemId
	 * @param usr
	 * @param pwd
	 * @param AreaId
	 * @param type
	 * @param device_unique_code
	 * @param actionCode
	 */
	public static void bindingAcct(Handler handler, int wwhat, String token,
			String SystemId, String usr, String pwd, String AreaId,
			String type, String device_unique_code, String actionCode) {
		new BindingAcctRequest(handler, wwhat, token, SystemId, usr, pwd,
				AreaId, type, device_unique_code, actionCode).sendPostRequest();
	}

	/**
	 * 保存登录用户信息
	 * 
	 * @param handler
	 * @param wwhat
	 * @param userName
	 * @param pwd
	 * @param actionCode
	 */
	public static void saveUserKey(Handler handler, int wwhat, String userName,
			String pwd, String actionCode) {
		new SaveUserKeyRequest(handler, wwhat, userName, pwd, actionCode)
				.sendPostRequest();
	}

	/**
	 * 更新桌面风格
	 * 
	 * @param handler
	 * @param wwhat
	 * @param pId
	 * @param styleType
	 * @param actionCode
	 */
	public static void updatePageStyle(Handler handler, int wwhat, String pId,
			String styleType, String actionCode) {
		new UpdatePageStyleRequest(handler, wwhat, pId, styleType, actionCode)
				.sendPostRequest();
	}

	/**
	 * 发送短信
	 * 
	 * @param handler
	 * @param wwhat
	 * @param accNbr
	 * @param messContent
	 * @param actionCode
	 */
	public static void sendSMS(Handler handler, int wwhat, SmsParam param,
			String actionCode) {
		new SendSMSRequest(handler, wwhat, param, actionCode).sendPostRequest();
	}

	/**
	 * 是否升级
	 * 
	 * @param handler
	 * @param wwhat
	 * @param userid
	 * @param packageCd
	 * @param softVersion
	 * @param deviceCode
	 * @param deviceToken
	 * @param sysVersion
	 * @param actionCode
	 */
	public static void upgrade(Handler handler, int wwhat, String userid,
			String packageCd, String softVersion, String deviceCode,
			String deviceToken, String sysVersion, String actionCode) {
		new UpgradeRequest(handler, wwhat, userid, packageCd, softVersion,
				deviceCode, deviceToken, sysVersion, actionCode)
				.sendPostRequest();
	}

	/**
	 * 简单取数
	 * 
	 * @param handler
	 * @param wwhat
	 * @param Pname1
	 * @param Uname
	 * @param serviceParam
	 * @param instId
	 * @param actionCode
	 */
	public static void simpleGetNum(Handler handler, int wwhat, String Uname,
			String serviceParam, long instId, String actionCode) {
		new SimplePortalRequest(handler, wwhat, Uname, serviceParam, instId,
				actionCode).sendPostRequest();
	}

	/**
	 * 获取Uname
	 * 
	 * @param handler
	 * @param wwhat
	 * @param personCode
	 * @param personName
	 * @param areaCode
	 * @param actionCode
	 */
	public static void getUname(Handler handler, int wwhat, String personCode,
			String personName, String areaCode, String actionCode) {
		new GetUnameRequest(handler, wwhat, personCode, personName, areaCode,
				actionCode).sendPostRequest();
	}
}
