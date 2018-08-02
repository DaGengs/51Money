/**
 * @Author: iruier
 * @Date: 2018/8/2 8:57
 */
public class Num {
    public static void main(String[] args) {
        for (int i = 2 ; i < 101; i++) {
            boolean b = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    b = false;
                    break;
                }
            }
            if (b) {
                System.out.println(i);
            }
        }
    }
}
/**
 * 第一次循环 i=2; j=2; i%j==0--->b = true; 2
 * 第二次循环 i=3; j=2; i%j!=0--->b = false;
 *           i=3; j=3; i%j==0--->b = true; 3
 * 第三次循环 i=4; j=2; i%j!=0--->b = false;
 *           i=4; j=3; i%j!=0--->b = true;
 *           i=4; j=4; i%j==0--->b = false;不输出4
 * ...
 * */
