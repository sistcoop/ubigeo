package org.sistcoop.ubigeo.admin.client.resource;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.sistcoop.ubigeo.representations.idm.UbigeoRepresentation;
import org.sistcoop.ubigeo.representations.idm.search.SearchResultsRepresentation;

import com.jayway.restassured.http.ContentType;

public class UbigeoProviderTest extends AbstractTest {

    private final String baseURI = "http://127.0.0.1:8080/test/rest/ubigeos";

    @Test
    public void create() {
        UbigeoRepresentation rep = new UbigeoRepresentation();
        rep.setDenominacion("Ayacucho");
        rep.setUbigeoDepartamento("01");
        rep.setUbigeoProvincia("01");
        rep.setUbigeoDistrito("03");

        UbigeoRepresentation repCreated = given().contentType(ContentType.JSON).body(rep).log().everything()
                .expect().statusCode(201).log().ifError().when().post(baseURI).as(UbigeoRepresentation.class);

        assertThat(repCreated, is(notNullValue()));
        assertThat(repCreated.getUbigeo(), is(notNullValue()));
    }

    @Test
    public void search() {
        UbigeoRepresentation rep1 = new UbigeoRepresentation();
        rep1.setDenominacion("Ayacucho");
        rep1.setUbigeoDepartamento("01");
        rep1.setUbigeoProvincia("01");
        rep1.setUbigeoDistrito("03");

        UbigeoRepresentation rep2 = new UbigeoRepresentation();
        rep2.setDenominacion("Lima");
        rep2.setUbigeoDepartamento("05");
        rep2.setUbigeoProvincia("01");
        rep2.setUbigeoDistrito("03");

        @SuppressWarnings("unused")
        UbigeoRepresentation repCreated1 = given().contentType(ContentType.JSON).body(rep1).log()
                .everything().expect().statusCode(201).log().ifError().when().post(baseURI)
                .as(UbigeoRepresentation.class);
        @SuppressWarnings("unused")
        UbigeoRepresentation repCreated2 = given().contentType(ContentType.JSON).body(rep2).log()
                .everything().expect().statusCode(201).log().ifError().when().post(baseURI)
                .as(UbigeoRepresentation.class);

        @SuppressWarnings("rawtypes")
        SearchResultsRepresentation result = given().contentType(ContentType.JSON).log().everything()
                .expect().statusCode(200).log().ifError().when().get(baseURI)
                .as(SearchResultsRepresentation.class);

        // assert
        assertThat(result, is(notNullValue()));
        assertThat(result.getTotalSize(), is(2));
        assertThat(result.getItems().size(), is(2));
    }

    @Test
    public void searchFiltertext() {
        UbigeoRepresentation rep1 = new UbigeoRepresentation();
        rep1.setDenominacion("Ayacucho");
        rep1.setUbigeoDepartamento("01");
        rep1.setUbigeoProvincia("01");
        rep1.setUbigeoDistrito("03");

        UbigeoRepresentation rep2 = new UbigeoRepresentation();
        rep2.setDenominacion("Lima");
        rep2.setUbigeoDepartamento("05");
        rep2.setUbigeoProvincia("01");
        rep2.setUbigeoDistrito("03");

        @SuppressWarnings("unused")
        UbigeoRepresentation repCreated1 = given().contentType(ContentType.JSON).body(rep1).log()
                .everything().expect().statusCode(201).log().ifError().when().post(baseURI)
                .as(UbigeoRepresentation.class);
        @SuppressWarnings("unused")
        UbigeoRepresentation repCreated2 = given().contentType(ContentType.JSON).body(rep2).log()
                .everything().expect().statusCode(201).log().ifError().when().post(baseURI)
                .as(UbigeoRepresentation.class);

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("filterText", "ayacucho");
        parameters.put("page", "1");
        parameters.put("pageSize", "10");

        @SuppressWarnings("rawtypes")
        SearchResultsRepresentation result = given().parameters(parameters).contentType(ContentType.JSON)
                .log().everything().expect().statusCode(200).log().ifError().when().get(baseURI)
                .as(SearchResultsRepresentation.class);

        // assert
        assertThat(result, is(notNullValue()));
        assertThat(result.getItems().size(), greaterThan(1));
        assertThat(result.getTotalSize(), greaterThan(1));
    }
}
