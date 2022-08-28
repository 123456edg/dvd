package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends Mapper<User> {

    User findUserByUserName(@Param("userName") String userName, @Param("id") Long id);

    List<User> findUserById(Long userId);

    User selectUserById(String createdBy);
}
