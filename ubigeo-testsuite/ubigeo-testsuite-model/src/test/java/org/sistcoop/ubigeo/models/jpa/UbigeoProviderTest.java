package org.sistcoop.ubigeo.models.jpa;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import org.junit.Test;
import org.sistcoop.ubigeo.models.UbigeoModel;
import org.sistcoop.ubigeo.models.UbigeoProvider;
import org.sistcoop.ubigeo.models.search.SearchResultsModel;

public class UbigeoProviderTest extends AbstractTest {

    @Inject
    private UbigeoProvider ubigeoProvider;

    @Test
    public void create() {
        UbigeoModel model = ubigeoProvider.create("05", "01", "01", "Ayacucho");

        assertThat(model, is(notNullValue()));
        assertThat(model.getUbigeo(), is(notNullValue()));
    }

    @Test
    public void findByUbigeo() {
        UbigeoModel model1 = ubigeoProvider.create("05", "01", "01", "Ayacucho");

        String ubigeo = model1.getUbigeo();

        UbigeoModel model2 = ubigeoProvider.findByUbigeo(ubigeo);

        assertThat(model1, is(equalTo(model2)));
    }

    @Test
    public void getCurrencies() {
        @SuppressWarnings("unused")
        UbigeoModel model1 = ubigeoProvider.create("05", "01", "01", "Ayacucho");

        SearchResultsModel<UbigeoModel> results = ubigeoProvider.search();
        for (UbigeoModel model : results.getModels()) {
            assertThat(model, is(notNullValue()));
        }

        assertThat(results.getTotalSize(), is(1));
        assertThat(results.getModels().size(), is(1));
    }

    @Test
    public void remove() {
        UbigeoModel model1 = ubigeoProvider.create("05", "01", "01", "Ayacucho");

        String ubigeo = model1.getUbigeo();
        boolean result = ubigeoProvider.remove(model1);

        UbigeoModel model2 = ubigeoProvider.findByUbigeo(ubigeo);

        assertThat(result, is(true));
        assertThat(model2, is(nullValue()));
    }

}
