package cn.limitless.ruijitakeout.dao;

import cn.limitless.ruijitakeout.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 * <p>
 * 项目： ruiji-TakeOut
 *
 * @author GnaixEuy
 * @date 2022/5/7
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@Mapper
public interface EmployeeDao extends BaseMapper<Employee> {
}
