package org.sistcoop.ubigeo.models;

import javax.ejb.Local;

import org.sistcoop.ubigeo.models.search.SearchCriteriaModel;
import org.sistcoop.ubigeo.models.search.SearchResultsModel;
import org.sistcoop.ubigeo.provider.Provider;

@Local
public interface UbigeoProvider extends Provider {

    UbigeoModel findByUbigeo(String ubigeo);

    UbigeoModel create(String ubigeoDepartamento, String ubigeoProvincia, String ubigeoDistrito,
            String denominacion);

    boolean remove(UbigeoModel UbigeoModel);

    SearchResultsModel<UbigeoModel> search();

    SearchResultsModel<UbigeoModel> search(SearchCriteriaModel criteria);

    SearchResultsModel<UbigeoModel> search(SearchCriteriaModel criteria, String filterText);

}
