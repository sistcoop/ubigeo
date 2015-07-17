package org.sistcoop.ubigeo.models.jpa;

import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sistcoop.ubigeo.models.UbigeoModel;
import org.sistcoop.ubigeo.models.jpa.JpaUbigeoProvider;
import org.sistcoop.ubigeo.models.jpa.entities.UbigeoEntity;
import org.sistcoop.ubigeo.models.jpa.search.filters.JpaUbigeoFilterProvider;
import org.sistcoop.ubigeo.models.search.SearchCriteriaFilterModel;
import org.sistcoop.ubigeo.models.search.filters.UbigeoFilterProvider;
import org.sistcoop.ubigeo.provider.Provider;

@RunWith(Arquillian.class)
@UsingDataSet("empty.xml")
public abstract class AbstractTest {

    protected Logger log = LoggerFactory.getLogger(AbstractTest.class);

    @Deployment
    public static WebArchive createDeployment() {
        File[] dependencies = Maven.resolver().resolve("org.slf4j:slf4j-simple:1.7.10").withoutTransitivity()
                .asFile();

        WebArchive war = ShrinkWrap
                .create(WebArchive.class, "test.war")
                /** model-api **/
                .addPackage(Provider.class.getPackage())
                .addPackage(UbigeoModel.class.getPackage())

                // .addPackage(TipoPersona.class.getPackage())

                .addPackage(SearchCriteriaFilterModel.class.getPackage())
                .addPackage(UbigeoFilterProvider.class.getPackage())

                /** model-jpa **/
                .addPackage(JpaUbigeoProvider.class.getPackage())
                .addPackage(JpaUbigeoFilterProvider.class.getPackage())
                .addPackage(UbigeoEntity.class.getPackage())

                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource("META-INF/test-jboss-deployment-structure.xml",
                        "jboss-deployment-structure.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml").addAsWebInfResource("test-ds.xml");

        war.addAsLibraries(dependencies);

        return war;
    }
}
