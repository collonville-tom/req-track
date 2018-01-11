package org.tc.osgi.bundle.requirement.tracking.apt.connector.module.activator;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceRegistration;
import org.tc.osgi.bundle.requirement.tracking.apt.connector.module.factory.AptReqConnectorServiceFactory;
import org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service.AptIoServiceProxy;
import org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service.IAptRequirementBuilderService;
import org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service.M2TrackingRequirementFactoryServiceProxy;
import org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service.UtilsServiceProxy;
import org.tc.osgi.bundle.requirement.tracking.apt.connector.module.tracker.AptIoServiceTracker;
import org.tc.osgi.bundle.requirement.tracking.apt.connector.module.tracker.M2ReqServiceTracker;
import org.tc.osgi.bundle.requirement.tracking.apt.connector.module.tracker.UtilsServiceTracker;
import org.tc.osgi.bundle.utils.exception.FieldTrackingAssignementException;

/**
 * Activator.java.
 * @author Collonville Thomas
 * @version 0.0.1
 */
public class AptConnectorActivator implements BundleActivator {

    private ServiceRegistration aptConnectorServiceRegistration;

    private AptIoServiceTracker aptIoServiceTracker;

    private M2ReqServiceTracker m2ReqServiceTracker;

    /**
     * AptServiceTracker aptServiceTracker.
     */
    private UtilsServiceTracker utilsServiceTracker;

    private void activeAptConnectorService(final BundleContext context) {
        final AptReqConnectorServiceFactory factory = new AptReqConnectorServiceFactory();
        aptConnectorServiceRegistration = context.registerService(IAptRequirementBuilderService.class.getName(), factory, null);
        UtilsServiceProxy.getInstance().getLogger(AptConnectorActivator.class).debug("AptIoService start");
    }

    /**
     * activeGraphBuilderServiceTracker.
     * @param context
     * @throws BundleException
     * @throws InvalidSyntaxException
     */
    private void activeAptIoServiceTracker(final BundleContext context) throws InvalidSyntaxException, BundleException {
        aptIoServiceTracker = new AptIoServiceTracker(context);
        aptIoServiceTracker.open();
        AptIoServiceProxy.getInstance().setService(aptIoServiceTracker.getAptIoService());
        UtilsServiceProxy.getInstance().getLogger(AptConnectorActivator.class).debug("Start of aptIoServiceTracker");

    }

    /**
     * activeAptRequirementBuilderServiceTracker.
     * @param context
     * @throws BundleException
     * @throws InvalidSyntaxException
     */
    private void activeM2ReqServiceTracker(final BundleContext context) throws InvalidSyntaxException, BundleException {
        m2ReqServiceTracker = new M2ReqServiceTracker(context);
        m2ReqServiceTracker.open();
        M2TrackingRequirementFactoryServiceProxy.getInstance().setService(m2ReqServiceTracker.getM2ReqService());
        UtilsServiceProxy.getInstance().getLogger(AptConnectorActivator.class).debug("Start of aptRequirementBuilderServiceTracker");

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
        UtilsServiceProxy.getInstance().getLogger(AptConnectorActivator.class).debug("Start of utils service tracking");

    }

    /**
     * @param context BundleContext
     * @throws Exception
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        activeUtilsServiceTracker(context);
        activeM2ReqServiceTracker(context);
        activeAptIoServiceTracker(context);
        activeAptConnectorService(context);
    }

    /**
     * @param context BundleContext
     * @throws Exception
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(final BundleContext context) throws Exception {
        stopAptConnectorService();
        stopM2ReqServiceTracker();
        stopAptIoServiceTracker();
        stopUtilsServiceTracker();

    }

    private void stopAptConnectorService() {
        aptConnectorServiceRegistration.unregister();
        UtilsServiceProxy.getInstance().getLogger(AptConnectorActivator.class).debug("APtIoCoreService stop");
    }

    /**
     * stopGraphBuilderServiceTracker.
     */
    private void stopAptIoServiceTracker() {
        UtilsServiceProxy.getInstance().getLogger(AptConnectorActivator.class).debug("Stop of aptIoServiceTracker service tracking");
        aptIoServiceTracker.close();

    }

    /**
     * stopAptRequirementBuilderServiceTracker.
     */
    private void stopM2ReqServiceTracker() {
        UtilsServiceProxy.getInstance().getLogger(AptConnectorActivator.class).debug("Stop of aptIoServiceTracker service tracking");
        m2ReqServiceTracker.close();

    }

    /**
     * stopTracker.
     *
     */
    private void stopUtilsServiceTracker() {
        UtilsServiceProxy.getInstance().getLogger(AptConnectorActivator.class).debug("Stop of utils service tracking");
        utilsServiceTracker.close();
    }

}
