package com.quxue.wedding_host.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_menu")
public class Menu implements Serializable {
    @TableId(value = "mid", type = IdType.AUTO)
    private Integer menuId;
    @TableField("mname")
    private String menuName;
    @TableField("pid")
    private String parentId;
    private String url;
    private String mdesc;
}
