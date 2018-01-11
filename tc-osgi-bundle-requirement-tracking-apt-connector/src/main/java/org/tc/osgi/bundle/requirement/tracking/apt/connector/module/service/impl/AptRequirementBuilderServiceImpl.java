/**
 */
package org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.tc.osgi.bundle.apt.io.model.AptObject;
import org.tc.osgi.bundle.apt.io.utils.AptType;
import org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service.AptIoServiceProxy;
import org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service.IAptRequirementBuilderService;
import org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service.M2TrackingRequirementFactoryServiceProxy;
import org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service.UtilsServiceProxy;
import org.tc.osgi.bundle.requirement.tracking.apt.connector.tools.ReqBuilder;
import org.tc.osgi.bundle.requirement.tracking.m2.builder.AbstractRequirementBuilder;
import org.tc.osgi.bundle.requirement.tracking.m2.model.AbstractRequirementElement;
import org.tc.osgi.bundle.requirement.tracking.m2.model.Requirement;
import org.tc.osgi.bundle.requirement.tracking.m2.model.RequirementGroup;
import org.tc.osgi.bundle.requirement.tracking.m2.utils.RequirementSymbolsEnum;
import org.tc.osgi.bundle.utils.exception.FieldTrackingAssignementException;

/**
 * AptRequirementBuilder.java.
 * @author collonville thomas
 * @version 0.1.0
 */
public class AptRequirementBuilderServiceImpl extends AbstractRequirementBuilder implements IAptRequirementBuilderService {

    /**
     * convert2Requirement.
     * @param l
     * @return
     * @throws FieldTrackingAssignementException
     */
    private AbstractRequirementElement convert2Requirement(final List<AptObject> l) throws FieldTrackingAssignementException {
        UtilsServiceProxy.getInstance().getLogger(AptRequirementBuilderServiceImpl.class).info("Conversion en exigences");
        final List<AptObject> cleanList = AptIoServiceProxy.getInstance().cleanObject(l);
        Requirement req = M2TrackingRequirementFactoryServiceProxy.getInstance().getRequirementInstance("tmp");
        for (final AptObject aptO : cleanList) {
            final String element = trimSpace(aptO.getContent().get(0));
            if (aptO.getAptType().equals(AptType.ITEM)) {
                if (element.startsWith(RequirementSymbolsEnum.VERSION_TAG.toString())) {
                    req.setVersion(extractVersion(element));
                }
                if (element.startsWith(RequirementSymbolsEnum.TRACKING_TAG.toString())) {
                    req.setTracking(extractTracking(element));
                }
                if (element.startsWith(RequirementSymbolsEnum.DEPENDENCY_TAG.toString())) {
                    req.setDependency(extractDependency(element));
                }
            }
            if (aptO.getAptType().equals(AptType.PARAGRAPHE)) {
                if (element.startsWith(RequirementSymbolsEnum.DESCRIPTION_TAG.toString())) {
                    if (req.getDescription() != null) {
                        req.getDescription().addElementDescription(extractDescription(element).toString());
                    } else {
                        req.setDescription(extractDescription(element));
                    }
                } else {
                    if (req.getDescription() != null) {
                        req.getDescription().addElementDescription(element);
                    } else {
                        req.setDescription(M2TrackingRequirementFactoryServiceProxy.getInstance().getRequirementDescription((element)));
                    }
                }
            }
            if (aptO.getAptType().equals(AptType.SUBCHAPTER)) {
                if (element.startsWith(RequirementSymbolsEnum.REQUIREMENT_TAG.toString())) {
                    final Requirement newReq = extractRequirementName(element);
                    newReq.setVersion(req.getVersion());
                    newReq.setTracking(req.getTracking());
                    // ici on ne verifie pas que @description est utilisé, a
                    // priori n'est pas obligatoire pour les fichiers apt
                    newReq.setDependency(req.getDependency());
                    newReq.setDescription(req.getDescription());
                    req = newReq;
                }
            }
        }

        return req;
    }

    @Override
    public List<AbstractRequirementElement> extractRequirement(final File file, final List<String> content) throws IOException {
        UtilsServiceProxy.getInstance().getLogger(AptRequirementBuilderServiceImpl.class).info("Traitement du fichier apt " + file.getName());
        UtilsServiceProxy.getInstance().getLogger(AptRequirementBuilderServiceImpl.class).debug("Content:" + printContent(content));
        final List<AptObject> aptObjects = AptIoServiceProxy.getInstance().getAptObjectList(file.getAbsolutePath(), content);

        final ReqBuilder builder = new ReqBuilder();
        // Prepare le group
        final RequirementGroup group = builder.buildGroup(aptObjects, file);
        final List<List<AptObject>> aptSlipt = builder.prepareAptObjec(aptObjects);

        for (final List<AptObject> lapt : aptSlipt) {

            try {
                group.add(convert2Requirement(lapt));
            } catch (final FieldTrackingAssignementException e) {
                UtilsServiceProxy.getInstance().getLogger(AptRequirementBuilderServiceImpl.class).error(e);
            }

        }
        final List<AbstractRequirementElement> l = new ArrayList<AbstractRequirementElement>();
        l.add(group);

        return l;
    }

    private String printContent(final List<String> content) {
        final StringBuilder b = new StringBuilder();
        int l = 0;
        for (final String s : content) {
            b.append(String.valueOf(l++)).append(":").append(s).append("\n");
        }
        return b.toString();
    }

    /**
     * splitAndFilterRequirement.
     * @param aptObjects
     */

    protected String trimSpace(String value) {
        UtilsServiceProxy.getInstance().getLogger(AptRequirementBuilderServiceImpl.class).info("Suppression des blancs");
        while (value.startsWith(AbstractRequirementElement.BLANK_SPACE)) {
            value = value.substring(1, value.length());
        }
        return value;
    }

}
