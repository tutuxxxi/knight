package cdu.linmu.knight.controller;

import cdu.linmu.knight.entity.Follow;
import cdu.linmu.knight.entity.ResponseCode;
import cdu.linmu.knight.entity.ResponseData;
import cdu.linmu.knight.service.FollowService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ：xxx_
 * @date ：Created in 2021/6/8 12:04 下午
 * @description：
 * @modified By：
 * @version:
 */
@CrossOrigin
@RestController
@RequestMapping("/follow")
public class FollowController {

    @Resource
    FollowService followService;

    @ApiOperation("关注")
    @PostMapping("/follow")
    public ResponseData follow(@RequestBody Follow follow){
        return followService.follow(follow);
    }

    @ApiOperation("取关")
    @DeleteMapping("/unfollow")
    public ResponseData unfollow(@RequestBody Follow follow){
        if(followService.unfollow(follow)){
            return new ResponseData(ResponseCode.SUCCESS);
        }
        return new ResponseData(ResponseCode.FAILED);
    }

    @ApiOperation("获取粉丝数")
    @GetMapping("/getFansNum")
    public ResponseData getFansNum(String userId){
        return new ResponseData(ResponseCode.SUCCESS, followService.getFansNum(userId));
    }

    @ApiOperation("获取粉丝列表")
    @GetMapping("/listFans")
    public ResponseData listFans(@RequestParam(required = true) String userId, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10")  Integer pageSize){
        return new ResponseData(ResponseCode.SUCCESS, followService.getFansList(userId, pageNum, pageSize));
    }

    @ApiOperation("获取关注人数")
    @GetMapping("/getFollowNum")
    public ResponseData getFollowNum(String userId){
        return new ResponseData(ResponseCode.SUCCESS, followService.getFollowsNum(userId));
    }

    @ApiOperation("获取关注列表")
    @GetMapping("/listFollows")
    public ResponseData listFollows(@RequestParam(required = true) String userId, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10")  Integer pageSize){
        return new ResponseData(ResponseCode.SUCCESS, followService.getFollowsList(userId, pageNum, pageSize));
    }

}
