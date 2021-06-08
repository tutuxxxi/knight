package cdu.linmu.knight.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author ：xxx_
 * @date ：Created in 2021/3/22 3:17 下午
 * @description：用户实体
 * @modified By：
 * @version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("tb_user")
@ApiModel("用户")
public class User {

    private String id;

    private String password;

    /**
     * 加密盐
     */
    private String salt;

    private String tel;

    private String email;

    @TableField("nickname")
    private String nickName;

    private Integer age;

    private String gender;

    private String profile;

    private String signature;

}
