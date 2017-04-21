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

    private String parseResponse(String response) {
        Document doc = Jsoup.parse(response);
        String text = doc.text();

        String splitedText[] = text.split("\\.\\s");
        splitedText[0] = splitedText[0].replaceAll("\\(.*?<span.*<\\/span>.*?\\)", "");

        StringBuilder parsedText = new StringBuilder();
        for (String sentence: splitedText) {
            parsedText.append(sentence + ". ");
        }

        String parsedTextWithoutTags = parsedText.toString().replaceAll("<[^>]*>", "");

        return parsedTextWithoutTags;
    }
}
