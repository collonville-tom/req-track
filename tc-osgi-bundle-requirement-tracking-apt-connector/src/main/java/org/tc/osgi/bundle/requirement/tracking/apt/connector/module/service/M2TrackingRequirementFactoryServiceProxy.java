package org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service;

import org.tc.osgi.bundle.requirement.tracking.m2.model.Requirement;
import org.tc.osgi.bundle.requirement.tracking.m2.model.RequirementGroup;
import org.tc.osgi.bundle.requirement.tracking.m2.model.elements.RequirementDescription;
import org.tc.osgi.bundle.requirement.tracking.m2.module.service.IM2TrackingRequirementFactoryService;

public class M2TrackingRequirementFactoryServiceProxy implements IM2TrackingRequirementFactoryService {

    private static M2TrackingRequirementFactoryServiceProxy instance = null;

    public static M2TrackingRequirementFactoryServiceProxy getInstance() {
        if (M2TrackingRequirementFactoryServiceProxy.instance == null) {
            M2TrackingRequirementFactoryServiceProxy.instance = new M2TrackingRequirementFactoryServiceProxy();
        }
        return M2TrackingRequirementFactoryServiceProxy.instance;
    }

    private IM2TrackingRequirementFactoryService service = null;

    private M2TrackingRequirementFactoryServiceProxy() {

    }

    /**
     * @param string
     * @return
     * @see org.tc.osgi.bundle.requirement.tracking.m2.module.service.IM2TrackingRequirementFactoryService#getRequirementDescription(java.lang.String)
     */
    @Override
    public RequirementDescription getRequirementDescription(final String string) {
        return service.getRequirementDescription(string);
    }

    /**
     * @param string
     * @return
     * @see org.tc.osgi.bundle.requirement.tracking.m2.module.service.IM2TrackingRequirementFactoryService#getRequirementGroupInstance(java.lang.String)
     */
    @Override
    public RequirementGroup getRequirementGroupInstance(final String string) {
        return service.getRequirementGroupInstance(string);
    }

    /**
     * @param title
     * @return
     * @see org.tc.osgi.bundle.requirement.tracking.m2.module.service.IM2TrackingRequirementFactoryService#getRequirementInstance(java.lang.String)
     */
    @Override
    public Requirement getRequirementInstance(final String title) {
        return service.getRequirementInstance(title);
    }

    public IM2TrackingRequirementFactoryService getService() {
        return service;
    }

    public void setService(final IM2TrackingRequirementFactoryService service) {
        this.service = service;
    }
}
