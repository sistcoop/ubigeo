package org.sistcoop.ubigeo.models.search.filters;

import javax.ejb.Local;

import org.sistcoop.ubigeo.provider.Provider;

@Local
public interface UbigeoFilterProvider extends Provider {

    String getUbigeoFilter();

    String getUbigeoDepartamentoFilter();

    String getUbigeoProvinciaFilter();

    String getUbigeoDistritoFilter();

    String getDenominacionFilter();

}
