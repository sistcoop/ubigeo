package org.sistcoop.ubigeo.models.jpa;

import javax.persistence.EntityManager;

import org.sistcoop.ubigeo.models.UbigeoModel;
import org.sistcoop.ubigeo.models.jpa.entities.UbigeoEntity;

public class UbigeoAdapter implements UbigeoModel {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private UbigeoEntity ubigeoEntity;
    private EntityManager em;

    public UbigeoAdapter(EntityManager em, UbigeoEntity ubigeoEntity) {
        this.em = em;
        this.ubigeoEntity = ubigeoEntity;
    }

    public UbigeoEntity getUbigeoEntity() {
        return ubigeoEntity;
    }

    public static UbigeoEntity toUbigeoEntity(UbigeoModel model, EntityManager em) {
        if (model instanceof UbigeoAdapter) {
            return ((UbigeoAdapter) model).getUbigeoEntity();
        }
        return em.getReference(UbigeoEntity.class, model.getUbigeo());
    }

    @Override
    public void commit() {
        em.merge(ubigeoEntity);
    }

    @Override
    public String getUbigeo() {
        return ubigeoEntity.getUbigeo();
    }

    @Override
    public String getDenominacion() {
        return ubigeoEntity.getDenominacion();
    }

    @Override
    public String getUbigeoDepartamento() {
        return ubigeoEntity.getUbigeoDepartamento();
    }

    @Override
    public String getUbigeoProvincia() {
        return ubigeoEntity.getUbigeoProvincia();
    }

    @Override
    public String getUbigeoDistrito() {
        return ubigeoEntity.getUbigeoDistrito();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUbigeo() == null) ? 0 : getUbigeo().hashCode());
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
        UbigeoModel other = (UbigeoModel) obj;
        if (getUbigeo() == null) {
            if (other.getUbigeo() != null)
                return false;
        } else if (!getUbigeo().equals(other.getUbigeo()))
            return false;
        return true;
    }
}
