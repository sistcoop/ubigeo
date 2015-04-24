package org.sistcoop.ubigeo.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.sistcoop.ubigeo.admin.client.resource.UbigeoResource;
import org.sistcoop.ubigeo.models.DepartamentoModel;
import org.sistcoop.ubigeo.models.DistritoModel;
import org.sistcoop.ubigeo.models.ProvinciaModel;
import org.sistcoop.ubigeo.models.UbigeoProvider;
import org.sistcoop.ubigeo.models.utils.ModelToRepresentation;
import org.sistcoop.ubigeo.representations.idm.DepartamentoRepresentation;
import org.sistcoop.ubigeo.representations.idm.DistritoRepresentation;
import org.sistcoop.ubigeo.representations.idm.ProvinciaRepresentation;

@Path("/departamentos")
@Stateless
public class UbigeoResourceImpl implements UbigeoResource {

	@Inject
	protected UbigeoProvider ubigeoProvider;
	
	public List<DepartamentoRepresentation> findAll() {
		List<DepartamentoModel> list = ubigeoProvider.getDepartamentos();
		List<DepartamentoRepresentation> result = new ArrayList<DepartamentoRepresentation>();
		for (DepartamentoModel model : list) {
			result.add(ModelToRepresentation.toRepresentation(model));
		}
		return result;
	}
	
	public List<ProvinciaRepresentation> getProvincias(@PathParam("codigo") String codigoDepartamento) {
		List<ProvinciaModel> list = ubigeoProvider.getProvincias(codigoDepartamento);
		List<ProvinciaRepresentation> result = new ArrayList<ProvinciaRepresentation>();
		for (ProvinciaModel model : list) {
			result.add(ModelToRepresentation.toRepresentation(model));
		}
		return result;
	}
	
	public List<DistritoRepresentation> getDistritos(@PathParam("codigoDep") String codigoDepartamento, @PathParam("codigoProv") String codigoProvincia) {
		List<DistritoModel> list = ubigeoProvider.getDistritos(codigoDepartamento, codigoProvincia);
		List<DistritoRepresentation> result = new ArrayList<DistritoRepresentation>();
		for (DistritoModel model : list) {
			result.add(ModelToRepresentation.toRepresentation(model));
		}
		return result;
	}
	
}