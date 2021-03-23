package cdu.linmu.knight.service.impl;

import cdu.linmu.knight.entity.User;
import cdu.linmu.knight.mapper.UserMapper;
import cdu.linmu.knight.service.UserService;
import cdu.linmu.knight.util.SecureUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * @author ：xxx_
 * @date ：Created in 2021/3/22 3:25 下午
 * @description：
 * @modified By：
 * @version:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Autowired
    UserMapper userMapper;

    @Override
    public boolean save(User user) {

        //检查手机号注册情况
        if(userMapper.selectByTel(user.getTel()) != null){
            return false;
        }


        // 添加id
        user.setId(UUID.randomUUID().toString());
        // 获取加密盐
        user.setSalt(SecureUtil.getRandomSalt());
        // 加密密码
        user.setPassword(SecureUtil.getSecurePassword(user.getPassword(), user.getSalt()));

        //添加用户
        return userMapper.insert(user) != 0;
    }


    @Override
    public User login(User user) {
        User targetUser = userMapper.selectByTel(user.getTel());
        if(targetUser != null){
            if(SecureUtil.checkSecurePassword(user.getPassword(),
                    targetUser.getSalt(), targetUser.getPassword())){

                //密码匹配 验证登陆成功
                return targetUser;
            }
        }
        return null;
    }


    @Override
    public User update(User user) {

        //如果在此处尝试修改手机号
        if(StringUtils.hasText(user.getTel())){
            //需要验证手机号不存在
            if(userMapper.selectByTel(user.getTel()) != null){
                //手机号存在则失败
                return null;
            }
        }

        //如果在此处尝试更新密码
        if(StringUtils.hasText(user.getPassword())){
            // 获取加密盐
            user.setSalt(SecureUtil.getRandomSalt());
            // 加密密码
            user.setPassword(SecureUtil.getSecurePassword(user.getPassword(), user.getSalt()));
        }

        if(userMapper.updateById(user) != 0){
            return userMapper.selectById(user.getId());
        }else{
            return null;
        }
    }
}
