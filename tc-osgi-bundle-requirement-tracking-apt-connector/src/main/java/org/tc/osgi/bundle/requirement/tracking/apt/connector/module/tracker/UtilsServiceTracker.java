package org.tc.osgi.bundle.requirement.tracking.apt.connector.module.tracker;

import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.tc.osgi.bundle.utils.module.service.IUtilsService;

/**
 * UtilsServiceTracker.java.
 * @author Collonville Thomas
 * @version 0.0.1
 */
public class UtilsServiceTracker extends ServiceTracker {

    /**
     * AptServiceTracker constructor.
     * @param context BundleContext
     * @throws InvalidSyntaxException
     * @throws BundleException
     */
    public UtilsServiceTracker(final BundleContext context) throws InvalidSyntaxException, BundleException {
        super(context, IUtilsService.class.getName(), null);
    }

    /**
     * @param reference ServiceReference
     * @return Object
     * @see org.osgi.util.tracker.ServiceTracker#addingService(org.osgi.framework.ServiceReference)
     */
    @Override
    public Object addingService(final ServiceReference reference) {
        System.out.println("Inside UtilsServiceTracker.addingService " + reference.getBundle());
        return super.addingService(reference);
    }

    public IUtilsService getUtilsService() {
        return (IUtilsService) super.getService();
    }

    /**
     * @param reference ServiceReference
     * @param service Object
     * @see org.osgi.util.tracker.ServiceTracker#removedService(org.osgi.framework.ServiceReference, java.lang.Object)
     */
    @Override
    public void removedService(final ServiceReference reference, final Object service) {
        System.out.println("Inside UtilsServiceTracker.removedService " + reference.getBundle());
        super.removedService(reference, service);
    }

}
