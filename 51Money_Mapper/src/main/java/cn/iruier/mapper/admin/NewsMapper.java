package cn.iruier.mapper.admin;

import cn.iruier.entity.admin.News;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/8/1 12:01
 */
public interface NewsMapper {

    @Insert("insert into t_news(title, contentHtml, lastTime, sourceName, sourceUrl, refHtml, createTime, status) values(#{title},#{contentHtml},#{lastTime},#{sourceName},#{sourceUrl},#{refHtml},now(), 1)")
    int insertNews(News news);

    @Select("select * from t_news where status = 1 order by lastTime desc")
    @ResultType(News.class)
    List<News> queryAll();
}
