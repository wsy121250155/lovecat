package com.ailk.ec.unitdesk.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Title: HTML相关的正则表达式工具类
 * Description: 包括过滤HTML标记，转换HTML标记，替换特定HTML标记
 * User: Noah
 * Date: 13-3-13
 * Time: 上午10:44
 */
public class HtmlRegexpUtils {
    private final static String regexpForHtml = "<([^>]*)>"; // 过滤所有以<开头以>结尾的标签

    private final static String regexpForImgTag = "<\\s*img\\s+([^>]*)\\s*>"; // 找出IMG标签

    private final static String regexpForImaTagSrcAttr = "src=\"([^\"]+)\""; // 找出IMG标签的SRC属性

    public HtmlRegexpUtils() {
    }

    /**
     * 基本功能：替换标记以正常显示
     *
     * @param input
     * @return String
     */
    public String replaceTag(String input) {
        if (!hasSpecialChars(input)) {
            return input;
        }
        StringBuffer filtered = new StringBuffer(input.length());
        char c;
        for (int i = 0; i <= input.length() - 1; i++) {
            c = input.charAt(i);
            switch (c) {
                case '<':
                    filtered.append("&lt;");
                    break;
                case '>':
                    filtered.append("&gt;");
                    break;
                case '"':
                    filtered.append("&quot;");
                    break;
                case '&':
                    filtered.append("&amp;");
                    break;
                default:
                    filtered.append(c);
            }

        }
        return (filtered.toString());
    }

    /**
     * 基本功能：判断标记是否存在
     *
     * @param input
     * @return boolean
     */
    public boolean hasSpecialChars(String input) {
        boolean flag = false;
        if ((input != null) && (input.length() > 0)) {
            char c;
            for (int i = 0; i <= input.length() - 1; i++) {
                c = input.charAt(i);
                switch (c) {
                    case '>':
                        flag = true;
                        break;
                    case '<':
                        flag = true;
                        break;
                    case '"':
                        flag = true;
                        break;
                    case '&':
                        flag = true;
                        break;
                }
            }
        }
        return flag;
    }

    /**
     * 基本功能：过滤所有以"<"开头以">"结尾的标签
     *
     * @param str
     * @return String
     */
    public static String filterHtml(String str) {
        Pattern pattern = Pattern.compile(regexpForHtml);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result1 = matcher.find();
        while (result1) {
            matcher.appendReplacement(sb, "");
            result1 = matcher.find();
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 基本功能：过滤指定标签
     *
     * @param str
     * @param tag 指定标签
     * @return String
     */
    public static String filterHtmlTag(String str, String tag) {
        String regexp = "<\\s*" + tag + "\\s+([^>]*)\\s*>";
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result1 = matcher.find();
        while (result1) {
            matcher.appendReplacement(sb, "");
            result1 = matcher.find();
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 基本功能：替换指定的标签
     *
     * @param str
     * @param beforeTag 要替换的标签
     * @param tagAttr 要替换的标签属性值
     * @param startTag  新标签开始标记
     * @param endTag    新标签结束标记
     * @return String
     * @如：替换img标签的src属性值为[img]属性值[/img]
     */
    public static String replaceHtmlTag(String str, String beforeTag,
                                        String tagAttr, String startTag, String endTag) {
        String regexpForTag = "<\\s*" + beforeTag + "\\s+([^>]*)\\s*>";
        String regexpForTagAttr = tagAttr + "=\"([^\"]+)\"";
        Pattern patternForTag = Pattern.compile(regexpForTag);
        Pattern patternForAttr = Pattern.compile(regexpForTagAttr);
        Matcher matcherForTag = patternForTag.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result = matcherForTag.find();
        while (result) {
            StringBuffer sbReplace = new StringBuffer();
            Matcher matcherForAttr = patternForAttr.matcher(matcherForTag
                    .group(1));
            if (matcherForAttr.find()) {
                matcherForAttr.appendReplacement(sbReplace, startTag
                        + matcherForAttr.group(1) + endTag);
            }
            matcherForTag.appendReplacement(sb, sbReplace.toString());
            result = matcherForTag.find();
        }
        matcherForTag.appendTail(sb);
        return sb.toString();
    }
}
