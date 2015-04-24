package org.sistcoop.ubigeo.models.utils;

import org.sistcoop.ubigeo.models.DepartamentoModel;
import org.sistcoop.ubigeo.models.DistritoModel;
import org.sistcoop.ubigeo.models.ProvinciaModel;
import org.sistcoop.ubigeo.representations.idm.DepartamentoRepresentation;
import org.sistcoop.ubigeo.representations.idm.DistritoRepresentation;
import org.sistcoop.ubigeo.representations.idm.ProvinciaRepresentation;

public class ModelToRepresentation {	

	public static DepartamentoRepresentation toRepresentation(DepartamentoModel model) {
		if (model == null)
			return null;
		DepartamentoRepresentation rep = new DepartamentoRepresentation();

		rep.setId(model.getId());
		rep.setCodigo(model.getCodigo());
		rep.setDenominacion(model.getDenominacion());

		return rep;
	}

	public static ProvinciaRepresentation toRepresentation(ProvinciaModel model) {
		if (model == null)
			return null;
		ProvinciaRepresentation rep = new ProvinciaRepresentation();

		rep.setId(model.getId());
		rep.setCodigo(model.getCodigo());
		rep.setDenominacion(model.getDenominacion());
		rep.setDepartamento(model.getDepartamento().getCodigo());

		return rep;
	}

	public static DistritoRepresentation toRepresentation(DistritoModel model) {
		if (model == null)
			return null;
		DistritoRepresentation rep = new DistritoRepresentation();

		rep.setId(model.getId());
		rep.setCodigo(model.getCodigo());
		rep.setDenominacion(model.getDenominacion());

		rep.setProvincia(model.getProvincia().getCodigo());
		return rep;
	}	

}
