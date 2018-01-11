/**
 */
package org.tc.osgi.bundle.requirement.tracking.core.graph;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.tc.osgi.bundle.requirement.tracking.core.analyser.AbstractTrackingGraphVisitor;
import org.tc.osgi.bundle.requirement.tracking.core.graph.m2.node.AbstractNode;
import org.tc.osgi.bundle.requirement.tracking.core.graph.m2.node.DescriptionNode;
import org.tc.osgi.bundle.requirement.tracking.core.graph.m2.node.RequirementNode;
import org.tc.osgi.bundle.requirement.tracking.core.graph.m2.node.VersionNode;
import org.tc.osgi.bundle.requirement.tracking.core.graph.m2.relation.BasicRelation;
import org.tc.osgi.bundle.requirement.tracking.core.graph.m2.relation.DependencyRelation;
import org.tc.osgi.bundle.requirement.tracking.core.graph.m2.relation.TrackingRelation;
import org.tc.osgi.bundle.utils.pattern.visitor.IVisitable;

/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nodes" type="{http://collonville.thomas.fr/tc-osgi-bundle-requirement-tracking-core/config-file-schema}NodeSet"/>
 *         &lt;element name="relations" type="{http://collonville.thomas.fr/tc-osgi-bundle-requirement-tracking-core/config-file-schema}RelationSet"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "name", "nodes", "relations" })
@XmlRootElement(name = "TrackingGraph")
public class TrackingGraph implements IVisitable<AbstractTrackingGraphVisitor> {
    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private NodeSet nodes = new NodeSet();
    @XmlElement(required = true)
    private RelationSet relations = new RelationSet();

    public TrackingGraph() {

    }

    public TrackingGraph(final String name) {
        this.name = name;
    }

    /**
     * @param visitor
     * @see org.tc.osgi.bundle.utils.pattern.visitor.IVisitable#accept(org.tc.osgi.bundle.utils.pattern.visitor.AbstractVisitor)
     */
    @Override
    public void accept(final AbstractTrackingGraphVisitor visitor) {
        visitor.visit(this);
    }

    public boolean addBasicRelation(final String req, final String dependOn) {
        final AbstractNode reqO = nodes.get(req);
        final AbstractNode dependOnO = nodes.get(dependOn);
        if (reqO == null) {
            return false;
        }
        if (dependOnO == null) {
            return false;
        }

        final BasicRelation relation = new BasicRelation(reqO, dependOnO);
        return relations.add(relation);
    }

    public boolean addDependency(final String req, final String dependOn) {
        final AbstractNode reqO = nodes.get(req);
        final AbstractNode dependOnO = nodes.get(dependOn);
        if (reqO == null) {
            return false;
        }
        if (dependOnO == null) {
            return false;
        }

        final DependencyRelation relation = new DependencyRelation(reqO, dependOnO);
        return relations.add(relation);
    }

    public boolean addDescriptionNode(final String name) {
        return nodes.add(new DescriptionNode(name));
    }

    public boolean addRequirementNode(final String name) {
        return nodes.add(new RequirementNode(name));
    }

    public boolean addTracking(final String req, final String dependOn) {
        final AbstractNode reqO = nodes.get(req);
        final AbstractNode dependOnO = nodes.get(dependOn);
        if (reqO == null) {
            return false;
        }
        if (dependOnO == null) {
            return false;
        }

        final TrackingRelation relation = new TrackingRelation(reqO, dependOnO);
        return relations.add(relation);
    }

    public boolean addVersionNode(final String name) {
        return nodes.add(new VersionNode(name));
    }

    /**
     * Gets the value of the name property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the value of the nodes property.
     *
     * @return
     *     possible object is
     *     {@link NodeSet }
     *
     */
    public NodeSet getNodes() {
        return nodes;
    }

    /**
     * Gets the value of the relations property.
     *
     * @return
     *     possible object is
     *     {@link RelationSet }
     *
     */
    public RelationSet getRelations() {
        return relations;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setName(final String value) {
        name = value;
    }

    /**
     * Sets the value of the nodes property.
     *
     * @param value
     *     allowed object is
     *     {@link NodeSet }
     *
     */
    public void setNodes(final NodeSet value) {
        nodes = value;
    }

    /**
     * Sets the value of the relations property.
     *
     * @param value
     *     allowed object is
     *     {@link RelationSet }
     *
     */
    public void setRelations(final RelationSet value) {
        relations = value;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(getName());
        builder.append("\n");
        builder.append(getNodes().toString());
        builder.append("\n");
        builder.append(getRelations().toString());
        return builder.toString();
    }
}
