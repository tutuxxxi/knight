package cdu.linmu.knight.controller;

import cdu.linmu.knight.entity.ResponseCode;
import cdu.linmu.knight.entity.ResponseData;
import cdu.linmu.knight.entity.User;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author ：xxx_
 * @date ：Created in 2021/3/22 3:29 下午
 * @description：测试Controller
 * @modified By：
 * @version:
 */
@CrossOrigin
@RestController
@ApiIgnore
@RequestMapping("/test")
public class TestController {

    @PostMapping("/add")
    public ResponseData addUser(@RequestBody User user){
        return new ResponseData(ResponseCode.SUCCESS);
    }


}
