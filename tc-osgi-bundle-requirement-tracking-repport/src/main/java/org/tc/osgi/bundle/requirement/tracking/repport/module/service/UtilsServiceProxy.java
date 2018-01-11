package org.tc.osgi.bundle.requirement.tracking.repport.module.service;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleContext;
import org.tc.osgi.bundle.utils.collection.Collections;
import org.tc.osgi.bundle.utils.collection.element.Pair;
import org.tc.osgi.bundle.utils.conf.XMLPropertyFile;
import org.tc.osgi.bundle.utils.context.BundleKiller;
import org.tc.osgi.bundle.utils.context.BundleStarter;
import org.tc.osgi.bundle.utils.exception.FieldTrackingAssignementException;
import org.tc.osgi.bundle.utils.logger.LoggerGestionnary;
import org.tc.osgi.bundle.utils.module.service.IUtilsService;
import org.tc.osgi.bundle.utils.serial.SerialTool;
import org.tc.osgi.bundle.utils.tools.Tools;

public class UtilsServiceProxy implements IUtilsService {

    private static UtilsServiceProxy instance = null;

    public static UtilsServiceProxy getInstance() {
        if (UtilsServiceProxy.instance == null) {
            UtilsServiceProxy.instance = new UtilsServiceProxy();
        }
        return UtilsServiceProxy.instance;
    }

    private IUtilsService service = null;

    private UtilsServiceProxy() {

    }

    @Override
    public BundleContext getBundleContext() throws FieldTrackingAssignementException, MalformedURLException, RemoteException, NotBoundException, NumberFormatException, UnknownHostException {
        return service.getBundleContext();
    }

    @Override
    public BundleKiller getBundleKiller() {
        return service.getBundleKiller();
    }

    @Override
    public BundleStarter getBundleStarter() {
        return service.getBundleStarter();
    }

    @Override
    public Collections getCollectionTool() {
        return service.getCollectionTool();
    }

    @Override
    public Logger getLogger(final Class _class) {
        return service.getLogger(_class);
    }

    @Override
    public LoggerGestionnary getLoggerGestionnary() {
        return service.getLoggerGestionnary();
    }

    @Override
    public <T, N> Pair<T, N> getPair(final T t, final N n) {
        return service.getPair(t, n);
    }

    @Override
    public <T extends Serializable> SerialTool<T> getSerialTool() {
        return service.getSerialTool();
    }

    public IUtilsService getService() {
        return service;
    }

    @Override
    public Tools getTool() {
        return service.getTool();
    }

    @Override
    public XMLPropertyFile getXMLPropertyFile(final String propertyFileName) throws FieldTrackingAssignementException {
        return service.getXMLPropertyFile(propertyFileName);
    }

    public void setService(final IUtilsService service) {
        this.service = service;
    }
}
