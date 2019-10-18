package cn.njit.utils;

import javax.servlet.http.Cookie;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dustdawn
 * @date 2019/10/18 14:44
 */
public class LoginUtil {
    public static Map<String, Cookie> saveCookie(String no, String password) {
        //要自动登录
        //创建存储用户名的cookie
        Cookie cookie_no = new Cookie("no", no);
        cookie_no.setMaxAge(10*60);
        //创建存储密码的cookie
        Cookie cookie_password = new Cookie("password", password);
        cookie_password.setMaxAge(10*60);
        Map<String, Cookie> map = new HashMap<>();
        map.put("cookie_no", cookie_no);
        map.put("cookie_password", cookie_password);
        return map;

    }
    public static String getTime() {
        Date date = Calendar.getInstance().getTime();
        return new SimpleDateFormat("YYYY年MM月dd日").format(date);
    }
}
