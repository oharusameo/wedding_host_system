package com.quxue.wedding_host.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quxue.wedding_host.mapper.UserMapper;
import com.quxue.wedding_host.pojo.User;
import com.quxue.wedding_host.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> selectUser() {
        return userMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public List<User> selectAllUser() {
        return userMapper.selectAll();
    }


    @Override
    public List<User> selectUserByCondition(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (user.getUsername()!=null){
            wrapper.like("aname",user.getUsername());
        }
        if (user.getPhone()!=null){
            wrapper.like("aphone",user.getPhone());
        }
        return userMapper.selectList(wrapper);
    }

    @Transactional
    @Override
    public Integer editUser(User user) {
        return userMapper.updateById(user);
    }

    @Transactional
    @Override
    public Integer delUserById(Integer id) {


        return userMapper.deleteById(id);
    }

    @Transactional
    @Override
    public Integer batchDelUser(List<Integer> userIds) {
        return userMapper.deleteBatchIds(userIds);
    }

    @Transactional
    @Override
    public Integer addUser(User user) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(new Date());
        user.setCreateTime(time);
        return userMapper.insert(user);
    }


}
