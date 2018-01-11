/**
 */
package org.tc.osgi.bundle.requirement.tracking.core.graph.m2.node;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for RequirementNode complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="RequirementNode">
 *   &lt;complexContent>
 *     &lt;extension base="{http://collonville.thomas.fr/tc-osgi-bundle-requirement-tracking-core/config-file-schema}AbstractNode">
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequirementNode")
public class RequirementNode extends AbstractNode implements Serializable {
    /**
     * long serialVersionUID.
     */
    private static final long serialVersionUID = 3450711518086177657L;

    public RequirementNode() {
        super();
    }

    /**
     * RequirementNode constructor.
     * @param requirementName
     */
    public RequirementNode(final String requirementName) {
        super(requirementName);
    }

}
