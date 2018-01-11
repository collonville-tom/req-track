/**
 */
package org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.rpc;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

import javax.xml.ws.Endpoint;

import org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.conf.MavenPluginConnectorPropertyFile;
import org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.module.service.UtilsServiceProxy;
import org.tc.osgi.bundle.utils.conf.XMLPropertyFile;
import org.tc.osgi.bundle.utils.exception.FieldTrackingAssignementException;
import org.tc.osgi.bundle.utils.logger.LoggerGestionnary;
import org.tc.osgi.bundle.utils.rpc.AbstractRPCServer;

/**
 * MavenPluginRMIConnector.java.
 * @author collonville thomas
 * @version
 */
public class MavenPluginRPCServer extends AbstractRPCServer {

    private final String rmiAddr = null;

    private final String rmiPort = null;

    /**
     * MavenPluginRMIConnector constructor.
     * @throws RemoteException
     * @throws FieldTrackingAssignementException
     */
    public MavenPluginRPCServer() {

    }

    @Override
    public void addObject(final String signature, final Object obj) throws RemoteException, MalformedURLException, UnknownHostException, FieldTrackingAssignementException {
        final StringBuilder b = new StringBuilder("http://");
        b.append(getRmiAddr());
        b.append(":");
        b.append(getRmiPort());
        b.append("/ws/");
        b.append(signature);
        LoggerGestionnary.getInstance(MavenPluginRPCServer.class).debug("Deploiement du webservice : " + b.toString());
        Endpoint.publish(b.toString(), obj);
    }

    /**
     * @return
     * @throws FieldTrackingAssignementException
     * @see org.tc.osgi.bundle.utils.rmi.server.AbstractRMIServer#getRmiAddr()
     */
    @Override
    public String getRmiAddr() throws FieldTrackingAssignementException {
        if (rmiAddr == null) {
            UtilsServiceProxy.getInstance().getXMLPropertyFile(MavenPluginConnectorPropertyFile.getInstance().getXMLFile()).fieldTraking(this, "rmiAddr");
        }
        return rmiAddr;
    }

    /**
     * @return
     * @throws FieldTrackingAssignementException
     * @see org.tc.osgi.bundle.utils.rmi.server.AbstractRMIServer#getRmiPort()
     */
    @Override
    public String getRmiPort() throws FieldTrackingAssignementException {
        if (rmiPort == null) {
            final String xmlFile = MavenPluginConnectorPropertyFile.getInstance().getXMLFile();
            final XMLPropertyFile xmlFileObject = UtilsServiceProxy.getInstance().getXMLPropertyFile(xmlFile);
            xmlFileObject.fieldTraking(this, "rmiPort");
        }
        return rmiPort;
    }

}
