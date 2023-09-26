package com.quxue.wedding_host.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_admin")
public class User implements Serializable {
    @TableId(value = "aid", type = IdType.AUTO)
    private Integer id;
    @TableField("aname")
    @NotBlank(message = "用户名不能为空")
    private String username;
    @TableField("apwd")
    @NotBlank(message = "密码不能为空")
    private String password;
    @TableField("aphone")
    private String phone;
    @TableField("rid")
    private Integer role;
    private String createTime;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer status;


    public User(String username,String phone){
        this.username=username;
        this.phone=phone;
    }

}
