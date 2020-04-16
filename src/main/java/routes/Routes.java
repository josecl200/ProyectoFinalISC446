package routes;

import org.rosuda.JRI.Rengine;
import spark.Spark;
import com.google.gson.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;


public class Routes {
    public static void rutas(){
        String[] args = new String[]{"--vanilla"};
        Rengine rengine = new Rengine(args,false,null);
        rengine.eval("setwd(\"src/main/java/routes/\")");
        System.out.println(rengine.eval("source(\"rscripts/GPscriptUSA.r\")"));
        Gson gson = new Gson();
        System.out.println(rengine.eval("source(\"rscripts/GPscriptCH.r\")"));
        System.out.println(rengine.eval("source(\"rscripts/GPscriptES.r\")"));
        System.out.println(rengine.eval("source(\"rscripts/GPscriptGER.r\")"));
        System.out.println(rengine.eval("source(\"rscripts/GPscriptIT.r\")"));
        System.out.println(rengine.eval("source(\"rscripts/GPscriptRD.r\")"));
        Spark.afterAfter("/*", (request, response) ->
                response.header("Content-Type", "application/json")
        );
        Spark.get("/",(request, response) -> {
            return rengine.eval("summary(prediccionUSA)");
        });
        Spark.get("/usa/:days",(request, response) -> {
            HashMap<String,Object> info = new HashMap<>();
            info.put("First_Case_Date",rengine.eval("format(usFirstDate,format=\"%Y-%m-%d\")",true).asString());
            LocalDate firstDate = LocalDate.parse(rengine.eval("format(usFirstDate,format=\"%Y-%m-%d\")",true).asString());
            JsonArray data = new JsonArray();
            System.out.println("k bien");
            for(int i=0;i<Integer.parseInt(request.params("days"));i++){
                HashMap<String,Object> point=new HashMap<>();
                rengine.eval(String.format("predictUSA$Dias_primer_caso<-%d", i));
                rengine.eval("prediccionUSA<-predict(USAmodel,predictUSA)");
                point.put("Date",firstDate.plusDays(i).toString());
                point.put("DaysAfterFirstCase",i);
                point.put("Cases",rengine.eval("prediccionUSA$Y_hat",true).asDouble());
                JsonElement jPoint = new JsonParser().parse(gson.toJson(point));
                data.add(jPoint);
            }
            System.out.println("kk bien");
            JsonObject jInfo = (JsonObject) new JsonParser().parse(gson.toJson(info));
            System.out.println("kkk bien");
            jInfo.add("data",data);
            System.out.println("kkkk bien");
            //Double yhat = rengine.eval("prediccionUSA$Y_hat", true).asDouble();
            return jInfo;
        });
        Spark.get("/cn/:days",(request, response) -> {
            HashMap<String,Object> info = new HashMap<>();
            info.put("First_Case_Date",rengine.eval("format(cnFirstDate,format=\"%Y-%m-%d\")",true).asString());
            LocalDate firstDate = LocalDate.parse(rengine.eval("format(cnFirstDate,format=\"%Y-%m-%d\")",true).asString());
            JsonArray data = new JsonArray();
            System.out.println("k bien");
            for(int i=0;i<Integer.parseInt(request.params("days"));i++){
                HashMap<String,Object> point=new HashMap<>();
                rengine.eval(String.format("predictCH$Dias_primer_caso<-%d", i));
                rengine.eval("prediccionCH<-predict(CHmodel,predictCH)");
                point.put("Date",firstDate.plusDays(i).toString());
                point.put("DaysAfterFirstCase",i);
                point.put("Cases",rengine.eval("prediccionCH$Y_hat",true).asDouble());
                JsonElement jPoint = new JsonParser().parse(gson.toJson(point));
                data.add(jPoint);
            }
            System.out.println("kk bien");
            JsonObject jInfo = (JsonObject) new JsonParser().parse(gson.toJson(info));
            System.out.println("kkk bien");
            jInfo.add("data",data);
            System.out.println("kkkk bien");
            return jInfo;
        });
        Spark.get("/do/:days",(request, response) -> {
            HashMap<String,Object> info = new HashMap<>();
            info.put("First_Case_Date",rengine.eval("format(rdFirstDate,format=\"%Y-%m-%d\")",true).asString());
            LocalDate firstDate = LocalDate.parse(rengine.eval("format(usFirstDate,format=\"%Y-%m-%d\")",true).asString());
            JsonArray data = new JsonArray();
            System.out.println("k bien");
            for(int i=0;i<Integer.parseInt(request.params("days"));i++){
                HashMap<String,Object> point=new HashMap<>();
                rengine.eval(String.format("predictRD$Dias_primer_caso<-%d", i));
                rengine.eval("prediccionRD<-predict(GPmodel,predictRD)");
                point.put("Date",firstDate.plusDays(i).toString());
                point.put("DaysAfterFirstCase",i);
                point.put("Cases",rengine.eval("prediccionRD$Y_hat",true).asDouble());
                JsonElement jPoint = new JsonParser().parse(gson.toJson(point));
                data.add(jPoint);
            }
            System.out.println("kk bien");
            JsonObject jInfo = (JsonObject) new JsonParser().parse(gson.toJson(info));
            System.out.println("kkk bien");
            jInfo.add("data",data);
            System.out.println("kkkk bien");
            //Double yhat = rengine.eval("prediccionUSA$Y_hat", true).asDouble();
            return jInfo;
        });
        Spark.get("/it/:days",(request, response) -> {
            HashMap<String,Object> info = new HashMap<>();
            info.put("First_Case_Date",rengine.eval("format(itFirstDate,format=\"%Y-%m-%d\")",true).asString());
            LocalDate firstDate = LocalDate.parse(rengine.eval("format(itFirstDate,format=\"%Y-%m-%d\")",true).asString());
            JsonArray data = new JsonArray();
            System.out.println("k bien");
            for(int i=0;i<Integer.parseInt(request.params("days"));i++){
                HashMap<String,Object> point=new HashMap<>();
                rengine.eval(String.format("predictIT$Dias_primer_caso<-%d", i));
                rengine.eval("prediccionIT<-predict(ITmodel,predictIT)");
                point.put("Date",firstDate.plusDays(i).toString());
                point.put("DaysAfterFirstCase",i);
                point.put("Cases",rengine.eval("prediccionIT$Y_hat",true).asDouble());
                JsonElement jPoint = new JsonParser().parse(gson.toJson(point));
                data.add(jPoint);
            }
            System.out.println("kk bien");
            JsonObject jInfo = (JsonObject) new JsonParser().parse(gson.toJson(info));
            System.out.println("kkk bien");
            jInfo.add("data",data);
            System.out.println("kkkk bien");
            //Double yhat = rengine.eval("prediccionUSA$Y_hat", true).asDouble();
            return jInfo;
        });
        Spark.get("/es/:days",(request, response) -> {
            HashMap<String,Object> info = new HashMap<>();
            info.put("First_Case_Date",rengine.eval("format(usFirstDate,format=\"%Y-%m-%d\")",true).asString());
            LocalDate firstDate = LocalDate.parse(rengine.eval("format(usFirstDate,format=\"%Y-%m-%d\")",true).asString());
            JsonArray data = new JsonArray();
            System.out.println("k bien");
            for(int i=0;i<Integer.parseInt(request.params("days"));i++){
                HashMap<String,Object> point=new HashMap<>();
                rengine.eval(String.format("predictUSA$Dias_primer_caso<-%d", i));
                rengine.eval("prediccionUSA<-predict(USAmodel,predictUSA)");
                point.put("Date",firstDate.plusDays(i).toString());
                point.put("DaysAfterFirstCase",i);
                point.put("Cases",rengine.eval("prediccionUSA$Y_hat",true).asDouble());
                JsonElement jPoint = new JsonParser().parse(gson.toJson(point));
                data.add(jPoint);
            }
            System.out.println("kk bien");
            JsonObject jInfo = (JsonObject) new JsonParser().parse(gson.toJson(info));
            System.out.println("kkk bien");
            jInfo.add("data",data);
            System.out.println("kkkk bien");
            //Double yhat = rengine.eval("prediccionUSA$Y_hat", true).asDouble();
            return jInfo;
        });
        Spark.get("/de/:days",(request, response) -> {
            HashMap<String,Object> info = new HashMap<>();
            info.put("First_Case_Date",rengine.eval("format(deFirstDate,format=\"%Y-%m-%d\")",true).asString());
            LocalDate firstDate = LocalDate.parse(rengine.eval("format(deFirstDate,format=\"%Y-%m-%d\")",true).asString());
            JsonArray data = new JsonArray();
            System.out.println("k bien");
            for(int i=0;i<Integer.parseInt(request.params("days"));i++){
                HashMap<String,Object> point=new HashMap<>();
                rengine.eval(String.format("predictGER$Dias_primer_caso<-%d", i));
                rengine.eval("prediccionGER<-predict(GERmodel,predictUSA)");
                point.put("Date",firstDate.plusDays(i).toString());
                point.put("DaysAfterFirstCase",i);
                point.put("Cases",rengine.eval("prediccionGER$Y_hat",true).asDouble());
                JsonElement jPoint = new JsonParser().parse(gson.toJson(point));
                data.add(jPoint);
            }
            System.out.println("kk bien");
            JsonObject jInfo = (JsonObject) new JsonParser().parse(gson.toJson(info));
            System.out.println("kkk bien");
            jInfo.add("data",data);
            System.out.println("kkkk bien");
            //Double yhat = rengine.eval("prediccionUSA$Y_hat", true).asDouble();
            return jInfo;
        });
    }
}
