package org.sistcoop.ubigeo.admin.client.resource;

import javax.ejb.EJBException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.ubigeo.representations.idm.UbigeoRepresentation;
import org.sistcoop.ubigeo.representations.idm.search.SearchResultsRepresentation;

@Path("/ubigeos")
@Consumes(MediaType.APPLICATION_JSON)
public interface UbigeosResource {

    /**
     * @param ubigeo
     *            codigode ubigeo.
     */
    @Path("/{ubigeo}")
    public UbigeoResource ubigeo(@PathParam("ubigeo") String ubigeo);

    /**
     * Crea un Ubigeo con los datos enviados.
     * 
     * @summary Crea un Ubigeo
     * @param representation
     *            El detalle del objeto a enviar.
     * @statuscode 201 Si el objeto fue creado satisfactoriamente.
     * @return Currency creado.
     * @throws EJBException
     *             datos validos pero ocurrio un error interno
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(UbigeoRepresentation representation);

    /**
     * Buscar ubigeos segun los parametros enviados.
     * 
     * @summary Buscar Ubigeos
     * @param filterText
     *            Palabra filtro.
     * @param page
     *            Numero de pagina.
     * @param pageSize
     *            Tamano de pagina.
     * @statuscode 200 Si la busqueda fue exitosa.
     * @return SearchResultsRepresentation resultado de busqueda.
     * @throws EJBException
     *             datos validos pero ocurrio un error interno
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public SearchResultsRepresentation<UbigeoRepresentation> search(@QueryParam("ubigeo") String ubigeo);

}