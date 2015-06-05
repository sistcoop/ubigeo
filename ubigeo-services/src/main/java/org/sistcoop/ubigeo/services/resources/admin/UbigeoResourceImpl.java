package org.sistcoop.ubigeo.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;

import org.jboss.ejb3.annotation.SecurityDomain;
import org.sistcoop.ubigeo.admin.client.Roles;
import org.sistcoop.ubigeo.admin.client.resource.UbigeoResource;
import org.sistcoop.ubigeo.models.UbigeoModel;
import org.sistcoop.ubigeo.models.UbigeoProvider;
import org.sistcoop.ubigeo.models.utils.ModelToRepresentation;
import org.sistcoop.ubigeo.representations.idm.UbigeoRepresentation;

@Stateless
@SecurityDomain("keycloak")
public class UbigeoResourceImpl implements UbigeoResource {

	@Inject
	protected UbigeoProvider ubigeoProvider;
	
	@RolesAllowed(Roles.ver_ubigeos)
	@Override
	public List<UbigeoRepresentation> getDepartamentos() {
		List<UbigeoModel> list = ubigeoProvider.getDepartamentos();
		List<UbigeoRepresentation> result = new ArrayList<UbigeoRepresentation>();
		for (UbigeoModel model : list) {
			result.add(ModelToRepresentation.toRepresentation(model));
		}
		return result;
	}
	
	@RolesAllowed(Roles.ver_ubigeos)
	@Override
	public List<UbigeoRepresentation> getProvincias(@PathParam("codigo") String codigoDepartamento) {
		List<UbigeoModel> list = ubigeoProvider.getProvincias(codigoDepartamento);
		List<UbigeoRepresentation> result = new ArrayList<UbigeoRepresentation>();
		for (UbigeoModel model : list) {
			result.add(ModelToRepresentation.toRepresentation(model));
		}
		return result;
	}
	
	@RolesAllowed(Roles.ver_ubigeos)
	@Override
	public List<UbigeoRepresentation> getDistritos(@PathParam("codigoDep") String codigoDepartamento, @PathParam("codigoProv") String codigoProvincia) {
		List<UbigeoModel> list = ubigeoProvider.getDistritos(codigoDepartamento, codigoProvincia);
		List<UbigeoRepresentation> result = new ArrayList<UbigeoRepresentation>();
		for (UbigeoModel model : list) {
			result.add(ModelToRepresentation.toRepresentation(model));
		}
		return result;
	}
	
}