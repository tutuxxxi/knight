package cdu.linmu.knight.service;

import cdu.linmu.knight.entity.ResponseData;
import cdu.linmu.knight.entity.Tweets;
import cdu.linmu.knight.entity.vo.TweetsVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author xxx_
 */
public interface TweetsService extends IService<Tweets> {

    /**
     * 删除动态
     * @param tweets
     * @return
     */
    ResponseData deleteTweets(Tweets tweets);


    /**
     * 查看博客
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<TweetsVO> listTweets(int pageNum, int pageSize);

    /**
     * 查看我的博客
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<Tweets> listMyTweets(String userId, int pageNum, int pageSize);
}
