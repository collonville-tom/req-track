package org.tc.osgi.bundle.requirement.tracking.apt.connector.conf;

import org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service.UtilsServiceProxy;
import org.tc.osgi.bundle.utils.conf.AbstractPropertyFile;
import org.tc.osgi.bundle.utils.exception.FieldTrackingAssignementException;

/**
 * AptGuiPropertyFile.java.
 * @author collonville thomas
 * @version 0.0.1
 */
public final class AptConnectorPropertyFile extends AbstractPropertyFile {
    /**
     * String APT_GUI_FILE.
     */
    public static final String APT_REQ_CONNECTOR_FILE = "apt-requirement-connector";

    public final static String BUNDLE_RACINE = "tc.osgi.bundle.requirement.tracking.apt.connector.";

    /**
     * DefaultConfig conf.
     */
    private static AptConnectorPropertyFile instance = null;

    /**
     * getInstance.
     * @return DefaultConfig
     * @throws EquinoxConfigException
     * @throws FieldTrackingAssignementException
     */
    public static AptConnectorPropertyFile getInstance() {
        if (AptConnectorPropertyFile.instance == null) {
            AptConnectorPropertyFile.instance = new AptConnectorPropertyFile();
        }
        return AptConnectorPropertyFile.instance;
    }

    private String listOfExcludeDir;

    /**
     * AptConfiguration constructor.
     */
    private AptConnectorPropertyFile() {
        super(AptConnectorPropertyFile.APT_REQ_CONNECTOR_FILE, AptConnectorPropertyFile.class.getClassLoader());
    }

    @Override
    public String getBundleRacine() {
        return AptConnectorPropertyFile.BUNDLE_RACINE;
    }

    @Override
    public String getConfFile() {
        return AptConnectorPropertyFile.APT_REQ_CONNECTOR_FILE;
    }

    public String getExcludeDir() throws FieldTrackingAssignementException {
        if (listOfExcludeDir == null) {
            UtilsServiceProxy.getInstance().getXMLPropertyFile(getXMLFile()).fieldTraking(this, "listOfExcludeDir");
        }
        return listOfExcludeDir;
    }

    @Override
    public String getXMLFile() {
        return AptConnectorPropertyFile.getInstance().getConfigDirectory() + getConfFile();
    }

}
