package cn.iruier.service.impl;

import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.admin.News;
import cn.iruier.pojo.NewsES;
import cn.iruier.repository.NewsESRepository;
import cn.iruier.service.NewsESService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/8/9 11:54
 */
@Service
public class NewsESServiceImpl implements NewsESService, MessageListener {

    @Autowired
    private NewsESRepository repository;

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            System.out.println("收到消息：" + textMessage.getText());
            ResultVo resultVo = JSON.parseObject(textMessage.getText(), ResultVo.class);
            List<News> news = JSON.parseArray(resultVo.getMsg(), News.class);
            List<NewsES> newsESs = new ArrayList<>(news.size());
            for (int i = 0; i < news.size(); i++) {
                NewsES newsES = new NewsES();
                newsES.setId(news.get(i).getId());
                newsES.setTitle(news.get(i).getTitle());
                newsESs.add(newsES);
            }
            switch (resultVo.getCode()) {
                case 10001:
                    repository.saveAll(newsESs);
                    break;
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<NewsES> queryAll(String title) {
        return repository.findByTitleLike(title);
    }


}
