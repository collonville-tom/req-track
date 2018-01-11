package org.tc.osgi.bundle.requirement.tracking.maven.plugin.connector.module.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service.IAptRequirementBuilderService;
import org.tc.osgi.bundle.requirement.tracking.m2.model.AbstractRequirementElement;

public class AptRequirementBuilderServiceProxy implements IAptRequirementBuilderService {

    private static AptRequirementBuilderServiceProxy instance = null;

    public static AptRequirementBuilderServiceProxy getInstance() {
        if (AptRequirementBuilderServiceProxy.instance == null) {
            AptRequirementBuilderServiceProxy.instance = new AptRequirementBuilderServiceProxy();
        }
        return AptRequirementBuilderServiceProxy.instance;
    }

    private IAptRequirementBuilderService service = null;

    private AptRequirementBuilderServiceProxy() {

    }

    /**
     * @param file
     * @param content
     * @return
     * @throws IOException
     * @see org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service.IAptRequirementBuilderService#extractRequirement(java.io.File, java.util.List)
     */
    @Override
    public List<AbstractRequirementElement> extractRequirement(final File file, final List<String> content) throws IOException {
        return service.extractRequirement(file, content);
    }

    public IAptRequirementBuilderService getService() {
        return service;
    }

    public void setService(final IAptRequirementBuilderService service) {
        this.service = service;
    }
}
