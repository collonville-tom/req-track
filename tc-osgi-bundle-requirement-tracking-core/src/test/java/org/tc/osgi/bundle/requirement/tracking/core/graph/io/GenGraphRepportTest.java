/**
 */
package org.tc.osgi.bundle.requirement.tracking.core.graph.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import junit.framework.Assert;

import org.junit.Test;
import org.tc.osgi.bundle.apt.io.module.service.UtilsServiceProxy;
import org.tc.osgi.bundle.apt.io.module.service.impl.AptIoServiceImpl;
import org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service.AptIoServiceProxy;
import org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service.M2TrackingRequirementFactoryServiceProxy;
import org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service.impl.AptRequirementBuilderServiceImpl;
import org.tc.osgi.bundle.requirement.tracking.core.graph.TrackingGraph;
import org.tc.osgi.bundle.requirement.tracking.core.module.service.impl.GraphBuilderServiceImpl;
import org.tc.osgi.bundle.requirement.tracking.m2.model.AbstractRequirementElement;
import org.tc.osgi.bundle.requirement.tracking.m2.module.service.impl.M2TrackingRequirementFactoryServiceImpl;
import org.tc.osgi.bundle.utils.module.service.impl.UtilsServiceImpl;

/**
 * GenGraphRepportTest.java.
 * @author collonville thomas
 * @version
 */
public class GenGraphRepportTest {

    @Test
    public void test() {
        final File f = new File("src/test/resources/srs.apt");
        final List<AbstractRequirementElement> reqs = new ArrayList<AbstractRequirementElement>();
        try {
            final BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/srs.apt")));
            final List<String> l = new ArrayList<String>();
            while (reader.ready()) {
                l.add(reader.readLine());
            }
            reader.close();
            final AptRequirementBuilderServiceImpl builder = new AptRequirementBuilderServiceImpl();

            AptIoServiceProxy.getInstance().setService(new AptIoServiceImpl());
            M2TrackingRequirementFactoryServiceProxy.getInstance().setService(new M2TrackingRequirementFactoryServiceImpl());
            UtilsServiceProxy.getInstance().setService(new UtilsServiceImpl());
            org.tc.osgi.bundle.apt.io.module.service.UtilsServiceProxy.getInstance().setService(new UtilsServiceImpl());
            org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service.UtilsServiceProxy.getInstance().setService(new UtilsServiceImpl());

            reqs.addAll(builder.extractRequirement(f, l));
            // TODO prevoir de completer le test lorsque l'on integrera le
            // decodage des fichiers java
            final TrackingGraph graph = new GraphBuilderServiceImpl().buildTrackingGraph(f.getName(), reqs);

            Assert.assertEquals(new BufferedReader(new FileReader(new File("src/test/resources/srsGraph.xml"))).readLine(), new GenGraphRepport().generateXML(graph));

        } catch (final IOException e) {
            Assert.fail(e.getMessage());
        } catch (final JAXBException e) {
            Assert.fail(e.getMessage());
        }

    }

}
