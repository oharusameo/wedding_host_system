package com.quxue.wedding_host.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_role")
public class Role {
    @TableId(value = "rid", type = IdType.AUTO)
    private Integer rId;
    @NotBlank
    private String rname;
    private String rdesc;
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer status;

    public Role(String rname) {
        this.rname = rname;
    }
}
