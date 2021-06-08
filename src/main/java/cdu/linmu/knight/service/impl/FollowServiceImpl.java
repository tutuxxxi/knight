package cdu.linmu.knight.service.impl;

import cdu.linmu.knight.entity.Follow;
import cdu.linmu.knight.entity.ResponseCode;
import cdu.linmu.knight.entity.ResponseData;
import cdu.linmu.knight.entity.User;
import cdu.linmu.knight.mapper.FollowMapper;
import cdu.linmu.knight.mapper.UserMapper;
import cdu.linmu.knight.service.FollowService;
import static cdu.linmu.knight.util.PageUtil.startPage;

import cdu.linmu.knight.util.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ：xxx_
 * @date ：Created in 2021/6/8 12:03 下午
 * @description：
 * @modified By：
 * @version:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements FollowService {

    @Resource
    FollowMapper followMapper;

    @Resource
    UserMapper userMapper;

    @Override
    public ResponseData follow(Follow follow) {
        ResponseData res = null;

        if (userMapper.selectById(follow.getFollowId()) == null ||
            userMapper.selectById(follow.getFansId()) == null){
            res = new ResponseData(500, "id不合法");
        }else{
            if(follow.getFollowId().equals(follow.getFansId())){
                res = new ResponseData(500, "不合法的操作: 您不能关注自己");
            }else{
                follow.setFollowTime(new Date());
                try{
                    followMapper.insert(follow);
                    res = new ResponseData(ResponseCode.SUCCESS);
                }catch (DuplicateKeyException e){
                    res = new ResponseData(500, "不合法的操作: 重复关注");
                }
            }
        }

        return res;
    }


    @Override
    public boolean unfollow(Follow follow) {
        QueryWrapper<Follow> wrapper = new QueryWrapper<>();

        //使用and连接，无需担心 "" 或 null
        wrapper.eq("follow_id", follow.getFollowId());
        wrapper.eq("fans_id", follow.getFansId());

        return followMapper.delete(wrapper) == 1;
    }


    @Override
    public long getFansNum(String userId) {
        QueryWrapper<Follow> wrapper = new QueryWrapper<>();
        wrapper.eq("follow_id", userId);

        return followMapper.selectCount(wrapper);
    }

    @Override
    public Page<User> getFansList(String userId, int pageNum, int pageSize) {
        QueryWrapper<Follow> wrapper = new QueryWrapper<>();
        wrapper.eq("follow_id", userId);
        wrapper.select("fans_id");

        Page<Follow> followPage = startPage(pageNum, pageSize);
        followMapper.selectPage(followPage, wrapper);

        List<String> fansIds = new ArrayList<>(followPage.getRecords().size());
        for(Follow fans : followPage.getRecords()){
            fansIds.add(fans.getFansId());
        }

        List<User> users = userMapper.selectBatchIds(fansIds);
        users.forEach((user) -> {
            SecureUtil.clearSecureInfo(user);
        });

        Page<User> fansPage = new Page<>();
        fansPage.setPages(followPage.getPages());
        fansPage.setSize(followPage.getSize());
        fansPage.setTotal(followPage.getTotal());
        fansPage.setCurrent(followPage.getCurrent());
        fansPage.setRecords(users);

        return fansPage;
    }

    @Override
    public long getFollowsNum(String userId) {
        QueryWrapper<Follow> wrapper = new QueryWrapper<>();
        wrapper.eq("fans_id", userId);

        return followMapper.selectCount(wrapper);
    }

    @Override
    public Page<User> getFollowsList(String userId, int pageNum, int pageSize) {
        QueryWrapper<Follow> wrapper = new QueryWrapper<>();
        wrapper.eq("fans_id", userId);
        wrapper.select("follow_id");

        Page<Follow> followPage = startPage(pageNum, pageSize);
        followMapper.selectPage(followPage, wrapper);

        List<String> fansIds = new ArrayList<>(followPage.getRecords().size());
        for(Follow fans : followPage.getRecords()){
            fansIds.add(fans.getFollowId());
        }

        List<User> users = userMapper.selectBatchIds(fansIds);
        users.forEach((user) -> {
            SecureUtil.clearSecureInfo(user);
        });

        Page<User> fansPage = new Page<>();
        fansPage.setPages(followPage.getPages());
        fansPage.setSize(followPage.getSize());
        fansPage.setTotal(followPage.getTotal());
        fansPage.setCurrent(followPage.getCurrent());
        fansPage.setRecords(users);

        return fansPage;
    }
}
