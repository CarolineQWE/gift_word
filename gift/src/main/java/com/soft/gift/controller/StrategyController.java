package com.soft.gift.controller;

import com.alibaba.fastjson.JSON;
import com.soft.gift.model.Comment;
import com.soft.gift.model.Strategy;
import com.soft.gift.model.UserInfo;
import com.soft.gift.service.CommentService;
import com.soft.gift.service.StrategyService;
import com.soft.gift.service.UserService;
import com.soft.gift.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by fyq on 2017/4/18.
 */
@Controller
public class StrategyController {
    @Autowired
    private StrategyService strategyService;
    @Autowired
    private ServletContext context;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/strategy/all")
    public String strategy(HttpServletRequest request, ModelMap map) {
        List<Strategy> latestStras = new ArrayList<>();
        List<Strategy> hotestStras = new ArrayList<>();
        latestStras = strategyService.getLatestStras();
        hotestStras = strategyService.getHotestStras();
        map.put("latestStras", latestStras);
        map.put("hotestStras", hotestStras);
        return "strategy";
    }

    @RequestMapping(value = "/strategy/addStrategy")
    public String addStrategy(HttpServletRequest request, ModelMap map) {
        return "addStrategy";
    }

    @ResponseBody
    @RequestMapping(value = "/uploadImg")
    public Map uploadImg(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map returnMap = new HashMap();
       // String callbackcontent = null;
        try {
            FileUtil fileUtil = new FileUtil();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            returnMap = fileUtil.uploadEditor(file, "/upload/" + simpleDateFormat.format(new Date()), request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnMap;
    }

    @RequestMapping(value = "/strategy/submitStra")
    public String submitStra(HttpServletRequest request,String relationship,
            String occasion,String style,String title, String content,String src,Integer ifPublish,String brief) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Strategy strategy = new Strategy(title,content,src,0,0, timestamp,
                relationship,style,occasion,userInfo.getAccount(),0,0,ifPublish,brief);
        strategyService.addStrategy(strategy);
        return "strategy";
    }

    @ResponseBody
    @RequestMapping(value = "/strategy/strategies")
    public String selectStra(HttpServletRequest request,ModelMap map,
                             @RequestParam(value = "relationship",required = false) String relationship,
                             @RequestParam(value = "occasion" ,required = false) String occasion,
                             @RequestParam(value = "style",required = false) String style) throws UnsupportedEncodingException {
       // List<Strategy> strategies = strategyService.findStraByKeyword(keyword);
        request.setCharacterEncoding("utf-8");
        List<Strategy> strategies = new ArrayList<>();
        System.out.println("rel:"+relationship);
        System.out.println("occ:"+occasion);
        System.out.println("sty:"+style);
        if(relationship.equals("")||relationship.equals("null")){
            relationship = null;
        }
        if(style.equals("")||style.equals("null")){
            style = null;
        }
        if(occasion.equals("")||occasion.equals("null")){
            occasion = null;
        }
        strategies = strategyService.findStra(relationship,occasion,style);
        System.out.println(strategies);
        String json = JSON.toJSONString(strategies);
        System.out.println("json"+json);
        return json;
    }

    @RequestMapping(value = "/strategy/strategyInfo")
    public String getStrategyInfo(@RequestParam(value = "id",required = true) Integer id,ModelMap map){
        System.out.print(id);
        Strategy strategy = new Strategy();
        strategy.setId(id);
        Strategy strategy1 = strategyService.findStraById(id);
        String account = strategy1.getAccount();
        UserInfo userInfo = new UserInfo();
        userInfo = userService.getUserInfo(account);
        Map<Comment,List<Comment>> commentListMap = new HashMap<>();
        commentListMap = commentService.getComments(id);
        map.put("author_userInfo",userInfo);
        map.put("strategy",strategy1);
        map.put("commentListMap",commentListMap);
        return "strategyInfo";
    }

    @ResponseBody
    @RequestMapping(value = "/strategy/addLike")
    public String addLike(@RequestParam(value = "id",required = true) Integer id){
        strategyService.addLike(id);
        return "成功";
    }

    @ResponseBody
    @RequestMapping(value = "/strategy/addDislike")
    public String addDislike(@RequestParam(value = "id",required = true) Integer id){
        strategyService.addDislike(id);
        return "成功";
    }

    @ResponseBody
    @RequestMapping(value = "/strategy/addCollect")
    public String addCollect(@RequestParam(value = "id",required = true) Integer id){
        strategyService.addCollect(id);
        System.out.println("id:"+id);
        return "成功";
    }
}