package cdu.linmu.knight.service;

import cdu.linmu.knight.entity.Comment;
import cdu.linmu.knight.entity.ResponseData;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author xxx_
 */
public interface CommentService extends IService<Comment> {

    /**
     * 发布评论
     * @param entity
     * @return
     */
    ResponseData postComment(Comment entity);

    /**
     * 获取评论
     * @param tweetId
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<Comment> listComments(String tweetId, int pageNum, int pageSize);


    /**
     * 删除评论
     * @param comment
     * @return
     */
    boolean deleteComment(Comment comment);
}
