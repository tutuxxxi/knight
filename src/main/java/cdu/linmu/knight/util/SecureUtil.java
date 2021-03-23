package cdu.linmu.knight.util;

import cdu.linmu.knight.entity.User;
import org.springframework.util.DigestUtils;
import sun.security.provider.MD5;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

/**
 * @author ：xxx_
 * @date ：Created in 2021/3/22 4:04 下午
 * @description：加密工具类
 * @modified By：
 * @version:
 */
public class SecureUtil {
    private static SecureRandom random = new SecureRandom();


    /**
     * 返回一个长度为8的随机加密盐
     *
     * @return
     */
    public static String getRandomSalt(){
        byte[] bytes = new byte[8];
        random.nextBytes(bytes);
        return new String(bytes);
    }

    /**
     * 通过密码和salt获取加密密码
     *  使用md5实现密码加密
     * @param passWord
     * @param salt
     * @return
     */
    public static String getSecurePassword(String passWord, String salt){
        char c = salt.charAt(0);

        if(c % 2 == 0){
            return DigestUtils.md5DigestAsHex((passWord + salt).getBytes(StandardCharsets.UTF_8));
        }else{
            return DigestUtils.md5DigestAsHex((salt + passWord).getBytes(StandardCharsets.UTF_8));
        }
    }

    /**
     * 根据密码校对
     * @param passWord
     * @param salt
     * @param secure
     * @return
     */
    public static boolean checkSecurePassword(String passWord, String salt, String secure){
        return getSecurePassword(passWord, salt).equals(secure);
    }


    /**
     * 清除用户携带的加密信息
     * @param user
     */
    public static void clearSecureInfo(User user){
        user.setSalt(null);
        user.setPassword(null);
    }



}
