package org.tc.osgi.bundle.requirement.tracking.core.module.service.activator;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceRegistration;
import org.tc.osgi.bundle.requirement.tracking.core.module.service.IGraphBuilderService;
import org.tc.osgi.bundle.requirement.tracking.core.module.service.UtilsServiceProxy;
import org.tc.osgi.bundle.requirement.tracking.core.module.service.factory.CoreReqTrackingServiceFactory;
import org.tc.osgi.bundle.requirement.tracking.core.module.tracker.UtilsServiceTracker;
import org.tc.osgi.bundle.utils.exception.FieldTrackingAssignementException;

/**
 * Activator.java.
 * @author Collonville Thomas
 * @version 0.0.1
 */
public class CoreReqTrackingActivator implements BundleActivator {

    private ServiceRegistration coreReqTrackingServiceRegistration;

    /**
     * AptServiceTracker aptServiceTracker.
     */
    private UtilsServiceTracker utilsServiceTracker;

    private void activeCoreReqTrackingService(final BundleContext context) {
        final CoreReqTrackingServiceFactory factory = new CoreReqTrackingServiceFactory();
        coreReqTrackingServiceRegistration = context.registerService(IGraphBuilderService.class.getName(), factory, null);
        UtilsServiceProxy.getInstance().getLogger(CoreReqTrackingActivator.class).debug("IGraphBuilderService start");
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
        UtilsServiceProxy.getInstance().getLogger(CoreReqTrackingActivator.class).debug("Start of utils service tracking");

    }

    /**
     * @param context BundleContext
     * @throws Exception
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        activeUtilsServiceTracker(context);
        activeCoreReqTrackingService(context);
    }

    /**
     * @param context BundleContext
     * @throws Exception
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(final BundleContext context) throws Exception {
        stopCoreReqTrackingService();
        stopUtilsServiceTracker();

    }

    private void stopCoreReqTrackingService() {
        coreReqTrackingServiceRegistration.unregister();
        UtilsServiceProxy.getInstance().getLogger(CoreReqTrackingActivator.class).debug("IGraphBuilderService stop");
    }

    /**
     * stopTracker.
     *
     */
    private void stopUtilsServiceTracker() {
        UtilsServiceProxy.getInstance().getLogger(CoreReqTrackingActivator.class).debug("Stop of utils service tracking");
        utilsServiceTracker.close();
    }

}
