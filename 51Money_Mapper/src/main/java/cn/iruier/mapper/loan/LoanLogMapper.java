package cn.iruier.mapper.loan;

import cn.iruier.entity.loan.LoanLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/8/4 20:19
 */
public interface LoanLogMapper {

    @Insert("insert into t_loanlog(type, sysUser_id, msg) values(#{type}, #{sysUser_id}, #{msg})")
    int insert(LoanLog loanLog);

    @Select("select * from t_loanlog limit #{index}, #{size}")
    @ResultType(LoanLog.class)
    List<LoanLog> queryAll(@Param("index")int index, @Param("size")int size);
}
