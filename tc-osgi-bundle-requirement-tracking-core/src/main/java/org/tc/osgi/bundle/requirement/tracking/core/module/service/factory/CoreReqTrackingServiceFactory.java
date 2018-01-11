package org.tc.osgi.bundle.requirement.tracking.core.module.service.factory;

import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;
import org.tc.osgi.bundle.requirement.tracking.core.module.service.IGraphBuilderService;
import org.tc.osgi.bundle.requirement.tracking.core.module.service.UtilsServiceProxy;
import org.tc.osgi.bundle.requirement.tracking.core.module.service.impl.GraphBuilderServiceImpl;

/**
 * MorphMathCoreServiceFactory.java.
 * @author Collonville Thomas
 * @version 0.0.3
 */
public class CoreReqTrackingServiceFactory implements ServiceFactory {

    /**
     * int usageCounter.
     */
    private int usageCounter = 0;

    /**
     * @param bundle Bundle
     * @param registration ServiceRegistration
     * @return Object
     * @see org.osgi.framework.ServiceFactory#getService(org.osgi.framework.Bundle, org.osgi.framework.ServiceRegistration)
     */
    @Override
    public Object getService(final Bundle bundle, final ServiceRegistration registration) {
        UtilsServiceProxy.getInstance().getLogger(CoreReqTrackingServiceFactory.class).debug("Get CoreReqTrackingServiceFactory for " + bundle.getSymbolicName());
        usageCounter++;
        UtilsServiceProxy.getInstance().getLogger(CoreReqTrackingServiceFactory.class).debug("Number of bundles using service " + usageCounter);
        final IGraphBuilderService aptService = new GraphBuilderServiceImpl();
        return aptService;
    }

    /**
     * @param bundle Bundle
     * @param registration ServiceRegistration
     * @param service Object
     * @see org.osgi.framework.ServiceFactory#ungetService(org.osgi.framework.Bundle, org.osgi.framework.ServiceRegistration, java.lang.Object)
     */
    @Override
    public void ungetService(final Bundle bundle, final ServiceRegistration registration, final Object service) {
        UtilsServiceProxy.getInstance().getLogger(CoreReqTrackingServiceFactory.class).debug("Release CoreReqTrackingServiceFactory for " + bundle.getSymbolicName());
        usageCounter--;
        UtilsServiceProxy.getInstance().getLogger(CoreReqTrackingServiceFactory.class).debug("Number of bundles using service " + usageCounter);
    }
}
