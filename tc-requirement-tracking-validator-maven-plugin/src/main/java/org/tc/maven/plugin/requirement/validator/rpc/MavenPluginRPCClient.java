package org.tc.maven.plugin.requirement.validator.rpc;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.rpc.IRPCMavenPluginConnector;

/**
 * RMIClient.java.
 * @author collonville thomas
 * @version 0.2.0
 * @track SDD_BUNDLE_UTILS_130
 */
public class MavenPluginRPCClient {

    private static MavenPluginRPCClient instance = null;

    public synchronized static MavenPluginRPCClient getInstance() throws RemoteException, UnknownHostException {
        if (MavenPluginRPCClient.instance == null) {
            MavenPluginRPCClient.instance = new MavenPluginRPCClient();
        }
        return MavenPluginRPCClient.instance;
    }

    public static void main(final String[] args) {
        try {
            MavenPluginRPCClient.getInstance().getIRMIMavenPluginConnector();
        } catch (MalformedURLException | RemoteException | UnknownHostException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private IRPCMavenPluginConnector iConnector;

    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("requirement-tracking-validator-maven-plugin");

    private MavenPluginRPCClient() throws RemoteException, NumberFormatException, UnknownHostException {
    }

    public IRPCMavenPluginConnector getIRMIMavenPluginConnector() throws MalformedURLException, RemoteException, NotBoundException, UnknownHostException {
        if (iConnector == null) {

            final StringBuffer buff = new StringBuffer("http://");
            buff.append(InetAddress.getByName(getRmiAddr()).getHostAddress()).append(":").append(getRmiPort()).append("/ws/").append(IRPCMavenPluginConnector.class.getSimpleName()).append("?wsdl");
            System.out.println(buff.toString());
            final URL url = new URL(buff.toString());
            // 1st argument service URI le namespace peut etre, refer to wsdl
            // document above
            // 2nd argument is service name, refer to wsdl document above
            final QName qname = new QName("http://rpc.connector.plugin.maven.tracking.requirement.bundle.osgi.tc.org/", "IRPCMavenPluginConnectorImplService");
            final Service service = Service.create(url, qname);
            iConnector = service.getPort(IRPCMavenPluginConnector.class);
        }
        return iConnector;
    }

    public String getRmiAddr() {
        return resourceBundle.getString("tc.osgi.bundle.requirement.tracking.maven.plugin.connector.rmi.ip");
    }

    public String getRmiPort() {
        return resourceBundle.getString("tc.osgi.bundle.requirement.tracking.maven.plugin.connector.rmi.port");
    }

}
