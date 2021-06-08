package cdu.linmu.knight.mapper;

import cdu.linmu.knight.entity.Tweets;
import cdu.linmu.knight.entity.vo.TweetsVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ：xxx_
 * @date ：Created in 2021/6/8 3:33 下午
 * @description：
 * @modified By：
 * @version:
 */
@Mapper
public interface TweetsMapper extends BaseMapper<Tweets> {

}
