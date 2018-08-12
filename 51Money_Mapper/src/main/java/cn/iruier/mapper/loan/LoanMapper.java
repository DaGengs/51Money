package cn.iruier.mapper.loan;

import cn.iruier.core.vo.LoanVo;
import cn.iruier.core.vo.StatusVo;
import cn.iruier.entity.loan.Loan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/8/4 17:48
 */
public interface LoanMapper {

    int insert(Loan loan);

    List<Loan> queryAll(@Param("index")int index, @Param("size")int size);

    int queryCount();

    int update(Loan loan);

    int updateStatus(@Param("id")int id, @Param("status")int status);

    Loan queryByAID(int account_id);

    StatusVo queryStatus(int user_id);

    List<Loan> queryList();

    LoanVo queryDetail(int id);
}
