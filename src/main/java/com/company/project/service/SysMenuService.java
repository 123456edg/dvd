package com.company.project.service;
import com.company.project.model.SysMenu;
import com.company.project.core.Service;

import java.util.List;


/**
* Created by CodeGenerator on 2021/04/20.
*/
public interface SysMenuService extends Service<SysMenu> {

    List<Object> selectMenuByRoleId(Integer role);
}
