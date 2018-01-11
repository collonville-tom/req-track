package org.tc.osgi.bundle.requirement.tracking.repport.module.service;

import java.util.List;

import org.tc.osgi.bundle.requirement.tracking.core.graph.TrackingGraph;
import org.tc.osgi.bundle.requirement.tracking.core.graph.io.GenGraphRepport;
import org.tc.osgi.bundle.requirement.tracking.core.graph.m2.node.AbstractNode;
import org.tc.osgi.bundle.requirement.tracking.core.graph.m2.relation.AbstractRelationShip;
import org.tc.osgi.bundle.requirement.tracking.core.module.service.IGraphBuilderService;
import org.tc.osgi.bundle.requirement.tracking.m2.model.AbstractRequirementElement;

public class GraphBuilderServiceProxy implements IGraphBuilderService {

    private static GraphBuilderServiceProxy instance = null;

    public static GraphBuilderServiceProxy getInstance() {
        if (GraphBuilderServiceProxy.instance == null) {
            GraphBuilderServiceProxy.instance = new GraphBuilderServiceProxy();
        }
        return GraphBuilderServiceProxy.instance;
    }

    private IGraphBuilderService service = null;

    private GraphBuilderServiceProxy() {

    }

    /**
     * @param graphName
     * @param nodes
     * @param relations
     * @return
     * @see org.tc.osgi.bundle.requirement.tracking.core.module.service.IGraphBuilderService#buildTrackingGraph(java.lang.String, java.util.List, java.util.List)
     */
    @Override
    public TrackingGraph buildTrackingGraph(final String graphName, final List<AbstractNode> nodes, final List<AbstractRelationShip> relations) {
        return service.buildTrackingGraph(graphName, nodes, relations);
    }

    /**
     * @param graphName
     * @param reqElement
     * @return
     * @see org.tc.osgi.bundle.requirement.tracking.core.module.service.IGraphBuilderService#buildTrackingGraph(java.lang.String, java.util.List)
     */
    @Override
    public TrackingGraph buildTrackingGraph(final String graphName, final List<AbstractRequirementElement> reqElement) {
        return service.buildTrackingGraph(graphName, reqElement);
    }

    public IGraphBuilderService getService() {
        return service;
    }

    /**
     * @return
     * @see org.tc.osgi.bundle.requirement.tracking.core.module.service.IGraphBuilderService#getTrackingGraphxmlSerializer()
     */
    @Override
    public GenGraphRepport getTrackingGraphxmlSerializer() {
        return service.getTrackingGraphxmlSerializer();
    }

    public void setService(final IGraphBuilderService service) {
        this.service = service;
    }
}
