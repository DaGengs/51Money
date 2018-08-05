package cn.iruier.controller.job;

import cn.iruier.core.vo.PageVo;
import cn.iruier.core.vo.ResultVo;
import cn.iruier.entity.job.ScheduleJob;
import cn.iruier.service.job.ScheduleJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: iruier
 * @Date: 2018/8/4 9:49
 */
@RestController
@RequestMapping("/schedule/job")
public class ScheduleJobController {

    @Autowired
    private ScheduleJobService service;

    @PostMapping("/save")
    public ResultVo saveJob(ScheduleJob scheduleJob) {
        return service.save(scheduleJob);
    }

    @GetMapping("/delete")
    public ResultVo deleteJob(@RequestParam("job_ids[]") int[] job_ids) {
        return service.deleteBatch(job_ids);
    }

    @GetMapping("/select")
    public ScheduleJob queryById(int job_id) {
        return service.queryById(job_id);
    }

    @PostMapping("/update")
    public ResultVo updateJob(ScheduleJob scheduleJob) {
        return service.update(scheduleJob);
    }

    @GetMapping("/list")
    public PageVo<ScheduleJob> queryByPage(int page, int limit) {
        return service.queryByPage(page, limit);
    }

    @GetMapping("/run")
    public ResultVo runBatch(@RequestParam("job_ids[]") int[] job_ids) {
        System.out.println(job_ids.length);
        return service.runBatch(job_ids);
    }

    @GetMapping("/pause")
    public ResultVo pauseBatch(@RequestParam("job_ids[]")int[] job_ids) {
        return service.pauseBatch(job_ids);
    }

    @GetMapping("/resume")
    public ResultVo resumeBatch(@RequestParam("job_ids[]")int[] job_ids) {
        return service.resumeBatch(job_ids);
    }
}
