package cdu.linmu.knight.util;

import cdu.linmu.knight.entity.User;
import org.springframework.util.StringUtils;

/**
 * @author ：xxx_
 * @date ：Created in 2021/3/22 4:19 下午
 * @description：
 * @modified By：
 * @version:
 */
public class DataCheckUtil {

    /**
     * 检查用户必要字段是否为空
     * @param user
     * @return
     */
    public static boolean registerCheck(User user){
        return loginCheck(user) &&
                StringUtils.hasText(user.getNickName());
    }


    /**
     * 检查用户tel和password
     * @param user
     * @return
     */
    public static boolean loginCheck(User user){
        return StringUtils.hasText(user.getPassword()) &&
                StringUtils.hasText(user.getTel());
    }

}
