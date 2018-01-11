package org.tc.osgi.bundle.requirement.tracking.m2.module.factory;

import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;
import org.tc.osgi.bundle.requirement.tracking.m2.module.service.IM2TrackingRequirementFactoryService;
import org.tc.osgi.bundle.requirement.tracking.m2.module.service.UtilsServiceProxy;
import org.tc.osgi.bundle.requirement.tracking.m2.module.service.impl.M2TrackingRequirementFactoryServiceImpl;

/**
 * MorphMathCoreServiceFactory.java.
 * @author Collonville Thomas
 * @version 0.0.3
 */
public class M2ReqTrackingServiceFactory implements ServiceFactory {

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
        UtilsServiceProxy.getInstance().getLogger(M2ReqTrackingServiceFactory.class).debug("Get M2ReqTrackingServiceFactory for " + bundle.getSymbolicName());
        usageCounter++;
        UtilsServiceProxy.getInstance().getLogger(M2ReqTrackingServiceFactory.class).debug("Number of bundles using service " + usageCounter);
        final IM2TrackingRequirementFactoryService aptService = new M2TrackingRequirementFactoryServiceImpl();
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
        UtilsServiceProxy.getInstance().getLogger(M2ReqTrackingServiceFactory.class).debug("Release M2ReqTrackingServiceFactory for " + bundle.getSymbolicName());
        usageCounter--;
        UtilsServiceProxy.getInstance().getLogger(M2ReqTrackingServiceFactory.class).debug("Number of bundles using service " + usageCounter);
    }
}
