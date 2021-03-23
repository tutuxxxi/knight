package cdu.linmu.knight.service;

import cdu.linmu.knight.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author xxx_
 */
public interface UserService extends IService<User> {


    /**
     * 用户登陆
     * @param user
     * @return
     */
    public User login(User user);

    /**
     * 用户信息更新
     * @param user
     * @return
     */
    public User update(User user);


}
