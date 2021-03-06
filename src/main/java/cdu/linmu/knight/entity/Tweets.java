package cdu.linmu.knight.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ：xxx_
 * @date ：Created in 2021/6/8 3:29 下午
 * @description：
 * @modified By：
 * @version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("动态实体")
@TableName("tb_tweets")
public class Tweets {

    /**
     * 动态id
     */
    private String tweetsId;

    /**
     * 博主id
     */
    private String userId;

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
     * 动态状态
     *  1： 正常
     *  2： 被删除
     */
    private int status;
}
