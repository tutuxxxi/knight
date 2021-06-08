package cdu.linmu.knight.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author ：xxx_
 * @date ：Created in 2021/6/8 2:08 下午
 * @description：
 * @modified By：
 * @version:
 */
public class PageUtil {

    public static <T> Page<T> startPage(int pageNum, int pageSize){
        Page<T> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        return page;
    }

}
