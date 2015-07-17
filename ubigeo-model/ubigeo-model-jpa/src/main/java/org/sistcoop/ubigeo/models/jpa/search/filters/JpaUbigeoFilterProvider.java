package org.sistcoop.ubigeo.models.jpa.search.filters;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;

import org.sistcoop.ubigeo.models.search.filters.UbigeoFilterProvider;

@Named
@Stateless
@Local(UbigeoFilterProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaUbigeoFilterProvider implements UbigeoFilterProvider {

    private final String ubigeo = "ubigeo";
    private final String ubigeoDepartamento = "ubigeoDepartamento";
    private final String ubigeoProvincia = "ubigeoProvincia";
    private final String ubigeoDistrito = "ubigeoDistrito";
    private final String denominacion = "denominacion";

    @Override
    public void close() {
        // TODO Auto-generated method stub

    }

    @Override
    public String getUbigeoFilter() {
        return this.ubigeo;
    }

    @Override
    public String getUbigeoDepartamentoFilter() {
        return this.ubigeoDepartamento;
    }

    @Override
    public String getUbigeoProvinciaFilter() {
        return this.ubigeoProvincia;
    }

    @Override
    public String getUbigeoDistritoFilter() {
        return this.ubigeoDistrito;
    }

    @Override
    public String getDenominacionFilter() {
        return this.denominacion;
    }

}
