package org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.module.activator;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.InvalidSyntaxException;
import org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.module.service.AptRequirementBuilderServiceProxy;
import org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.module.service.GraphBuilderServiceProxy;
import org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.module.service.RequirementRepportServiceProxy;
import org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.module.service.UtilsServiceProxy;
import org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.module.tracker.AptRequirementBuilderServiceTracker;
import org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.module.tracker.GraphBuilderServiceTracker;
import org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.module.tracker.RequirementRepportServiceTracker;
import org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.module.tracker.UtilsServiceTracker;
import org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.rpc.IRPCMavenPluginConnectorImpl;
import org.tc.osgi.bundle.utils.exception.FieldTrackingAssignementException;

/**
 * Activator.java.
 * @author Collonville Thomas
 * @version 0.0.1
 */
public class MavenPluginConnectorActivator implements BundleActivator {

    private AptRequirementBuilderServiceTracker aptRequirementBuilderServiceTracker;

    private GraphBuilderServiceTracker graphBuilderServiceTracker;

    private RequirementRepportServiceTracker requirementRepportServiceTracker;
    /**
     * AptServiceTracker aptServiceTracker.
     */
    private UtilsServiceTracker utilsServiceTracker;

    private IRPCMavenPluginConnectorImpl validator;

    /**
     * activeAptRequirementBuilderServiceTracker.
     * @param context
     * @throws BundleException
     * @throws InvalidSyntaxException
     */
    private void activeAptRequirementBuilderServiceTracker(final BundleContext context) throws InvalidSyntaxException, BundleException {
        aptRequirementBuilderServiceTracker = new AptRequirementBuilderServiceTracker(context);
        aptRequirementBuilderServiceTracker.open();
        AptRequirementBuilderServiceProxy.getInstance().setService(aptRequirementBuilderServiceTracker.getAptRequirementBuilderService());
        UtilsServiceProxy.getInstance().getLogger(MavenPluginConnectorActivator.class).debug("Start of aptRequirementBuilderServiceTracker");

    }

    /**
     * activeGraphBuilderServiceTracker.
     * @param context
     * @throws BundleException
     * @throws InvalidSyntaxException
     */
    private void activeGraphBuilderServiceTracker(final BundleContext context) throws InvalidSyntaxException, BundleException {
        graphBuilderServiceTracker = new GraphBuilderServiceTracker(context);
        graphBuilderServiceTracker.open();
        GraphBuilderServiceProxy.getInstance().setService(graphBuilderServiceTracker.getGraphBuilderService());
        UtilsServiceProxy.getInstance().getLogger(MavenPluginConnectorActivator.class).debug("Start of graphBuilderServiceTracker");

    }

    /**
     * activeRequirementRepportServiceTracker.
     * @param context
     * @throws BundleException
     * @throws InvalidSyntaxException
     */
    private void activeRequirementRepportServiceTracker(final BundleContext context) throws InvalidSyntaxException, BundleException {
        requirementRepportServiceTracker = new RequirementRepportServiceTracker(context);
        requirementRepportServiceTracker.open();
        RequirementRepportServiceProxy.getInstance().setService(requirementRepportServiceTracker.getRequirementRepportService());
        UtilsServiceProxy.getInstance().getLogger(MavenPluginConnectorActivator.class).debug("Start of requirementRepportServiceTracker");
    }

    /**
     * activeTracker.
     * @throws BundleException
     * @throws InvalidSyntaxException
     * @throws UnknownHostException
     * @throws MalformedURLException
     * @throws FieldTrackingAssignementException
     * @throws RemoteException
     */
    private void activeUtilsServiceTracker(final BundleContext context) throws InvalidSyntaxException, BundleException, RemoteException, FieldTrackingAssignementException, MalformedURLException,
    UnknownHostException {
        utilsServiceTracker = new UtilsServiceTracker(context);
        utilsServiceTracker.open();
        UtilsServiceProxy.getInstance().setService(utilsServiceTracker.getUtilsService());
        UtilsServiceProxy.getInstance().getLogger(MavenPluginConnectorActivator.class).debug("Start of utils service tracking");

    }

    /**
     * @param context BundleContext
     * @throws Exception
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        activeUtilsServiceTracker(context);
        activeAptRequirementBuilderServiceTracker(context);
        activeGraphBuilderServiceTracker(context);
        activeRequirementRepportServiceTracker(context);
        validator = new IRPCMavenPluginConnectorImpl();
    }

    /**
     * @param context BundleContext
     * @throws Exception
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(final BundleContext context) throws Exception {
        stopRequirementRepportServiceTracker();
        stopAptRequirementBuilderServiceTracker();
        stopGraphBuilderServiceTracker();
        stopUtilsServiceTracker();

    }

    /**
     * stopAptRequirementBuilderServiceTracker.
     */
    private void stopAptRequirementBuilderServiceTracker() {
        UtilsServiceProxy.getInstance().getLogger(MavenPluginConnectorActivator.class).debug("Stop of AptRequirementBuilder service tracking");
        aptRequirementBuilderServiceTracker.close();

    }

    /**
     * stopGraphBuilderServiceTracker.
     */
    private void stopGraphBuilderServiceTracker() {
        UtilsServiceProxy.getInstance().getLogger(MavenPluginConnectorActivator.class).debug("Stop of GraphBuilder service tracking");
        graphBuilderServiceTracker.close();

    }

    /**
     * stopRequirementRepportServiceTracker.
     */
    private void stopRequirementRepportServiceTracker() {
        UtilsServiceProxy.getInstance().getLogger(MavenPluginConnectorActivator.class).debug("Stop of RequirementRepport service tracking");
        requirementRepportServiceTracker.close();

    }

    /**
     * stopTracker.
     *
     */
    private void stopUtilsServiceTracker() {
        UtilsServiceProxy.getInstance().getLogger(MavenPluginConnectorActivator.class).debug("Stop of utils service tracking");
        utilsServiceTracker.close();
    }

}
