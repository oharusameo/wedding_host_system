package com.quxue.wedding_host.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_host")
public class Host {
    @TableId(value = "hid", type = IdType.AUTO)
    private Integer hid;
    private String hname;
    private String hpwd;
    private String hphone;
    private String starttime;
    private String strong;

    private Integer ordernumber;
    private Integer hprice;
    private String image;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer status;


}
