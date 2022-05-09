package cn.limitless.ruijitakeout.controller;

import cn.limitless.ruijitakeout.dto.SetMealDto;
import cn.limitless.ruijitakeout.entity.Category;
import cn.limitless.ruijitakeout.entity.SetMeal;
import cn.limitless.ruijitakeout.service.CategoryService;
import cn.limitless.ruijitakeout.service.SetMealDishService;
import cn.limitless.ruijitakeout.service.SetMealService;
import cn.limitless.ruijitakeout.vo.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * <p>项目： ruiji-TakeOut </p>
 * 套餐管理
 *
 * @author GnaixEuy
 * @date 2022/5/9
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetmealController {


    private SetMealService setMealService;

    private CategoryService categoryService;

    private SetMealDishService setMealDishService;

    /**
     * 新增套餐
     *
     * @param setMealDto
     * @return
     */
    @PostMapping
    public Result<String> save(@RequestBody SetMealDto setMealDto) {
        log.info("套餐信息：{}", setMealDto);

        setMealService.saveWithDish(setMealDto);

        return Result.success("新增套餐成功");
    }

    /**
     * 套餐分页查询
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Result<Page> page(int page, int pageSize, String name) {
        //分页构造器对象
        Page<SetMeal> pageInfo = new Page<>(page, pageSize);
        Page<SetMealDto> dtoPage = new Page<>();

        LambdaQueryWrapper<SetMeal> queryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件，根据name进行like模糊查询
        queryWrapper.like(name != null, SetMeal::getName, name);
        //添加排序条件，根据更新时间降序排列
        queryWrapper.orderByDesc(SetMeal::getUpdateTime);

        setMealService.page(pageInfo, queryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(pageInfo, dtoPage, "records");
        List<SetMeal> records = pageInfo.getRecords();

        List<SetMealDto> list = records.stream().map((item) -> {
            SetMealDto setMealDto = new SetMealDto();
            //对象拷贝
            BeanUtils.copyProperties(item, setMealDto);
            //分类id
            Long categoryId = item.getCategoryId();
            //根据分类id查询分类对象
            Category category = categoryService.getById(categoryId);
            if (category != null) {
                //分类名称
                String categoryName = category.getName();
                setMealDto.setCategoryName(categoryName);
            }
            return setMealDto;
        }).collect(Collectors.toList());

        dtoPage.setRecords(list);
        return Result.success(dtoPage);
    }

    /**
     * 删除套餐
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    public Result<String> delete(@RequestParam List<Long> ids) {
        log.info("ids:{}", ids);

        setMealService.removeWithDish(ids);

        return Result.success("套餐数据删除成功");
    }

    @GetMapping("/list")
    public Result<List<SetMeal>> list(SetMeal setMeal) {
        log.info("setmeal:{}", setMeal);
        //条件构造器
        LambdaQueryWrapper<SetMeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(setMeal.getName()), SetMeal::getName, setMeal.getName());
        queryWrapper.eq(null != setMeal.getCategoryId(), SetMeal::getCategoryId, setMeal.getCategoryId());
        queryWrapper.eq(null != setMeal.getStatus(), SetMeal::getStatus, setMeal.getStatus());
        queryWrapper.orderByDesc(SetMeal::getUpdateTime);

        return Result.success(setMealService.list(queryWrapper));
    }

    @Autowired
    public void setSetMealService(SetMealService setMealService) {
        this.setMealService = setMealService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setSetMealDishService(SetMealDishService setMealDishService) {
        this.setMealDishService = setMealDishService;
    }
}
