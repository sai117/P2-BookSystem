package com.tuny.demo.util;

import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.text.NumberFormat;
import java.util.Random;
import java.util.function.Consumer;

public class CommonUtil {

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        return request;
    }

    /**
     * 得到session
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }


    /**
     * 获取请求的真实IP
     *
     * @param request HttpServletRequest请求
     * @return
     */
    public static String getIp(HttpServletRequest request) {
        String ip = getIpArrStr(request);
        // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }

    /**
     * 获取请求的真实IP及代理ip
     *
     * @param request HttpServletRequest请求
     * @return
     */
    public static String getIpArrStr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals("127.0.0.1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }
        return ip;
    }

    /**
     * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
     *
     * @param s 需要得到长度的字符串
     * @return int 得到的字符串长度
     */
    public static int length(String s) {
        if (s == null) {
            return 0;
        }
        char[] c = s.toCharArray();
        int len = 0;
        int k = 0x80;
        for (int i = 0; i < c.length; i++) {
            len++;
            if (c[i] / k != 0) {
                len++;
            }
        }
        return len;
    }

    /**
     * 生成随机字符串
     *
     * @param len
     * @return
     */
    public static String randomStr(int len) {
        Random r = new Random();
        char[] result = new char[len];

        for (int i = 0; i < len; i++) {
            result[i] = (char) (r.nextInt(26) + 65);
        }

        return new String(result);
    }

    /**
     * 计算百分比
     *
     * @param part      分子
     * @param all       分母
     * @param pointSize 小数点后保留位数
     * @return
     */
    public static String getPercent(int part, int all, int pointSize) {
        pointSize = pointSize < 0 ? 0 : pointSize;
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(pointSize);

        return numberFormat.format((float) part / (float) all * 100) + "%";
    }

    public static String makeLink(String content, long id, int type){
        return String.format("<a href='-httpurl-%s-%s'>%s</a>", id, type, content);
    }

    public static String makeLink(String content, long id, int type, int subType){
        return String.format("<a href='-httpurl-%s-%s-%s'>%s</a>", id, type, subType, content);
    }

    public static int exec(String cmd) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(cmd);
        try(InputStream inputStream = process.getInputStream();
            InputStream errorStream = process.getErrorStream()){
            dumpOutput(inputStream, LoggerFactory.getLogger(CommonUtil.class)::info);
            dumpOutput(errorStream, LoggerFactory.getLogger(CommonUtil.class)::error);
        }

        return process.waitFor();
    }

    private static void dumpOutput(InputStream inputStream, Consumer<String> outputMethod){
        new Thread(()->{
            final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            reader.lines().forEach(outputMethod);
        }).start();
    }

    public static String randCode(int length) {
        Random random = new Random();
        StringBuilder rand = new StringBuilder();
        for (int i = 0; i < length; i++) {
            rand.append(random.nextInt(10));
        }
        return rand.toString();
    }

    public static <T> T self(T obj){
        return obj;
    }

}

