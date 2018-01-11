/**
 */
package org.tc.osgi.bundle.requirement.tracking.m2.module.service.impl;

import org.tc.osgi.bundle.requirement.tracking.m2.model.Requirement;
import org.tc.osgi.bundle.requirement.tracking.m2.model.RequirementGroup;
import org.tc.osgi.bundle.requirement.tracking.m2.model.elements.RequirementDescription;
import org.tc.osgi.bundle.requirement.tracking.m2.module.service.IM2TrackingRequirementFactoryService;

/**
 * M2TrackingFactoryService.java.
 * @author collonville thomas
 * @version
 */
public class M2TrackingRequirementFactoryServiceImpl implements IM2TrackingRequirementFactoryService {

    /**
     * @param string
     * @return
     * @see org.tc.osgi.bundle.requirement.tracking.m2.module.service.IM2TrackingRequirementFactoryService#getRequirementDescription(java.lang.String)
     */
    @Override
    public RequirementDescription getRequirementDescription(final String string) {
        return new RequirementDescription(string);
    }

    /**
     * @param string
     * @return
     * @see org.tc.osgi.bundle.requirement.tracking.m2.module.service.IM2TrackingRequirementFactoryService#getRequirementGroupInstance(java.lang.String)
     */
    @Override
    public RequirementGroup getRequirementGroupInstance(final String string) {
        return new RequirementGroup(string);
    }

    @Override
    public Requirement getRequirementInstance(final String title) {
        return new Requirement(title);

    }

}
