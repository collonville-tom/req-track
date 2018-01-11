/**
 */
package org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.analyser;

import java.io.File;

import org.tc.osgi.bundle.requirement.tracking.m2.builder.AbstractRequirementBuilder;

/**
 * JavaFileAnalyser.java.
 * @author collonville thomas
 * @version
 */
public class JavaFileAnalyser extends AbstractFileAnalyser {
    private static final String JAVA_EXT = ".java";

    /**
     * JavaFileAnalyser constructor.
     * @param builder
     */
    public JavaFileAnalyser(final AbstractRequirementBuilder builder) {
        super(builder);
        // TODO Auto-generated constructor stub
    }

    /**
     *
     * @see org.tc.maven.plugin.requirement.validator.AbstractFileAnalyser#extractFile()
     */
    @Override
    public boolean filterFile(final File file) {
        if (file.getName().endsWith(JavaFileAnalyser.JAVA_EXT)) {
            return true;
        }
        return false;
    }
}
