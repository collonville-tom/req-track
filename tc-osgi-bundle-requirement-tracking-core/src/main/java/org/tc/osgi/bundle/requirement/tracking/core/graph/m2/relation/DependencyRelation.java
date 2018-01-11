/**
 */
package org.tc.osgi.bundle.requirement.tracking.core.graph.m2.relation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.tc.osgi.bundle.requirement.tracking.core.graph.m2.node.AbstractNode;

/**
 * <p>Java class for DependencyRelation complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="DependencyRelation">
 *   &lt;complexContent>
 *     &lt;extension base="{http://collonville.thomas.fr/tc-osgi-bundle-requirement-tracking-core/config-file-schema}AbstractRelationShip">
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DependencyRelation")
public class DependencyRelation extends AbstractRelationShip {

    public DependencyRelation() {
        super();

    }

    /**
     * DependencyRelation constructor.
     * @param in
     * @param out
     */
    public DependencyRelation(final AbstractNode in, final AbstractNode out) {
        super(in, out);
    }

}
