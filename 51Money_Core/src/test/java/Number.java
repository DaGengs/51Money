import cn.iruier.core.pay.PayCommonUtil;

/**
 * @Author: iruier
 * @Date: 2018/8/11 14:52
 */
public class Number {
    public static void main(String[] args) {
        System.out.println(PayCommonUtil.getCurrTime());
        System.out.println(PayCommonUtil.getCurrTime()+PayCommonUtil.buildRandom(6));
    }
}
