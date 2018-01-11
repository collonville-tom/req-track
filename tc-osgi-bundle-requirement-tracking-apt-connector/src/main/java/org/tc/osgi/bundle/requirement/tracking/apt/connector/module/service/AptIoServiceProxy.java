package org.tc.osgi.bundle.requirement.tracking.apt.connector.module.service;

import java.io.IOException;
import java.util.List;

import org.tc.osgi.bundle.apt.io.exception.AptConnectorException;
import org.tc.osgi.bundle.apt.io.model.AptObject;
import org.tc.osgi.bundle.apt.io.module.service.IAptIoService;
import org.tc.osgi.bundle.apt.io.utils.AptType;
import org.tc.osgi.bundle.utils.exception.FieldTrackingAssignementException;

public class AptIoServiceProxy implements IAptIoService {

    private static AptIoServiceProxy instance = null;

    public static AptIoServiceProxy getInstance() {
        if (AptIoServiceProxy.instance == null) {
            AptIoServiceProxy.instance = new AptIoServiceProxy();
        }
        return AptIoServiceProxy.instance;
    }

    private IAptIoService service = null;

    private AptIoServiceProxy() {

    }

    /**
     * @param l
     * @return
     * @throws FieldTrackingAssignementException
     * @see org.tc.osgi.bundle.apt.io.module.service.IAptIoService#cleanObject(java.util.List)
     */
    @Override
    public List<AptObject> cleanObject(final List<AptObject> l) throws FieldTrackingAssignementException {
        return service.cleanObject(l);
    }

    @Override
    public List<AptObject> getAptObjectList(final String filePathName) throws IOException, AptConnectorException {
        return service.getAptObjectList(filePathName);
    }

    /**
     * @param filePathName
     * @param content
     * @return
     * @see org.tc.osgi.bundle.apt.io.module.service.IAptIoService#getAptObjectList(java.lang.String, java.util.List)
     */
    @Override
    public List<AptObject> getAptObjectList(final String filePathName, final List<String> content) {
        return service.getAptObjectList(filePathName, content);
    }

    /**
     * @param aptType
     * @return
     * @throws FieldTrackingAssignementException
     * @see org.tc.osgi.bundle.apt.io.module.service.IAptIoService#getAptType(java.lang.String)
     */
    @Override
    public AptType getAptType(final String aptType) throws FieldTrackingAssignementException {
        return service.getAptType(aptType);
    }

    public IAptIoService getService() {
        return service;
    }

    /**
     * @param filePathName
     * @param aptObject
     * @throws IOException
     * @throws AptConnectorException
     * @see org.tc.osgi.bundle.apt.io.module.service.IAptIoService#saveAptFile(java.lang.String, org.tc.osgi.bundle.apt.io.model.AptObject)
     */
    @Override
    public void saveAptFile(final String filePathName, final AptObject aptObject) throws IOException, AptConnectorException {
        UtilsServiceProxy.getInstance().getLogger(AptIoServiceProxy.class).debug("saveAptFile.Sauvegarde fichier " + filePathName);
        service.saveAptFile(filePathName, aptObject);
        UtilsServiceProxy.getInstance().getLogger(AptIoServiceProxy.class).debug("Sauvegarde fichier " + filePathName + " fait");

    }

    public void setService(final IAptIoService service) {
        this.service = service;
    }

}
