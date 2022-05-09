package cn.limitless.ruijitakeout.service;

import cn.limitless.ruijitakeout.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * <p>项目： ruiji-TakeOut </p>
 *
 * @author GnaixEuy
 * @date 2022/5/9
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
public interface OrderService extends IService<Orders> {
    /**
     * 用户下单
     *
     * @param orders
     */
    void submit(Orders orders);
}
