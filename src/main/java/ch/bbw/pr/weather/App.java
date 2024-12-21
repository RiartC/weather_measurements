package ch.bbw.pr.weather;

import ch.bbw.pr.weather.dao.DataAccessObject;
import ch.bbw.pr.weather.model.Measure;
import com.mongodb.client.*;
import com.mongodb.client.model.Projections;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.BsonDateTime;
import org.bson.BsonDouble;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

/**
 * Try out mongoDB
 *
 * @author Peter Rutschmann
 * @version 18.11.2022
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello Weather");

        String connectionString = "mongodb://root:1234@localhost:27017";
        MongoClient mongoClient = MongoClients.create(connectionString);

        //list all databases
        System.out.println("List all databases:");
        mongoClient.listDatabases().forEach((Consumer<? super Document>) result -> System.out.println(result.toJson()));


        MongoDatabase db = mongoClient.getDatabase("weather");
        MongoCollection<Document> collection = db.getCollection("weather");

        Document station = new Document();
        station.append("city", "Winterthur");
        station.append("plz", "8400");

        Document measure1 = new Document();
        measure1.append("kind", "temperature");
        measure1.append("value", 20.1);

        Document measure2 = new Document();
        measure2.append("kind", "windspeed");
        measure2.append("value", 2.3);
        List<Document> measurements = List.of(measure1, measure2);


        Document doc = new Document();
        doc.append("type", "Wettermessung");
        doc.append("timestamp", new BsonDateTime(new Date().getTime()));
        doc.append("station", station);
        doc.append("measurements", measurements);


        collection.insertOne(doc);
        mongoClient.close();


        withDAO();
    }


    private static void withDAO(){
        DataAccessObject dao = new DataAccessObject();

        dao.setupDB();

        dao.printDatabases();

        List<Measure> measures = List.of(
                new Measure("wind", 33.3),
                new Measure("temperature", 29.8)
        );
        dao.saveMeasure("Winterthur", "8408", measures);

        dao.shutdown();
    }
}
