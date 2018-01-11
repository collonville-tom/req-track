/**
 */
package org.tc.osgi.bundle.requirement.tracking.m2.java.connector.builder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.tc.osgi.bundle.requirement.tracking.java.connector.module.service.impl.JavaRequirementBuilderServiceImpl;
import org.tc.osgi.bundle.requirement.tracking.m2.model.AbstractRequirementElement;
import org.tc.osgi.bundle.requirement.tracking.m2.model.Requirement;
import org.tc.osgi.bundle.requirement.tracking.m2.module.service.impl.M2TrackingRequirementFactoryServiceImpl;
import org.tc.osgi.bundle.utils.module.service.impl.UtilsServiceImpl;

/**
 * JavaRequirementBuilderTest.java.
 * @author collonville thomas
 * @version
 */
public class JavaRequirementBuilderTest {

    private static final String filePath = "src/test/resources/ErosionFunctionTest.java";

    @Test
    public void testDependency() {
        final JavaRequirementBuilderServiceImpl builder = new JavaRequirementBuilderServiceImpl();
        builder.setM2FactoryService(new M2TrackingRequirementFactoryServiceImpl());
        builder.setUtilsService(new UtilsServiceImpl());
        List<AbstractRequirementElement> element;
        try {
            final BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/ErosionFunctionTest.java")));
            final List<String> l = new ArrayList<String>();
            while (reader.ready()) {
                l.add(reader.readLine());
            }
            reader.close();
            element = builder.extractRequirement(new File(JavaRequirementBuilderTest.filePath), l);
            final Requirement req = (Requirement) element.get(0);

            Assert.assertNull(req.getDependency());

        } catch (final IOException e) {
            Assert.fail("fichier n'est pas trouvé");
        }

    }

    @Test
    public void testDescription() {
        final JavaRequirementBuilderServiceImpl builder = new JavaRequirementBuilderServiceImpl();
        builder.setM2FactoryService(new M2TrackingRequirementFactoryServiceImpl());
        builder.setUtilsService(new UtilsServiceImpl());
        List<AbstractRequirementElement> element;
        try {
            final BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/ErosionFunctionTest.java")));
            final List<String> l = new ArrayList<String>();
            while (reader.ready()) {
                l.add(reader.readLine());
            }
            reader.close();
            element = builder.extractRequirement(new File(JavaRequirementBuilderTest.filePath), l);
            final Requirement req = (Requirement) element.get(0);

            Assert.assertEquals("STD_BUNDLE_MORPHPATH_020", req.getFirstElementDescription());

        } catch (final IOException e) {
            Assert.fail("fichier n'est pas trouvé");
        }

    }

    @Test
    public void testLoadFile() {
        final JavaRequirementBuilderServiceImpl builder = new JavaRequirementBuilderServiceImpl();
        builder.setM2FactoryService(new M2TrackingRequirementFactoryServiceImpl());
        builder.setUtilsService(new UtilsServiceImpl());
        List<AbstractRequirementElement> element;
        try {
            final BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/ErosionFunctionTest.java")));
            final List<String> l = new ArrayList<String>();
            while (reader.ready()) {
                l.add(reader.readLine());
            }
            reader.close();
            element = builder.extractRequirement(new File(JavaRequirementBuilderTest.filePath), l);
            final Requirement req = (Requirement) element.get(0);
            Assert.assertEquals("babla pour ce test qui n'en est pas un", req.getDescription().getFirstElementDescription());

        } catch (final IOException e) {
            Assert.fail("fichier n'est pas trouvé");
        }

    }

    @Test
    public void testTracking() {
        final JavaRequirementBuilderServiceImpl builder = new JavaRequirementBuilderServiceImpl();
        builder.setM2FactoryService(new M2TrackingRequirementFactoryServiceImpl());
        builder.setUtilsService(new UtilsServiceImpl());
        List<AbstractRequirementElement> element;
        try {
            final BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/ErosionFunctionTest.java")));
            final List<String> l = new ArrayList<String>();
            while (reader.ready()) {
                l.add(reader.readLine());
            }
            reader.close();
            element = builder.extractRequirement(new File(JavaRequirementBuilderTest.filePath), l);
            final Requirement req = (Requirement) element.get(0);

            Assert.assertEquals("SRS_BUNDLE_MORPHPATH_015", req.getTracking().getFirstElementDescription());
            Assert.assertEquals("SRS_BUNDLE_MORPHPATH_030", req.getTracking().getElementDescription().get(1));

        } catch (final IOException e) {
            Assert.fail("fichier n'est pas trouvé");
        }

    }
}
