/**
 */
package org.tc.osgi.bundle.requirement.tracking.core.graph;

import java.util.HashSet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.tc.osgi.bundle.requirement.tracking.core.graph.m2.node.AbstractNode;
import org.tc.osgi.bundle.utils.collection.Collections;
import org.tc.osgi.bundle.utils.collection.IPredicate;

/**
 * <p>Java class for NodeSet complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="NodeSet">
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
@XmlType(name = "NodeSet")
public class NodeSet extends HashSet<AbstractNode> {

    /**
     * long serialVersionUID.
     */
    private static final long serialVersionUID = 1871208988751697697L;

    @Override
    public boolean add(final AbstractNode node) {
        if (!this.contains(node.getNodeName())) {
            return super.add(node);
        }
        return false;
    }

    public boolean contains(final String name) {
        return Collections.getInstance().detect(this, new IPredicate<AbstractNode>() {

            @Override
            public boolean evaluate(final AbstractNode e) {
                if (e.getNodeName().equals(name)) {
                    return true;
                }
                return false;
            }
        });
    }

    public AbstractNode get(final String nodeName) {
        return Collections.getInstance().extract(this, new IPredicate<AbstractNode>() {

            @Override
            public boolean evaluate(final AbstractNode e) {
                if (e.getNodeName().equals(nodeName)) {
                    return true;
                }
                return false;
            }

        });

    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("Nodes:[");
        for (final AbstractNode node : this) {
            builder.append(node.toString()).append("\n");
        }
        builder.append("]");
        return builder.toString();
    }

}
