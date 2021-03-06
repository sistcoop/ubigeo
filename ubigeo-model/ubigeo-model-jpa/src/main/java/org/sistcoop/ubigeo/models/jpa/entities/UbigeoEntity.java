package org.sistcoop.ubigeo.models.jpa.entities;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.validator.constraints.NotBlank;

@Cacheable
@Entity
@Table(name = "UBIGEO")
@NamedQueries({ @NamedQuery(name = "UbigeoEntity.findAll", query = "Select u from UbigeoEntity u") })
public class UbigeoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String ubigeo;
    private String denominacion;

    private String ubigeoDepartamento;
    private String ubigeoProvincia;
    private String ubigeoDistrito;

    public UbigeoEntity() {
        // TODO Auto-generated constructor stub
    }

    @Size(min = 6, max = 6)
    @Id
    @Column(name = "UBIGEO")
    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

    @NotNull
    @Size(min = 1, max = 200)
    @NotBlank
    @Column(name = "DENOMINACION")
    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    @NotNull
    @Size(min = 2, max = 2)
    @NotBlank
    @Column(name = "UBIGEO_DEPARTAMENTO")
    public String getUbigeoDepartamento() {
        return ubigeoDepartamento;
    }

    public void setUbigeoDepartamento(String ubigeoDepartamento) {
        this.ubigeoDepartamento = ubigeoDepartamento;
    }

    @NotNull
    @Size(min = 2, max = 2)
    @NotBlank
    @Column(name = "UBIGEO_PROVINCIA")
    public String getUbigeoProvincia() {
        return ubigeoProvincia;
    }

    public void setUbigeoProvincia(String ubigeoProvincia) {
        this.ubigeoProvincia = ubigeoProvincia;
    }

    @NotNull
    @Size(min = 2, max = 2)
    @NotBlank
    @Column(name = "UBIGEO_DISTRITO")
    public String getUbigeoDistrito() {
        return ubigeoDistrito;
    }

    public void setUbigeoDistrito(String ubigeoDistrito) {
        this.ubigeoDistrito = ubigeoDistrito;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ubigeo == null) ? 0 : ubigeo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UbigeoEntity other = (UbigeoEntity) obj;
        if (ubigeo == null) {
            if (other.ubigeo != null)
                return false;
        } else if (!ubigeo.equals(other.ubigeo))
            return false;
        return true;
    }

}
