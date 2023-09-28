package com.quxue.wedding_host.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_role")
public class Role {
    @TableId(value = "rid", type = IdType.AUTO)
    private Integer rid;
    @NotBlank
    private String rname;
    private String rdesc;
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer status;

    @TableField(exist = false)
    private List<Integer> selectedNode;

    public Role(String rname) {
        this.rname = rname;
    }

    public Role(String rname, Integer rid) {
        this.rname = rname;
        this.rid = rid;
    }
}
