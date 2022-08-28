package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.SysMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysMenuMapper extends Mapper<SysMenu> {

    List<Object> selectMenuByRoleId(Integer roleId);

}