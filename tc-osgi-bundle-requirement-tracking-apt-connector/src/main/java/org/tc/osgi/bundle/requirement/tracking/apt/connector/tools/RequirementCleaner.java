/**
 */
package org.tc.osgi.bundle.requirement.tracking.apt.connector.tools;

import java.util.Collection;

import org.tc.osgi.bundle.apt.io.model.AptObject;
import org.tc.osgi.bundle.apt.io.utils.AptType;
import org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service.UtilsServiceProxy;
import org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service.impl.AptRequirementBuilderServiceImpl;
import org.tc.osgi.bundle.utils.collection.ITransformer;

/**
 * RequirementCleaner.java.
 * @author collonville thomas
 * @version
 */
public class RequirementCleaner {

    public Collection<AptObject> cleanCHAPTER(final Collection<AptObject> aptObjects) {

        return UtilsServiceProxy.getInstance().getCollectionTool().collect(aptObjects, new ITransformer<AptObject>() {

            @Override
            public void evaluate(final Collection<AptObject> c, final AptObject e) {
                if (!e.getAptType().equals(AptType.CHAPTER)) {
                    UtilsServiceProxy.getInstance().getLogger(AptRequirementBuilderServiceImpl.class).debug("CHAPTER Preservation de " + e.toString());
                    c.add(e);
                }
            }
        });
    }

    public Collection<AptObject> cleanDELIMITER(final Collection<AptObject> aptObjects) {

        return UtilsServiceProxy.getInstance().getCollectionTool().collect(aptObjects, new ITransformer<AptObject>() {

            @Override
            public void evaluate(final Collection<AptObject> c, final AptObject e) {
                if (!e.getAptType().equals(AptType.DELIMITER)) {
                    UtilsServiceProxy.getInstance().getLogger(AptRequirementBuilderServiceImpl.class).debug("DELIMITER Preservation de " + e.toString());
                    c.add(e);
                }
            }
        });
    }

    public Collection<AptObject> cleanEMPTYLINE(final Collection<AptObject> aptObjects) {

        return UtilsServiceProxy.getInstance().getCollectionTool().collect(aptObjects, new ITransformer<AptObject>() {

            @Override
            public void evaluate(final Collection<AptObject> c, final AptObject e) {
                if (!e.getAptType().equals(AptType.EMPTYLINE)) {
                    UtilsServiceProxy.getInstance().getLogger(AptRequirementBuilderServiceImpl.class).debug("EMPTYLINE Preservation de " + e.toString());
                    c.add(e);
                }
            }
        });
    }

    public Collection<AptObject> cleanPICTURE(final Collection<AptObject> aptObjects) {

        return UtilsServiceProxy.getInstance().getCollectionTool().collect(aptObjects, new ITransformer<AptObject>() {

            @Override
            public void evaluate(final Collection<AptObject> c, final AptObject e) {
                if (!e.getAptType().equals(AptType.PICTURE)) {
                    UtilsServiceProxy.getInstance().getLogger(AptRequirementBuilderServiceImpl.class).debug("PICTURE Preservation de " + e.toString());
                    c.add(e);
                }
            }
        });
    }

    /**
     * cleanRequirement.
     * @param aptObjects
     * @return Collection<AptObject>
     */
    public Collection<AptObject> cleanRequirement(final Collection<AptObject> aptObjects) {

        Collection<AptObject> filtredAptObject = cleanUNKNOW(aptObjects);
        filtredAptObject = cleanEMPTYLINE(filtredAptObject);
        filtredAptObject = cleanCHAPTER(filtredAptObject);
        filtredAptObject = cleanPICTURE(filtredAptObject);
        return cleanDELIMITER(filtredAptObject);

    }

    public Collection<AptObject> cleanUNKNOW(final Collection<AptObject> aptObjects) {

        return UtilsServiceProxy.getInstance().getCollectionTool().collect(aptObjects, new ITransformer<AptObject>() {

            @Override
            public void evaluate(final Collection<AptObject> c, final AptObject e) {
                if (!e.getAptType().equals(AptType.UNKNOW)) {
                    UtilsServiceProxy.getInstance().getLogger(AptRequirementBuilderServiceImpl.class).debug("UNKNOW Preservation de " + e.toString());
                    c.add(e);
                }
            }
        });
    }

}
