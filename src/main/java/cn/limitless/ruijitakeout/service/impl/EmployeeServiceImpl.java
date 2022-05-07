package cn.limitless.ruijitakeout.service.impl;

import cn.limitless.ruijitakeout.dao.EmployeeDao;
import cn.limitless.ruijitakeout.entity.Employee;
import cn.limitless.ruijitakeout.service.EmployeeService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * <p>项目： ruiji-TakeOut </p>
 *
 * @author GnaixEuy
 * @date 2022/5/7
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeDao,Employee> implements EmployeeService {

}
