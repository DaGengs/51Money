package cn.iruier.controller;

import cn.iruier.pojo.NewsES;
import cn.iruier.service.NewsESService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/8/9 21:59
 */

@Controller
public class NewsESController {

    @Autowired
    private NewsESService service;

    @RequestMapping("/getList.do")
    public List<NewsES> getList(String title) {
        return service.queryAll(title);
    }
}
