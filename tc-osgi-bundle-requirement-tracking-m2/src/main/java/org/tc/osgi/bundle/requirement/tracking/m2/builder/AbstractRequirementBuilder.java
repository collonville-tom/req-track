/**
 */
package org.tc.osgi.bundle.requirement.tracking.m2.builder;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.tc.osgi.bundle.requirement.tracking.m2.model.AbstractRequirementElement;
import org.tc.osgi.bundle.requirement.tracking.m2.model.Requirement;
import org.tc.osgi.bundle.requirement.tracking.m2.model.RequirementVersion;
import org.tc.osgi.bundle.requirement.tracking.m2.model.elements.RequirementDependency;
import org.tc.osgi.bundle.requirement.tracking.m2.model.elements.RequirementDescription;
import org.tc.osgi.bundle.requirement.tracking.m2.model.elements.RequirementTracking;
import org.tc.osgi.bundle.requirement.tracking.m2.utils.RequirementSymbolsEnum;

/**
 * AbstractRequirementBuilder.java.
 * @author collonville thomas
 * @version
 */
public abstract class AbstractRequirementBuilder {

    protected String delSymbol(final String value, final RequirementSymbolsEnum symbol) {

        if (value.split(symbol.toString()).length > 0) {
            final int index = value.indexOf(symbol.toString());
            return value.substring(index + symbol.toString().length());
        }
        return value;
    }

    public RequirementDependency extractDependency(final String value) {

        return new RequirementDependency(delSymbol(value, RequirementSymbolsEnum.DEPENDENCY_TAG));
    }

    public RequirementDescription extractDescription(final String value) {

        return new RequirementDescription(delSymbol(value, RequirementSymbolsEnum.DESCRIPTION_TAG));
    }

    public abstract List<AbstractRequirementElement> extractRequirement(File file, List<String> content) throws IOException;

    public Requirement extractRequirementName(final String value) {

        return new Requirement(delSymbol(value, RequirementSymbolsEnum.REQUIREMENT_TAG));
    }

    public RequirementTracking extractTracking(final String value) {

        return new RequirementTracking(delSymbol(value, RequirementSymbolsEnum.TRACKING_TAG));
    }

    public RequirementVersion extractVersion(final String value) {

        return new RequirementVersion(delSymbol(value, RequirementSymbolsEnum.VERSION_TAG));
    }
}
