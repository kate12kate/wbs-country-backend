package com.example.country.queries;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Queires {

    public static final String DBPEDIA_SPARQL_URL = "https://dbpedia.org/sparql";
    public static final String DBPEDIA_SPARQL_URL_GRAPH = "?default-graph-uri=http%3A%2F%2Fdbpedia.org&query=";
    public static final String DBPEDIA_SPARQL_URL_END = "&format=application%2Fsparql-results%2Bjson&timeout=30000&signal_void=on&signal_unconnected=on";

    public static String getCountries(String continent){
        String continentSparql = continent.replaceAll("\\s+","_");
        //return "https://dbpedia.org/sparql/?default-graph-uri=http%3A%2F%2Fdbpedia.org&query=SELECT+DISTINCT+%3Fcountry++%3Fid+%3Fname+%3Fdesc+%3Fcapital_city+%3Fthumbnail+%3Fcurrency%0D%0AWHERE+%7B%0D%0A++++%3Fcountry+rdf%3Atype+dbo%3ACountry+%3B%0D%0Adbp%3AcommonName+%3Fname%3B%0D%0A+++++++++dct%3Asubject+dbc%3ACountries_in_"+continentSparql+"%3B%0D%0Adbo%3AwikiPageID+%3Fid%3B%0D%0Adbo%3Acapital+%3Fcapital_city%3B%0D%0Adbo%3Athumbnail+%3Fthumbnail%3B%0D%0Adbo%3Acurrency+%3Fcurrency%3B%0D%0A++++++++++dbo%3Aabstract+%3Fdesc+.%0D%0A++++FILTER%28langMatches%28lang%28%3Fname%29%2C+%22en%22%29%29%0D%0AFILTER%28langMatches%28lang%28%3Fdesc%29%2C+%22en%22%29%29%0D%0A%7D%0D%0A%0D%0ALIMIT+100&format=application%2Fsparql-results%2Bjson&timeout=30000&signal_void=on&signal_unconnected=on";
        return DBPEDIA_SPARQL_URL+DBPEDIA_SPARQL_URL_GRAPH+
                encodeValue("select distinct ?country  ?id ?name ?desc ?capital_city ?thumbnail ?currency"+
                        " where{" +
                                "?country rdf:type dbo:Country;" +
                                "dbp:commonName ?name;" +
                                "dct:subject dbc:Countries_in_"+continentSparql+";" +
                                "dbo:wikiPageID ?id;" +
                                "dbo:capital ?capital_city;" +
                                "dbo:thumbnail ?thumbnail;" +
                                "dbo:currency ?currency;" +
                                "dbo:abstract ?desc." +
                                "filter(langMatches(lang(?name),\"en\"))" +
                                "filter(langMatches(lang(?desc),\"en\"))" +
                                "}"
                        )
                +DBPEDIA_SPARQL_URL_END;
//        SELECT DISTINCT ?country  ?id ?name ?desc ?capital_city ?thumbnail ?currency
//        WHERE {
//    ?country rdf:type dbo:Country ;
//            dbp:commonName ?name;
//            dct:subject dbc:Countries_in_Europe;
//            dbo:wikiPageID ?id;
//            dbo:capital ?capital_city;
//            dbo:thumbnail ?thumbnail;
//            dbo:currency ?currency;
//            dbo:abstract ?desc .
//                    FILTER(langMatches(lang(?name), "en"))
//            FILTER(langMatches(lang(?desc), "en"))
//        }
//
//        LIMIT 100
    }

    public static String getCountryDetails(String country){
        String sparqlCountry = country.replaceAll("\\s+","_");
        return "https://dbpedia.org/sparql/?default-graph-uri=http%3A%2F%2Fdbpedia.org&query=SELECT+DISTINCT++%3Fid+%3Fname+%3Fdesc+%3Fcapital_city+%3Fthumbnail+%3Fcurrency+%3Fcity%0D%0AWHERE+%7B%0D%0A++++dbr%3A"+sparqlCountry+"+rdf%3Atype+dbo%3ACountry+%3B%0D%0Adbp%3AcommonName+%3Fname%3B%0D%0Adbo%3AwikiPageID+%3Fid%3B%0D%0A++dbo%3Acapital+%3Fcapital_city%3B%0D%0Adbo%3Athumbnail+%3Fthumbnail%3B%0D%0Adbo%3Acurrency+%3Fcurrency%3B%0D%0A++++++++++dbo%3Aabstract+%3Fdesc+.%0D%0A%3Fcity+rdf%3Atype+dbo%3ACity%3B+dbo%3Acountry+dbr%3A"+sparqlCountry+"+.%0D%0A%0D%0A++++FILTER%28langMatches%28lang%28%3Fname%29%2C+%22en%22%29%29%0D%0AFILTER%28langMatches%28lang%28%3Fdesc%29%2C+%22en%22%29%29%0D%0A%0D%0A%7D%0D%0A%0D%0ALIMIT+100&format=application%2Fsparql-results%2Bjson&timeout=30000&signal_void=on&signal_unconnected=on";
        //return %0D%0AWHERE+%7B%0D%0A++++dbr%3A"+sparqlCountry+"+rdf%3Atype+dbo%3ACountry+%3B%0D%0Adbp%3AcommonName+%3Fname%3B%0D%0A++dbo%3Acapital+%3Fcapital_city%3B%0D%0Adbo%3Athumbnail+%3Fthumbnail%3B%0D%0Adbo%3Acurrency+%3Fcurrency%3B%0D%0A++++++++++dbo%3Aabstract+%3Fdesc+.%0D%0A%3Fcity+rdf%3Atype+dbo%3ACity%3B+dbo%3Acountry+dbr%3A"+sparqlCountry+"+.%0D%0A%0D%0A++++FILTER%28langMatches%28lang%28%3Fname%29%2C+%22en%22%29%29%0D%0AFILTER%28langMatches%28lang%28%3Fdesc%29%2C+%22en%22%29%29%0D%0A%0D%0A%7D%0D%0A%0D%0ALIMIT+100&format=application%2Fsparql-results%2Bjson&timeout=30000&signal_void=on&signal_unconnected=on";
        //        return DBPEDIA_SPARQL_URL+DBPEDIA_SPARQL_URL_GRAPH+
//                encodeValue("select distinct ?name ?desc ?capital_city ?thumbnail ?currency ?city"+
//                        " where{" +
//                        "dbr:"+country.replaceAll("\\s+","_")+" rdf:type dbo:Country;" +
//                        "dbp:commonName ?name;" +
//                        "dbo:capital ?capital_city;" +
//                        "dbo:thumbnail ?thumbnail;" +
//                        "dbo:currency ?currency;" +
//                        "dbo:abstract ?desc." +
//                        "?city rdf:type dbo:City; dbo:country dbr:"+country.replaceAll("\\s+","_")+"." +
//                        "filter(langMatches(lang(?name),\"en\"))" +
//                        "filter(langMatches(lang(?desc),\"en\"))" +
//                        "}LIMIT 100"
//                )
//                +DBPEDIA_SPARQL_URL_END;
    }

    public static String getCityDetails(String city){
        String sparqlCity = city.replaceAll("\\s+","_");
        return "https://dbpedia.org/sparql?default-graph-uri=http%3A%2F%2Fdbpedia.org&query=SELECT+DISTINCT++%3Fname+%3Fdesc+%3Fthumbnail+%0D%0AWHERE+%7B%0D%0A++++dbr%3A"+sparqlCity+"+rdf%3Atype+dbo%3ACity%3B%0D%0Adbp%3Aname+%3Fname%3B%0D%0Adbo%3Athumbnail+%3Fthumbnail%3B%0D%0A++++++++++dbo%3Aabstract+%3Fdesc+.%0D%0A++++FILTER%28langMatches%28lang%28%3Fname%29%2C+%22en%22%29%29%0D%0AFILTER%28langMatches%28lang%28%3Fdesc%29%2C+%22en%22%29%29%0D%0A%7D%0D%0A%0D%0ALIMIT+100&format=application%2Fsparql-results%2Bjson&timeout=30000&signal_void=on&signal_unconnected=on";
//        return DBPEDIA_SPARQL_URL+DBPEDIA_SPARQL_URL_GRAPH+
//                encodeValue("select distinct ?name ?desc ?thumbnail"+
//                        " where{" +
//                        "dbr:"+city.replaceAll(" ","_")+" rdf:type dbo:City;" +
//                        "dbp:commonName ?name;" +
//                        "dbo:thumbnail ?thumbnail;" +
//                        "dbo:abstract ?desc." +
//                        "filter(langMatches(lang(?name),\"en\"))" +
//                        "filter(langMatches(lang(?desc),\"en\"))" +
//                        "}LIMIT 100"
//                )
//                +DBPEDIA_SPARQL_URL_END;
    }

    private static String encodeValue(String value) {
        String encodedValue = "";
        try {
            encodedValue = URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (Exception ex) {
            ex.getMessage();
        }
        return encodedValue;
    }
}
