package cdu.linmu.knight.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ：xxx_
 * @date ：Created in 2021/6/8 11:58 上午
 * @description：
 * @modified By：
 * @version:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("关注列表")
@TableName("tb_follow")
public class Follow {

    /**
     * 粉丝id
     */
    private String fansId;

    /**
     * 被关注者id
     */
    private String followId;

    /**
     * 关注时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date followTime;

}
