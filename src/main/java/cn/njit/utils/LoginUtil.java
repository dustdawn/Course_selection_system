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
    public static Map<String, Cookie> saveCookie(String user, String no, String password) {
        //自动登录


        Map<String, Cookie> map = new HashMap<>();
        //创建存储用户名的cookie
        Cookie cookie_no = null;
        //创建存储密码的cookie
        Cookie cookie_password = null;

        if (null != user && "admin".equals(user)) {
            cookie_no = new Cookie("no", no);
            cookie_password = new Cookie("password", password);
        }else if (null != user && "student".equals(user)) {
            cookie_no = new Cookie("sno", no);
            cookie_password = new Cookie("spassword", password);
        }else if (null != user && "teacher".equals(user)) {
            cookie_no = new Cookie("tno", no);
            cookie_password = new Cookie("tpassword", password);
        }

        if (null != cookie_no && null != cookie_password) {
            cookie_no.setMaxAge(10*60);
            cookie_no.setPath("/");
            cookie_password.setMaxAge(10*60);
            cookie_password.setPath("/");

        }

        map.put("no", cookie_no);
        map.put("password", cookie_password);
        return map;

    }
    public static String getTime() {
        Date date = Calendar.getInstance().getTime();
        return new SimpleDateFormat("YYYY年MM月dd日").format(date);
    }
}
