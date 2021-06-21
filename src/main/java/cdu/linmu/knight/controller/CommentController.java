package cdu.linmu.knight.controller;

import cdu.linmu.knight.entity.Comment;
import cdu.linmu.knight.entity.ResponseCode;
import cdu.linmu.knight.entity.ResponseData;
import cdu.linmu.knight.service.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ：xxx_
 * @date ：Created in 2021/6/8 4:45 下午
 * @description：
 * @modified By：
 * @version:
 */
@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    CommentService commentService;


    @ApiOperation("发布评论")
    @PostMapping("/post")
    public ResponseData postComment(@RequestBody Comment comment){
        return commentService.postComment(comment);
    }

    @ApiOperation("获取评论")
    @GetMapping("/list")
    public ResponseData listComments(@RequestParam(required = true) String tweetsId, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10")  Integer pageSize){
        return new ResponseData(ResponseCode.SUCCESS, commentService.listComments(tweetsId, pageNum, pageSize));
    }

    @ApiOperation("删除评论")
    @DeleteMapping("/delete")
    public ResponseData deleteComment(@RequestBody Comment comment){
        if(commentService.deleteComment(comment)){
            return new ResponseData(ResponseCode.SUCCESS);
        }
        return new ResponseData(ResponseCode.FAILED);
    }


}
