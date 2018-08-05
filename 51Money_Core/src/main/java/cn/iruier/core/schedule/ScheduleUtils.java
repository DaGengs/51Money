package cn.iruier.core.schedule;

import cn.iruier.entity.job.ScheduleJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;


public class ScheduleUtils {

	public static void createJob(Scheduler scheduler, ScheduleJob scheduleJob) {

		try {
			//创建Job对象
			JobDetail job = JobBuilder.newJob(QuartzJob.class).withIdentity("JOB_TASK_" + scheduleJob.getJob_id()).build();

			//创建调度构造器
			CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule(scheduleJob.getCron_expression())
					.withMisfireHandlingInstructionDoNothing();

			//创建触发器
			Trigger trigger = TriggerBuilder
					.newTrigger()
					.withIdentity("TRIGGER_TASK_" + scheduleJob.getJob_id())
					.withSchedule(cronSchedule)
					.build();

			//放入参数，运行时的JOB可以获取
			job.getJobDataMap().put("JOB_PARAM_KEY", scheduleJob);
			
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
		    e.printStackTrace();
//
//			throw new RRException("创建定时任务失败", e);
		}
	}

	public static void updateJob(Scheduler scheduler, ScheduleJob scheduleJob) {
		try {
			
			TriggerKey triggerKey = TriggerKey.triggerKey("TRIGGER_TASK_" + scheduleJob.getJob_id());
			
			//创建调度构造器
			CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule(scheduleJob.getCron_expression())
					.withMisfireHandlingInstructionDoNothing();
			
			//获得已有的触发器
			CronTrigger trigger = (CronTrigger)scheduler.getTrigger(triggerKey);
			
			//按照新的Cron表达式构建触发器
			trigger = trigger.getTriggerBuilder()
					.withIdentity(triggerKey)
					.withSchedule(cronSchedule)
					.build();
			
			//放入参数，运行时的JOB可以获取
			trigger.getJobDataMap().put("JOB_PARAM_KEY", scheduleJob);
			
			scheduler.rescheduleJob(triggerKey, trigger);
			
		} catch (SchedulerException e) {
            e.printStackTrace();
			//throw new RRException("更新定时任务失败", e);
		}
	}

	public static void pauseJob(Scheduler scheduler, int jobId) {
		try {

			scheduler.pauseJob(JobKey.jobKey("JOB_TASK_" + jobId));
		} catch (SchedulerException e) {
            e.printStackTrace();
			//throw new RRException("暂停定时任务失败", e);
		}

	}

	public static void resumeJob(Scheduler scheduler, int jobId) {
		try {

			scheduler.resumeJob(JobKey.jobKey("JOB_TASK_" + jobId));
		} catch (SchedulerException e) {
            e.printStackTrace();
			//throw new RRException("暂停定时任务失败", e);
		}
	}

	public static void deleteJob(Scheduler scheduler, int jobId) {
		try {

			scheduler.deleteJob(JobKey.jobKey("JOB_TASK_" + jobId));
		} catch (SchedulerException e) {
            e.printStackTrace();
			//throw new RRException("暂停定时任务失败", e);
		}
	}

	public static void run(Scheduler scheduler, int jobId) {
        try {
			scheduler.triggerJob(JobKey.jobKey("JOB_TASK_" +jobId));
		} catch (SchedulerException e) {
            e.printStackTrace();
			//throw new RRException("立即执行定时任务失败", e);
		}
	}
}
