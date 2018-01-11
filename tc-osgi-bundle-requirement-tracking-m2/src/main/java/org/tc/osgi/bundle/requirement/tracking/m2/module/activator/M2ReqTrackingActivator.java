package org.tc.osgi.bundle.requirement.tracking.m2.module.activator;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceRegistration;
import org.tc.osgi.bundle.requirement.tracking.m2.module.factory.M2ReqTrackingServiceFactory;
import org.tc.osgi.bundle.requirement.tracking.m2.module.service.IM2TrackingRequirementFactoryService;
import org.tc.osgi.bundle.requirement.tracking.m2.module.service.UtilsServiceProxy;
import org.tc.osgi.bundle.requirement.tracking.m2.module.tracking.UtilsServiceTracker;
import org.tc.osgi.bundle.utils.exception.FieldTrackingAssignementException;

/**
 * Activator.java.
 * @author Collonville Thomas
 * @version 0.0.1
 */
public class M2ReqTrackingActivator implements BundleActivator {

    private ServiceRegistration m2ReqTrackingServiceRegistration;

    /**
     * AptServiceTracker aptServiceTracker.
     */
    private UtilsServiceTracker utilsServiceTracker;

    private void activeM2ReqTrackingService(final BundleContext context) {
        final M2ReqTrackingServiceFactory factory = new M2ReqTrackingServiceFactory();
        m2ReqTrackingServiceRegistration = context.registerService(IM2TrackingRequirementFactoryService.class.getName(), factory, null);
        UtilsServiceProxy.getInstance().getLogger(M2ReqTrackingActivator.class).debug("IM2TrackingRequirementFactoryService start");
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
        UtilsServiceProxy.getInstance().getLogger(M2ReqTrackingActivator.class).debug("Start of utils service tracking");

    }

    /**
     * @param context BundleContext
     * @throws Exception
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        activeUtilsServiceTracker(context);
        activeM2ReqTrackingService(context);
    }

    /**
     * @param context BundleContext
     * @throws Exception
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(final BundleContext context) throws Exception {
        stopM2ReqTrackingService();
        stopUtilsServiceTracker();

    }

    private void stopM2ReqTrackingService() {
        m2ReqTrackingServiceRegistration.unregister();
        UtilsServiceProxy.getInstance().getLogger(M2ReqTrackingActivator.class).debug("IM2TrackingRequirementFactoryService stop");
    }

    /**
     * stopTracker.
     *
     */
    private void stopUtilsServiceTracker() {
        UtilsServiceProxy.getInstance().getLogger(M2ReqTrackingActivator.class).debug("Stop of utils service tracking");
        utilsServiceTracker.close();
    }

}
