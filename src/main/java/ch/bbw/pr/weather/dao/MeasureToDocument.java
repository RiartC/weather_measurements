package ch.bbw.pr.weather.dao;

import ch.bbw.pr.weather.model.Measure;
import org.bson.Document;


public class MeasureToDocument {
    static Document measureToDocument(Measure measure){
        Document doc = new Document();
        doc.put("kind", measure.getKind());
        doc.put("value", measure.getValue());
        return doc;
    }
}
