package cdu.linmu.knight.entity.vo;

import cdu.linmu.knight.entity.Tweets;
import cdu.linmu.knight.entity.User;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ：xxx_
 * @date ：Created in 2021/6/8 4:04 下午
 * @description：
 * @modified By：
 * @version:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_tweets")
public class TweetsVO extends Tweets{

    /**
     * 动态id
     */
    private String tweetsId;

    /**
     * 博主
     */
    private User user;

    /**
     * tweet内容
     */
    private String content;

    /**
     * 图片url，以"，"分割
     */
    private String images;

    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 点赞数
     */
    private Integer likes;

    /**
     * 评论数
     */
    private Integer comments;


    public TweetsVO(Tweets tweets){
        this.tweetsId = tweets.getTweetsId();
        this.content = tweets.getContent();
        this.images = tweets.getImages();
        this.createTime = tweets.getCreateTime();
    }
}
