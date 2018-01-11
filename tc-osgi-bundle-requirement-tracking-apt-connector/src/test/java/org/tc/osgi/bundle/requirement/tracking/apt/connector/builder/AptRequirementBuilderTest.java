/**
 */
package org.tc.osgi.bundle.requirement.tracking.apt.connector.builder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.tc.osgi.bundle.apt.io.module.service.impl.AptIoServiceImpl;
import org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service.AptIoServiceProxy;
import org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service.M2TrackingRequirementFactoryServiceProxy;
import org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service.UtilsServiceProxy;
import org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service.impl.AptRequirementBuilderServiceImpl;
import org.tc.osgi.bundle.requirement.tracking.m2.model.AbstractRequirementElement;
import org.tc.osgi.bundle.requirement.tracking.m2.model.RequirementGroup;
import org.tc.osgi.bundle.requirement.tracking.m2.module.service.impl.M2TrackingRequirementFactoryServiceImpl;
import org.tc.osgi.bundle.utils.module.service.impl.UtilsServiceImpl;

/**
 * AptRequirementBuilderTest.java.
 * @author collonville thomas
 * @version
 */
public class AptRequirementBuilderTest {

    @Test
    public void testDepend() {
        UtilsServiceProxy.getInstance().setService(new UtilsServiceImpl());

        final AptRequirementBuilderServiceImpl builder = new AptRequirementBuilderServiceImpl();
        Assert.assertEquals("SRS_BUNDLE_MORPHMATH_CORE_010", builder.extractDependency("   * @depend SRS_BUNDLE_MORPHMATH_CORE_010").getFirstElementDescription());

    }

    @Test
    public void testDescription() {
        UtilsServiceProxy.getInstance().setService(new UtilsServiceImpl());

        final AptRequirementBuilderServiceImpl builder = new AptRequirementBuilderServiceImpl();
        Assert.assertEquals("blabla", builder.extractDescription("  @description blabla").getFirstElementDescription());

    }

    @Test
    public void testExtract() {

        final AptRequirementBuilderServiceImpl builder = new AptRequirementBuilderServiceImpl();
        AptIoServiceProxy.getInstance().setService(new AptIoServiceImpl());
        M2TrackingRequirementFactoryServiceProxy.getInstance().setService(new M2TrackingRequirementFactoryServiceImpl());
        UtilsServiceProxy.getInstance().setService(new UtilsServiceImpl());

        final List<AbstractRequirementElement> element;

        try {
            final BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/srs.apt")));
            final List<String> l = new ArrayList<String>();
            while (reader.ready()) {
                l.add(reader.readLine());
            }
            reader.close();
            element = builder.extractRequirement(new File("src/test/resources/srs.apt"), l);
            final RequirementGroup req = (RequirementGroup) element.get(0);
            req.toString();

        } catch (final IOException e) {
            Assert.fail("fichier n'est pas trouvé");
        }
    }

    @Test
    public void testExtractEmpty() {

        final AptRequirementBuilderServiceImpl builder = new AptRequirementBuilderServiceImpl();
        AptIoServiceProxy.getInstance().setService(new AptIoServiceImpl());
        M2TrackingRequirementFactoryServiceProxy.getInstance().setService(new M2TrackingRequirementFactoryServiceImpl());
        UtilsServiceProxy.getInstance().setService(new UtilsServiceImpl());
        org.tc.osgi.bundle.apt.io.module.service.UtilsServiceProxy.getInstance().setService(new UtilsServiceImpl());

        final List<AbstractRequirementElement> element;

        try {
            final BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/index.apt")));
            final List<String> l = new ArrayList<String>();
            while (reader.ready()) {
                l.add(reader.readLine());
            }
            reader.close();
            element = builder.extractRequirement(new File("src/test/resources/index.apt"), l);
            final RequirementGroup req = (RequirementGroup) element.get(0);
            req.toString();

        } catch (final IOException e) {
            Assert.fail("fichier n'est pas trouvé");
        }
    }

    @Test
    public void testName() {
        UtilsServiceProxy.getInstance().setService(new UtilsServiceImpl());

        final AptRequirementBuilderServiceImpl builder = new AptRequirementBuilderServiceImpl();
        Assert.assertEquals("SRS_BUNDLE_MORPHMATH_CORE_010", builder.extractRequirementName("** SRS_BUNDLE_MORPHMATH_CORE_010").getFirstElementDescription());

    }

    @Test
    public void testTrack() {
        UtilsServiceProxy.getInstance().setService(new UtilsServiceImpl());

        final AptRequirementBuilderServiceImpl builder = new AptRequirementBuilderServiceImpl();
        Assert.assertEquals("SRS_BUNDLE_MORPHMATH_CORE_010", builder.extractTracking("   * @track SRS_BUNDLE_MORPHMATH_CORE_010").getFirstElementDescription());

    }

    @Test
    public void testVersion() {
        UtilsServiceProxy.getInstance().setService(new UtilsServiceImpl());

        final AptRequirementBuilderServiceImpl builder = new AptRequirementBuilderServiceImpl();
        Assert.assertEquals("0.0.2", builder.extractVersion("   * @version 0.0.2").getFirstElementDescription());

    }
}
