package cn.iruier.core.util;

import cn.iruier.core.vo.PageVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/7/31 17:24
 */
public class PageUtil {
    public static PageVo getPageVo(List data, int count) {
        PageVo pageVo = new PageVo();
        if (data != null) {
            pageVo.setCode(0);
            pageVo.setCount(count);
            pageVo.setMsg("");
            pageVo.setData(data);
        } else {
            pageVo.setCode(1);
            pageVo.setMsg("暂无数据");
            pageVo.setCount(0);
            pageVo.setData(new ArrayList<>());
        }
        return pageVo;
    }
}
