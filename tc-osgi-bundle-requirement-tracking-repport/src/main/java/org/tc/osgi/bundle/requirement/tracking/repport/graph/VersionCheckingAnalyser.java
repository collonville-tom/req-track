/**
 */
package org.tc.osgi.bundle.requirement.tracking.repport.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.tc.osgi.bundle.requirement.tracking.core.graph.TrackingGraph;
import org.tc.osgi.bundle.requirement.tracking.core.graph.m2.node.AbstractNode;
import org.tc.osgi.bundle.requirement.tracking.core.graph.m2.node.RequirementNode;
import org.tc.osgi.bundle.requirement.tracking.core.graph.m2.node.VersionNode;
import org.tc.osgi.bundle.requirement.tracking.core.graph.m2.relation.AbstractRelationShip;
import org.tc.osgi.bundle.requirement.tracking.repport.gen.AbstractGenerator;
import org.tc.osgi.bundle.requirement.tracking.repport.gen.XmlVersionRepportGenerator;

/**
 * VersionCheckingAnalyser.java.
 * @author collonville thomas
 * @version
 */
public class VersionCheckingAnalyser extends AbstractGraphAnalyser {

    private List<AbstractNode> anomalieList = null;
    private TrackingGraph graph = null;

    @Override
    public AbstractGenerator getRepportGenerator() {
        final XmlVersionRepportGenerator generator = new XmlVersionRepportGenerator("VersionCheckingAnalyser :" + graph.getName(), anomalieList);
        return generator;
    }

    /**
     * nodeHasVersion.
     * @param collectRelationContains
     * @return
     */
    private boolean nodeHasVersion(final RequirementNode node, final Collection<AbstractRelationShip> collectRelationContains) {
        for (final AbstractRelationShip relation : collectRelationContains) {
            if (relation.getIn().equals(node)) {
                if (relation.getOut() instanceof VersionNode) {
                    return true;
                }
            }
            if (relation.getOut().equals(node)) {
                if (relation.getIn() instanceof VersionNode) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return
     * @see org.tc.osgi.bundle.utils.pattern.visitor.AbstractVisitor#toString()
     */
    @Override
    public String toString() {
        return "VersionCheckingAnalyser";
    }

    /**
     * @param o
     * @see org.tc.osgi.bundle.utils.pattern.visitor.AbstractVisitor#visit(org.tc.osgi.bundle.utils.pattern.visitor.IVisitable)
     */
    @Override
    public void visit(final TrackingGraph o) {
        graph = o;
        anomalieList = new ArrayList<AbstractNode>();
        for (final AbstractNode node : graph.getNodes()) {
            if (node instanceof RequirementNode) {

                if (!nodeHasVersion((RequirementNode) node, graph.getRelations().collectRelationContains(node))) {
                    anomalieList.add(node);
                }

            }

        }
    }
}
