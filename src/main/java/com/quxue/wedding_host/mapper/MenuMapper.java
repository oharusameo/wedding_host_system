package com.quxue.wedding_host.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.quxue.wedding_host.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> queryByRole(@Param(Constants.WRAPPER) Wrapper<Integer> wrapper);

    @Select("select rid from t_role_menu where mid=#{mid}")
    List<Integer> queryBeforeDelMenu(Integer mid);

    @Select("select count(*) from t_menu where pid=#{mid}")
    Integer queryChildrenBeforeDel(Integer mid);
}
