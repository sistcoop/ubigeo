package org.sistcoop.ubigeo.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.ubigeo.admin.client.resource.UbigeoResource;
import org.sistcoop.ubigeo.admin.client.resource.UbigeosResource;
import org.sistcoop.ubigeo.models.UbigeoModel;
import org.sistcoop.ubigeo.models.UbigeoProvider;
import org.sistcoop.ubigeo.models.search.PagingModel;
import org.sistcoop.ubigeo.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.ubigeo.models.search.SearchCriteriaModel;
import org.sistcoop.ubigeo.models.search.SearchResultsModel;
import org.sistcoop.ubigeo.models.search.filters.UbigeoFilterProvider;
import org.sistcoop.ubigeo.models.utils.ModelToRepresentation;
import org.sistcoop.ubigeo.models.utils.RepresentationToModel;
import org.sistcoop.ubigeo.representations.idm.UbigeoRepresentation;
import org.sistcoop.ubigeo.representations.idm.search.SearchResultsRepresentation;

@Stateless
public class UbigeosResourceImpl implements UbigeosResource {

    @Inject
    private UbigeoProvider ubigeoProvider;

    @Inject
    private RepresentationToModel representationToModel;

    @Inject
    private UbigeoFilterProvider filterProvider;

    @Context
    private UriInfo uriInfo;

    @Inject
    private UbigeoResource ubigeoResource;

    @Override
    public UbigeoResource ubigeo(String ubigeo) {
        return ubigeoResource;
    }

    @Override
    public Response create(UbigeoRepresentation representation) {
        UbigeoModel model = representationToModel.createUbigeo(representation, ubigeoProvider);
        return Response.created(uriInfo.getAbsolutePathBuilder().build())
                .header("Access-Control-Expose-Headers", "Location")
                .entity(ModelToRepresentation.toRepresentation(model)).build();
    }

    @Override
    public SearchResultsRepresentation<UbigeoRepresentation> search(String filterText, int page, int pageSize) {
        PagingModel paging = new PagingModel();
        paging.setPage(page);
        paging.setPageSize(pageSize);

        SearchCriteriaModel searchCriteriaBean = new SearchCriteriaModel();
        searchCriteriaBean.setPaging(paging);
        searchCriteriaBean.addOrder(filterProvider.getUbigeoFilter(), true);

        // search
        SearchResultsModel<UbigeoModel> results = ubigeoProvider.search(searchCriteriaBean, filterText);
        SearchResultsRepresentation<UbigeoRepresentation> rep = new SearchResultsRepresentation<>();
        List<UbigeoRepresentation> representations = new ArrayList<>();
        for (UbigeoModel model : results.getModels()) {
            representations.add(ModelToRepresentation.toRepresentation(model));
        }
        rep.setTotalSize(results.getTotalSize());
        rep.setItems(representations);
        return rep;
    }

    @Override
    public SearchResultsRepresentation<UbigeoRepresentation> search(String ubigeo) {
        SearchCriteriaModel searchCriteriaBean = new SearchCriteriaModel();
        searchCriteriaBean.addOrder(filterProvider.getUbigeoFilter(), true);

        if (ubigeo.length() == 2) {
            searchCriteriaBean.addFilter(filterProvider.getUbigeoDepartamentoFilter(), ubigeo,
                    SearchCriteriaFilterOperator.eq);
        } else if (ubigeo.length() == 4) {
            searchCriteriaBean.addFilter(filterProvider.getUbigeoDepartamentoFilter(),
                    ubigeo.substring(0, 2), SearchCriteriaFilterOperator.eq);
            searchCriteriaBean.addFilter(filterProvider.getUbigeoProvinciaFilter(), ubigeo.substring(2, 4),
                    SearchCriteriaFilterOperator.eq);
        } else if (ubigeo.length() == 6) {
            searchCriteriaBean.addFilter(filterProvider.getUbigeoDepartamentoFilter(),
                    ubigeo.substring(0, 2), SearchCriteriaFilterOperator.eq);
            searchCriteriaBean.addFilter(filterProvider.getUbigeoProvinciaFilter(), ubigeo.substring(2, 4),
                    SearchCriteriaFilterOperator.eq);
            searchCriteriaBean.addFilter(filterProvider.getUbigeoDistritoFilter(), ubigeo.substring(4, 6),
                    SearchCriteriaFilterOperator.eq);
        }

        // search
        SearchResultsModel<UbigeoModel> results = ubigeoProvider.search(searchCriteriaBean);
        SearchResultsRepresentation<UbigeoRepresentation> rep = new SearchResultsRepresentation<>();
        List<UbigeoRepresentation> representations = new ArrayList<>();
        for (UbigeoModel model : results.getModels()) {
            representations.add(ModelToRepresentation.toRepresentation(model));
        }
        rep.setTotalSize(results.getTotalSize());
        rep.setItems(representations);
        return rep;
    }

}