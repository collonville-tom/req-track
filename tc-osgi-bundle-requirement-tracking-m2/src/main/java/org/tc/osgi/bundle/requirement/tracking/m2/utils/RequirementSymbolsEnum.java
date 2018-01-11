package org.tc.osgi.bundle.requirement.tracking.m2.utils;

/**
 * Symbols.java.
 * @author Collonville Thomas
 * @version 0.0.1
 */
public enum RequirementSymbolsEnum {

    DEPENDENCY_TAG("@depend"), DESCRIPTION_TAG("@description"), ELEMENT("@"), FINAL_REQUIREMENT_TAG("@finalreq"), INITIAL_REQUIREMENT_TAG("@initialreq"), REQUIREMENT_TAG("@req"), TRACKING_TAG(
        "@track"), VERSION_TAG("@version");

    private String name = "";

    RequirementSymbolsEnum(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
