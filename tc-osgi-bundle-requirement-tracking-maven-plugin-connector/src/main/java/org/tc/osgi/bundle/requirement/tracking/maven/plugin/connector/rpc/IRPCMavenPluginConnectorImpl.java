/**
 */
package org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.rpc;

import java.beans.XMLEncoder;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.tc.osgi.bundle.requirement.tracking.core.graph.TrackingGraph;
import org.tc.osgi.bundle.requirement.tracking.m2.model.AbstractRequirementElement;
import org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.analyser.AbstractFileAnalyser;
import org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.analyser.AptFileAnalyser;
import org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.module.service.AptRequirementBuilderServiceProxy;
import org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.module.service.GraphBuilderServiceProxy;
import org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.module.service.RequirementRepportServiceProxy;
import org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.module.service.UtilsServiceProxy;
import org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.tools.MavenPLuginTools;
import org.tc.osgi.bundle.requirement.tracking.repport.exception.RequirementAnalyserException;
import org.tc.osgi.bundle.requirement.tracking.repport.gen.RepportGenerator;
import org.tc.osgi.bundle.utils.exception.FieldTrackingAssignementException;
import org.tc.osgi.bundle.utils.io.StringBufferOutputStream;

/**
 * RequirementValidatorServiceImpl.java.
 * @author collonville thomas
 * @version 0.1.0
 */
@WebService(endpointInterface = "org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.rpc.IRPCMavenPluginConnector")
public class IRPCMavenPluginConnectorImpl implements IRPCMavenPluginConnector {

    /**
     * long serialVersionUID.
     */
    private static final long serialVersionUID = 4104316156354557345L;

    /**
     * Getter serialversionuid.
     * @return
     */
    public static long getSerialversionuid() {
        return IRPCMavenPluginConnectorImpl.serialVersionUID;
    }

    private final List<AbstractFileAnalyser> analysers = new ArrayList<AbstractFileAnalyser>();
    private MavenPluginRPCServer mavenPluginRPCServer = null;
    private final List<AbstractRequirementElement> reqElement = new ArrayList<AbstractRequirementElement>();

    public IRPCMavenPluginConnectorImpl() throws RemoteException, FieldTrackingAssignementException, MalformedURLException, UnknownHostException {
        initRPCServer();

    }

    /**
     * @return
     * @throws RequirementAnalyserException
     * @see org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.rpc.IRPCMavenPluginConnector.plugin.requirement.validator.rmi.interf.IRMIMavenPluginConnector#extractRepportList()
     */
    @Override
    public String extractRepportList(final String projectName) throws RequirementAnalyserException {
        // TODO voir le contenu de cette fontion, voir si les logs sont
        // suffisant et revoir le mode de retour des valeurs
        final TrackingGraph graph = GraphBuilderServiceProxy.getInstance().buildTrackingGraph(projectName, reqElement);
        UtilsServiceProxy.getInstance().getLogger(IRPCMavenPluginConnectorImpl.class).info("RequirementValidatorMojo graph description:" + graph.toString());

        UtilsServiceProxy.getInstance().getLogger(IRPCMavenPluginConnectorImpl.class).info("Generation des rapports d'analyse");
        final RepportGenerator generator = RequirementRepportServiceProxy.getInstance().getRepportGenerator(graph);
        final StringBufferOutputStream sbos = new StringBufferOutputStream();
        final XMLEncoder xmlEncoder = new XMLEncoder(sbos);
        xmlEncoder.writeObject(generator.generate());
        xmlEncoder.flush();
        xmlEncoder.close();
        UtilsServiceProxy.getInstance().getLogger(IRPCMavenPluginConnectorImpl.class).info("XMLRepport" + sbos.getValue());
        return sbos.getValue().toString();
    }

    /**
     * @param file
     * @param l
     * @throws IOException
     * @see org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.rpc.IRPCMavenPluginConnector.plugin.requirement.validator.rmi.interf.IRMIMavenPluginConnector#extractRequirementsInFile(java.io.File, java.util.List)
     */
    @Override
    public void extractRequirementsInFile(final File file, final String l) throws IOException {
        UtilsServiceProxy.getInstance().getLogger(IRPCMavenPluginConnectorImpl.class).debug("File content not cutting trought web service:" + l);
        final List<String> content = MavenPLuginTools.cutContent(l);
        UtilsServiceProxy.getInstance().getLogger(IRPCMavenPluginConnectorImpl.class).info("File content trought web service:" + content.get(0));
        for (final AbstractFileAnalyser analyser : analysers) {
            if (analyser.filterFile(file)) {
                reqElement.addAll(analyser.extractRequirement(file, content));
            }
        }

        UtilsServiceProxy.getInstance().getLogger(IRPCMavenPluginConnectorImpl.class).info("List of Extract Requirement" + MavenPLuginTools.printLoadedReqList(reqElement));

    }

    /**
     * @param file
     * @see org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.rpc.IRPCMavenPluginConnector.plugin.requirement.validator.rmi.interf.IRMIMavenPluginConnector#filterFile(java.io.File)
     */
    @Override
    public boolean filterFile(final File file) {
        for (final AbstractFileAnalyser analyser : analysers) {
            if (analyser.filterFile(file)) {
                return true;
            }
        }
        return false;

    }

    /**
     *
     * @see org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.rpc.IRPCMavenPluginConnector.plugin.requirement.validator.rmi.interf.IRMIMavenPluginConnector#flushState()
     */
    @Override
    public void flushState() {
        UtilsServiceProxy.getInstance().getLogger(IRPCMavenPluginConnectorImpl.class).info("Vidage pile d'exigences");
        reqElement.clear();

    }

    /**
     * Getter analysers.
     * @return
     */
    public List<AbstractFileAnalyser> getAnalysers() {
        return analysers;
    }

    /**
     * Getter mavenPluginRMIServer.
     * @return
     */
    public MavenPluginRPCServer getMavenPluginRMIServer() {
        return mavenPluginRPCServer;
    }

    /**
     * Getter mavenPluginRMIServerService.
     * @return
     */
    public MavenPluginRPCServer getMavenPluginRMIServerService() {
        return mavenPluginRPCServer;
    }

    // TODO ca c'est a ajouter quand on aura extrait les exigences des classe
    // java aussi (comprenant les test bien entendu)
    // private IJavaRequirementBuilderService javaRequirementBuilderService =
    // null;

    /**
     * Getter reqElement.
     * @return
     */
    public List<AbstractRequirementElement> getReqElement() {
        return reqElement;
    }

    /**
     * initAnalysers.
     */
    @Override
    public void initAnalysers() {
        UtilsServiceProxy.getInstance().getLogger(IRPCMavenPluginConnectorImpl.class).info("Initialisation des analysers de fichiers");
        final AbstractFileAnalyser aptAnalyser = new AptFileAnalyser(AptRequirementBuilderServiceProxy.getInstance().getService());
        // TODO ca c'est a ajouter quand on aura extrait les exigences des
        // classe java aussi (comprenant les test bien entendu)
        // final AbstractFileAnalyser javaAnalyser = new
        // JavaFileAnalyser(this.javaRequirementBuilderService);

        analysers.add(aptAnalyser);
        // TODO ca c'est a ajouter quand on aura extrait les exigences des
        // classe java aussi (comprenant les test bien entendu)
        // this.analysers.add(javaAnalyser);

    }

    protected void initRPCServer() throws RemoteException, MalformedURLException, UnknownHostException, FieldTrackingAssignementException {
        mavenPluginRPCServer = new MavenPluginRPCServer();
        mavenPluginRPCServer.addObject(IRPCMavenPluginConnector.class.getSimpleName(), this);
    }

    /**
     * Setter mavenPluginRMIServer.
     * @param mavenPluginRMIServer MavenPluginRMIServer
     */
    public void setMavenPluginRMIServer(final MavenPluginRPCServer mavenPluginRMIServer) {
        mavenPluginRPCServer = mavenPluginRMIServer;
    }

    /**
     * Setter mavenPluginRMIServerService.
     * @param mavenPluginRMIServerService IMavenPluginRMIServerServiceImpl
     */
    public void setMavenPluginRMIServerService(final MavenPluginRPCServer mavenPluginRMIServerService) {
        mavenPluginRPCServer = mavenPluginRMIServerService;
    }

}
