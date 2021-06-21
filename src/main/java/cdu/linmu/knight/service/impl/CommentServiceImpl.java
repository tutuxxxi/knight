package cdu.linmu.knight.service.impl;

import cdu.linmu.knight.entity.Comment;
import cdu.linmu.knight.entity.ResponseCode;
import cdu.linmu.knight.entity.ResponseData;
import cdu.linmu.knight.entity.Tweets;
import cdu.linmu.knight.mapper.CommentMapper;
import cdu.linmu.knight.mapper.TweetsMapper;
import cdu.linmu.knight.mapper.UserMapper;
import cdu.linmu.knight.service.CommentService;
import cdu.linmu.knight.util.PageUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @author ：xxx_
 * @date ：Created in 2021/6/8 4:44 下午
 * @description：
 * @modified By：
 * @version:
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {


    @Resource
    CommentMapper commentMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    TweetsMapper tweetsMapper;


    @Override
    public ResponseData postComment(Comment entity) {
        if(userMapper.selectById(entity.getUserId()) != null
                && tweetsMapper.selectOne(new QueryWrapper<Tweets>().eq("tweets_id", entity.getTweetsId())) != null){

            if(entity.getContent() != null && entity.getContent().length() != 0){
                entity.setCreateTime(new Date());
                entity.setCommentId(UUID.randomUUID().toString());
                entity.setStatus(1);

                commentMapper.insert(entity);
                return new ResponseData(ResponseCode.SUCCESS);
            }
            return new ResponseData(500, "评论不可为空");
        }
        return new ResponseData(500, "id不存在");
    }

    @Override
    public Page<Comment> listComments(String tweetId, int pageNum, int pageSize) {
        Page<Comment> page = PageUtil.startPage(pageNum, pageSize);
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("tweets_id", tweetId);
        wrapper.eq("status", 1);
        wrapper.orderByAsc("create_time");

        return commentMapper.selectPage(page, wrapper);
    }


    @Override
    public boolean deleteComment(Comment comment) {
        UpdateWrapper<Comment> wrapper = new UpdateWrapper<>();
        wrapper.eq("comment_id", comment.getCommentId());
        wrapper.eq("tweets_id", comment.getTweetsId());
        wrapper.eq("user_id", comment.getUserId());
        wrapper.set("status", 2);

        return commentMapper.update(comment, wrapper) != 0;
    }
}
