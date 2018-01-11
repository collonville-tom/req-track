package org.tc.osgi.bundle.requirement.tracking.m2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * AbstractElement.java.
 * @author Collonville Thomas
 * @version 0.0.1
 */
public abstract class AbstractRequirementElement {

    public static final String BLANK_SPACE = " ";

    /**
     * String title.
     */
    private final List<String> elementDescription = new ArrayList<String>();

    protected AbstractRequirementElement() {

    }

    /**
     * AbstractReqElement constructor.
     * @param elementDescription String
     */
    public AbstractRequirementElement(final String elementDescription) {

        this.elementDescription.add(trimSpace(elementDescription));
    }

    /**
     * setTitle.
     * @param elementDescription String
     */
    public void addElementDescription(final String elementDescription) {
        this.elementDescription.add(trimSpace(elementDescription));
    }

    public List<String> getElementDescription() {
        return elementDescription;
    }

    /**
     * getTitle.
     * @return String
     */
    public String getFirstElementDescription() {
        return elementDescription.get(0);
    }

    @Override
    public String toString() {
        final StringBuffer buff = new StringBuffer();
        for (final String s : elementDescription) {
            buff.append(s).append("|");
        }
        return buff.toString();

    }

    protected String trimSpace(String value) {
        while (value.startsWith(AbstractRequirementElement.BLANK_SPACE)) {
            value = value.substring(1, value.length());
        }
        return value;
    }
}
