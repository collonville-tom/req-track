/**
 */
package org.tc.osgi.bundle.requirement.tracking.core.graph.m2.node;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for DescriptionNode complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="DescriptionNode">
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
@XmlType(name = "DescriptionNode")
public class DescriptionNode extends AbstractNode {
    public DescriptionNode() {
        super();
    }

    /**
     * DescriptionNode constructor.
     * @param nodeName
     */
    public DescriptionNode(final String nodeName) {
        super(nodeName);
    }

}
