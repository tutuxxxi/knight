package cdu.linmu.knight.service.impl;

import cdu.linmu.knight.entity.ResponseCode;
import cdu.linmu.knight.entity.ResponseData;
import cdu.linmu.knight.entity.Tweets;
import cdu.linmu.knight.entity.User;
import cdu.linmu.knight.entity.vo.TweetsVO;
import cdu.linmu.knight.mapper.TweetsMapper;
import cdu.linmu.knight.mapper.UserMapper;
import cdu.linmu.knight.service.TweetsService;
import cdu.linmu.knight.util.PageUtil;
import cdu.linmu.knight.util.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.file.Watchable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author ：xxx_
 * @date ：Created in 2021/6/8 3:35 下午
 * @description：
 * @modified By：
 * @version:
 */
@Service
public class TweetsServiceImpl extends ServiceImpl<TweetsMapper, Tweets> implements TweetsService {

    @Resource
    UserMapper userMapper;

    @Resource
    TweetsMapper tweetsMapper;



    @Override
    public boolean save(Tweets entity) {
        if(userMapper.selectById(entity.getUserId()) != null
                && entity.getContent() != null && !entity.getContent().equals("")){

            entity.setTweetsId(UUID.randomUUID().toString());
            entity.setCreateTime(new Date());
            entity.setStatus(1);

            return tweetsMapper.insert(entity) != 0;
        }
        return false;
    }


    @Override
    public ResponseData deleteTweets(Tweets tweets) {
        UpdateWrapper<Tweets> wrapper = new UpdateWrapper<>();
        wrapper.eq("tweets_id", tweets.getTweetsId());
        wrapper.eq("user_id", tweets.getUserId());
        wrapper.set("status", 2);

        if(tweetsMapper.update(tweets, wrapper) == 0){
            return new ResponseData(ResponseCode.FAILED, "动态不存在或已被删除");
        }else{
            return new ResponseData(ResponseCode.SUCCESS);
        }
    }


    @Override
    public Page<TweetsVO> listTweets(int pageNum, int pageSize) {
        Page<Tweets> tweetsPage = PageUtil.startPage(pageNum, pageSize);
        QueryWrapper<Tweets> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        tweetsMapper.selectPage(tweetsPage, wrapper);

        Page<TweetsVO> page = new Page<>();
        page.setPages(tweetsPage.getPages());
        page.setSize(tweetsPage.getSize());
        page.setTotal(tweetsPage.getTotal());
        page.setCurrent(tweetsPage.getCurrent());

        List<TweetsVO> list = new ArrayList<>();
        for(Tweets tweets : tweetsPage.getRecords()){
            TweetsVO tweetsVO = new TweetsVO(tweets);
            User user = userMapper.selectById(tweets.getUserId());
            SecureUtil.clearSecureInfo(user);
            tweetsVO.setUser(user);
            // TODO
            tweetsVO.setComments(123);
            tweetsVO.setLikes(123);

            list.add(tweetsVO);
        }
        page.setRecords(list);

        return page;
    }


    @Override
    public Page<Tweets> listMyTweets(String userId, int pageNum, int pageSize) {
        Page<Tweets> tweetsPage = PageUtil.startPage(pageNum, pageSize);
        QueryWrapper<Tweets> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.orderByDesc("create_time");

        return tweetsMapper.selectPage(tweetsPage, wrapper);
    }
}
