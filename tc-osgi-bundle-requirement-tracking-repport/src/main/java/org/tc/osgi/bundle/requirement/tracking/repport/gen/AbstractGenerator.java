/**
 */
package org.tc.osgi.bundle.requirement.tracking.repport.gen;

import java.io.FileNotFoundException;

/**
 * AbstractGenerator.java.
 * @author collonville thomas
 * @version
 */
public abstract class AbstractGenerator {

    private String outputFile;
    private String title;

    public AbstractGenerator(final String title) {
        this.title = title;
    }

    /**
     * generateRepport.
     * @throws FileNotFoundException
     */
    public abstract String generateRepport();

    /**
     * Getter outputFile.
     * @return
     */
    public String getOutputFile() {
        return outputFile;
    }

    /**
     * Getter title.
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter outputFile.
     * @param outputFile String
     */
    public void setOutputFile(final String outputFile) {
        this.outputFile = outputFile;
    }

    /**
     * Setter title.
     * @param title String
     */
    public void setTitle(final String title) {
        this.title = title;
    }
}
