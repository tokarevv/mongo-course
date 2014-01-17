package com.tengen;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.net.UnknownHostException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws UnknownHostException {
        //System.out.println( "Hello World!" );


        //hello Freemarker
        final Configuration conf = new Configuration();

        conf.setClassForTemplateLoading(App.class, "/");

        MongoClient client = new MongoClient();
        DB db = client.getDB("test");
        final DBCollection list = db.getCollection("mycoll");

        Spark.get(new Route("/") {//:thing
            @Override
            public Object handle(Request request, Response response) {
                StringWriter writer = new StringWriter();
                try {
                    Template template = conf.getTemplate("hello.ftl");
//                    Map<String, Object> helloMap = new HashMap<String, Object>();

//                    helloMap.put("hello", request.params(":thing"));

                    template.process(list.findOne(), writer);
//                    template.process(helloMap, writer);

                    System.out.println(writer);

                } catch (Exception e) {
                    halt(500);
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                return writer;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });






            //hello Freemarker
//        Configuration conf = new Configuration();
//
//        conf.setClassForTemplateLoading(App.class, "/");
//
//        try {
//            Template template = conf.getTemplate("hello.ftl");
//            StringWriter writer = new StringWriter();
//            Map<String, Object> helloMap = new HashMap<String, Object>();
//
//            helloMap.put("name", "Victor");
//
//            template.process(helloMap, writer);
//
//            System.out.println(writer);
//
//
//        } catch (Exception e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }


        //hello Spark
//        Spark.get(new Route("/") {
//            @Override
//            public Object handle(Request request, Response response) {
//                return "hello world from Spark";  //To change body of implemented methods use File | Settings | File Templates.
//            }
//        });


        //hello Mongo
//        MongoClient client = new MongoClient();
//
//        DB db = client.getDB("test");
//
//        DBCollection list = db.getCollection("mycoll");
//
//        System.out.println(list.findOne());

    }
}
