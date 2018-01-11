/**
 */
package org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.conf;

import junit.framework.Assert;

import org.junit.Test;
import org.tc.osgi.bundle.utils.conf.XMLPropertyFile;
import org.tc.osgi.bundle.utils.exception.FieldTrackingAssignementException;

/**
 * MavenPluginConnectorPropertyFileTest.java.
 * @author collonville thomas
 * @version
 */
public class MavenPluginConnectorPropertyFileTest {

    private String rmiPort;

    public String getRmiPort() throws FieldTrackingAssignementException {
        if (rmiPort == null) {
            XMLPropertyFile.getInstance("src/test/resources/requirement-tracking-maven-plugin-connector").fieldTraking(this, "rmiPort");
        }
        return rmiPort;
    }

    @Test
    public void test() {

        try {
            Assert.assertEquals("24681", new MavenPluginConnectorPropertyFileTest().getRmiPort());
        } catch (final FieldTrackingAssignementException e) {
            Assert.fail(e.getLocalizedMessage());
        }
    }

}
