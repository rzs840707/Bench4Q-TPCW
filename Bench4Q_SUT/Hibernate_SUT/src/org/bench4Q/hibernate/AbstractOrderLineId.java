package org.bench4Q.hibernate;

/**
 * AbstractOrderLineId entity provides the base persistence definition of the
 * OrderLineId entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public abstract class AbstractOrderLineId implements java.io.Serializable {

	// Fields

	private Integer olId;
	private Integer olOId;

	// Constructors

	/** default constructor */
	public AbstractOrderLineId() {
	}

	/** full constructor */
	public AbstractOrderLineId(Integer olId, Integer olOId) {
		this.olId = olId;
		this.olOId = olOId;
	}

	// Property accessors

	public Integer getOlId() {
		return this.olId;
	}

	public void setOlId(Integer olId) {
		this.olId = olId;
	}

	public Integer getOlOId() {
		return this.olOId;
	}

	public void setOlOId(Integer olOId) {
		this.olOId = olOId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractOrderLineId))
			return false;
		AbstractOrderLineId castOther = (AbstractOrderLineId) other;

		return ((this.getOlId() == castOther.getOlId()) || (this.getOlId() != null
				&& castOther.getOlId() != null && this.getOlId().equals(
				castOther.getOlId())))
				&& ((this.getOlOId() == castOther.getOlOId()) || (this
						.getOlOId() != null
						&& castOther.getOlOId() != null && this.getOlOId()
						.equals(castOther.getOlOId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getOlId() == null ? 0 : this.getOlId().hashCode());
		result = 37 * result
				+ (getOlOId() == null ? 0 : this.getOlOId().hashCode());
		return result;
	}

}