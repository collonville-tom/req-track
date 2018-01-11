package org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.conf;

import org.tc.osgi.bundle.utils.conf.AbstractPropertyFile;
import org.tc.osgi.bundle.utils.exception.FieldTrackingAssignementException;

/**
 * AptConfiguration.java.
 * @author collonville thomas
 * @version 0.0.1
 */
public final class MavenPluginConnectorPropertyFile extends AbstractPropertyFile {

    public final static String BUNDLE_RACINE = "tc.osgi.bundle.requirement.tracking.maven.plugin.connector.";

    /**
     * DefaultConfig conf.
     */
    private static MavenPluginConnectorPropertyFile instance = null;

    /**
     * String EQUINOXLOADERFILE.
     */
    public static final String MAVEN_PLUGIN_CONF_FILE = "requirement-tracking-maven-plugin-connector";

    /**
     * getInstance.
     * @return DefaultConfig
     * @throws EquinoxConfigException
     * @throws FieldTrackingAssignementException
     */
    public static MavenPluginConnectorPropertyFile getInstance() {
        if (MavenPluginConnectorPropertyFile.instance == null) {
            MavenPluginConnectorPropertyFile.instance = new MavenPluginConnectorPropertyFile();
        }
        return MavenPluginConnectorPropertyFile.instance;
    }

    /**
     * AptConfiguration constructor.
     */
    private MavenPluginConnectorPropertyFile() {
        super(MavenPluginConnectorPropertyFile.MAVEN_PLUGIN_CONF_FILE, MavenPluginConnectorPropertyFile.class.getClassLoader());
    }

    @Override
    public String getBundleRacine() {
        return MavenPluginConnectorPropertyFile.BUNDLE_RACINE;
    }

    @Override
    public String getConfFile() {
        return MavenPluginConnectorPropertyFile.MAVEN_PLUGIN_CONF_FILE;
    }

    @Override
    public String getXMLFile() {
        return getConfigDirectory() + getConfFile();
    }

}
