package cdu.linmu.knight.controller;

import cdu.linmu.knight.entity.ResponseCode;
import cdu.linmu.knight.entity.ResponseData;
import cdu.linmu.knight.entity.User;
import cdu.linmu.knight.service.UserService;
import cdu.linmu.knight.util.DataCheckUtil;
import cdu.linmu.knight.util.SecureUtil;
import cdu.linmu.knight.util.UploadUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.DateUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author ：xxx_
 * @date ：Created in 2021/3/22 4:21 下午
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;


    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    ObjectMapper objectMapper;



    @PostMapping("/register")
    public ResponseData register(@RequestBody User user){
        if(DataCheckUtil.registerCheck(user)){
            try{
                if(userService.save(user)){
                    SecureUtil.clearSecureInfo(user);
                    return new ResponseData(ResponseCode.SUCCESS, user);
                }else{
                    return new ResponseData(500, "手机号已被注册过");
                }
            }catch (Exception e){
                e.printStackTrace();
                return new ResponseData(500, "未知错误");
            }
        }
        return new ResponseData(500, "注册信息不完整");
    }


    @GetMapping("login")
    public ResponseData login(@RequestBody User user){
        if(DataCheckUtil.loginCheck(user)){
            try{
                User login = userService.login(user);
                if(login != null){
                    SecureUtil.clearSecureInfo(login);
                    return new ResponseData(ResponseCode.SUCCESS, login);
                }else{
                    return new ResponseData(500, "账号或密码错误");
                }
            }catch (Exception e){
                e.printStackTrace();
                return new ResponseData(500, "未知错误");
            }
        }
        return new ResponseData(500, "信息不完整");
    }


    @PostMapping("/update")
    public ResponseData updateInfo(@RequestBody User user){

        try{
            if(StringUtils.hasText(user.getId())){
                //如果用户拥有图片上传
                if(StringUtils.hasText(user.getProfile())){
                    String s = redisTemplate.opsForValue().get(user.getProfile());

                    if(s == null){
                        return new ResponseData(500, "图片信息丢失");
                    }

                    redisTemplate.delete(user.getProfile());
                    List<String> list = objectMapper.readValue(s, new TypeReference<List<String>>() {});
                    user.setProfile(list.get(0));
                }

                user = userService.update(user);
                if(user != null){
                    SecureUtil.clearSecureInfo(user);
                    return new ResponseData(ResponseCode.SUCCESS, user);
                }else{
                    return new ResponseData(500, "id错误或手机号已存在");
                }
            }
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ResponseData(500, "用户id缺失");
    }



}
