/**
 */
package org.tc.maven.plugin.requirement.validator;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.tc.maven.plugin.requirement.validator.rpc.MavenPluginRPCClient;
import org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.rpc.IRPCMavenPluginConnector;

/**
 * RequirementValidatorMojo.java.
 * @author collonville thomas
 * @version 0.1.0
 */
@Mojo(name = "clean", defaultPhase = LifecyclePhase.CLEAN)
public class RequirementFlushMojo extends AbstractMojo {

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("RequirementFlushMojo execution begin");

        IRPCMavenPluginConnector connector;
        try {
            connector = MavenPluginRPCClient.getInstance().getIRMIMavenPluginConnector();
            connector.flushState();
        } catch (final MalformedURLException e) {
            getLog().error("Erreur au traitement du fichier" + e.getLocalizedMessage());
        } catch (final NotBoundException e) {
            getLog().error("Erreur lors de la connexion au service RMI" + e.getLocalizedMessage());
        } catch (final RemoteException e) {
            getLog().error("Erreur lors de la connexion au service RMI" + e.getLocalizedMessage());
        } catch (final UnknownHostException e) {
            getLog().error("Erreur lors de la connexion au service RMI" + e.getLocalizedMessage());
        }

        getLog().info("RequirementFlushMojo execution finish");
    }

}
