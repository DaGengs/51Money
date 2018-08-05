package cn.iruier.mapper.job;

import cn.iruier.entity.job.ScheduleJob;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: iruier
 * @Date: 2018/8/4 8:56
 */
public interface ScheduleJobMapper {

    int insert(ScheduleJob scheduleJob);

    int deleteBatch(int[] job_ids);

    ScheduleJob queryById(int job_id);

    int update(ScheduleJob scheduleJob);

    List<ScheduleJob> queryByPage(@Param("index")int index, @Param("size")int size);

    int queryCount();

    int updateStatus(@Param("job_id") int job_id, @Param("status") int status);
}
