package cn.iruier.service.admin.impl;

import cn.iruier.entity.admin.News;
import cn.iruier.mapper.admin.NewsMapper;
import cn.iruier.service.admin.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/8/1 14:13
 */
@Service
public class SpiderServiceImpl implements SpiderService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public void saveNews(News news) {
        newsMapper.insertNews(news);
    }

    @Override
    public List<News> queryAll() {
        return newsMapper.queryAll();
    }
}
