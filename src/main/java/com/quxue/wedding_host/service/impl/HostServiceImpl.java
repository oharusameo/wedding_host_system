package com.quxue.wedding_host.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quxue.wedding_host.mapper.HostMapper;
import com.quxue.wedding_host.pojo.Host;
import com.quxue.wedding_host.service.HostService;
import com.quxue.wedding_host.vo.HostVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class HostServiceImpl implements HostService {

    @Resource
    private HostMapper hostMapper;


    @Override
    public Integer batchDelHost(List<Integer> hostIds) {
        return hostMapper.batchDelHost(hostIds);
    }

    @Override
    public Integer delHost(Integer hid) {
        return hostMapper.deleteHost(hid);
    }

    @Override
    public Integer editHost(Host host) {
        return hostMapper.updateHost(host);
    }

    @Override
    public Integer register(Host host) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(new Date());
        host.setStarttime(time);
        host.setStrong("50");//默认权重
        host.setOrdernumber(0);//订单数量初始值
        return hostMapper.insert(host);
    }

    @Override
    public Integer updateAvatar(Host host) {
        return hostMapper.updateAvatar(host.getHid(), host.getImage());
    }

    @Override
    public List<HostVo> selectHosts(Integer low, Integer high,Integer sort) {
        return hostMapper.selectHosts(low, high,sort);
    }

    @Override
    public IPage<Host> selectHostsByAdmin(Integer pageNum, Integer pageSize) {
        Page<Host> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Host> wrapper = new QueryWrapper<>();
        return hostMapper.selectAllHosts(page, wrapper);
    }

    @Override
    public IPage<Host> selectHostsByCondition(Host host, Integer pageNum, Integer pageSize) {
        Page<Host> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Host> wrapper = new QueryWrapper<>();
        if (host.getHname() != null) {
            wrapper.like("hname", host.getHname());
        }
        if (host.getHphone() != null) {
            wrapper.like("hphone", host.getHphone());
        }
        return hostMapper.selectAllHosts(page, wrapper);
    }

    @Override
    public List<HostVo> selectHostsByCondition(String hname) {
        return hostMapper.selectList(hname);
    }

    @Override
    public HostVo selectHostByHid(Integer hid) {
        return hostMapper.selectHostByHid(hid);
    }

}
