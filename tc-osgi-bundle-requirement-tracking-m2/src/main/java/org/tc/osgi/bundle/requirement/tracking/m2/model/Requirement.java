package org.tc.osgi.bundle.requirement.tracking.m2.model;

import org.tc.osgi.bundle.requirement.tracking.m2.model.elements.RequirementDependency;
import org.tc.osgi.bundle.requirement.tracking.m2.model.elements.RequirementDescription;
import org.tc.osgi.bundle.requirement.tracking.m2.model.elements.RequirementTracking;

/**
 * Requirement.java.
 * @author Collonville Thomas
 * @version 0.0.1
 */
public class Requirement extends AbstractRequirementElement {

    /**
     * RequirementDependency dependency.
     */
    private RequirementDependency dependency;

    /**
     * RequirementDescription description.
     */
    private RequirementDescription description;

    /**
     * RequirementTracking tracking.
     */
    private RequirementTracking tracking;

    /**
     * RequirementVersion version.
     */
    private RequirementVersion version;

    /**
     * Requirement constructor.
     * @param _title String
     */
    public Requirement(final String _title) {
        super(_title);
    }

    /**
     * getDependency.
     * @return RequirementDependency
     */
    public RequirementDependency getDependency() {
        return dependency;
    }

    /**
     * getDescription.
     * @return RequirementDescription
     */
    public RequirementDescription getDescription() {
        return description;
    }

    /**
     * getTracking.
     * @return RequirementTracking
     */
    public RequirementTracking getTracking() {
        return tracking;
    }

    /**
     * getVersion.
     * @return RequirementVersion
     */
    public RequirementVersion getVersion() {
        return version;
    }

    /**
     * setDependency.
     * @param dependency RequirementDependency
     */
    public void setDependency(final RequirementDependency dependency) {
        this.dependency = dependency;
    }

    /**
     * setDescription.
     * @param description RequirementDescription
     */
    public void setDescription(final RequirementDescription description) {
        this.description = description;
    }

    /**
     * setTracking.
     * @param tracking RequirementTracking
     */
    public void setTracking(final RequirementTracking tracking) {
        this.tracking = tracking;
    }

    /**
     * setVersion.
     * @param version RequirementVersion
     */
    public void setVersion(final RequirementVersion version) {
        this.version = version;
    }

    @Override
    public String toString() {
        final StringBuffer buff = new StringBuffer(super.toString());
        buff.append(description).append("|");
        buff.append(version).append("|");
        buff.append(tracking).append("|");
        buff.append(dependency).append("|");
        return buff.toString();

    }

}
