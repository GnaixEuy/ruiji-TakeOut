package cn.limitless.ruijitakeout.exception;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * <p>项目： ruiji-TakeOut </p>
 * 自定义业务异常类
 *
 * @author GnaixEuy
 * @date 2022/5/8
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
