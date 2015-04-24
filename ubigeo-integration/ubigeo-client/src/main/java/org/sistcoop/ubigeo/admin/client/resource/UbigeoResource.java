package org.sistcoop.ubigeo.admin.client.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sistcoop.ubigeo.representations.idm.DepartamentoRepresentation;
import org.sistcoop.ubigeo.representations.idm.DistritoRepresentation;
import org.sistcoop.ubigeo.representations.idm.ProvinciaRepresentation;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/departamentos")
public interface UbigeoResource {

	
	@GET	
	public List<DepartamentoRepresentation> findAll();

	@GET
	@Path("/{codigo}/provincias")	
	public List<ProvinciaRepresentation> getProvincias(
			@PathParam("codigo") String codigoDepartamento);

	@GET
	@Path("/{codigoDep}/provincias/{codigoProv}/distritos")	
	public List<DistritoRepresentation> getDistritos(
			@PathParam("codigoDep") String codigoDepartamento, 
			
			@PathParam("codigoProv") String codigoProvincia);
	
}