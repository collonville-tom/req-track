/**
 */
package org.tc.osgi.bundle.requirement.tracking.core.graph.m2.relation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import org.tc.osgi.bundle.requirement.tracking.core.analyser.AbstractRequirementAnalyser;
import org.tc.osgi.bundle.requirement.tracking.core.graph.m2.AbstractGraphElement;
import org.tc.osgi.bundle.requirement.tracking.core.graph.m2.node.AbstractNode;

/**
 * <p>Java class for AbstractRelationShip complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AbstractRelationShip">
 *   &lt;complexContent>
 *     &lt;extension base="{http://collonville.thomas.fr/tc-osgi-bundle-requirement-tracking-core/config-file-schema}AbstractGraphElement">
 *       &lt;sequence>
 *         &lt;element name="in" type="{http://collonville.thomas.fr/tc-osgi-bundle-requirement-tracking-core/config-file-schema}AbstractNode"/>
 *         &lt;element name="out" type="{http://collonville.thomas.fr/tc-osgi-bundle-requirement-tracking-core/config-file-schema}AbstractNode"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractRelationShip", propOrder = { "in", "out" })
@XmlSeeAlso({ TrackingRelation.class, BasicRelation.class, DependencyRelation.class })
public abstract class AbstractRelationShip extends AbstractGraphElement {
    @XmlElement(required = true)
    private AbstractNode in;

    @XmlElement(required = true)
    private AbstractNode out;

    public AbstractRelationShip() {
        super();
    }

    public AbstractRelationShip(final AbstractNode in, final AbstractNode out) {
        super();
        this.in = in;
        this.out = out;
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
        final AbstractRelationShip other = (AbstractRelationShip) obj;

        if (in == null) {
            if (other.in != null) {
                return false;
            }
        } else
            if (!in.equals(other.in)) {
                return false;
            }
        if (out == null) {
            if (other.out != null) {
                return false;
            }
        } else
            if (!out.equals(other.out)) {
                return false;
            }
        return true;
    }

    /**
     * Gets the value of the in property.
     *
     * @return
     *     possible object is
     *     {@link AbstractNode }
     *
     */
    public AbstractNode getIn() {
        return in;
    }

    /**
     * Gets the value of the out property.
     *
     * @return
     *     possible object is
     *     {@link AbstractNode }
     *
     */
    public AbstractNode getOut() {
        return out;
    }

    /**
     * @return
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((in == null) ? 0 : in.hashCode());
        result = (prime * result) + ((out == null) ? 0 : out.hashCode());
        return result;
    }

    /**
     * Sets the value of the in property.
     *
     * @param value
     *     allowed object is
     *     {@link AbstractNode }
     *
     */
    public void setIn(final AbstractNode value) {
        in = value;
    }

    /**
     * Sets the value of the out property.
     *
     * @param value
     *     allowed object is
     *     {@link AbstractNode }
     *
     */
    public void setOut(final AbstractNode value) {
        out = value;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(in);
        builder.append("-");
        builder.append(this.getClass().getSimpleName());
        builder.append("->");
        builder.append(out);
        return builder.toString();
    }
}
