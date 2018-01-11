/**
 */
package org.tc.osgi.bundle.requirement.tracking.m2.module.service;

import org.tc.osgi.bundle.requirement.tracking.m2.model.Requirement;
import org.tc.osgi.bundle.requirement.tracking.m2.model.RequirementGroup;
import org.tc.osgi.bundle.requirement.tracking.m2.model.elements.RequirementDescription;

/**
 * M2TrackingRequirementFactoryServiceImpl.java.
 * @author collonville thomas
 * @version
 */
public interface IM2TrackingRequirementFactoryService {

    /**
     * getRequirementDescription.
     * @param string
     * @return
     */
    public RequirementDescription getRequirementDescription(String string);

    /**
     * getRequirementGroupInstance.
     * @param string
     * @return
     */
    public RequirementGroup getRequirementGroupInstance(String string);

    public Requirement getRequirementInstance(final String title);
}
