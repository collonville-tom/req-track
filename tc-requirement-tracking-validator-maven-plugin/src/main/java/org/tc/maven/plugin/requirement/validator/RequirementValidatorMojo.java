/**
 */
package org.tc.maven.plugin.requirement.validator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.ResourceBundle;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.tc.maven.plugin.requirement.validator.rpc.MavenPluginRPCClient;
import org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.rpc.IRPCMavenPluginConnector;
import org.tc.osgi.bundle.requirement.tracking.repport.exception.RequirementAnalyserException;

/**
 * RequirementValidatorMojo.java.
 * @author collonville thomas
 * @version 0.1.0
 */
@Mojo(name = "validate", defaultPhase = LifecyclePhase.VERIFY)
public class RequirementValidatorMojo extends AbstractMojo {

    // private final File localDirectory = new File(".");
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("requirement-tracking-validator-maven-plugin");

    // @Component
    // MavenProject project;
    // <dependency>
    // <groupId>org.apache.maven</groupId>
    // <artifactId>maven-project</artifactId>
    // <version>2.2.1</version>
    // </dependency>

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("RequirementValidatorMojo execution begin");
        final String[] values = getPluginContext().get("project").toString().split("@");
        final File pomFile = new File(values[1].substring(1));

        try {
            final IRPCMavenPluginConnector connector = MavenPluginRPCClient.getInstance().getIRMIMavenPluginConnector();
            connector.initAnalysers();
            initLitOfReqFile(new File(pomFile.getParent()), connector);

            // A ce stade le composant distant connait la liste des exigences
            // On peut alors demander a faire construire le graph associés
            final String repports = connector.extractRepportList(new File(pomFile.getParent()).getName());
            getLog().info("Repport List:" + pomFile.getParent());
            getLog().info(repports);
            // Le repport est une map de pair chaque fichier + le contenu de du
            // rapport
            final FileOutputStream fos = new FileOutputStream(new File(pomFile.getParent()).getName());
            fos.write(repports.getBytes());
            fos.flush();
            fos.close();
        } catch (final IOException e) {
            getLog().error("Erreur au traitement du fichier" + e.getLocalizedMessage());
        } catch (final NotBoundException e) {
            getLog().error("Erreur lors de la connexion au service RMI" + e.getLocalizedMessage());
        } catch (final RequirementAnalyserException e) {
            getLog().error("Erreur lors de la construction du graph des exigences:" + e.getLocalizedMessage());
        }
        getLog().info("RequirementValidatorMojo execution finish");
    }

    public String getTargetDir() {
        return resourceBundle.getString("tc.osgi.bundle.requirement.tracking.maven.plugin.connector.target");
    }

    private void initLitOfReqFile(final File subDir, final IRPCMavenPluginConnector connector) throws IOException {

        getLog().debug("Analyse " + subDir.getAbsolutePath());
        for (final File file : subDir.listFiles()) {
            if (file.isDirectory()) {
                if (file.getCanonicalPath().contains(getTargetDir())) {
                    initLitOfReqFile(file, connector);
                }
            } else {
                if (connector.filterFile(file)) {

                    final BufferedReader reader = new BufferedReader(new FileReader(file));
                    final StringBuilder builder = new StringBuilder();
                    while (reader.ready()) {
                        builder.append(reader.readLine());
                        builder.append("|");
                    }
                    reader.close();

                    getLog().debug("Verification contenu de chaine:");
                    getLog().debug(builder.toString());
                    connector.extractRequirementsInFile(file, builder.toString());
                }
            }

        }
    }
}
