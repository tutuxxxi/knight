package cdu.linmu.knight.mapper;

import cdu.linmu.knight.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xxx_
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过手机号获取信息
     * @param tel
     * @return
     */
     User selectByTel(String tel);


}
