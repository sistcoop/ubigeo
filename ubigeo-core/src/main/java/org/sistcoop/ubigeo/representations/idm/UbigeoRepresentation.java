package org.sistcoop.ubigeo.representations.idm;

import java.io.Serializable;

public class UbigeoRepresentation implements Serializable {

    private static final long serialVersionUID = 1L;

    private String ubigeo;
    private String denominacion;

    private String ubigeoDepartamento;
    private String ubigeoProvincia;
    private String ubigeoDistrito;

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getUbigeoDepartamento() {
        return ubigeoDepartamento;
    }

    public void setUbigeoDepartamento(String ubigeoDepartamento) {
        this.ubigeoDepartamento = ubigeoDepartamento;
    }

    public String getUbigeoProvincia() {
        return ubigeoProvincia;
    }

    public void setUbigeoProvincia(String ubigeoProvincia) {
        this.ubigeoProvincia = ubigeoProvincia;
    }

    public String getUbigeoDistrito() {
        return ubigeoDistrito;
    }

    public void setUbigeoDistrito(String ubigeoDistrito) {
        this.ubigeoDistrito = ubigeoDistrito;
    }

}
