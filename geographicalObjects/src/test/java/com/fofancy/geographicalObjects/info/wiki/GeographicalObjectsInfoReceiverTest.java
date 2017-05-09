/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fofancy.geographicalObjects.info.wiki;

import com.fofancy.geographicalObjects.info.GeographicalObjectsInfoParameters;
import com.fofancy.geographicalObjects.info.IGeographicalObjectInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 *
 * @author shaylin3
 */
public class GeographicalObjectsInfoReceiverTest {

    public GeographicalObjectsInfoReceiverTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void wikiQueryTest() {
         String queryResult = new String();

         try(InputStream is = new URL("https://en.wikipedia.org/w/api.php?format=xml&action=query&prop=extracts&titles=Louvre&redirects=true&exintro").openStream();
             ByteArrayOutputStream result = new ByteArrayOutputStream()){
             int length;
             byte[] buffer = new byte[1024];
             while((length = is.read(buffer)) != -1){
                 result.write(buffer, 0, length);
             }

             queryResult = result.toString("UTF-8");
         } catch (IOException ex) {
             Logger.getLogger(GeographicalObjectsInfoReceiverTest.class.getName()).log(Level.SEVERE, null, ex);
         }

         Document doc = Jsoup.parse(queryResult);
         String text = doc.text();

         String splitedText[] = text.split("\\.\\s");
         splitedText[0] = splitedText[0].replaceAll("\\(.*?<span.*<\\/span>.*?\\)", "");

         StringBuilder parsedText = new StringBuilder();
         for (String sentence: splitedText) {
             parsedText.append(sentence + ". ");
         }

         String parsedTextWithoutTags = parsedText.toString().replaceAll("<[^>]*>", "");
         System.out.print(parsedTextWithoutTags);
     }

    @Test
    public void wikiGeographicalObjectsInfoQueryHelperTest() {
//        try{
//            String queryString = WikiGeographicalObjectsInfoQueryHelper.getGeographicalObjectsDescriptionQuery("Louvre");
//
//            assertEquals(queryString,
//                    "https://en.wikipedia.org/w/api.php?format=xml&action=query&prop=extracts&titles=Louvre&redirects=true&exintro");
//        }
//        catch(Exception ex){
//            System.out.println(ex.getMessage());
//        }
    }
}
