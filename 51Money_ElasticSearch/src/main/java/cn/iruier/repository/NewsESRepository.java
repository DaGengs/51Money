package cn.iruier.repository;

import cn.iruier.pojo.NewsES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/8/9 11:53
 */
public interface NewsESRepository extends ElasticsearchRepository<NewsES, Integer> {

    List<NewsES> findByTitleLike(String title);
}
