package cdu.linmu.knight.controller;

import cdu.linmu.knight.entity.ResponseCode;
import cdu.linmu.knight.entity.ResponseData;
import cdu.linmu.knight.entity.Tweets;
import cdu.linmu.knight.service.TweetsService;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ：xxx_
 * @date ：Created in 2021/6/8 3:36 下午
 * @description：
 * @modified By：
 * @version:
 */

@CrossOrigin
@RestController
@RequestMapping("/tweets")
@Api("TweetsController")
public class TweetsController {

    @Resource
    TweetsService tweetsService;


    @ApiOperation("发布博客")
    @PostMapping("/post")
    public ResponseData postTweets(@RequestBody Tweets tweets){
        if(tweetsService.save(tweets)){
            return new ResponseData(ResponseCode.SUCCESS);
        }
        return new ResponseData(ResponseCode.FAILED);
    }


    @ApiOperation("删除博客")
    @DeleteMapping("/delete")
    public ResponseData deleteTweets(@RequestBody Tweets tweets){
        return tweetsService.deleteTweets(tweets);
    }


    @ApiOperation("查看博客, 返回vo")
    @GetMapping("/list")
    public ResponseData listTweets(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize){
        return new ResponseData(ResponseCode.SUCCESS, tweetsService.listTweets(pageNum, pageSize));
    }

    @ApiOperation("查看指定用户的博客")
    @GetMapping("/listMyTweets")
    public ResponseData listMyTweets(@RequestParam(required = true) String userId, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize){
        return new ResponseData(ResponseCode.SUCCESS, tweetsService.listMyTweets(userId, pageNum, pageSize));
    }
}
