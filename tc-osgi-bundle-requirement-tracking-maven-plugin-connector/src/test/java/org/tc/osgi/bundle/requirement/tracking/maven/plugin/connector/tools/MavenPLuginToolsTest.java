/**
 */
package org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.tools;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.module.service.UtilsServiceProxy;
import org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.rpc.IRPCMavenPluginConnectorImpl;
import org.tc.osgi.bundle.utils.exception.FieldTrackingAssignementException;
import org.tc.osgi.bundle.utils.module.service.impl.UtilsServiceImpl;

/**
 * IRPCMavenPluginConnectorImplTest.java.
 * @author collonville thomas
 * @version
 */
public class MavenPLuginToolsTest {
    @SuppressWarnings("serial")
    public class IRPCMavenPluginConnectorImpl2 extends IRPCMavenPluginConnectorImpl {

        /**
         * long serialVersionUID.
         */
        private static final long serialVersionUID = -3688355632803257458L;

        /**
         * IRPCMavenPluginConnectorImpl2 constructor.
         * @throws RemoteException
         * @throws FieldTrackingAssignementException
         * @throws MalformedURLException
         * @throws UnknownHostException
         */
        public IRPCMavenPluginConnectorImpl2() throws RemoteException, FieldTrackingAssignementException, MalformedURLException, UnknownHostException {
            super();
        }

        @Override
        protected void initRPCServer() {

        }
    }

    @Test
    public void cutLigneTest() {

        UtilsServiceProxy.getInstance().setService(new UtilsServiceImpl());
        try {
            new IRPCMavenPluginConnectorImpl2();
            final String test = "toto|tata|titi||tutu";
            final List<String> val = MavenPLuginTools.cutContent(test);
            Assert.assertEquals("toto", val.get(0));
            Assert.assertEquals("tata", val.get(1));
            Assert.assertEquals("titi", val.get(2));
            Assert.assertEquals("", val.get(3));
            Assert.assertEquals("tutu", val.get(4));
        } catch (final RemoteException e) {
            Assert.fail(e.getMessage());
        } catch (final FieldTrackingAssignementException e) {
            Assert.fail(e.getMessage());
        } catch (final MalformedURLException e) {
            Assert.fail(e.getMessage());
        } catch (final UnknownHostException e) {
            Assert.fail(e.getMessage());
        }

    }

}