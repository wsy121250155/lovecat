/**
 * jquery的模板
 * @param {} html str
 * @param {} data json对象
 * @author zhaixq
 */
jQuery.fn.temp = function(html,data){
	$.each(data,function(key,value){
		var pattern = new RegExp("\{" + key + "\}", 'g');
		html = html.replace(pattern,value);
	})
	return html;
}
