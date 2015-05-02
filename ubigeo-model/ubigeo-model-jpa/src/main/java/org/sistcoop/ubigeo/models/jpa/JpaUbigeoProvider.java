package org.sistcoop.ubigeo.models.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.sistcoop.ubigeo.models.UbigeoModel;
import org.sistcoop.ubigeo.models.UbigeoProvider;
import org.sistcoop.ubigeo.models.jpa.entities.UbigeoEntity;

@Named
@Stateless
@Local(UbigeoProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaUbigeoProvider implements UbigeoProvider {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public List<UbigeoModel> getDepartamentos() {
		TypedQuery<UbigeoEntity> query = em.createNamedQuery(UbigeoEntity.findAllDepartamentos, UbigeoEntity.class);
		List<UbigeoEntity> list = query.getResultList();
		List<UbigeoModel> results = new ArrayList<UbigeoModel>();
		for (UbigeoEntity entity : list) {
			results.add(new UbigeoAdapter(em, entity));
		}
		return results;
	}

	@Override
	public List<UbigeoModel> getProvincias(String codigoDepartamento) {
		TypedQuery<UbigeoEntity> query = em.createNamedQuery(UbigeoEntity.findAllProvincias, UbigeoEntity.class);
		query.setParameter("ubigeoDepartamento", codigoDepartamento);
		List<UbigeoEntity> list = query.getResultList();
		List<UbigeoModel> results = new ArrayList<UbigeoModel>();
		for (UbigeoEntity entity : list) {
			results.add(new UbigeoAdapter(em, entity));
		}
		return results;
	}

	@Override
	public List<UbigeoModel> getDistritos(String codigoDepartamento, String codigoProvincia) {
		TypedQuery<UbigeoEntity> query = em.createNamedQuery(UbigeoEntity.findAllDistritos, UbigeoEntity.class);
		query.setParameter("ubigeoDepartamento", codigoDepartamento);
		query.setParameter("ubigeoProvincia", codigoProvincia);
		List<UbigeoEntity> list = query.getResultList();
		List<UbigeoModel> results = new ArrayList<UbigeoModel>();
		for (UbigeoEntity entity : list) {
			results.add(new UbigeoAdapter(em, entity));
		}
		return results;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}
	
}
