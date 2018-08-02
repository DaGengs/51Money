package cn.iruier.service.admin;

import cn.iruier.entity.admin.News;

import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/8/1 14:11
 */
public interface SpiderService {
    void saveNews(News news);

    List<News> queryAll();
}
