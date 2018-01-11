/**
 */
package org.tc.osgi.bundle.requirement.tracking.repport.exception;

/**
 * RequirementAnalyserException.java.
 * @author collonville thomas
 * @version
 */
public class RequirementAnalyserException extends Exception {

    /**
     * long serialVersionUID.
     */
    private static final long serialVersionUID = 8551637527498969979L;

    /**
     * long serialVersionUID.
     */

    public RequirementAnalyserException(final String msg) {
        super(msg);
    }

    public RequirementAnalyserException(final String msg, final Throwable t) {
        super(msg, t);
    }
}
