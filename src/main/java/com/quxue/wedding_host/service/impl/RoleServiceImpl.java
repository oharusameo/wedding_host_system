package com.quxue.wedding_host.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quxue.wedding_host.mapper.RoleMapper;
import com.quxue.wedding_host.pojo.Result;
import com.quxue.wedding_host.pojo.Role;
import com.quxue.wedding_host.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    public Result updateRoleMenu(Role role) {
        Integer rid = role.getRid();
        QueryWrapper<Integer> wrapper = new QueryWrapper<>();
        wrapper.eq("rid", rid);
        if (!roleMapper.queryRid(wrapper).isEmpty()) {
            delRoleMenu(rid);
        }
        List<Integer> nodes = role.getSelectedNode();
        if (nodes != null && nodes.size() > 0) {
            insertRoleMenu(rid, nodes);
        }
        return Result.success("更新成功");
    }


    @Transactional
    @Override
    public Integer delRoleMenu(Integer rid) {
        return roleMapper.delRoleMenu(rid);
    }


    @Transactional
    @Override
    public Integer insertRoleMenu(Integer roleId, List<Integer> menuIds) {
        return roleMapper.insertRoleMenu(roleId, menuIds);
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

    @Override
    public List<Integer> selectMenuIdByRole(Integer roleId) {
        QueryWrapper<Integer> wrapper = new QueryWrapper<>();
        wrapper.eq("rmt.rid", roleId);
        return roleMapper.selectMenuIdByRole(wrapper);
    }
}
