package cn.limitless.ruijitakeout.utils;

import java.util.Random;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 * <p>
 * 项目： ruiji-TakeOut
 * 随机生成验证码工具类
 *
 * @author GnaixEuy
 * @date 2022/5/7
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */


public class ValidateCodeUtils {
    /**
     * 随机生成验证码
     *
     * @param length 长度为4位或者6位
     * @return Integer 随机生成验证码
     */
    public static Integer generateValidateCode(int length) {
        Integer code = null;
        if (length == 4) {
            //生成随机数，最大为9999
            code = new Random().nextInt(9999);
            if (code < 1000) {
                //保证随机数为4位数字
                code = code + 1000;
            }
        } else if (length == 6) {
            //生成随机数，最大为999999
            code = new Random().nextInt(999999);
            if (code < 100000) {
                //保证随机数为6位数字
                code = code + 100000;
            }
        } else {
            throw new RuntimeException("只能生成4位或6位数字验证码");
        }
        return code;
    }

    /**
     * 随机生成指定长度字符串验证码
     *
     * @param length 长度
     * @return String 随机生成指定长度字符串验证码
     */
    public static String generateValidateCode4String(int length) {
        Random rdm = new Random();
        String hash1 = Integer.toHexString(rdm.nextInt());
        String capstr = hash1.substring(0, length);
        return capstr;
    }
}

