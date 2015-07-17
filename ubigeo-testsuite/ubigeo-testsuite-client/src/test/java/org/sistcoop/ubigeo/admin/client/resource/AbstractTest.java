package org.sistcoop.ubigeo.admin.client.resource;

import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.runner.RunWith;
import org.sistcoop.ubigeo.JaxRsActivator;
import org.sistcoop.ubigeo.admin.client.Config;
import org.sistcoop.ubigeo.models.UbigeoModel;
import org.sistcoop.ubigeo.models.jpa.JpaUbigeoProvider;
import org.sistcoop.ubigeo.models.jpa.entities.UbigeoEntity;
import org.sistcoop.ubigeo.models.jpa.search.filters.JpaUbigeoFilterProvider;
import org.sistcoop.ubigeo.models.search.SearchCriteriaFilterModel;
import org.sistcoop.ubigeo.models.utils.ModelToRepresentation;
import org.sistcoop.ubigeo.provider.Provider;
import org.sistcoop.ubigeo.representations.idm.UbigeoRepresentation;
import org.sistcoop.ubigeo.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.ubigeo.services.messages.Messages;
import org.sistcoop.ubigeo.services.resources.admin.UbigeosResourceImpl;
import org.sistcoop.ubigeo.models.search.filters.UbigeoFilterProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Arquillian.class)
@UsingDataSet("empty.xml")
public abstract class AbstractTest {

    protected Logger log = LoggerFactory.getLogger(AbstractTest.class);

    @Deployment
    public static WebArchive createDeployment() {
        File[] logger = Maven.resolver().resolve("org.slf4j:slf4j-simple:1.7.10").withoutTransitivity()
                .asFile();

        File[] restAssured = Maven.resolver().resolve("com.jayway.restassured:rest-assured:2.4.1")
                .withTransitivity().asFile();

        WebArchive war = ShrinkWrap
                .create(WebArchive.class, "test.war")

                /** model-api **/
                .addPackage(Provider.class.getPackage())
                .addPackage(UbigeoModel.class.getPackage())

                .addPackage(ModelToRepresentation.class.getPackage())

                .addPackage(SearchCriteriaFilterModel.class.getPackage())
                .addPackage(UbigeoFilterProvider.class.getPackage())

                /** model-jpa **/
                .addPackage(JpaUbigeoProvider.class.getPackage())
                .addPackage(JpaUbigeoFilterProvider.class.getPackage())
                .addPackage(UbigeoEntity.class.getPackage())

                /** client */
                .addPackage(Config.class.getPackage())
                .addPackage(UbigeosResource.class.getPackage())

                /** services */
                .addPackage(Messages.class.getPackage())
                .addPackage(UbigeosResourceImpl.class.getPackage())

                /** core */
                .addPackage(UbigeoRepresentation.class.getPackage())
                .addPackage(SearchResultsRepresentation.class.getPackage())

                /** core jaxrs */
                .addPackage(JaxRsActivator.class.getPackage())

                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource("META-INF/test-jboss-deployment-structure.xml",
                        "jboss-deployment-structure.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "web.xml").addAsWebInfResource("test-ds.xml");

        war.addAsLibraries(logger);
        war.addAsLibraries(restAssured);

        return war;
    }
}
