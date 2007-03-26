/**
 *
 */
package org.jaden.websterm.lib.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author jack
 *
 */
public class Model implements Serializable {
	private long id;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		boolean equals = true;
		if (!(obj instanceof Model))
			equals = false;
		else {
			EqualsBuilder builder = new EqualsBuilder();
			Model model = (Model) obj;
			equals = builder.append(model.getId(), id).isEquals();
		}

		return equals;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
