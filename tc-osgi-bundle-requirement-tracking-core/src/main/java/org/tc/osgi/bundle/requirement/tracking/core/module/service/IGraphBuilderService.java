/**
 */
package org.tc.osgi.bundle.requirement.tracking.core.module.service;

import java.util.List;

import org.tc.osgi.bundle.requirement.tracking.core.graph.TrackingGraph;
import org.tc.osgi.bundle.requirement.tracking.core.graph.io.GenGraphRepport;
import org.tc.osgi.bundle.requirement.tracking.core.graph.m2.node.AbstractNode;
import org.tc.osgi.bundle.requirement.tracking.core.graph.m2.relation.AbstractRelationShip;
import org.tc.osgi.bundle.requirement.tracking.m2.model.AbstractRequirementElement;

/**
 * IGraphBuilderService.java.
 * @author collonville thomas
 * @version
 */
public interface IGraphBuilderService {

    public TrackingGraph buildTrackingGraph(final String graphName, final List<AbstractNode> nodes, final List<AbstractRelationShip> relations);

    public TrackingGraph buildTrackingGraph(final String graphName, final List<AbstractRequirementElement> reqElement);

    public GenGraphRepport getTrackingGraphxmlSerializer();

}
