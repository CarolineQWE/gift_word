package com.soft.gift.controller;

import com.alibaba.fastjson.JSON;
import com.soft.gift.model.Gift;
import com.soft.gift.model.GiftInfo;
import com.soft.gift.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class GiftManagerController {
	@Autowired
    private GiftService giftService;

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
        giftService.updateGiftById(gift);
        GiftInfo giftInfo = new GiftInfo(gift_id,brief,content);
        giftService.updateGiftInfoByGiftId(giftInfo);
        String json = JSON.toJSONString(gift);
        return json;
    }
}
