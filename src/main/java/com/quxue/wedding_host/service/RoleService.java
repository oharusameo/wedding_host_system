package com.quxue.wedding_host.service;


import com.quxue.wedding_host.pojo.Role;

import java.util.List;

public interface RoleService {

    Integer addRole(Role role);
    Integer delRole(Integer id);

    Integer batchDelRole(List<Integer> roleIds);
    Integer updateRole(Role role);
    List<Role> selectRole();
    List<Role> selectRoleByCondition(Role role);


}
