/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.onlineguide;

import com.fofancy.geoDataProcessor.Coords;
import com.fofancy.geoDataProcessor.ProcessGeoDataEJB;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.json.Json; 
import javax.json.stream.JsonParserFactory;
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
    ProcessGeoDataEJB mProcessGeoDataEJB;

    public ProcessGeoDataEJB getMProcessGeoDataEJB() {
        return mProcessGeoDataEJB;
    }

    public void setMProcessGeoDataEJB(ProcessGeoDataEJB mProcessGeoDataEJB) {
        this.mProcessGeoDataEJB = mProcessGeoDataEJB;
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
        
        mProcessGeoDataEJB.process(aCoords);
        
        System.out.println("com.fofancy.onlineguide.processGeoData.processRequest()");
        try (PrintWriter out = response.getWriter()) {
            out.write("nm,");
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
     * Returns a short description of
     * the servlet.
     *
     * @return a String containing
     * servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
