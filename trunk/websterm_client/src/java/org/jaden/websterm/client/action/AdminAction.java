/**
 *
 */
package org.jaden.websterm.client.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

import org.quartz.Scheduler;

/**
 * @author jack
 * 
 */
public class AdminAction extends BaseAction {
	private String status;

	private Scheduler sched = null;

	@DefaultHandler
	public Resolution index() throws Exception {
		return new ForwardResolution("/WEB-INF/pages/admin/index.jsp");
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
}
