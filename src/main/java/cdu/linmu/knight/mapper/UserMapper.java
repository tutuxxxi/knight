package cdu.linmu.knight.mapper;

import cdu.linmu.knight.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xxx_
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
