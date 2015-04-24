package org.sistcoop.ubigeo.models;

public interface DistritoModel extends Model {

	Integer getId();

	String getCodigo();

	void setCodigo(String codigo);

	String getDenominacion();

	void setDenominacion(String denominacion);

	ProvinciaModel getProvincia();

	void setProvincia(ProvinciaModel provinciaModel);

}