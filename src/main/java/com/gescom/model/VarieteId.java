package com.gescom.model;
// Generated 1 avr. 2025, 20:05:00 by Hibernate Tools 6.5.1.Final

/**
 * VarieteId generated by hbm2java
 */
public class VarieteId implements java.io.Serializable {

	private int idcatvariete;
	private int idvariete;

	public VarieteId() {
	}

	public VarieteId(int idcatvariete, int idvariete) {
		this.idcatvariete = idcatvariete;
		this.idvariete = idvariete;
	}

	public int getIdcatvariete() {
		return this.idcatvariete;
	}

	public void setIdcatvariete(int idcatvariete) {
		this.idcatvariete = idcatvariete;
	}

	public int getIdvariete() {
		return this.idvariete;
	}

	public void setIdvariete(int idvariete) {
		this.idvariete = idvariete;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VarieteId))
			return false;
		VarieteId castOther = (VarieteId) other;

		return (this.getIdcatvariete() == castOther.getIdcatvariete())
				&& (this.getIdvariete() == castOther.getIdvariete());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdcatvariete();
		result = 37 * result + this.getIdvariete();
		return result;
	}

}
