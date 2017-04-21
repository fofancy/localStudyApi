package com.fofancy.onlineguide;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fofancy.geographicalObjects.info.GeographicalObjectsInfoParameters;
import com.fofancy.geographicalObjects.info.IGeographicalObjectInfo;
import com.fofancy.geographicalObjects.processor.GeographicalObjectsInfoEJB;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by shaylin3 on 11.04.2017.
 */
@WebServlet(name = "MapObjectDescriptionServlet", value = "/map-object-info")
public class MapObjectDescriptionServlet extends HttpServlet {
    @EJB
    GeographicalObjectsInfoEJB geographicalObjectsInfo;


    public GeographicalObjectsInfoEJB getGeographicalObjectsInfo() {
        return geographicalObjectsInfo;
    }

    public void setGeographicalObjectsInfo(GeographicalObjectsInfoEJB geographicalObjectsInfo) {
        this.geographicalObjectsInfo = geographicalObjectsInfo;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("name");

        GeographicalObjectsInfoParameters params = new GeographicalObjectsInfoParameters();
        params.setProperty("name", name);

        IGeographicalObjectInfo providerResponse = geographicalObjectsInfo
                .getGeographicalObjectInfo(
                        params,
                        "com.fofancy.geographicalObjects.info.wiki.WikiGeographicalObjectsInfoReceiver"
                );

        ObjectMapper mapper = new ObjectMapper();

        try (PrintWriter out = response.getWriter()) {
            mapper.writeValue(out, providerResponse);
        }
    }
}
