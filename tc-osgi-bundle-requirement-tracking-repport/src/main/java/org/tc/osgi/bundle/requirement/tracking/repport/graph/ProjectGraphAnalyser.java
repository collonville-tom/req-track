/**
 */
package org.tc.osgi.bundle.requirement.tracking.repport.graph;

import org.tc.osgi.bundle.requirement.tracking.core.graph.TrackingGraph;
import org.tc.osgi.bundle.requirement.tracking.repport.exception.RequirementAnalyserException;
import org.tc.osgi.bundle.requirement.tracking.repport.gen.AbstractGenerator;
import org.tc.osgi.bundle.requirement.tracking.repport.gen.XmlGraphRepportGenerator;

/**
 * ProjectGraphAnalyser.java.
 * @author collonville thomas
 * @version
 */
public class ProjectGraphAnalyser extends AbstractGraphAnalyser {

    private TrackingGraph graph = null;

    @Override
    public AbstractGenerator getRepportGenerator() throws RequirementAnalyserException {
        if (graph == null) {
            throw new RequirementAnalyserException("ProjectGraphAnalyser not initialize");
        }
        final XmlGraphRepportGenerator generator = new XmlGraphRepportGenerator("ProjectGraphAnalyser :" + graph.getName());
        return generator;
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
    }
}
