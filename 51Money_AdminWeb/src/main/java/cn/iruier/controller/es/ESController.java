package cn.iruier.controller.es;

import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.admin.News;
import cn.iruier.mapper.admin.NewsMapper;
import cn.iruier.service.activemq.ActiveMQService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/8/9 21:48
 */

/*@RestController
public class ESController {

    @Autowired
    private NewsMapper mapper;

    @Autowired
    private ActiveMQService activeMQService;

    @GetMapping("/save")
    public ResultVo saveNewsES() {
        List<News> news = mapper.queryAll();
        String json = JSON.toJSONString(news);
        activeMQService.senMsg(JSON.toJSONString(new ResultVo(10001, json, null)));
        return ResultVo.setSuccess("OK");
    }
}*/
