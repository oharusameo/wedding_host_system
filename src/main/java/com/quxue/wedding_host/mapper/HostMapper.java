package com.quxue.wedding_host.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quxue.wedding_host.pojo.Host;
import com.quxue.wedding_host.vo.HostVo;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface HostMapper extends BaseMapper<Host> {

    @Update("update t_host set image= #{image} where hid =#{hid}")
    Integer updateAvatar(@Param("hid") Integer hid, @Param("image") String image);

    @Delete("delete from t_host where hid=#{hid}")
    Integer deleteHost(Integer hid);

    Integer batchDelHost(List<Integer> hostIds);

    List<HostVo> selectHosts(@Param("low") Integer low, @Param("high") Integer high, @Param("sort") Integer sort);

    @Select("select * from t_host  ${ew.customSqlSegment}")
    IPage<Host> selectAllHosts(Page<Host> page, @Param(Constants.WRAPPER) Wrapper<Host> wrapper);

    @Update("update t_host set hname=#{hname},hphone=#{hphone},hpwd=#{hpwd},strong=#{strong},ordernumber=#{ordernumber},hprice=#{hprice} where hid=#{hid}")
    Integer updateHost(Host host);

    @Select("select * from t_host where hid=#{hid}")
    HostVo selectHostByHid(Integer hid);

    List<HostVo> selectList(String hname);
}
