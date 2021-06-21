package cdu.linmu.knight.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ：xxx_
 * @date ：Created in 2021/6/8 4:38 下午
 * @description：
 * @modified By：
 * @version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_comment")
public class Comment {

    /**
     * 评论id
     */
    private String commentId;


    /**
     * 用户id
     */
    private String userId;


    /**
     * 动态id
     */
    private String tweetsId;


    /**
     * 评论内容
     */
    private String content;


    /**
     * 评论时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


    /**
     * 状态 1正常 2被删除
     */
    private int status;
}
