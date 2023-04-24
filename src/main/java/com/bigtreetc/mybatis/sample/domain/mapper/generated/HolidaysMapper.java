package com.bigtreetc.mybatis.sample.domain.mapper.generated;

import com.bigtreetc.mybatis.sample.domain.model.generated.Holidays;
import com.bigtreetc.mybatis.sample.domain.model.generated.HolidaysExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HolidaysMapper {
  long countByExample(HolidaysExample example);

  int deleteByExample(HolidaysExample example);

  int deleteByPrimaryKey(Long holidayId);

  int insert(Holidays record);

  int insertSelective(Holidays record);

  List<Holidays> selectByExample(HolidaysExample example);

  Holidays selectByPrimaryKey(Long holidayId);

  int updateByExampleSelective(
      @Param("record") Holidays record, @Param("example") HolidaysExample example);

  int updateByExample(@Param("record") Holidays record, @Param("example") HolidaysExample example);

  int updateByPrimaryKeySelective(Holidays record);

  int updateByPrimaryKey(Holidays record);

  int batchInsert(@Param("list") List<Holidays> list);

  int batchInsertSelective(
      @Param("list") List<Holidays> list, @Param("selective") Holidays.Column... selective);

  int upsert(Holidays record);

  int upsertSelective(Holidays record);
}