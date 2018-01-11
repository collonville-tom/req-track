/**
 */
package org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.rpc;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.Remote;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.tc.osgi.bundle.requirement.tracking.repport.exception.RequirementAnalyserException;

/**
 * IRMIMavenPluginConnector.java.
 * @author collonville thomas
 * @version
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface IRPCMavenPluginConnector extends Remote, Serializable {

    /**
     * extractRepportList.
     * @return
     * @throws RequirementAnalyserException
     */
    @WebMethod
    String extractRepportList(String projectName) throws RequirementAnalyserException;

    /**
     * extractFile.
     * @param file
     * @param l
     * @throws IOException
     */
    @WebMethod
    void extractRequirementsInFile(File file, String l) throws IOException;

    /**
     * insertFile.
     * @param file
     */
    @WebMethod
    boolean filterFile(File file);

    /**
     * flushState.
     */
    @WebMethod
    void flushState();

    /**
     * initAnalysers.
     */
    @WebMethod
    void initAnalysers();

}
