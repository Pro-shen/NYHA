package com.nyha.quartz.util;

import org.quartz.JobExecutionContext;
import com.nyha.quartz.domain.SysJob;

/**
 * 定时任务处理（允许并发执行）
 * 
 * @author nyha
 *
 */
public class QuartzJobExecution extends AbstractQuartzJob
{
    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception
    {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
