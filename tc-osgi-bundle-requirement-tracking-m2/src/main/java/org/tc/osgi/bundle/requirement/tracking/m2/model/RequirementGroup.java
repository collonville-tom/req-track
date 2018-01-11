package org.tc.osgi.bundle.requirement.tracking.m2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * RequirementVersion.java.
 * @author Collonville Thomas
 * @version 0.0.1
 */
public class RequirementGroup extends AbstractRequirementElement {

    private List<AbstractRequirementElement> composit = new ArrayList<AbstractRequirementElement>();

    /**
     * RequirementVersion constructor.
     * @param elementDescription String
     */
    public RequirementGroup(final String elementDescription) {
        super(elementDescription);

    }

    public boolean add(final AbstractRequirementElement element) {
        return composit.add(element);
    }

    /**
     * Getter composit.
     * @return
     */
    public List<AbstractRequirementElement> getComposit() {
        return composit;
    }

    /**
     * Setter composit.
     * @param composit List<AbstractRequirementElement>
     */
    public void setComposit(final List<AbstractRequirementElement> composit) {
        this.composit = composit;
    }
}
