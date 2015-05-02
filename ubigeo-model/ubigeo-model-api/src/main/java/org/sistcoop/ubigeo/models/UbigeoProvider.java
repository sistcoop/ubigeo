package org.sistcoop.ubigeo.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.ubigeo.provider.Provider;

@Local
public interface UbigeoProvider extends Provider {

	public List<UbigeoModel> getDepartamentos();

	public List<UbigeoModel> getProvincias(String codigoDepartamento);

	public List<UbigeoModel> getDistritos(String codigoDepartamento, String codigoProvincia);

}
