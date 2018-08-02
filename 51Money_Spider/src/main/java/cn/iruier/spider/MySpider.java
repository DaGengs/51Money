package cn.iruier.spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @Author: iruier
 * @Date: 2018/7/31 18:13
 */
public class MySpider implements PageProcessor {
    @Override
    public void process(Page page) {

        page.addTargetRequests(page.getHtml().links().all());
        page.putField("标题:", page.getHtml().xpath("h1/text()").toString());
        page.putField("时间:", page.getHtml().xpath("[@class='la_t_a']/text()").toString());
        page.putField("内容:", page.getHtml().xpath("p/text()").all());
    }

    @Override
    public Site getSite() {
        return Site.me().setRetryTimes(10).setSleepTime(200);
    }
}
