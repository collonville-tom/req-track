/**
 */
package org.tc.osgi.bundle.requirement.tracking.repport.gen;

import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.tc.osgi.bundle.requirement.tracking.core.graph.TrackingGraph;
import org.tc.osgi.bundle.requirement.tracking.core.graph.m2.node.AbstractNode;
import org.tc.osgi.bundle.requirement.tracking.repport.module.service.GraphBuilderServiceProxy;

/**
 * HtmlRequirementGenerator.java.
 * @author collonville thomas
 * @version
 */
public class XmlVersionRepportGenerator extends AbstractGenerator {

    private final List<AbstractNode> anomalies;

    public XmlVersionRepportGenerator(final String title, final List<AbstractNode> anomalies) {
        super(title);
        this.anomalies = anomalies;
    }

    /**
     *
     * @throws FileNotFoundException
     * @see org.tc.osgi.bundle.requirement.tracking.repport.gen.AbstractGenerator#generateRepport()
     */
    @Override
    public String generateRepport() {
        final TrackingGraph graph = GraphBuilderServiceProxy.getInstance().buildTrackingGraph(getTitle(), anomalies, null);
        try {
            return GraphBuilderServiceProxy.getInstance().getTrackingGraphxmlSerializer().generateXML(graph);
        } catch (final JAXBException e) {
            return "Erreur de generation du graph XmlVersionRepportGenerator";
        }

    }
}
