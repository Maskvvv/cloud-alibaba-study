package com.zhy.module;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * @author zhouhongyin
 * @since 2024/4/14 11:04
 */
@Data
@TableName("`user`")
public class User extends Model<User> {
    @TableId
    private Integer id;
    private String name;
    private Integer age;

    private GradeEnum type;
}
