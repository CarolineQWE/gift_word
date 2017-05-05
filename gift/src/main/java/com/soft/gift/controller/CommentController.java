package com.soft.gift.controller;

import com.soft.gift.model.Comment;
import com.soft.gift.model.UserInfo;
import com.soft.gift.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

/**
 * Created by fyq on 2017/5/4.
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping("/strategy")
    public String commentStrategy(HttpServletRequest request,Integer commented_id,String content) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Comment comment = new Comment(commented_id,userInfo.getAccount(),content,timestamp,0,1,0,userInfo.getNickname(),null,null,userInfo.getHead());
        commentService.addComment(comment);
        return "成功";
    }

    @ResponseBody
    @RequestMapping("/replyComment")
    public String replyComment(HttpServletRequest request,Integer commented_id, String content,
                               String commented_account, String commented_nickname) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Comment replied_comment = commentService.getCommentByID(commented_id);
        Comment comment = new Comment(commented_id,userInfo.getAccount(),content, timestamp,0,2,1,userInfo.getNickname(),commented_account,commented_nickname,null);
        commentService.addComment(comment);
        return "成功";
    }

    @ResponseBody
    @RequestMapping("/gift")
    public String commentGift(HttpServletRequest request,Integer commented_id, String content,Integer score) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Comment comment = new Comment(commented_id,userInfo.getAccount(),content,timestamp,score,0,0,userInfo.getNickname(),null,null,userInfo.getHead());
        commentService.addComment(comment);
        return "成功";
    }
}
