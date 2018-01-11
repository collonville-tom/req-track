/**
 */
package org.tc.osgi.bundle.requirement.tracking.repport.gen;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.tc.osgi.bundle.requirement.tracking.core.graph.TrackingGraph;
import org.tc.osgi.bundle.requirement.tracking.repport.exception.RequirementAnalyserException;
import org.tc.osgi.bundle.requirement.tracking.repport.graph.ProjectGraphAnalyser;
import org.tc.osgi.bundle.requirement.tracking.repport.graph.VersionCheckingAnalyser;
import org.tc.osgi.bundle.utils.collection.element.Pair;

/**
 * RepportGenerator.java.
 * @author collonville thomas
 * @version
 */
public class RepportGenerator {

    private final static String SITE_REPPORT_LOCATION = "src/site/apt/xml/";

    private final TrackingGraph graph;

    /**
     * RepportGenerator constructor.
     * @param graph
     */
    public RepportGenerator(final TrackingGraph graph) {
        this.graph = graph;

    }

    /**
     * generate.
     * @return
     * @throws RequirementAnalyserException
     * @throws FileNotFoundException
     */
    public ArrayList<Pair<String, String>> generate() throws RequirementAnalyserException {
        final ArrayList<Pair<String, String>> listOfRepport = new ArrayList<Pair<String, String>>();
        listOfRepport.add(generateVersionCheckingRepport());
        listOfRepport.add(generateProjectGraphRepport());
        return listOfRepport;

    }

    /**
     * generateProjectGraphRepport.
     * @return
     * @throws RequirementAnalyserException
     */
    private Pair<String, String> generateProjectGraphRepport() throws RequirementAnalyserException {
        final Pair<String, String> pair = new Pair<String, String>();
        final ProjectGraphAnalyser projectGraphAnalyser = new ProjectGraphAnalyser();
        graph.accept(projectGraphAnalyser);
        final AbstractGenerator projectGraphRepportGenerator = projectGraphAnalyser.getRepportGenerator();
        pair.setFirst(RepportGenerator.SITE_REPPORT_LOCATION + "projectGraphRepport.xml");
        pair.setSecond(projectGraphRepportGenerator.generateRepport());
        return pair;
    }

    /**
     * generateVersionCheckingRepport.
     * @return
     * @throws FileNotFoundException
     */
    private Pair<String, String> generateVersionCheckingRepport() {
        final Pair<String, String> pair = new Pair<String, String>();
        final VersionCheckingAnalyser versionAnalyser = new VersionCheckingAnalyser();
        graph.accept(versionAnalyser);
        final AbstractGenerator versionRepportGenerator = versionAnalyser.getRepportGenerator();
        pair.setFirst(RepportGenerator.SITE_REPPORT_LOCATION + "versionRepport.xml");
        pair.setSecond(versionRepportGenerator.generateRepport());
        return pair;

    }

}
