package cn.iruier.controller.spider;

import cn.iruier.entity.admin.News;
import cn.iruier.service.admin.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;


@Service
public class RedisSavePepeLine implements Pipeline {

    @Autowired
    private SpiderService spiderService;
    @Override
    public void process(ResultItems resultItems, Task task) {
        if(resultItems.get("news")!=null){
            News news=resultItems.get("news");
            if(news.getTitle()!=null && news.getTitle().length()>0) {
                spiderService.saveNews(resultItems.get("news"));
            }
        }
    }
}
