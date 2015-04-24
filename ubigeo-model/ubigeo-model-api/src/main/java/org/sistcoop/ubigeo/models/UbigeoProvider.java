package org.sistcoop.ubigeo.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.ubigeo.provider.Provider;

@Local
public interface UbigeoProvider extends Provider {

	public List<DepartamentoModel> getDepartamentos();

	public List<ProvinciaModel> getProvincias(String codigoDepartamento);

	public List<DistritoModel> getDistritos(String codigoDepartamento, String codigoProvincia);

}
