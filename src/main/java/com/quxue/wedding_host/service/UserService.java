package com.quxue.wedding_host.service;

import com.quxue.wedding_host.pojo.User;

import java.util.List;

public interface UserService {
    List<User> selectUser();
    List<User> selectAllUser();
    Integer editUser(User user);
    Integer delUserById(Integer id);

    Integer batchDelUser(List<Integer> userIds);
    Integer addUser(User user);
}
