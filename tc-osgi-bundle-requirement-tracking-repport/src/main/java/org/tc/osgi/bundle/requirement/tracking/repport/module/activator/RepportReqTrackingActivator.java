package org.tc.osgi.bundle.requirement.tracking.repport.module.activator;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceRegistration;
import org.tc.osgi.bundle.requirement.tracking.repport.module.factory.RepportReqTrackingServiceFactory;
import org.tc.osgi.bundle.requirement.tracking.repport.module.service.GraphBuilderServiceProxy;
import org.tc.osgi.bundle.requirement.tracking.repport.module.service.IRequirementRepportService;
import org.tc.osgi.bundle.requirement.tracking.repport.module.service.UtilsServiceProxy;
import org.tc.osgi.bundle.requirement.tracking.repport.module.tracker.GraphBuilderServiceTracker;
import org.tc.osgi.bundle.requirement.tracking.repport.module.tracker.UtilsServiceTracker;
import org.tc.osgi.bundle.utils.exception.FieldTrackingAssignementException;

/**
 * Activator.java.
 * @author Collonville Thomas
 * @version 0.0.1
 */
public class RepportReqTrackingActivator implements BundleActivator {

    private GraphBuilderServiceTracker graphBuilderServiceTracker;
    private ServiceRegistration repportReqTrackingServiceRegistration;
    /**
     * AptServiceTracker aptServiceTracker.
     */
    private UtilsServiceTracker utilsServiceTracker;

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
        UtilsServiceProxy.getInstance().getLogger(RepportReqTrackingActivator.class).debug("Start of graphBuilderServiceTracker");

    }

    private void activeRepportReqTrackingService(final BundleContext context) {
        final RepportReqTrackingServiceFactory factory = new RepportReqTrackingServiceFactory();
        repportReqTrackingServiceRegistration = context.registerService(IRequirementRepportService.class.getName(), factory, null);
        UtilsServiceProxy.getInstance().getLogger(RepportReqTrackingActivator.class).debug("IRequirementRepportService start");
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
        UtilsServiceProxy.getInstance().getLogger(RepportReqTrackingActivator.class).debug("Start of utils service tracking");

    }

    /**
     * @param context BundleContext
     * @throws Exception
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        activeUtilsServiceTracker(context);
        activeGraphBuilderServiceTracker(context);
        activeRepportReqTrackingService(context);

    }

    /**
     * @param context BundleContext
     * @throws Exception
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(final BundleContext context) throws Exception {

        stopRepportReqTrackingService();
        stopGraphBuilderServiceTracker();
        stopUtilsServiceTracker();

    }

    /**
     * stopGraphBuilderServiceTracker.
     */
    private void stopGraphBuilderServiceTracker() {
        UtilsServiceProxy.getInstance().getLogger(RepportReqTrackingActivator.class).debug("Stop of GraphBuilder service tracking");
        graphBuilderServiceTracker.close();

    }

    private void stopRepportReqTrackingService() {
        repportReqTrackingServiceRegistration.unregister();
        UtilsServiceProxy.getInstance().getLogger(RepportReqTrackingActivator.class).debug("IRequirementRepportService stop");
    }

    /**
     * stopTracker.
     *
     */
    private void stopUtilsServiceTracker() {
        UtilsServiceProxy.getInstance().getLogger(RepportReqTrackingActivator.class).debug("Stop of utils service tracking");
        utilsServiceTracker.close();
    }

}
