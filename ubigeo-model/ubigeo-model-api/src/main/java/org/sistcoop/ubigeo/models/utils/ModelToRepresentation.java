package org.sistcoop.ubigeo.models.utils;

import org.sistcoop.ubigeo.models.UbigeoModel;
import org.sistcoop.ubigeo.representations.idm.UbigeoRepresentation;

public class ModelToRepresentation {	

	public static UbigeoRepresentation toRepresentation(UbigeoModel model) {
		if (model == null)
			return null;
		UbigeoRepresentation rep = new UbigeoRepresentation();

		rep.setUbigeo(model.getUbigeo());
		rep.setDenominacion(model.getDenominacion());
		rep.setUbigeoDepartamento(model.getUbigeoDepartamento());
		rep.setUbigeoProvincia(model.getUbigeoProvincia());
		rep.setUbigeoDistrito(model.getUbigeoDistrito());

		return rep;
	}

}
