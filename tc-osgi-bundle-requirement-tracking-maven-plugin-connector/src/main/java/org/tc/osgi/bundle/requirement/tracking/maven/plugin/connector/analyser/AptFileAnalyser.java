/**
 */
package org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.analyser;

import java.io.File;

import org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service.IAptRequirementBuilderService;
import org.tc.osgi.bundle.requirement.tracking.m2.builder.AbstractRequirementBuilder;

/**
 * AptFileAnalyser.java.
 * @author collonville thomas
 * @version
 */
public class AptFileAnalyser extends AbstractFileAnalyser {
    private static final String APT_EXT = ".apt";

    /**
    aptFileAnalyser constructor.
     * @param requirementValidatorMojo
     */
    public AptFileAnalyser(final IAptRequirementBuilderService builder) {
        super((AbstractRequirementBuilder) builder);
    }

    @Override
    public boolean filterFile(final File file) {
        if (file.getName().endsWith(AptFileAnalyser.APT_EXT)) {
            return true;
        }
        return false;
    }
}
