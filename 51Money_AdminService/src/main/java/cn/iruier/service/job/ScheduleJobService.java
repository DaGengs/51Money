package cn.iruier.service.job;

import cn.iruier.core.vo.PageVo;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.job.ScheduleJob;

/**
 * @Author: iruier
 * @Date: 2018/8/4 9:13
 */
public interface ScheduleJobService {

    ResultVo save(ScheduleJob scheduleJob);

    ResultVo deleteBatch(int[] job_ids);

    ScheduleJob queryById(int job_id);

    ResultVo update(ScheduleJob scheduleJob);

    PageVo<ScheduleJob> queryByPage(int page, int size);

    ResultVo runBatch(int[] job_ids);

    ResultVo pauseBatch(int[] job_ids);

    ResultVo resumeBatch(int[] job_ids);
}
