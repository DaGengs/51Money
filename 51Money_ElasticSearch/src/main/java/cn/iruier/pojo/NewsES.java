package cn.iruier.pojo;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @Author: iruier
 * @Date: 2018/8/9 11:51
 */
@Document(indexName = "51moneynews", type = "news")
public class NewsES {
    private int id;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public NewsES(int id) {
        this.id = id;
    }

    public NewsES() {
    }
}
