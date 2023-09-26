package com.quxue.wedding_host.service;


import com.quxue.wedding_host.pojo.Result;
import com.quxue.wedding_host.pojo.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {

    Integer addRole(Role role);
    Integer delRole(Integer id);

    Integer delRoleMenu(Integer id);
    Result updateRoleMenu(Role role);

    Integer insertRoleMenu(Integer roleId,List<Integer> menuIds);
    List<Integer> selectMenuIdByRole(Integer roleId);
    Integer batchDelRole(List<Integer> roleIds);
    Integer updateRole(Role role);
    List<Role> selectRole();
    List<Role> selectRoleByCondition(Role role);


}
