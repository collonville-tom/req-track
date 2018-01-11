/**
 */
package org.tc.osgi.bundle.requirement.tracking.java.connector.module.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.tc.osgi.bundle.requirement.tracking.java.connector.module.service.IJavaRequirementBuilderService;
import org.tc.osgi.bundle.requirement.tracking.m2.builder.AbstractRequirementBuilder;
import org.tc.osgi.bundle.requirement.tracking.m2.model.AbstractRequirementElement;
import org.tc.osgi.bundle.requirement.tracking.m2.model.Requirement;
import org.tc.osgi.bundle.requirement.tracking.m2.module.service.IM2TrackingRequirementFactoryService;
import org.tc.osgi.bundle.requirement.tracking.m2.utils.RequirementSymbolsEnum;
import org.tc.osgi.bundle.utils.module.service.IUtilsService;

/**
 * JavaRequirementBuilder.java.
 * @author collonville thomas
 * @version
 */
public class JavaRequirementBuilderServiceImpl extends AbstractRequirementBuilder implements IJavaRequirementBuilderService {

    private IM2TrackingRequirementFactoryService m2FactoryService = null;
    private IUtilsService utilsService = null;

    @Override
    public List<AbstractRequirementElement> extractRequirement(final File file, final List<String> content) throws IOException {
        Requirement req = this.m2FactoryService.getRequirementInstance(file.getAbsolutePath());
        for (final String element : content) {
            if (element.startsWith(RequirementSymbolsEnum.VERSION_TAG.toString())) {
                req.setVersion(this.extractVersion(element));
            }
            if (element.startsWith(RequirementSymbolsEnum.TRACKING_TAG.toString())) {
                req.setTracking(this.extractTracking(element));
            }
            if (element.startsWith(RequirementSymbolsEnum.DEPENDENCY_TAG.toString())) {
                req.setDependency(this.extractDependency(element));
            }
            if (element.startsWith(RequirementSymbolsEnum.DESCRIPTION_TAG.toString())) {
                if (req.getDescription() != null) {
                    req.getDescription().addElementDescription(this.extractDescription(element).toString());
                } else {
                    req.setDescription(this.extractDescription(element));
                }
            }

            if (element.startsWith(RequirementSymbolsEnum.REQUIREMENT_TAG.toString())) {
                final Requirement newReq = this.extractRequirementName(element);
                newReq.setVersion(req.getVersion());
                newReq.setTracking(req.getTracking());
                newReq.setDependency(req.getDependency());
                newReq.setDescription(req.getDescription());
                if (req.getDescription() != null) {
                    newReq.getDescription().addElementDescription(file.getAbsolutePath());
                } else {
                    newReq.setDescription(this.m2FactoryService.getRequirementDescription(file.getAbsolutePath()));
                }
                req = newReq;
            }
        }
        final List<AbstractRequirementElement> l = new ArrayList<AbstractRequirementElement>();
        l.add(req);
        return l;
    }

    /**
     * Getter m2FactoryService.
     * @return
     */
    public IM2TrackingRequirementFactoryService getM2FactoryService() {
        return this.m2FactoryService;
    }

    /**
     * Getter utilsService.
     * @return
     */
    public IUtilsService getUtilsService() {
        return this.utilsService;
    }

    /**
     * Setter m2FactoryService.
     * @param m2FactoryService IM2TrackingRequirementFactoryService
     */
    public void setM2FactoryService(final IM2TrackingRequirementFactoryService m2FactoryService) {
        this.m2FactoryService = m2FactoryService;
    }

    /**
     * Setter utilsService.
     * @param utilsService IUtilsService
     */
    public void setUtilsService(final IUtilsService utilsService) {
        this.utilsService = utilsService;
    }

}
