package com.soft.gift.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soft.gift.mapper.StrategyDAO;
import com.soft.gift.model.Category;
import com.soft.gift.model.Gift;
import com.soft.gift.model.Strategy;
import com.soft.gift.model.UserInfo;
import com.soft.gift.service.PicService;
import com.soft.gift.service.StrategyService;
import com.soft.gift.util.FileUtil;
import com.soft.gift.util.IPTimeStamp;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
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
    private PicService picService;

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
    @RequestMapping(value = "/strategy/uploadImg")
    public Map uploadImg(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        Map returnMap = new HashMap();
        String callbackcontent = null;
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
            String occasion,String style,String title, String content,String src,Integer ifPublish) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Strategy strategy = new Strategy(title,content,src,0,0, timestamp,
                relationship,style,occasion,userInfo.getAccount(),0,0,ifPublish);
        strategyService.addStrategy(strategy);
        return "strategy";
    }
}