package cn.iruier.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Author: iruier
 * @Date: 2018/7/31 21:33
 */
public class MyJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("哈哈哈哈" + System.currentTimeMillis()/1000);
    }
}
