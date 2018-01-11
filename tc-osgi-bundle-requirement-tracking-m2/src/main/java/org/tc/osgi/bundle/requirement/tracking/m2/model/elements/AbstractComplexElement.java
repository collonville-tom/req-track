/**
 */
package org.tc.osgi.bundle.requirement.tracking.m2.model.elements;

import org.tc.osgi.bundle.requirement.tracking.m2.model.AbstractRequirementElement;

/**
 * AbstractComplexElement.java.
 * @author collonville thomas
 * @version
 */
public abstract class AbstractComplexElement extends AbstractRequirementElement {

    public static final String SEPARATOR = ",";

    /**
     * AbstractComplexElement constructor.
     * @param elementDescription
     */
    public AbstractComplexElement(final String elementDescription) {
        extractListOfelement(elementDescription);
    }

    private void extractListOfelement(final String elementDescription) {
        final String[] elements = elementDescription.split(AbstractComplexElement.SEPARATOR);

        for (final String element : elements) {
            addElementDescription(element);
        }

    }
}
