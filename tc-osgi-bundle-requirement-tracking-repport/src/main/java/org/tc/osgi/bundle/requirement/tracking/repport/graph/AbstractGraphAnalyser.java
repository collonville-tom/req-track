/**
 */
package org.tc.osgi.bundle.requirement.tracking.repport.graph;

import org.tc.osgi.bundle.requirement.tracking.core.analyser.AbstractTrackingGraphVisitor;
import org.tc.osgi.bundle.requirement.tracking.repport.exception.RequirementAnalyserException;
import org.tc.osgi.bundle.requirement.tracking.repport.gen.AbstractGenerator;

/**
 * NodeAnalyser.java.
 * @author collonville thomas
 * @version
 */
public abstract class AbstractGraphAnalyser extends AbstractTrackingGraphVisitor {

    public abstract AbstractGenerator getRepportGenerator() throws RequirementAnalyserException;

}
