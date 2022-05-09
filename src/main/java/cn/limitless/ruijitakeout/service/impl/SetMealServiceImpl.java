package cn.limitless.ruijitakeout.service.impl;

import cn.limitless.ruijitakeout.dao.SetMealDao;
import cn.limitless.ruijitakeout.dto.SetMealDto;
import cn.limitless.ruijitakeout.entity.SetMeal;
import cn.limitless.ruijitakeout.entity.SetMealDish;
import cn.limitless.ruijitakeout.exception.CustomException;
import cn.limitless.ruijitakeout.service.SetMealDishService;
import cn.limitless.ruijitakeout.service.SetMealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * <p>项目： ruiji-TakeOut </p>
 *
 * @author GnaixEuy
 * @date 2022/5/8
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@Service
public class SetMealServiceImpl extends ServiceImpl<SetMealDao, SetMeal> implements SetMealService {


    private SetMealDishService setMealDishService;

    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     *
     * @param setmealDto
     */
    @Transactional
    public void saveWithDish(SetMealDto setmealDto) {
        //保存套餐的基本信息，操作setmeal，执行insert操作
        this.save(setmealDto);

        List<SetMealDish> setmealDishes = setmealDto.getSetMealDishes();
        setmealDishes.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        //保存套餐和菜品的关联信息，操作setmeal_dish,执行insert操作
        setMealDishService.saveBatch(setmealDishes);
    }

    /**
     * 删除套餐，同时需要删除套餐和菜品的关联数据
     *
     * @param ids
     */
    @Transactional
    public void removeWithDish(List<Long> ids) {
        //select count(*) from setmeal where id in (1,2,3) and status = 1
        //查询套餐状态，确定是否可用删除
        LambdaQueryWrapper<SetMeal> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.in(SetMeal::getId, ids);
        queryWrapper.eq(SetMeal::getStatus, 1);

        long count = this.count(queryWrapper);
        if (count > 0) {
            //如果不能删除，抛出一个业务异常
            throw new CustomException("套餐正在售卖中，不能删除");
        }

        //如果可以删除，先删除套餐表中的数据---setmeal
        this.removeByIds(ids);

        //delete from setmeal_dish where setmeal_id in (1,2,3)
        LambdaQueryWrapper<SetMealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SetMealDish::getSetmealId, ids);
        //删除关系表中的数据----setmeal_dish
        setMealDishService.remove(lambdaQueryWrapper);
    }

    @Autowired
    public void setSetMealDishService(SetMealDishService setMealDishService) {
        this.setMealDishService = setMealDishService;
    }
}
