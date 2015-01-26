/**
 * 
 */
package com.ailk.ec.unitdesk.models.http;

import java.util.List;

/**
 * 亚信联创 电信EC产品研发部
 * 
 * @author 李国荣
 * @Description:
 * @version V1.0
 * @date  2013-10-12
 */

public class QueryTaskListResult
{
	public String resultCode;
	public String resultMessage;
	public String totalCount;
	public List<TaskEle> taskList;
}
