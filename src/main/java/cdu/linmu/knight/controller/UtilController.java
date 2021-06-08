package cdu.linmu.knight.controller;

import cdu.linmu.knight.entity.ResponseCode;
import cdu.linmu.knight.entity.ResponseData;
import cdu.linmu.knight.util.UploadUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author ：xxx_
 * @date ：Created in 2021/3/23 3:24 下午
 * @description：
 * @modified By：
 * @version:
 */
@CrossOrigin
@RestController
@RequestMapping("/util")
public class UtilController {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("uploadImages")
    public ResponseData uploadImages(MultipartFile[] files){
        if(files != null){
            //准备一个uuid
            String key = UUID.randomUUID().toString();

            //进行图片的保存
            try{
                List<String> list = new ArrayList<>(files.length);
                for(MultipartFile file : files){
                    String url = UploadUtil.saveFile(file);
                    if(url != null){
                        list.add(url);
                    }else{
                        return new ResponseData(500, "上传失败");
                    }
                }
                //存入redis
                ValueOperations<String, String> operations = redisTemplate.opsForValue();
                operations.set(key, objectMapper.writeValueAsString(list));

                //返回内容
                return new ResponseData(ResponseCode.SUCCESS, key);
            } catch (IOException e) {
                e.printStackTrace();
                return new ResponseData(500, "上传失败");
            }
        }

        return new ResponseData(500, "上传内容为空");
    }

}
