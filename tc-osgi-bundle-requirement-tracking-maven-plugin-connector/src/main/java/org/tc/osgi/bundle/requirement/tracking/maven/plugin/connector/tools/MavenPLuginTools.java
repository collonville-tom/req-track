/**
 */
package org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.tools;

import java.util.ArrayList;
import java.util.List;

import org.tc.osgi.bundle.requirement.tracking.m2.model.AbstractRequirementElement;

/**
 * MavenPLuginools.java.
 * @author collonville thomas
 * @version
 */
public final class MavenPLuginTools {

    public static List<String> cutContent(final String l) {
        final List<String> chaine = new ArrayList<String>();
        final String[] tmp = l.split("\\|");
        for (int i = 0; i < tmp.length; i++) {
            final String var = tmp[i].replace("|", "");
            chaine.add(var);
        }
        return chaine;
    }

    public static String printLoadedReqList(final List<AbstractRequirementElement> reqElement) {
        final StringBuilder builder = new StringBuilder();
        for (final AbstractRequirementElement req : reqElement) {
            builder.append(req.getFirstElementDescription()).append("|||");
        }
        return builder.toString();
    }

    private MavenPLuginTools() {

    }
}
