/*
 * Copyright (C) 2008 feilong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.core.lang;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.core.date.DateUtil;
import com.feilong.test.User;

import static com.feilong.core.bean.ConvertUtil.toArray;

import static com.feilong.core.DatePattern.COMMON_DATE;
import static com.feilong.core.DatePattern.TIMESTAMP;
import static com.feilong.core.DatePattern.YEAR_AND_MONTH;

/**
 * The Class StringUtilTest.
 * 
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 */
public class StringUtilTest{

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtilTest.class);

    /** <code>{@value}</code>. */
    private static final String TEXT   = "jinxin.feilong";

    /**
     * Compare to.
     */
    @Test
    public void compareTo(){
        LOGGER.debug("" + "8".compareTo("13"));
        Integer parseInt = Integer.parseInt("8");
        LOGGER.debug("" + parseInt.compareTo(Integer.parseInt("13")));
        LOGGER.debug("" + "10".compareTo("13"));
    }

    /**
     * Test contains.
     */
    @Test
    public void testContains(){
        assertEquals(false, StringUtils.contains(null, ""));
        assertEquals(true, StringUtils.contains("", ""));
        assertEquals(true, StringUtils.contains("jiiiiiinxin.feilong", "xin"));
    }

    /**
     * Search count.
     */
    @Test
    public void testReplace(){
        Map<String, Object> valuesMap = new HashMap<String, Object>();
        valuesMap.put("today", DateUtil.toString(new Date(), COMMON_DATE));
        valuesMap.put("user", new User(1L));
        LOGGER.debug(StringUtil.replace("${today}${today1}${user.id}${user}", valuesMap) + "");
    }

    @Test
    public void testReplace3(){
        Map<String, Object> valuesMap = new HashMap<String, Object>();
        valuesMap.put("today", DateUtil.toString(new Date(), COMMON_DATE));
        valuesMap.put("user", 1L);
        LOGGER.debug(StringUtil.replace("${today}${today1}${user.id}${user}", valuesMap) + "");
    }

    /**
     * Test replace22.
     */
    @Test
    public void testReplace22(){
        String source = "jiiiiiinxin.feilong";
        assertEquals(source, StringUtil.replace(source, null));
    }

    /**
     * Test replace1.
     */
    @Test
    public void testReplace1(){
        assertEquals("", StringUtil.replace(null, null));
    }

    /**
     * Test replace2.
     */
    @Test
    public void testReplace2(){
        String template = "/home/webuser/expressdelivery/${yearMonth}/${expressDeliveryType}/vipQuery_${fileName}.log";

        Date date = new Date();
        Map<String, String> valuesMap = new HashMap<String, String>();
        valuesMap.put("yearMonth", DateUtil.toString(date, YEAR_AND_MONTH));
        valuesMap.put("expressDeliveryType", "sf");
        valuesMap.put("fileName", DateUtil.toString(date, TIMESTAMP));
        LOGGER.debug(StringUtil.replace(template, valuesMap));

        assertEquals(template, StringUtil.replace(template, null));

    }

    /**
     * Search count.
     */
    @Test
    public void testSearchCount(){
        String source = "jiiiiiinxin.feilong";
        assertEquals(8, StringUtils.countMatches(source, "i"));
        assertEquals(2, StringUtils.countMatches(source, "in"));
        assertEquals(3, StringUtils.countMatches(source, "ii"));
        assertEquals(1, StringUtils.countMatches(source, "xin"));
        assertEquals(1, StringUtils.countMatches("xin", "xin"));
        assertEquals(1, StringUtils.countMatches("xin", "i"));
        assertEquals(2, StringUtils.countMatches("xiin", "i"));
        assertEquals(2, StringUtils.countMatches("xiiiin", "ii"));
    }

    /**
     * Checks if is contain ignore case.
     */
    @Test
    public void testContainsIgnoreCase(){
        assertEquals(false, StringUtils.containsIgnoreCase(null, ""));
        LOGGER.debug(StringUtils.containsIgnoreCase(TEXT, null) + "");
        LOGGER.debug(StringUtils.containsIgnoreCase(TEXT, "") + "");
        assertEquals(true, StringUtils.containsIgnoreCase(TEXT, "feilong"));
        assertEquals(false, StringUtils.containsIgnoreCase(TEXT, "feilong1"));
        assertEquals(true, StringUtils.containsIgnoreCase(TEXT, "feiLong"));
        assertEquals(true, StringUtils.containsIgnoreCase("jiiiiiinxin.feilong", "Xin"));
    }

    /**
     * Format.
     */
    @Test
    public void format(){
        LOGGER.debug(StringUtil.format("%03d", 1));
        LOGGER.debug(StringUtil.format("%s%n%s%h", 1.2d, 2, "feilong"));
        LOGGER.debug(StringUtil.format("%+d", -5));
        LOGGER.debug(StringUtil.format("%-5d", -5));
        LOGGER.debug(StringUtil.format("%05d", -5));
        LOGGER.debug(StringUtil.format("% 5d", -5));
        LOGGER.debug(StringUtil.format("%,f", 5585458.254f));
        LOGGER.debug(StringUtil.format("%(f", -5585458.254f));
        LOGGER.debug(StringUtil.format("%#f", -5585458.254f));
        LOGGER.debug(StringUtil.format("%f和%<3.3f", 9.45));
        LOGGER.debug(StringUtil.format("%2$s,%1$s", 99, "abc"));
        LOGGER.debug(StringUtil.format("%1$s,%1$s", 99));
    }

    /**
     * Format3.
     */
    @Test
    public void format3(){
        LOGGER.debug(buildMessageLog(4, 30, "第三十二章 手段", 450));
        LOGGER.debug(buildMessageLog(14, 30, "第三十三章 王允的计谋", 6100));
        LOGGER.debug(buildMessageLog(1, 30, "第二十一章 恰似无情却有情", 60));
    }

    /**
     * TestStringUtilTest.
     */
    @Test
    public void testStringUtilTest(){
        String format = "|%1$-20s|%2$-10s|%3$-10s|";

        LOGGER.debug(StringUtil.format(format, "FirstName", "Init.", "LastName"));
        LOGGER.debug(StringUtil.format(format, "Real", "", "Gagnon"));
        LOGGER.debug(StringUtil.format(format, "Real2", "D", "Doe"));
        LOGGER.debug(StringUtil.format(format, "John", "F.", "Kennedy"));
    }

    /**
     * Builds the message log.
     *
     * @param writeIndex
     *            the write index
     * @param bookSectionUrlMapSize
     *            the book section url map size
     * @param sectionName
     *            the section name
     * @param contentLength
     *            the content length
     * @return the string
     */
    private static String buildMessageLog(int writeIndex,int bookSectionUrlMapSize,String sectionName,int contentLength){
        //进度,百分比
        String progress = NumberUtil.getProgress(writeIndex + 1, bookSectionUrlMapSize);
        String format = "%1$-20s %2$-6s %3$-3s";

        return StringUtil.format(format, sectionName, "" + contentLength, progress).replace(" ", "\u3000");
        //String padStr = "-";
        //return StringUtils.rightPad(sectionName, 40, padStr) + StringUtils.rightPad("" + contentLength, 6, padStr) + progress;
    }

    /**
     * Format1.
     */
    @Test
    public void format1(){
        Date date = new Date();
        LOGGER.debug(String.format("The date: %tY-%tm-%td", date, date, date));
        LOGGER.debug(String.format("The date: %1$tY-%1$tm-%1$td", date));
        LOGGER.debug(String.format("Time with tz: %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS.%1$tL%1$tz", date));
    }

    /**
     * Replace all.
     */
    @Test
    public void replaceAll(){
        assertEquals("黑色_黄色_蓝色", StringUtil.replaceAll("黑色/黄色/蓝色", "/", "_"));

        assertEquals("'SH1265','SH5951'", StringUtil.replaceAll("SH1265,SH5951", "([a-zA-Z]+[0-9]+)", "'$1'"));
        assertEquals("'12345','56789','1123456'", StringUtil.replaceAll("12345,56789,1123456", "([0-9]+)", "'$1'"));
        assertEquals("'SH1265',SH5951", "SH1265,SH5951".replaceFirst("([a-zA-Z]+[0-9]+)", "'$1'"));
    }

    /**
     * Substring2.
     */
    @Test
    public void substring2(){
        assertEquals(null, StringUtil.substring(null, 6, 8));
        LOGGER.debug(StringUtil.substring(TEXT, TEXT.length(), 8));
        LOGGER.debug(StringUtil.substring(TEXT, TEXT.length() - 1, 8));
        LOGGER.debug(StringUtil.substring(TEXT, 1, 0));
        assertEquals("jinxi", StringUtil.substring(TEXT, 0, 5));
        assertEquals(".f", StringUtil.substring(TEXT, 6, 2));
        LOGGER.debug(StringUtil.substring(TEXT, 6, 20));
    }

    /**
     * Substring.
     */
    @Test
    public void substring(){
        assertEquals(
                        "src/main/java/com/jumbo/shop/web/command/PageCacheCommand.java",
                        StringUtil.substring("Index: src/main/java/com/jumbo/shop/web/command/PageCacheCommand.java", "Index: ".length()));
        assertEquals(".feilong", StringUtil.substring(TEXT, "jinxin".length()));
        assertEquals(".feilong", StringUtil.substring(TEXT, 6));
        assertEquals("ng", StringUtil.substring(TEXT, -2));
    }

    /**
     * Test substring last.
     */
    @Test
    public void testSubstringLast(){
        assertEquals("ilong", StringUtil.substringLast(TEXT, 5));
    }

    /**
     * Test substring without last.
     */
    @Test
    public void testSubstringWithoutLast(){
        assertEquals("jinxin.fe", StringUtil.substringWithoutLast(TEXT, 5));
    }

    /**
     * Test substring without last.
     */
    @Test
    public void testSubstringWithoutLast2(){
        assertEquals("jinxin.feilo", StringUtil.substringWithoutLast(TEXT, "ng"));
        assertEquals("jinxin.feilong", StringUtil.substringWithoutLast("jinxin.feilong     ", "     "));
        assertEquals(TEXT, StringUtil.substringWithoutLast(TEXT, ""));
        assertEquals(TEXT, StringUtil.substringWithoutLast(TEXT, null));
        assertEquals("", StringUtil.substringWithoutLast(null, "222"));
    }

    /**
     * Tokenize to string array2.
     */
    @Test
    public void tokenizeToStringArray2(){
        String str = "jin.xin  @ @aha ,@ala;";
        String delimiters = "@";
        String[] tokenizeToStringArray = StringUtil.tokenizeToStringArray(str, delimiters, false, false);
        assertArrayEquals(toArray("jin.xin  ", " ", "aha ,", "ala;"), tokenizeToStringArray);
    }

    /**
     * Tokenize to string array1.
     */
    @Test
    public void tokenizeToStringArray1(){
        String str = "jin.xin  feilong ,jinxin;venusdrogon;jim ";
        String delimiters = ";, .";
        String[] tokenizeToStringArray = StringUtil.tokenizeToStringArray(str, delimiters);

        assertArrayEquals(toArray("jin", "xin", "feilong", "jinxin", "venusdrogon", "jim"), tokenizeToStringArray);
    }

}