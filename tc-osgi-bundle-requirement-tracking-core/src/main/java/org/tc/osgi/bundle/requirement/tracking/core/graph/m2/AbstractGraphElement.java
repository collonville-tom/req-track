/**
 */
package org.tc.osgi.bundle.requirement.tracking.core.graph.m2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import org.tc.osgi.bundle.requirement.tracking.core.analyser.AbstractRequirementAnalyser;
import org.tc.osgi.bundle.requirement.tracking.core.graph.m2.node.AbstractNode;
import org.tc.osgi.bundle.requirement.tracking.core.graph.m2.relation.AbstractRelationShip;
import org.tc.osgi.bundle.utils.pattern.visitor.IVisitable;

/**
 * <p>Java class for AbstractGraphElement complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AbstractGraphElement">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractGraphElement")
@XmlSeeAlso({ AbstractNode.class, AbstractRelationShip.class })
public abstract class AbstractGraphElement implements IVisitable<AbstractRequirementAnalyser> {

}
