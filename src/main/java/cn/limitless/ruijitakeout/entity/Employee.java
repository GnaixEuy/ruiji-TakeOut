package cn.limitless.ruijitakeout.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 * <p> 项目： ruiji-TakeOut </p>
 * 员工实体
 *
 * @author GnaixEuy
 * @date 2022/5/7
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@Data
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String name;

    private String password;

    private String phone;

    private String sex;

    /**
     * 身份证号码
     */
    private String idNumber;

    private Integer status;

    /**
     * 插入时填充字段
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 插入和更新时填充字段
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 插入时填充字段
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * 插入和更新时填充字段
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

}