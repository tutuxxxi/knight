package cdu.linmu.knight.service;

import cdu.linmu.knight.entity.Follow;
import cdu.linmu.knight.entity.ResponseData;
import cdu.linmu.knight.entity.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author xxx_
 */
public interface FollowService extends IService<Follow> {

    /**
     *  关注
     * @param follow
     * @return
     */
    public ResponseData follow(Follow follow);

    /**
     *  取消关注
     * @param follow
     * @return
     */
    public boolean unfollow(Follow follow);


    /**
     * 获取粉丝数
     * @param userId
     * @return
     */
    public long getFansNum(String userId);

    /**
     * 获取粉丝列表
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page<User> getFansList(String userId, int pageNum, int pageSize);


    /**
     * 获取关注数
     * @param userId
     * @return
     */
    public long getFollowsNum(String userId);


    /**
     * 获取关注列表
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page<User> getFollowsList(String userId, int pageNum, int pageSize);

}
