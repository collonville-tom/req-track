/**
 */
package org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.analyser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.tc.osgi.bundle.requirement.tracking.m2.builder.AbstractRequirementBuilder;
import org.tc.osgi.bundle.requirement.tracking.m2.model.AbstractRequirementElement;

/**
 * AbstractFileAnalyser.java.
 * @author collonville thomas
 * @version
 */
public abstract class AbstractFileAnalyser {

    private AbstractRequirementBuilder builder;

    public AbstractFileAnalyser(final AbstractRequirementBuilder builder) {
        this.builder = builder;
    }

    public List<AbstractRequirementElement> extractRequirement(final File f, final List<String> content) throws IOException {
        final List<AbstractRequirementElement> reqs = new ArrayList<AbstractRequirementElement>();
        reqs.addAll(builder.extractRequirement(f, content));
        return reqs;
    }

    /**
     * insertFile.
     * @param f File
     * @return boolean
     */
    public abstract boolean filterFile(File f);

    /**
     * Getter builder.
     * @return
     */
    public AbstractRequirementBuilder getBuilder() {
        return builder;
    }

    /**
     * Setter builder.
     * @param builder AbstractRequirementBuilder
     */
    public void setBuilder(final AbstractRequirementBuilder builder) {
        this.builder = builder;
    }

}
