package cdu.linmu.knight.service;

import cdu.linmu.knight.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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


    /**
     * 查询用户
     * @param user
     * @return
     */
    public List<User> query(User user);


    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page<User> page(int pageNum, int pageSize);
}
