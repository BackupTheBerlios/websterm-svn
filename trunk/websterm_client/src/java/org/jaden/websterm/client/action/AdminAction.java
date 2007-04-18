/**
 *
 */
package org.jaden.websterm.client.action;

import java.util.Date;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.jaden.websterm.lib.engines.RuleExecutionJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author jack
 * 
 */
public class AdminAction extends BaseAction {
	private String status;

	private RuleExecutionJob executionJob;

	private Scheduler sched = null;

	@DefaultHandler
	public Resolution index() throws Exception {
		return new ForwardResolution("/WEB-INF/pages/admin/index.jsp");
	}

	public Resolution toggleEngine() throws Exception {
		SchedulerFactory schedFact = new StdSchedulerFactory();
		String path = getContext().getServletContext().getRealPath(
				"WEB-INF/config");
		if (sched == null)
			sched = schedFact.getScheduler();
		if (status.equals("Start")) {
			sched.start();
			JobDetail detail = new JobDetail("ruleExecutionJob", null,
					RuleExecutionJob.class);
			Trigger trigger = TriggerUtils.makeMinutelyTrigger();
			trigger.setStartTime(TriggerUtils.getEvenSecondDate(new Date()));
			trigger.setName("ruleExecutionTrigger");

			detail.getJobDataMap().put("hibernateConfigPath",
					path + "/hibernate.cfg.xml");
			detail.getJobDataMap().put("definitionFilePath",
					path + "/rules.wbs");
			detail.getJobDataMap().put("inputConfigPath", path + "/fields.xml");
			detail.getJobDataMap()
					.put("functionsPath", path + "/functions.xml");
			detail.getJobDataMap().put("resultsPath", path + "/results.xml");
			sched.scheduleJob(detail, trigger);
		} else {
			sched.shutdown();
			sched = null;
		}

		return new StreamingResolution("text/plain", "Rule Engine "
				+ (status.equals("Start") ? "Started" : "Stopped")
				+ " Successfully.");
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the executionJob
	 */
	public RuleExecutionJob getExecutionJob() {
		return executionJob;
	}

	/**
	 * @param executionJob
	 *            the executionJob to set
	 */
	@SpringBean("ruleExecutionJob")
	public void setExecutionJob(RuleExecutionJob executionJob) {
		this.executionJob = executionJob;
	}
}
