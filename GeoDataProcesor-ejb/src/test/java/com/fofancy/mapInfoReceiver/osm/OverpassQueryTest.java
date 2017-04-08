/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.mapInfoReceiver.osm;

import com.fofancy.mapInfoReceiver.osm.OverpassQuery;
import com.fofancy.mapInfoReceiver.osm.CriteriaValue;
import com.fofancy.mapInfoReceiver.osm.OutEnum;
import com.fofancy.mapInfoReceiver.osm.Out;
import com.fofancy.mapInfoReceiver.osm.Around;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.TreeMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 *
 * @author shaylin3
 */
public class OverpassQueryTest {
    OverpassQuery query;
    
    public OverpassQueryTest() {
    }
    
    @Before
    public void setUp() {
        query = new OverpassQuery();
    }
    
    @After
    public void setDown(){
        query = null;
    }
    
    /**
     * Test of send method, of class OverpassQuery.
     */
    @Test
    public void testSend() throws Exception {
        query.setOut(OutEnum.BODY);
        query.setNodeAround(1000, 46, 30);
        
        assertEquals(
                query.toString(),
                "http://overpass-api.de/api/interpreter?data=node(around:1000,46.0,30.0);out%20body;"
        );
    }

    
    @Test
    public void testTreeMap(){
        TreeMap<OverpassQuery.Criteria, CriteriaValue> map = new TreeMap();
        
        Around around = new Around(1000, 46.416934, 30.736618);
        Out out = new Out(OutEnum.BODY);
        
        map.put(OverpassQuery.Criteria.OUT, out);
        map.put(OverpassQuery.Criteria.NODE_AROUND, around);
        
        ArrayList<CriteriaValue> criteraSequence = new ArrayList(map.values());
        
        assertEquals(criteraSequence.get(0), around);
        assertEquals(criteraSequence.get(1), out);
    }
    
    @Test
    public void testQueryResponse() throws Exception {
        query.setOut(OutEnum.BODY);
        query.setNodeAround(200, 46.483087, 30.732576); // Afina's coords
        
        String answer = query.send();
        
        assertNotNull(answer);
    }
    
    @Test
    public void testQueryContent() throws Exception {
        String query = "http://overpass-api.de/api/interpreter?data=" + 
                "node" +
                "[~\".\"~\".\"]" +
                "(around:200,46.483087,30.732576);" +
                "way" +
                "[~\".\"~\".\"]" +
                "(around:200,46.483087,30.732576);" +
                "out;";
        String answer;
        try(InputStream is = new URL(query).openStream();
                ByteArrayOutputStream result = new ByteArrayOutputStream()){
            int length;
            byte[] buffer = new byte[1024];
            while((length = is.read(buffer)) != -1){
                result.write(buffer, 0, length);
            }
            
            answer = result.toString("UTF-8");
        }
        
        assertNotNull(answer);
    }
}
