package org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.module.tracker;

import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.tc.osgi.bundle.requirement.tracking.core.module.service.IGraphBuilderService;
import org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.conf.MavenPluginConnectorPropertyFile;
import org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.module.service.UtilsServiceProxy;
import org.tc.osgi.bundle.utils.exception.FieldTrackingAssignementException;

/**
 * UtilsServiceTracker.java.
 * @author Collonville Thomas
 * @version 0.0.1
 */
public class GraphBuilderServiceTracker extends ServiceTracker {

    private final String coreTraqReqDependencyBundleName = null;

    /**
     * AptServiceTracker constructor.
     * @param context BundleContext
     * @throws InvalidSyntaxException
     * @throws BundleException
     */
    public GraphBuilderServiceTracker(final BundleContext context) throws InvalidSyntaxException, BundleException {
        super(context, IGraphBuilderService.class.getName(), null);
        try {
            UtilsServiceProxy.getInstance().getBundleStarter().startBundle(this.context, getCoreReqTrackDependencyBundleName());

        } catch (BundleException | FieldTrackingAssignementException e) {
            UtilsServiceProxy.getInstance().getLogger(GraphBuilderServiceTracker.class).error(e.getMessage(), e);
        }
    }

    /**
     * @param reference ServiceReference
     * @return Object
     * @see org.osgi.util.tracker.ServiceTracker#addingService(org.osgi.framework.ServiceReference)
     */
    @Override
    public Object addingService(final ServiceReference reference) {
        UtilsServiceProxy.getInstance().getLogger(GraphBuilderServiceTracker.class).debug("Inside GraphBuilderServiceTracker.addingService " + reference.getBundle());
        return super.addingService(reference);
    }

    private String getCoreReqTrackDependencyBundleName() throws FieldTrackingAssignementException {
        if (coreTraqReqDependencyBundleName == null) {
            UtilsServiceProxy.getInstance().getXMLPropertyFile(MavenPluginConnectorPropertyFile.getInstance().getXMLFile()).fieldTraking(this, "coreTraqReqDependencyBundleName");
        }
        UtilsServiceProxy.getInstance().getLogger(GraphBuilderServiceTracker.class).debug("Lancement auto du bundle :" + coreTraqReqDependencyBundleName);
        return coreTraqReqDependencyBundleName;
    }

    public IGraphBuilderService getGraphBuilderService() {
        return (IGraphBuilderService) super.getService();
    }

    /**
     * @param reference ServiceReference
     * @param service Object
     * @see org.osgi.util.tracker.ServiceTracker#removedService(org.osgi.framework.ServiceReference, java.lang.Object)
     */
    @Override
    public void removedService(final ServiceReference reference, final Object service) {
        UtilsServiceProxy.getInstance().getLogger(GraphBuilderServiceTracker.class).debug("Inside GraphBuilderServiceTracker.removedService " + reference.getBundle());
        super.removedService(reference, service);
    }
}
