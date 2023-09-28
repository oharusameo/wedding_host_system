package com.quxue.wedding_host.pojo;

import com.baomidou.mybatisplus.annotation.*;
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
    private Integer parentId;
    private String url;
    private String mdesc;


    public Menu(String menuName, Integer parentId, String url, String mdesc) {
        this.menuName = menuName;
        this.parentId = parentId;
        this.url = url;
        this.mdesc = mdesc;
    }
}
