package cn.iruier.service;

import cn.iruier.core.vo.ResultVo;
import cn.iruier.pojo.NewsES;

import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/8/9 11:54
 */
public interface NewsESService {


    List<NewsES> queryAll(String title);
}
