package com.bigtreetc.sample.mybatis.controller.holidays;

import com.bigtreetc.sample.mybatis.base.domain.validator.AbstractValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/** 祝日登録 入力チェック */
@Component
public class HolidayRequestValidator extends AbstractValidator<HolidayRequest> {

  @Override
  protected void doValidate(HolidayRequest request, Errors errors) {}
}
