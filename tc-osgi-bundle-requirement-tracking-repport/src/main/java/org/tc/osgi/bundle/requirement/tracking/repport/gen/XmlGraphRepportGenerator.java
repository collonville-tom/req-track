/**
 */
package org.tc.osgi.bundle.requirement.tracking.repport.gen;

import java.io.FileNotFoundException;

/**
 * HtmlRequirementGenerator.java.
 * @author collonville thomas
 * @version
 */
public class XmlGraphRepportGenerator extends AbstractGenerator {

    public XmlGraphRepportGenerator(final String title) {
        super(title);
    }

    /**
     *
     * @throws FileNotFoundException
     * @see org.tc.osgi.bundle.requirement.tracking.repport.gen.AbstractGenerator#generateRepport()
     */
    @Override
    public String generateRepport() {
        // Est ce que ca vaut le coup de structurer un xsd pour generer les
        // rapport xml et builder les anomalie ou/et autre rapport de facon
        // coherent et homogene dans le temps (cela n'ammenera qu'un xslt pas
        // format de sortie voulu)

        // TODO ici aufinal ion encode en XML final mais on veut final pas
        // forcement claquer ca dans un fichier du moins pas tout de suite
        // final XMLEncoder encoder = new XMLEncoder(new FileOutputStream(new
        // File(this.getOutputFile())));
        // encoder.writeObject(this.anomalies);
        // encoder.flush();
        // encoder.close();
        return null;

    }
}
