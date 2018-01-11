/**
 */
package org.tc.osgi.bundle.requirement.tracking.repport.module.service.impl;

import org.tc.osgi.bundle.requirement.tracking.core.graph.TrackingGraph;
import org.tc.osgi.bundle.requirement.tracking.repport.gen.RepportGenerator;
import org.tc.osgi.bundle.requirement.tracking.repport.module.service.IRequirementRepportService;
import org.tc.osgi.bundle.requirement.tracking.repport.module.service.UtilsServiceProxy;

/**
 * M2TrackingFactoryService.java.
 * @author collonville thomas
 * @version
 */
public class RequirementRepportServiceImpl implements IRequirementRepportService {

    /**
     * @param graph
     * @return
     * @see org.tc.osgi.bundle.requirement.tracking.repport.module.service.IRequirementRepportService#getRepportGenerator(org.tc.osgi.bundle.requirement.tracking.core.graph.TrackingGraph)
     */
    @Override
    public RepportGenerator getRepportGenerator(final TrackingGraph graph) {
        UtilsServiceProxy.getInstance().getLogger(RequirementRepportServiceImpl.class).debug("Construction du generateur de rapports");
        return new RepportGenerator(graph);
    }

}
