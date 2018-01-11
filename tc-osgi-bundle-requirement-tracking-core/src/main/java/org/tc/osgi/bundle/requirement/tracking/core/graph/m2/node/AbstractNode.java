/**
 */
package org.tc.osgi.bundle.requirement.tracking.core.graph.m2.node;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import org.tc.osgi.bundle.requirement.tracking.core.analyser.AbstractRequirementAnalyser;
import org.tc.osgi.bundle.requirement.tracking.core.graph.m2.AbstractGraphElement;

/**
 * <p>Java class for AbstractNode complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AbstractNode">
 *   &lt;complexContent>
 *     &lt;extension base="{http://collonville.thomas.fr/tc-osgi-bundle-requirement-tracking-core/config-file-schema}AbstractGraphElement">
 *       &lt;sequence>
 *         &lt;element name="nodeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractNode", propOrder = { "nodeName" })
@XmlSeeAlso({ VersionNode.class, RequirementNode.class, DescriptionNode.class })
public abstract class AbstractNode extends AbstractGraphElement {
    @XmlElement(required = true)
    private String nodeName;

    public AbstractNode() {
    }

    public AbstractNode(final String nodeName) {
        this.nodeName = nodeName;
    }

    /**
     * @param visitor
     * @see org.tc.osgi.bundle.utils.pattern.visitor.IVisitable#accept(org.tc.osgi.bundle.utils.pattern.visitor.AbstractVisitor)
     */
    @Override
    public void accept(final AbstractRequirementAnalyser visitor) {
        visitor.visit(this);

    }

    /**
     * @param obj
     * @return
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final AbstractNode other = (AbstractNode) obj;
        if (nodeName == null) {
            if (other.nodeName != null) {
                return false;
            }
        } else
            if (!nodeName.equals(other.nodeName)) {
                return false;
            }
        return true;
    }

    /**
     * Gets the value of the nodeName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * @return
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((nodeName == null) ? 0 : nodeName.hashCode());
        return result;
    }

    /**
     * Sets the value of the nodeName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNodeName(final String value) {
        nodeName = value;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ":" + getNodeName();
    }

}
