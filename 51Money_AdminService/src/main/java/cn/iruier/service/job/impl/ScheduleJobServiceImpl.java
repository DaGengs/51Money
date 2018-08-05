package cn.iruier.service.job.impl;

import cn.iruier.core.schedule.ScheduleUtils;
import cn.iruier.core.util.ExecuteUtil;
import cn.iruier.core.util.PageUtil;
import cn.iruier.core.vo.PageVo;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.job.ScheduleJob;
import cn.iruier.mapper.job.ScheduleJobMapper;
import cn.iruier.service.job.ScheduleJobService;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: iruier
 * @Date: 2018/8/4 9:14
 */
@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {

    @Autowired
    private ScheduleJobMapper scheduleJobMapper;

    @Autowired
    private Scheduler scheduler;

    @Override
    public ResultVo save(ScheduleJob scheduleJob) {
        scheduleJob.setCreate_time(new Date());
        ScheduleUtils.createJob(scheduler, scheduleJob);
        return ExecuteUtil.getResult(scheduleJobMapper.insert(scheduleJob), "新增任务");
    }

    @Override
    public ResultVo deleteBatch(int[] job_ids) {
        for (int job_id : job_ids) {
            ScheduleUtils.deleteJob(scheduler, job_id);
        }
        return ExecuteUtil.getResult(scheduleJobMapper.deleteBatch(job_ids), "删除任务");
    }

    @Override
    public ScheduleJob queryById(int job_id) {
        return scheduleJobMapper.queryById(job_id);
    }

    @Override
    public ResultVo update(ScheduleJob scheduleJob) {
        ScheduleUtils.updateJob(scheduler, scheduleJob);
        return ExecuteUtil.getResult(scheduleJobMapper.update(scheduleJob), "修改任务");
    }

    @Override
    public PageVo<ScheduleJob> queryByPage(int page, int size) {
        int index = 0;
        if (page > 0) {
            index = (page - 1) * size;
        }
        return PageUtil.getPageVo(scheduleJobMapper.queryByPage(index, size), scheduleJobMapper.queryCount());
    }

    @Override
    public ResultVo runBatch(int[] job_ids) {
        for (int job_id : job_ids) {
            ScheduleUtils.run(scheduler, job_id);
            scheduleJobMapper.updateStatus(job_id, 2);
        }
        return ResultVo.setSuccess("任务开始成功");
    }

    @Override
    public ResultVo pauseBatch(int[] job_ids) {
        for (int job_id : job_ids) {
            ScheduleUtils.pauseJob(scheduler, job_id);
            scheduleJobMapper.updateStatus(job_id, 3);
        }
        return ResultVo.setSuccess("任务暂停成功");
    }

    @Override
    public ResultVo resumeBatch(int[] job_ids) {
        for (int job_id : job_ids) {
            ScheduleUtils.resumeJob(scheduler, job_id);
            scheduleJobMapper.updateStatus(job_id, 2);
        }
        return ResultVo.setSuccess("任务恢复成功");
    }
}
