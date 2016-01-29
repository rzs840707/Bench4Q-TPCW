package org.bench4Q.hibernate;

/**
 * AbstractShoppingCartLineId entity provides the base persistence definition of
 * the ShoppingCartLineId entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public abstract class AbstractShoppingCartLineId implements
		java.io.Serializable {

	// Fields

	private Integer sclScId;
	private Integer sclIId;

	// Constructors

	/** default constructor */
	public AbstractShoppingCartLineId() {
	}

	/** full constructor */
	public AbstractShoppingCartLineId(Integer sclScId, Integer sclIId) {
		this.sclScId = sclScId;
		this.sclIId = sclIId;
	}

	// Property accessors

	public Integer getSclScId() {
		return this.sclScId;
	}

	public void setSclScId(Integer sclScId) {
		this.sclScId = sclScId;
	}

	public Integer getSclIId() {
		return this.sclIId;
	}

	public void setSclIId(Integer sclIId) {
		this.sclIId = sclIId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractShoppingCartLineId))
			return false;
		AbstractShoppingCartLineId castOther = (AbstractShoppingCartLineId) other;

		return ((this.getSclScId() == castOther.getSclScId()) || (this
				.getSclScId() != null
				&& castOther.getSclScId() != null && this.getSclScId().equals(
				castOther.getSclScId())))
				&& ((this.getSclIId() == castOther.getSclIId()) || (this
						.getSclIId() != null
						&& castOther.getSclIId() != null && this.getSclIId()
						.equals(castOther.getSclIId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getSclScId() == null ? 0 : this.getSclScId().hashCode());
		result = 37 * result
				+ (getSclIId() == null ? 0 : this.getSclIId().hashCode());
		return result;
	}

}