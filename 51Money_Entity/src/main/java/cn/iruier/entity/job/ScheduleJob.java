package cn.iruier.entity.job;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: iruier
 * @Date: 2018/8/4 8:51
 */
public class ScheduleJob implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";
    private int job_id;
    private String bean_name;
    private String method_name;
    private String params;
    private String cron_expression;
    private int status;
    private String remark;
    private Date create_time;

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public String getBean_name() {
        return bean_name;
    }

    public void setBean_name(String bean_name) {
        this.bean_name = bean_name;
    }

    public String getMethod_name() {
        return method_name;
    }

    public void setMethod_name(String method_name) {
        this.method_name = method_name;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getCron_expression() {
        return cron_expression;
    }

    public void setCron_expression(String cron_expression) {
        this.cron_expression = cron_expression;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
