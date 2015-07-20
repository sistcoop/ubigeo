package org.sistcoop.ubigeo.models.utils;

import javax.ejb.Stateless;

import org.sistcoop.ubigeo.models.UbigeoModel;
import org.sistcoop.ubigeo.models.UbigeoProvider;
import org.sistcoop.ubigeo.representations.idm.UbigeoRepresentation;

@Stateless
public class RepresentationToModel {

    public UbigeoModel createUbigeo(UbigeoRepresentation representation, UbigeoProvider ubigeoProvider) {
        UbigeoModel model = ubigeoProvider.create(representation.getUbigeoDepartamento(),
                representation.getUbigeoProvincia(), representation.getUbigeoDistrito(),
                representation.getDenominacion());
        return model;
    }

}
