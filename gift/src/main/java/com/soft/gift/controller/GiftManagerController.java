package com.soft.gift.controller;

import com.alibaba.fastjson.JSON;
import com.soft.gift.model.*;
import com.soft.gift.service.CollectService;
import com.soft.gift.service.CommentService;
import com.soft.gift.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

@Controller
public class GiftManagerController {
	@Autowired
    private GiftService giftService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CollectService collectService;

    @ResponseBody
    @RequestMapping(value = "gift/deleteGifts")
    public String deleteGifts(HttpServletRequest request,String str){
        giftService.batchDeleteGift(str);
        return "成功";
    }

    @ResponseBody
    @RequestMapping(value = "gift/deleteGift")
    public String deleteGift(HttpServletRequest request,Integer gift_id){
        giftService.deleteGift(gift_id);
        return "成功";
    }

    @ResponseBody
    @RequestMapping(value = "gift/updateGiftStatus")
    public String updateGiftStatus(HttpServletRequest request,Integer gift_id){
        giftService.updateGiftStatus(gift_id);
        return "成功";
    }

    @ResponseBody
    @RequestMapping(value = "gift/getGift")
    public String getGift(HttpServletRequest request, HttpServletResponse response,Integer gift_id){
        response.setContentType("text/plain;charset=UTF-8");
        GiftInfo giftInfo = giftService.getGigtInfoByID(gift_id);
        String json = JSON.toJSONString(giftInfo);
        /*JSONObject jsonObject = new JSONObject();
        jsonObject.put("title",gift.getName());
        jsonObject.put("stock",gift.getStock());*/
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "gift/modifyGift")
    public String modifyGift(HttpServletRequest request,Integer gift_id,String title,String brief,Double price,Integer stock,String content){
        Gift gift = new Gift();
        gift.setId(gift_id);
        gift.setName(title);
        gift.setStock(stock);
        gift.setPrice(price);
        giftService.updateGiftById(gift);//给bean设置了id，就可以用这一方法
        GiftInfo giftInfo = new GiftInfo(gift_id,brief,content);
        giftService.updateGiftInfoByGiftId(giftInfo);
        String json = JSON.toJSONString(gift);
        return json;
    }


    @ResponseBody
    @RequestMapping(value = "gift/submitGiftComment")
    public String commentGift(HttpServletRequest request,String text,Integer gift_id,Integer score){
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        Timestamp time = new Timestamp(System.currentTimeMillis());
        Comment comment = new Comment(gift_id,userInfo.getAccount(),text,time,score,0,0,userInfo.getNickname(),null,null,userInfo.getHead());
        commentService.addComment(comment);
        Gift gift = giftService.getGiftByID(gift_id);
        gift.setComment(gift.getComment()+1);
        giftService.updateGift(gift);
        return "成功";
    }

    @ResponseBody
    @RequestMapping(value = "/gift/addCollection")
    public String addCollect(HttpServletRequest request,@RequestParam(value = "id",required = true) Integer id){
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        Collections collections = new Collections(id,0,userInfo.getAccount());
        collectService.addCollect(collections);
        Gift gift = giftService.getGiftByID(id);
        gift.setCollection(gift.getCollection()+1);
        giftService.updateGift(gift);
        System.out.println("id:"+id);
        return "成功";
    }
}
