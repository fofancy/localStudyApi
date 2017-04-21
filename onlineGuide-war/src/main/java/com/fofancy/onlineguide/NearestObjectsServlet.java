/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.onlineguide;

import com.fofancy.mapInfo.nearestObjects.Coords;
import com.fofancy.mapInfo.nearestObjects.processor.NearestObjectsReceiverEJB;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fofancy.mapInfo.nearestObjects.IMapObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 

/**Servlet for processing geodata sent from a client
 * 1) Logs geoData
 *
 * @author shaylin3
 */

@WebServlet("/nearest-objects")
public class NearestObjectsServlet extends HttpServlet {
    
    @EJB
    NearestObjectsReceiverEJB mNearestObjectsReceiverEJB;

    public NearestObjectsReceiverEJB getMProcessGeoDataEJB() {
        return mNearestObjectsReceiverEJB;
    }

    public void setMProcessGeoDataEJB(NearestObjectsReceiverEJB mNearestObjectsReceiverEJB) {
        this.mNearestObjectsReceiverEJB = mNearestObjectsReceiverEJB;
    }
    
    /**
     * 
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a
     * servlet-specific error occurs
     * @throws IOException if an I/O
     * error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        ObjectMapper mapper = new ObjectMapper();
        Coords aCoords = mapper.readValue(request.getReader(), Coords.class);

        List<IMapObject> surroundingMapObjects = mNearestObjectsReceiverEJB.process(aCoords);

        try (PrintWriter out = response.getWriter()) {

            mapper.writeValue(out, surroundingMapObjects);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code>
     * method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a
     * servlet-specific error occurs
     * @throws IOException if an I/O
     * error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a
     * servlet-specific error occurs
     * @throws IOException if an I/O
     * error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short info of
     * the servlet.
     *
     * @return a String containing
     * servlet info
     */
    @Override
    public String getServletInfo() {
        return "Short info";
    }// </editor-fold>

}
