/**
 */
package org.tc.osgi.bundle.requirement.tracking.core.graph.io;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.tc.osgi.bundle.requirement.tracking.core.graph.TrackingGraph;
import org.tc.osgi.bundle.utils.io.StringBufferOutputStream;

/**
 * GenGraphRepport.java.
 * @author collonville thomas
 * @version
 */
public final class GenGraphRepport {

    public String generateXML(final TrackingGraph graph) throws JAXBException {
        final JAXBContext jc = JAXBContext.newInstance("org.tc.osgi.bundle.requirement.tracking.core.graph");
        final Marshaller marshaller = jc.createMarshaller();
        final StringBufferOutputStream sbos = new StringBufferOutputStream();
        marshaller.marshal(graph, sbos);
        return sbos.getValue().toString();

    }
}
