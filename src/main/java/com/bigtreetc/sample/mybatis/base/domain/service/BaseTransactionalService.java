package com.bigtreetc.sample.mybatis.base.domain.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Throwable.class)
public abstract class BaseTransactionalService {

  @Autowired protected ModelMapper modelMapper;
}
