/**
 */
package org.tc.osgi.bundle.requirement.tracking.core.module.service.impl;

import java.util.List;

import org.tc.osgi.bundle.requirement.tracking.core.graph.TrackingGraph;
import org.tc.osgi.bundle.requirement.tracking.core.graph.io.GenGraphRepport;
import org.tc.osgi.bundle.requirement.tracking.core.graph.m2.node.AbstractNode;
import org.tc.osgi.bundle.requirement.tracking.core.graph.m2.relation.AbstractRelationShip;
import org.tc.osgi.bundle.requirement.tracking.core.module.service.IGraphBuilderService;
import org.tc.osgi.bundle.requirement.tracking.m2.model.AbstractRequirementElement;
import org.tc.osgi.bundle.requirement.tracking.m2.model.Requirement;
import org.tc.osgi.bundle.requirement.tracking.m2.model.RequirementGroup;
import org.tc.osgi.bundle.requirement.tracking.m2.model.elements.RequirementDependency;
import org.tc.osgi.bundle.requirement.tracking.m2.model.elements.RequirementTracking;

/**
 * GraphBuilder.java.
 * @author collonville thomas
 * @version
 */
public class GraphBuilderServiceImpl implements IGraphBuilderService {

    /**
     * buildNodeGraphStructure.
     * @param graph
     * @param reqElement
     * @return
     */
    private TrackingGraph buildNodeGraphStructure(final TrackingGraph graph, final List<AbstractRequirementElement> reqElement) {
        for (final AbstractRequirementElement element : reqElement) {
            if (element instanceof Requirement) {
                final Requirement req = (Requirement) element;
                graph.addRequirementNode(req.getFirstElementDescription());
                if (req.getVersion() != null) {
                    graph.addDescriptionNode(req.getDescription().toString());
                    graph.addBasicRelation(req.getFirstElementDescription(), req.getDescription().toString());
                    graph.addVersionNode(req.getVersion().getFirstElementDescription());
                    graph.addBasicRelation(req.getFirstElementDescription(), req.getVersion().getFirstElementDescription());
                }
            }
            if (element instanceof RequirementGroup) {
                final RequirementGroup reqGroup = (RequirementGroup) element;
                for (final AbstractRequirementElement req : reqGroup.getComposit()) {
                    if (req instanceof Requirement) {
                        final Requirement reqInGroup = (Requirement) req;
                        graph.addRequirementNode(reqInGroup.getFirstElementDescription());
                        if (reqInGroup.getVersion() != null) {
                            graph.addDescriptionNode(reqInGroup.getDescription().toString());
                            graph.addBasicRelation(reqInGroup.getFirstElementDescription(), reqInGroup.getDescription().toString());
                            graph.addVersionNode(reqInGroup.getVersion().getFirstElementDescription());
                            graph.addBasicRelation(reqInGroup.getFirstElementDescription(), reqInGroup.getVersion().getFirstElementDescription());
                        }
                    }
                }
            }
        }
        return graph;

    }

    /**
     * buildRelationGraphStructure.
     * @param graph
     * @param reqElement
     * @return
     */
    private TrackingGraph buildRelationGraphStructure(final TrackingGraph graph, final List<AbstractRequirementElement> reqElement) {
        for (final AbstractRequirementElement element : reqElement) {
            if (element instanceof Requirement) {
                final Requirement req = (Requirement) element;
                final RequirementDependency dep = req.getDependency();
                final RequirementTracking track = req.getTracking();
                if (dep != null) {
                    for (final String depString : dep.getElementDescription()) {
                        graph.addDependency(req.getFirstElementDescription(), depString);
                    }
                }
                if (track != null) {
                    for (final String trackString : track.getElementDescription()) {
                        graph.addTracking(req.getFirstElementDescription(), trackString);
                    }
                }

            }
            if (element instanceof RequirementGroup) {
                final RequirementGroup reqGroup = (RequirementGroup) element;
                for (final AbstractRequirementElement req : reqGroup.getComposit()) {
                    if (req instanceof Requirement) {
                        final Requirement reqInGroup = (Requirement) req;
                        final RequirementDependency dep = reqInGroup.getDependency();
                        final RequirementTracking track = reqInGroup.getTracking();
                        if (dep != null) {
                            for (final String depString : dep.getElementDescription()) {
                                graph.addDependency(reqInGroup.getFirstElementDescription(), depString);
                            }
                        }
                        if (track != null) {
                            for (final String trackString : track.getElementDescription()) {
                                graph.addTracking(reqInGroup.getFirstElementDescription(), trackString);
                            }
                        }
                    }

                }
            }
        }
        return graph;

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
        final TrackingGraph graph = new TrackingGraph(graphName);
        if (nodes != null) {
            for (final AbstractNode node : nodes) {
                graph.getNodes().add(node);
            }
        }

        if (relations != null) {
            for (final AbstractRelationShip relation : relations) {
                graph.getRelations().add(relation);
            }
        }

        return graph;
    }

    /**
     * buildTrackingGraph.
     * @return
     */
    @Override
    public TrackingGraph buildTrackingGraph(final String graphName, final List<AbstractRequirementElement> reqElement) {

        final TrackingGraph graph = new TrackingGraph(graphName);
        // Construction des nodes et des relation avec les versions

        buildNodeGraphStructure(graph, reqElement);

        // Construction des relation entre exigences
        buildRelationGraphStructure(graph, reqElement);

        return graph;
    }

    /**
     * @return
     * @see org.tc.osgi.bundle.requirement.tracking.core.module.service.IGraphBuilderService#getTrackingGraphxmlSerializer()
     */
    @Override
    public GenGraphRepport getTrackingGraphxmlSerializer() {
        return new GenGraphRepport();
    }

}
