/**
 */
package org.tc.osgi.bundle.requirement.tracking.repport.module.service;

import org.tc.osgi.bundle.requirement.tracking.core.graph.TrackingGraph;
import org.tc.osgi.bundle.requirement.tracking.repport.gen.RepportGenerator;

/**
 * IRequirementValidator.java.
 * @author collonville thomas
 * @version
 */
public interface IRequirementRepportService {

    /**
     * getRepportGenerator.
     * @param graph
     * @return
     */
    RepportGenerator getRepportGenerator(TrackingGraph graph);

}
