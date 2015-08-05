package org.sistcoop.ubigeo.models.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.sistcoop.ubigeo.models.UbigeoModel;
import org.sistcoop.ubigeo.models.UbigeoProvider;
import org.sistcoop.ubigeo.models.jpa.entities.UbigeoEntity;
import org.sistcoop.ubigeo.models.search.SearchCriteriaModel;
import org.sistcoop.ubigeo.models.search.SearchResultsModel;
import org.sistcoop.ubigeo.models.search.filters.UbigeoFilterProvider;

@Named
@Stateless
@Local(UbigeoProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaUbigeoProvider extends AbstractHibernateStorage implements UbigeoProvider {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UbigeoFilterProvider filterProvider;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public UbigeoModel findByUbigeo(String ubigeo) {
        UbigeoEntity ubigeoEntity = this.em.find(UbigeoEntity.class, ubigeo);
        return ubigeoEntity != null ? new UbigeoAdapter(em, ubigeoEntity) : null;
    }

    @Override
    public UbigeoModel create(String ubigeoDepartamento, String ubigeoProvincia, String ubigeoDistrito,
            String denominacion) {
        UbigeoEntity ubigeoEntity = new UbigeoEntity();
        ubigeoEntity.setUbigeo(ubigeoDepartamento + ubigeoProvincia + ubigeoDistrito);
        ubigeoEntity.setUbigeoDepartamento(ubigeoDepartamento);
        ubigeoEntity.setUbigeoProvincia(ubigeoProvincia);
        ubigeoEntity.setUbigeoDistrito(ubigeoDistrito);
        ubigeoEntity.setDenominacion(denominacion);
        em.persist(ubigeoEntity);
        return new UbigeoAdapter(em, ubigeoEntity);
    }

    @Override
    public boolean remove(UbigeoModel UbigeoModel) {
        UbigeoEntity countryCodeEntity = em.find(UbigeoEntity.class, UbigeoModel.getUbigeo());
        if (countryCodeEntity == null) {
            return false;
        }
        em.remove(countryCodeEntity);
        return true;
    }

    @Override
    public SearchResultsModel<UbigeoModel> search() {
        TypedQuery<UbigeoEntity> query = em.createNamedQuery("UbigeoEntity.findAll", UbigeoEntity.class);

        List<UbigeoEntity> entities = query.getResultList();
        List<UbigeoModel> models = new ArrayList<UbigeoModel>();
        for (UbigeoEntity ubigeoEntity : entities) {
            models.add(new UbigeoAdapter(em, ubigeoEntity));
        }

        SearchResultsModel<UbigeoModel> result = new SearchResultsModel<>();
        result.setModels(models);
        result.setTotalSize(models.size());
        return result;
    }

    @Override
    public SearchResultsModel<UbigeoModel> search(SearchCriteriaModel criteria) {
        SearchResultsModel<UbigeoEntity> entityResult = find(criteria, UbigeoEntity.class);

        SearchResultsModel<UbigeoModel> modelResult = new SearchResultsModel<>();
        List<UbigeoModel> list = new ArrayList<>();
        for (UbigeoEntity entity : entityResult.getModels()) {
            list.add(new UbigeoAdapter(em, entity));
        }
        modelResult.setTotalSize(entityResult.getTotalSize());
        modelResult.setModels(list);
        return modelResult;
    }

    @Override
    public SearchResultsModel<UbigeoModel> search(SearchCriteriaModel criteria, String filterText) {
        SearchResultsModel<UbigeoEntity> entityResult = findFullText(criteria, UbigeoEntity.class, filterText,
                filterProvider.getUbigeoFilter(), filterProvider.getUbigeoDepartamentoFilter(),
                filterProvider.getUbigeoProvinciaFilter(), filterProvider.getUbigeoDistritoFilter(),
                filterProvider.getDenominacionFilter());

        SearchResultsModel<UbigeoModel> modelResult = new SearchResultsModel<>();
        List<UbigeoModel> list = new ArrayList<>();
        for (UbigeoEntity entity : entityResult.getModels()) {
            list.add(new UbigeoAdapter(em, entity));
        }
        modelResult.setTotalSize(entityResult.getTotalSize());
        modelResult.setModels(list);
        return modelResult;
    }

}
