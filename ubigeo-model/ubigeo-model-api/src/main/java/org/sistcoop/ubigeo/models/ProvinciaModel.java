package org.sistcoop.ubigeo.models;

import java.util.Set;

public interface ProvinciaModel extends Model {

	Integer getId();

	String getCodigo();

	void setCodigo(String codigo);

	String getDenominacion();

	void setDenominacion(String denominacion);

	DepartamentoModel getDepartamento();

	void setDepartamento(DepartamentoModel departamentoModel);

	Set<DistritoModel> getDistritos();

	void setDistritos(Set<DistritoModel> provincias);

}