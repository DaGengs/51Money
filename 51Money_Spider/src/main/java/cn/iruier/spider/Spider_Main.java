package cn.iruier.spider;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

/**
 * @Author: iruier
 * @Date: 2018/7/31 19:13
 */
public class Spider_Main {
    public static void main(String[] args) {
        Spider.create(new MySpider()).addUrl("http://finance.huanqiu.com/jinr/").addPipeline(new ConsolePipeline()).thread(10).start();
    }
}
