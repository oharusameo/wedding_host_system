package com.quxue.wedding_host.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MenuVo {
    private Integer id;
    private String label;
    private String url;
    private String mdesc;
    private Integer parentId;
    private List<MenuVo> children = new ArrayList<>();
}
