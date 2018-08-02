package cn.iruier.quartz;


import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @Author: iruier
 * @Date: 2018/7/31 21:05
 */
public class Quartz_Main {
    public static void main(String[] args) throws Exception {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("myQuartz", "quartzG").build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myQuartz", "quartzG").startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * 31 * ? ")).build();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}
