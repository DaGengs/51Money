package cn.iruier.uicontroller.pay;

import cn.iruier.core.jedis.JedisUtil;
import cn.iruier.core.pay.PayCommonUtil;
import cn.iruier.core.pay.PayConfig;
import cn.iruier.core.pay.XmlUtil;
import cn.iruier.core.pay.ZxingUtil;
import cn.iruier.core.util.CookieUtil;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.user.User;
import cn.iruier.uicontroller.base.BaseUtil;
import com.alibaba.fastjson.JSON;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 * @Author: iruier
 * @Date: 2018/8/11 11:47
 */
@RestController
@RequestMapping("/invest")
public class WeiXinPayController {

    @Autowired
    private JedisUtil jedisUtil;

/*    @Autowired
    private InvestService service;*/

    @PostMapping("payPre")
    public ResultVo payPre(String price,HttpServletRequest request) throws Exception {
        User user = BaseUtil.getUser(jedisUtil, request);
        String oid = PayCommonUtil.getCurrTime() + PayCommonUtil.buildRandom(6);
        String body = "信用贷投资";
        String url = PayCommonUtil.weixin_pay(price, body, oid);
        jedisUtil.addStr(user.getUsername(), url, TimeUnit.HOURS, 2);
        return ResultVo.setSuccess("支付信息生成成功");
    }

    @GetMapping("payImage")
    public void payImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = BaseUtil.getUser(jedisUtil, request);
        String url = jedisUtil.getStr(user.getUsername());
        BufferedImage image;
        if (url !=null && url.length() > 0) {
            image = ZxingUtil.createImage(url, 300, 300);
        } else {
            image = ZxingUtil.createImage("支付失效，请重新下单", 300, 300);
        }
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }

    @RequestMapping("payCallBack")
    public ResultVo payCallBack(HttpServletRequest request) throws IOException, JDOMException {
        ServletInputStream inputStream = request.getInputStream();
        byte[] data = new byte[1024];
        int length;
        StringBuffer buffer = new StringBuffer();
        while ((length=inputStream.read(data)) != -1) {
            buffer.append(new String(data, 0, length));
        }
        Map<String, String> map = XmlUtil.doXMLParse(buffer.toString());
        SortedMap<Object, Object> sortedMap = new TreeMap();
        for (String key : map.keySet()) {
            String value = map.get(key);
            if (value.length() > 0) {
                sortedMap.put(key, value);
            }
        }
        if (PayCommonUtil.isTenpaySign("UTF-8", sortedMap, PayConfig.API_KEY)) {
            if (map.get("return_code").equals("SUCCESS")) {
                if (map.get("result_code").equals("SUCCESS")) {
                    System.err.println("订单id:"+map.get("out_trade_no")+"----成功！");
                    for (String key : map.keySet()) {
                        System.err.println("key:" + key);
                        System.out.println("value:" + map.get(key));
                    }
                }
            }
        }
        return ResultVo.setSuccess("交易完成");
    }
}
