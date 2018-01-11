package org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.module.service;

import org.tc.osgi.bundle.requirement.tracking.core.graph.TrackingGraph;
import org.tc.osgi.bundle.requirement.tracking.repport.gen.RepportGenerator;
import org.tc.osgi.bundle.requirement.tracking.repport.module.service.IRequirementRepportService;

public class RequirementRepportServiceProxy implements IRequirementRepportService {

    private static RequirementRepportServiceProxy instance = null;

    public static RequirementRepportServiceProxy getInstance() {
        if (RequirementRepportServiceProxy.instance == null) {
            RequirementRepportServiceProxy.instance = new RequirementRepportServiceProxy();
        }
        return RequirementRepportServiceProxy.instance;
    }

    private IRequirementRepportService service = null;

    private RequirementRepportServiceProxy() {

    }

    /**
     * @param graph
     * @return
     * @see org.tc.osgi.bundle.requirement.tracking.repport.module.service.IRequirementRepportService#getRepportGenerator(org.tc.osgi.bundle.requirement.tracking.core.graph.TrackingGraph)
     */
    @Override
    public RepportGenerator getRepportGenerator(final TrackingGraph graph) {
        return service.getRepportGenerator(graph);
    }

    public IRequirementRepportService getService() {
        return service;
    }

    public void setService(final IRequirementRepportService service) {
        this.service = service;
    }
}
