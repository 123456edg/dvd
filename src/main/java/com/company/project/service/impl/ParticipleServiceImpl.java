package com.company.project.service.impl;

import com.company.project.dao.ParticipleMapper;
import com.company.project.model.Participle;
import com.company.project.service.ParticipleService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;



@Service
@Transactional
public class ParticipleServiceImpl extends AbstractService<Participle> implements ParticipleService {

    @Resource
    private ParticipleMapper tParticipleMapper;

}
