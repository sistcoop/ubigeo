package org.sistcoop.ubigeo.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;

import org.sistcoop.ubigeo.admin.client.resource.UbigeoResource;
import org.sistcoop.ubigeo.models.UbigeoModel;
import org.sistcoop.ubigeo.models.UbigeoProvider;
import org.sistcoop.ubigeo.models.utils.ModelToRepresentation;
import org.sistcoop.ubigeo.representations.idm.UbigeoRepresentation;

@Stateless
public class UbigeoResourceImpl implements UbigeoResource {

    @PathParam("ubigeo")
    private String ubigeo;

    @Inject
    private UbigeoProvider ubigeoProvider;

    private UbigeoModel getUbigeoModel() {
        return ubigeoProvider.findByUbigeo(ubigeo);
    }

    @Override
    public UbigeoRepresentation ubigeo() {
        UbigeoRepresentation rep = ModelToRepresentation.toRepresentation(getUbigeoModel());
        if (rep != null) {
            return rep;
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public void update(UbigeoRepresentation representation) {
        // TODO
    }

    @Override
    public void disable() {
        throw new NotFoundException();
    }

    @Override
    public void remove() {
        boolean result = ubigeoProvider.remove(getUbigeoModel());
        if (!result) {
            throw new InternalServerErrorException();
        }
    }

}