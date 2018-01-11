package org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.module.tracker;

import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service.IAptRequirementBuilderService;
import org.tc.osgi.bundle.requirement.tracking.apt.connector.module.tracker.AptIoServiceTracker;
import org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.conf.MavenPluginConnectorPropertyFile;
import org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.module.service.UtilsServiceProxy;
import org.tc.osgi.bundle.utils.exception.FieldTrackingAssignementException;

/**
 * UtilsServiceTracker.java.
 * @author Collonville Thomas
 * @version 0.0.1
 */
public class AptRequirementBuilderServiceTracker extends ServiceTracker {

    private final String aptConnectorDependencyBundleName = null;

    /**
     * AptServiceTracker constructor.
     * @param context BundleContext
     * @throws InvalidSyntaxException
     * @throws BundleException
     */
    public AptRequirementBuilderServiceTracker(final BundleContext context) throws InvalidSyntaxException, BundleException {
        super(context, IAptRequirementBuilderService.class.getName(), null);
        try {
            UtilsServiceProxy.getInstance().getBundleStarter().startBundle(this.context, getAptConnectorDependencyBundleName());

        } catch (BundleException | FieldTrackingAssignementException e) {
            UtilsServiceProxy.getInstance().getLogger(AptIoServiceTracker.class).error(e.getMessage(), e);
        }
    }

    /**
     * @param reference ServiceReference
     * @return Object
     * @see org.osgi.util.tracker.ServiceTracker#addingService(org.osgi.framework.ServiceReference)
     */
    @Override
    public Object addingService(final ServiceReference reference) {
        UtilsServiceProxy.getInstance().getLogger(AptRequirementBuilderServiceTracker.class).debug("Inside AptRequirementBuilderServiceTracker.addingService " + reference.getBundle());
        return super.addingService(reference);
    }

    private String getAptConnectorDependencyBundleName() throws FieldTrackingAssignementException {
        if (aptConnectorDependencyBundleName == null) {
            UtilsServiceProxy.getInstance().getXMLPropertyFile(MavenPluginConnectorPropertyFile.getInstance().getXMLFile()).fieldTraking(this, "aptConnectorDependencyBundleName");
        }
        UtilsServiceProxy.getInstance().getLogger(AptIoServiceTracker.class).debug("Lancement auto du bundle :" + aptConnectorDependencyBundleName);
        return aptConnectorDependencyBundleName;
    }

    public IAptRequirementBuilderService getAptRequirementBuilderService() {
        return (IAptRequirementBuilderService) super.getService();
    }

    /**
     * @param reference ServiceReference
     * @param service Object
     * @see org.osgi.util.tracker.ServiceTracker#removedService(org.osgi.framework.ServiceReference, java.lang.Object)
     */
    @Override
    public void removedService(final ServiceReference reference, final Object service) {
        UtilsServiceProxy.getInstance().getLogger(AptRequirementBuilderServiceTracker.class).debug("Inside AptRequirementBuilderServiceTracker.removedService " + reference.getBundle());
        super.removedService(reference, service);
    }
}
