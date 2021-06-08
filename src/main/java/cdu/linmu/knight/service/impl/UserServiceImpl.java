package cdu.linmu.knight.service.impl;

import cdu.linmu.knight.entity.User;
import cdu.linmu.knight.mapper.UserMapper;
import cdu.linmu.knight.service.UserService;
import cdu.linmu.knight.util.DataCheckUtil;
import cdu.linmu.knight.util.PageUtil;
import cdu.linmu.knight.util.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.SecureRandom;
import java.util.*;

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
        if(userMapper.selectList(new QueryWrapper<User>().eq("tel", user.getTel())).size() != 0){
            return false;
        }

        user.setId(UUID.randomUUID().toString());
        user.setSalt(SecureUtil.getRandomSalt());
        user.setPassword(SecureUtil.getSecurePassword(user.getPassword(), user.getSalt()));

        return userMapper.insert(user) != 0;
    }


    @Override
    public User login(User user) {
        User targetUser = userMapper.selectList(new QueryWrapper<User>().eq("tel", user.getTel())).get(0);
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
            if(userMapper.selectList(new QueryWrapper<User>().eq("tel", user.getTel())).size() != 0){
                //手机号存在则失败
                return null;
            }
        }

        //如果在此处尝试更新密码
        if(StringUtils.hasText(user.getPassword())){
            user.setSalt(SecureUtil.getRandomSalt());
            user.setPassword(SecureUtil.getSecurePassword(user.getPassword(), user.getSalt()));
        }

        if(userMapper.updateById(user) != 0){
            return userMapper.selectById(user.getId());
        }else{
            return null;
        }
    }

    @Override
    public List<User> query(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper();

        if(user.getId() != null && !user.getId().equals("")){
            wrapper.eq("id", user.getId());
        }
        if(user.getTel() != null && !user.getTel().equals("")){
            wrapper.eq("tel", user.getTel());
        }
        if(user.getNickName() != null && !user.getNickName().equals("")){
            wrapper.eq("nickname", user.getNickName());
        }
        if(user.getEmail() != null && !user.getEmail().equals("")){
            wrapper.eq("email", user.getEmail());
        }

        wrapper.select("id", "tel", "email", "nickname", "age", "gender", "profile", "signature");

        return userMapper.selectList(wrapper);
    }


    @Override
    public Page<User> page(int pageNum, int pageSize) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "tel", "email", "nickname", "age", "gender", "profile", "signature");

        return userMapper.selectPage(PageUtil.startPage(pageNum, pageSize), queryWrapper);
    }


}
