package com.fofancy.geographicalObjects.info;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by shaylin3 on 14.04.2017.
 * TODO: maven repository
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use= SOAPBinding.Use.LITERAL)
public interface IGeographicalObjectsInfoReceiver {
    @WebMethod
    GeographicalObjectInfo receiveDescription(GeographicalObjectsInfoParameters properties);
}
