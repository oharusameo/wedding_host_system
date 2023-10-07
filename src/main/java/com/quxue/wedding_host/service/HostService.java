package com.quxue.wedding_host.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.quxue.wedding_host.pojo.Host;
import com.quxue.wedding_host.vo.HostVo;

import java.util.List;

public interface HostService {

    Integer batchDelHost(List<Integer> hostIds);

    Integer delHost(Integer hid);

    Integer editHost(Host host);

    Integer register(Host host);

    Integer updateAvatar(Host host);

    List<HostVo> selectHosts(Integer low,Integer high,Integer sort);

    IPage<Host> selectHostsByAdmin(Integer pageNum, Integer pageSize);

    IPage<Host> selectHostsByCondition(Host host, Integer pageNum, Integer pageSize);

    List<HostVo> selectHostsByCondition(String hname);

    HostVo selectHostByHid(Integer hid);
}
