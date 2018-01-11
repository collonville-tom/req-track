package org.tc.osgi.bundle.requirement.tracking.apt.connector.module.tracker;

import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.tc.osgi.bundle.apt.io.module.service.IAptIoService;
import org.tc.osgi.bundle.requirement.tracking.apt.connector.conf.AptConnectorPropertyFile;
import org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service.UtilsServiceProxy;
import org.tc.osgi.bundle.utils.exception.FieldTrackingAssignementException;

/**
 * UtilsServiceTracker.java.
 * @author Collonville Thomas
 * @version 0.0.1
 */
public class AptIoServiceTracker extends ServiceTracker {

    private String aptIoDependencyBundleName;

    /**
     * AptServiceTracker constructor.
     * @param context BundleContext
     * @throws InvalidSyntaxException
     * @throws BundleException
     */
    public AptIoServiceTracker(final BundleContext context) throws InvalidSyntaxException, BundleException {
        super(context, IAptIoService.class.getName(), null);
        try {
            UtilsServiceProxy.getInstance().getBundleStarter().startBundle(this.context, getAptIoDependencyBundleName());

        } catch (BundleException | FieldTrackingAssignementException e) {
            UtilsServiceProxy.getInstance().getLogger(AptIoServiceTracker.class).error(e.getMessage(), e);
        }
    }

    /**
     * @param reference ServiceReference
     * @return Object
     * @see org.osgi.util.tracker.ServiceTracker#addingService(org.osgi.framework.ServiceReference)
     */
    @Override
    public Object addingService(final ServiceReference reference) {
        UtilsServiceProxy.getInstance().getLogger(AptIoServiceTracker.class).debug("Inside AptIoServiceTracker.addingService " + reference.getBundle());
        return super.addingService(reference);
    }

    private String getAptIoDependencyBundleName() throws FieldTrackingAssignementException {
        if (aptIoDependencyBundleName == null) {
            UtilsServiceProxy.getInstance().getXMLPropertyFile(AptConnectorPropertyFile.getInstance().getXMLFile()).fieldTraking(this, "aptIoDependencyBundleName");
        }
        UtilsServiceProxy.getInstance().getLogger(AptIoServiceTracker.class).debug("Lancement auto du bundle :" + aptIoDependencyBundleName);
        return aptIoDependencyBundleName;
    }

    public IAptIoService getAptIoService() {
        return (IAptIoService) super.getService();
    }

    /**
     * @param reference ServiceReference
     * @param service Object
     * @see org.osgi.util.tracker.ServiceTracker#removedService(org.osgi.framework.ServiceReference, java.lang.Object)
     */
    @Override
    public void removedService(final ServiceReference reference, final Object service) {
        UtilsServiceProxy.getInstance().getLogger(AptIoServiceTracker.class).debug("Inside AptIoServiceTracker.removedService " + reference.getBundle());
        super.removedService(reference, service);
    }

}
