package com.company.project.service;
import com.company.project.core.Result;
import com.company.project.model.User;
import com.company.project.core.Service;
import com.company.project.vo.LoginVo;

import java.util.List;


/**
* Created by CodeGenerator on 2021/04/16.
*/
public interface UserService extends Service<User> {

    Result logout(Long userId);

    Result login(LoginVo vo);

    Result captcha();

    Result add(User user);

    Result updateUser(User user);

    List<User> findUserById(Long valueOf);

    User selectUserById(String createdBy);

    Result updateMoneyById(User user);
}
