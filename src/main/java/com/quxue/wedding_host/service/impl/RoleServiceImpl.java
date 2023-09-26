package com.quxue.wedding_host.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quxue.wedding_host.mapper.RoleMapper;
import com.quxue.wedding_host.pojo.Role;
import com.quxue.wedding_host.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Transactional
    @Override
    public Integer addRole(Role role) {
        return roleMapper.insert(role);
    }

    @Transactional
    @Override
    public Integer delRole(Integer id) {
        return roleMapper.deleteById(id);
    }

    @Transactional
    @Override
    public Integer batchDelRole(List<Integer> roleIds) {
        return roleMapper.deleteBatchIds(roleIds);
    }

    @Transactional
    @Override
    public Integer updateRole(Role role) {
        return roleMapper.updateById(role);
    }

    @Override
    public List<Role> selectRole() {
        return roleMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public List<Role> selectRoleByCondition(Role role) {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.like("rname", role.getRname());
        return roleMapper.selectList(wrapper);
    }
}
