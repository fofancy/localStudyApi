/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.mapInfo.nearestObjects;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;

/**
 * This is the main interface for api for data receiving from any type of map provider.
 * It is supposed that there will be implementations for each type of map provider and type of data receiving.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use= SOAPBinding.Use.LITERAL)
public interface INearestObjectsReceiver {
    @WebMethod
    ArrayList<MapObjectImpl> receiveNearestMapObjects(NearestObjectsReceiverParametersImpl properties);

}
