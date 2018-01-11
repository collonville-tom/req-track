/**
 */
package org.tc.osgi.bundle.requirement.tracking.core.graph;

import java.util.Collection;
import java.util.HashSet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.tc.osgi.bundle.requirement.tracking.core.graph.m2.node.AbstractNode;
import org.tc.osgi.bundle.requirement.tracking.core.graph.m2.relation.AbstractRelationShip;
import org.tc.osgi.bundle.requirement.tracking.core.graph.m2.relation.BasicRelation;
import org.tc.osgi.bundle.requirement.tracking.core.graph.m2.relation.DependencyRelation;
import org.tc.osgi.bundle.requirement.tracking.core.graph.m2.relation.TrackingRelation;
import org.tc.osgi.bundle.utils.collection.Collections;
import org.tc.osgi.bundle.utils.collection.IPredicate;

/**
 * <p>Java class for RelationSet complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="RelationSet">
 *   &lt;complexContent>
 *     &lt;extension base="{http://collonville.thomas.fr/tc-osgi-bundle-requirement-tracking-core/config-file-schema}HashSet">
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RelationSet")
public class RelationSet extends HashSet<AbstractRelationShip> {

    /**
     * long serialVersionUID.
     */
    private static final long serialVersionUID = -2097529066011597896L;

    @Override
    public boolean add(final AbstractRelationShip relation) {
        if (!this.contains(relation)) {
            return super.add(relation);
        }
        return false;
    }

    public Collection<AbstractRelationShip> collectRelationContains(final AbstractNode node) {
        return Collections.getInstance().select(this, new IPredicate<AbstractRelationShip>() {

            @Override
            public boolean evaluate(final AbstractRelationShip e) {
                if (e.getIn().equals(node)) {
                    return true;
                }
                if (e.getOut().equals(node)) {
                    return true;
                }
                return false;
            }

        });
    }

    public boolean contains(final AbstractRelationShip relation) {
        return Collections.getInstance().detect(this, new IPredicate<AbstractRelationShip>() {

            @Override
            public boolean evaluate(final AbstractRelationShip e) {
                if (e.equals(relation)) {
                    return true;
                }
                return false;
            }
        });
    }

    public Collection<AbstractRelationShip> getBasicRelation() {
        return Collections.getInstance().select(this, new IPredicate<AbstractRelationShip>() {

            @Override
            public boolean evaluate(final AbstractRelationShip e) {
                if (e instanceof BasicRelation) {
                    return true;
                }
                return false;
            }
        });
    }

    public Collection<AbstractRelationShip> getDependencies() {
        return Collections.getInstance().select(this, new IPredicate<AbstractRelationShip>() {

            @Override
            public boolean evaluate(final AbstractRelationShip e) {
                if (e instanceof DependencyRelation) {
                    return true;
                }
                return false;
            }
        });
    }

    public Collection<AbstractRelationShip> getTrackingRelation() {
        return Collections.getInstance().select(this, new IPredicate<AbstractRelationShip>() {

            @Override
            public boolean evaluate(final AbstractRelationShip e) {
                if (e instanceof TrackingRelation) {
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("Relations:[");
        for (final AbstractRelationShip rel : this) {
            builder.append(rel.toString()).append("\n");
        }
        builder.append("]");
        return builder.toString();
    }
}
