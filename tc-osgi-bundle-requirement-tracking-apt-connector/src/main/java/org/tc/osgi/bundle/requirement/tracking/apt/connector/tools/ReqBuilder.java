/**
 */
package org.tc.osgi.bundle.requirement.tracking.apt.connector.tools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.tc.osgi.bundle.apt.io.model.AptObject;
import org.tc.osgi.bundle.apt.io.utils.AptType;
import org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service.M2TrackingRequirementFactoryServiceProxy;
import org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service.UtilsServiceProxy;
import org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service.impl.AptRequirementBuilderServiceImpl;
import org.tc.osgi.bundle.requirement.tracking.m2.model.RequirementGroup;

/**
 * ReqBuilder.java.
 * @author collonville thomas
 * @version
 */
public class ReqBuilder {

    public RequirementGroup buildGroup(final List<AptObject> aptObjects, final File file) throws IOException {
        RequirementGroup group;
        if (aptObjects.get(0).getAptType().equals(AptType.FILE)) {
            UtilsServiceProxy.getInstance().getLogger(AptRequirementBuilderServiceImpl.class).debug("aptObjects:" + aptObjects.get(0));
            group = M2TrackingRequirementFactoryServiceProxy.getInstance().getRequirementGroupInstance(file.getName());
            aptObjects.remove(0);
            if (aptObjects.size() > 0) {
                if (aptObjects.get(0).getAptType().equals(AptType.TITLE)) {
                    group = M2TrackingRequirementFactoryServiceProxy.getInstance().getRequirementGroupInstance(aptObjects.get(0).toString());
                    aptObjects.remove(0);
                } else {
                    throw new IOException("Apt File must beginning with TITLE type");
                }
            } else {
                UtilsServiceProxy.getInstance().getLogger(AptRequirementBuilderServiceImpl.class).error("Apt File must contain TITLE type, continue");
            }
        } else {
            throw new IOException("Apt File must beginning with FILE type");
        }
        return group;
    }

    public List<List<AptObject>> prepareAptObjec(final List<AptObject> aptObjects) {
        UtilsServiceProxy.getInstance().getLogger(AptRequirementBuilderServiceImpl.class).info("Nettoyage du fichier apt");
        final Collection<AptObject> cleanAptObject = new RequirementCleaner().cleanRequirement(aptObjects);
        return splitAndFilterRequirement(cleanAptObject);
    }

    private List<List<AptObject>> splitAndFilterRequirement(final Collection<AptObject> aptObjects) {
        final List<List<AptObject>> filtredAptObject = new ArrayList<List<AptObject>>();
        for (final AptObject o : aptObjects) {
            if (o.getAptType().equals(AptType.SUBCHAPTER)) {
                final List<AptObject> req = new ArrayList<AptObject>();
                req.add(o);
                filtredAptObject.add(req);
            }
        }

        for (final List<AptObject> l : filtredAptObject) {
            final AptObject req = l.get(0);
            boolean isSelectable = false;
            for (final AptObject o : aptObjects) {
                if (o.equals(req)) {
                    isSelectable = true;
                } else {
                    if (o.getAptType().equals(AptType.SUBCHAPTER)) {
                        isSelectable = false;
                    } else {
                        if (isSelectable) {
                            l.add(o);
                        }
                    }
                }
            }

        }

        return filtredAptObject;
    }

}
