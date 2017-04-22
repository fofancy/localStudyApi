package com.fofancy.geographicalObjects.info.wiki;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by shaylin3 on 21.04.2017.
 */
class WikiGeographicalObjectsInfoResponseProcessor
        implements IWikiResponseProcessor<String> {

    @Override
    public String process(String response) {
        String parsedResponse = parseResponse(response);

        return parsedResponse;
    }

    /* Wiki response contains xml code with several unnecessary tags and info
     *  this class was designed to get rid of this text info
     * */
    private String parseResponse(String response) {
        Document doc = Jsoup.parse(response);
        String text = doc.text();

        /* Splitting of first sentence (it is considered that mostly some unnecessary info like pronounciation
         * an so on is situated inside first sentence)
         *  */
        String splitedText[] = text.split("\\.\\s");


        /* It was noticed that all information about pronounication and representation in other languages
        *  is involved inside tags <span>. It was decided to cut all brackets which contain tag <span>
        * */
        splitedText[0] = splitedText[0].replaceAll("\\(.*?<span.*<\\/span>.*?\\)", "");

        /* Building the whole text
        * */
        StringBuilder parsedText = new StringBuilder();
        for (String sentence: splitedText) {
            parsedText.append(sentence + ". ");
        }

        /* Deleting all tags */
        String parsedTextWithoutTags = parsedText.toString().replaceAll("<[^>]*>", "");

        return parsedTextWithoutTags;
    }
}
