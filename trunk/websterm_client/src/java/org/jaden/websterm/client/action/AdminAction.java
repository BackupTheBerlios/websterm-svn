/**
 *
 */
package org.jaden.websterm.client.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;

/**
 * @author jack
 * 
 */
public class AdminAction extends BaseAction {
	private String status;

	@DefaultHandler
	public Resolution index() throws Exception {
		return new ForwardResolution("/WEB-INF/pages/admin/index.jsp");
	}

	public Resolution toggleEngine() throws Exception {

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
}
