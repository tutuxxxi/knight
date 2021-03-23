package cdu.linmu.knight.controller;

import cdu.linmu.knight.entity.ResponseCode;
import cdu.linmu.knight.entity.ResponseData;
import cdu.linmu.knight.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：xxx_
 * @date ：Created in 2021/3/22 3:29 下午
 * @description：测试Controller
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/add")
    public ResponseData addUser(@RequestBody User user){
        return new ResponseData(ResponseCode.SUCCESS);
    }


}
