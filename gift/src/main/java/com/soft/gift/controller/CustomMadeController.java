package com.soft.gift.controller;

import com.soft.gift.model.*;
import com.soft.gift.service.CommentService;
import com.soft.gift.service.DesignService;
import com.soft.gift.service.GiftService;
import com.soft.gift.service.PicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by fyq on 2017/5/17.
 */
@Controller
public class CustomMadeController {
    @Autowired
    private GiftService giftService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private DesignService designService;
    @Autowired
    ServletContext context;

    @RequestMapping(value="/getCustomGift")
    public String searchGiftByLargeCateOrderByHot(HttpServletRequest request, ModelMap map){
        List<Gift> gifts = giftService.getCustomGift(0);
        map.put("gifts",gifts);
        return "custom_made";
    }

    @RequestMapping(value="/customMade/giftInfo")
    public String giftInfo2(HttpServletRequest request, Integer gift_id, ModelMap mv) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        Gift gift = giftService.getGiftByID(gift_id);
        System.out.print("gift_id"+gift_id);
        GiftInfo giftInfo = giftService.getGigtInfoByID(gift_id);
        List<Spec> saleSpecMap = giftService.getGiftSpecByGiftIDAndType(gift_id, 0);
        System.out.println(saleSpecMap);
        List<Spec> baseSpecMap = giftService.getGiftSpecByGiftIDAndType(gift_id, 1);
        List<SpecNum> specNums = giftService.getSpecNum(gift_id);
        System.out.println(specNums);
        int specTypeNum = specNums.size();
        System.out.println(specTypeNum);
        Map<String,List<Spec>> classifiedSpecMap = new HashMap<>();
        int i = 0;
        for (int j = 0; j < specTypeNum; j++){
            List<Spec> oneSpecName = new ArrayList<>();
            oneSpecName = saleSpecMap.subList(i ,i+specNums.get(j).getSpec_num());
            i += specNums.get(j).getSpec_num();
            classifiedSpecMap.put(specNums.get(j).getSpec_name(),oneSpecName);
        }
        System.out.println(classifiedSpecMap);
        List<Comment> comments = commentService.getCommentByGiftID(gift_id);
        Double avg = commentService.getAverageSorceByGiftID(gift_id);
        List<Integer> nums = commentService.getCommentNum(gift_id);
        Design design = designService.getDesignByGiftIdAndAccount(gift_id,userInfo.getAccount());
        boolean flag = false;//无方案
        if(design != null){
            flag = true;//有方案
        }
        mv.put("gift",gift);
        mv.put("giftInfo",giftInfo);
        mv.put("classifiedSpecMap",classifiedSpecMap);
        mv.put("baseSpecMap",baseSpecMap);
        mv.put("comments",comments);
        mv.put("avg",avg);
        mv.put("scoreNums",nums);
        mv.put("flag",flag);
        mv.put("design",design);
        return "custom_made_gift_info";
    }

    @ResponseBody
    @RequestMapping(value="/customMade/submit_plan")
    public String submit_plan(HttpServletRequest request, Integer gift_id,String design_img) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo" );
        Design design = new Design(gift_id,design_img,userInfo.getAccount(),1,0);
        designService.addDesign(design);
        return "成功";
    }

    @RequestMapping(value="/getAllDesign")
    public String getAllDesign(HttpServletRequest request,ModelMap map) {
        List<Design> designs = designService.getAllDesign();
        Map<Design,Gift> dgmap = new HashMap<>();
        for (Design design : designs) {
            Gift gift = giftService.getGiftByID(design.getGift_id());
            dgmap.put(design,gift);
        }
        map.put("designMap",dgmap);
        return "designs";
    }

    @RequestMapping(value="/myCustomMade")
    public String myCustomMade(HttpServletRequest request,ModelMap map) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        List<Design> designs = designService.getMyCustomMade(userInfo.getAccount());
        Map<Design,Gift> dgmap = new HashMap<>();
        for (Design design : designs) {
            Gift gift = giftService.getGiftByID(design.getGift_id());
            dgmap.put(design,gift);
        }
        map.put("designMap",dgmap);
        return "myCustomMade";
    }

    @RequestMapping(value="/getDesign")
    public String getDesign(HttpServletRequest request, ModelMap map, @RequestParam(value= "exam" ,required = false) Integer exam, @RequestParam(value = "achieve" ,required = false)Integer achieve,Integer num) {
        Design design = new Design();
        design.setIf_exam(exam);
        design.setIf_achieve(achieve);
        List<Design> designs = designService.getDesign(design);
        Map<Design,Gift> dgmap = new HashMap<>();
        for (Design  d: designs) {
            Gift gift = giftService.getGiftByID(d.getGift_id());
            dgmap.put(d,gift);
        }
        map.put("num",num);
        map.put("designMap",dgmap);
        return "designs";
    }

    @ResponseBody
    @RequestMapping(value="/uploadDesignImage")
    public String uploadDesignImg(HttpServletRequest request,String image) {
        PicService service=new PicService();
        Timestamp ipTimeStamp = new Timestamp(new Date().getTime());// 获取时间戳+随机IP
        SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
        String timeStamp = format.format(ipTimeStamp);
        String path=context.getRealPath("/designImages");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>path:"+path);
        String filename=service.base64ToImage(image,path);
        System.out.println(filename);
        return filename;
    }


    @ResponseBody
    @RequestMapping(value="/exam/design")
    public String examDesign(HttpServletRequest request,Integer if_achieve,Integer id) {
        Design design = new Design();
        design.setId(id);
        design.setIf_exam(1);
        design.setIf_achieve(if_achieve);
        designService.updateDesignById(design);
        return "成功";
    }
}
