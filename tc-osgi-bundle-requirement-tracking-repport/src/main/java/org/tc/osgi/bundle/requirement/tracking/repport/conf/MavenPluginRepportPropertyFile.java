package org.tc.osgi.bundle.requirement.tracking.repport.conf;

import org.tc.osgi.bundle.utils.conf.AbstractPropertyFile;
import org.tc.osgi.bundle.utils.exception.FieldTrackingAssignementException;

/**
 * AptConfiguration.java.
 * @author collonville thomas
 * @version 0.0.1
 */
public final class MavenPluginRepportPropertyFile extends AbstractPropertyFile {

    public final static String BUNDLE_RACINE = "tc.osgi.bundle.requirement.tracking.repport.";

    /**
     * DefaultConfig conf.
     */
    private static MavenPluginRepportPropertyFile instance = null;

    /**
     * String EQUINOXLOADERFILE.
     */
    public static final String MAVEN_PLUGIN_CONF_FILE = "tc-osgi-bundle-requirement-tracking-repport";

    /**
     * getInstance.
     * @return DefaultConfig
     * @throws EquinoxConfigException
     * @throws FieldTrackingAssignementException
     */
    public static MavenPluginRepportPropertyFile getInstance() {
        if (MavenPluginRepportPropertyFile.instance == null) {
            MavenPluginRepportPropertyFile.instance = new MavenPluginRepportPropertyFile();
        }
        return MavenPluginRepportPropertyFile.instance;
    }

    /**
     * AptConfiguration constructor.
     */
    private MavenPluginRepportPropertyFile() {
        super(MavenPluginRepportPropertyFile.MAVEN_PLUGIN_CONF_FILE, MavenPluginRepportPropertyFile.class.getClassLoader());
    }

    @Override
    public String getBundleRacine() {
        return MavenPluginRepportPropertyFile.BUNDLE_RACINE;
    }

    @Override
    public String getConfFile() {
        return MavenPluginRepportPropertyFile.MAVEN_PLUGIN_CONF_FILE;
    }

    @Override
    public String getXMLFile() {
        return getConfigDirectory() + getConfFile();
    }

}
