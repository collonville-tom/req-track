package org.tc.osgi.bundle.requirement.tracking.apt.connector.module.tracker;

import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.tc.osgi.bundle.requirement.tracking.apt.connector.conf.AptConnectorPropertyFile;
import org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service.UtilsServiceProxy;
import org.tc.osgi.bundle.requirement.tracking.m2.module.service.IM2TrackingRequirementFactoryService;
import org.tc.osgi.bundle.utils.exception.FieldTrackingAssignementException;

/**
 * UtilsServiceTracker.java.
 * @author Collonville Thomas
 * @version 0.0.1
 */
public class M2ReqServiceTracker extends ServiceTracker {

    private String m2ReqDependencyBundleName;

    /**
     * AptServiceTracker constructor.
     * @param context BundleContext
     * @throws InvalidSyntaxException
     * @throws BundleException
     */
    public M2ReqServiceTracker(final BundleContext context) throws InvalidSyntaxException, BundleException {
        super(context, IM2TrackingRequirementFactoryService.class.getName(), null);
        try {
            UtilsServiceProxy.getInstance().getBundleStarter().startBundle(this.context, getM2ReqDependencyBundleName());

        } catch (BundleException | FieldTrackingAssignementException e) {
            UtilsServiceProxy.getInstance().getLogger(M2ReqServiceTracker.class).error(e.getMessage(), e);
        }
    }

    /**
     * @param reference ServiceReference
     * @return Object
     * @see org.osgi.util.tracker.ServiceTracker#addingService(org.osgi.framework.ServiceReference)
     */
    @Override
    public Object addingService(final ServiceReference reference) {
        UtilsServiceProxy.getInstance().getLogger(M2ReqServiceTracker.class).debug("Inside M2ReqServiceTracker.addingService " + reference.getBundle());
        return super.addingService(reference);
    }

    private String getM2ReqDependencyBundleName() throws FieldTrackingAssignementException {
        if (m2ReqDependencyBundleName == null) {
            UtilsServiceProxy.getInstance().getXMLPropertyFile(AptConnectorPropertyFile.getInstance().getXMLFile()).fieldTraking(this, "m2ReqDependencyBundleName");
        }
        UtilsServiceProxy.getInstance().getLogger(M2ReqServiceTracker.class).debug("Lancement auto du bundle :" + m2ReqDependencyBundleName);
        return m2ReqDependencyBundleName;
    }

    public IM2TrackingRequirementFactoryService getM2ReqService() {
        return (IM2TrackingRequirementFactoryService) super.getService();
    }

    /**
     * @param reference ServiceReference
     * @param service Object
     * @see org.osgi.util.tracker.ServiceTracker#removedService(org.osgi.framework.ServiceReference, java.lang.Object)
     */
    @Override
    public void removedService(final ServiceReference reference, final Object service) {
        UtilsServiceProxy.getInstance().getLogger(M2ReqServiceTracker.class).debug("Inside M2ReqServiceTracker.removedService " + reference.getBundle());
        super.removedService(reference, service);
    }

}
