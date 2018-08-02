package cn.iruier.controller.spider;


import cn.iruier.core.ftl.FtlUtil;
import cn.iruier.core.util.DateUtil;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.admin.News;
import cn.iruier.service.admin.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Spider;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/8/1 16:12
 */
@RestController
public class SpiderController {

    Spider spider;

    @Autowired
    private SpiderService spiderService;

    @Autowired
    private RedisSavePepeLine redisSavePepeLine;

    @Autowired
    private FtlUtil ftlUtil;

    @GetMapping("/spiderStart.do")
    public ResultVo start() {
        spider = Spider.create(new FinanceProcessor());
        spider.thread(10).addUrl("http://finance.huanqiu.com/jinr/").addPipeline(redisSavePepeLine).start();
        return ResultVo.setSuccess("爬虫启动成功");
    }

    @GetMapping("/spiderStop.do")
    public ResultVo stop() {
        spider.stop();
        spider.close();
        return ResultVo.setSuccess("爬虫停止成功");
    }

    @GetMapping("/createHtml.do")
    public ResultVo createHtml(HttpServletRequest request) {
        String path = new File(request.getServletContext().getRealPath("/")).getAbsolutePath();

        new Thread(() -> {
            List<News> news = spiderService.queryAll();
            File dir = new File(path, DateUtil.getDate());
            if (!dir.exists()) {
                dir.mkdirs();
            }
            for (News n :news){
                ftlUtil.createHtml(path, "news.ftl", n, dir.getAbsolutePath()+File.separator+n.getId()+".html");
            }
        }).start();
        return ResultVo.setSuccess("创建成功");
    }
}
