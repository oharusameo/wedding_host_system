package com.quxue.wedding_host.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.quxue.wedding_host.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    List<Integer> selectMenuIdByRole(@Param(Constants.WRAPPER) Wrapper<Integer> wrapper);

    Integer delRoleMenu(@Param("rid") Integer rid);

    Integer insertRoleMenu(@Param("rid") Integer rid,@Param("menuIds") List<Integer> menuIds);

    List<Integer> queryRid(@Param(Constants.WRAPPER) Wrapper<Integer> wrapper);
}
