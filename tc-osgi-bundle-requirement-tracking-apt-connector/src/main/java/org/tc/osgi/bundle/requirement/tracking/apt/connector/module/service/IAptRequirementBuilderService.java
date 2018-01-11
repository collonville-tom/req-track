/**
 */
package org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.tc.osgi.bundle.requirement.tracking.m2.model.AbstractRequirementElement;

/**
 * IJavaRequirementBuilderService.java.
 * @author collonville thomas
 * @version
 */
public interface IAptRequirementBuilderService {

    public List<AbstractRequirementElement> extractRequirement(File file, List<String> content) throws IOException;
}
