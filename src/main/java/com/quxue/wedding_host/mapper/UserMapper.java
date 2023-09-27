package com.quxue.wedding_host.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quxue.wedding_host.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<User> selectAll();

}
