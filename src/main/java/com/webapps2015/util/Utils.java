/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2015.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author dar27
 */
public class Utils {

    public static Object consumeGetWebService(String url, Map<String, String> params, ParamType paramType, Class clazz) {

        try {
            String paramsToAppend = prepareParamsToAppend(params, paramType);
            Client client = Client.create();
            WebResource webResource = client.resource(String.format("%s%s", url, paramsToAppend));
            ClientResponse response = webResource.accept(MediaType.TEXT_PLAIN)
                    .get(ClientResponse.class);
            return response.getEntity(clazz);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Object consumePostWebService(String url, Object param, Class clazz) {
        try {
            Client client = Client.create();

            WebResource webResource = client
                    .resource(url);

            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

            String input = gson.toJson(param);
            ClientResponse response = null;
            if (param != null) {
                response = webResource.type("application/json")
                        .post(ClientResponse.class, input);
            } else {
                response = webResource.type("application/json").post(ClientResponse.class);

            }

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            System.out.println("Output from Server .... \n");
            return gson.fromJson(response.getEntity(String.class), clazz);

        } catch (Exception e) {

            e.printStackTrace();

        }
        return null;
    }

    public static String prepareParamsToAppend(Map<String, String> params, ParamType paramType) {
        if (params != null && params.size() > 0 && paramType != null) {
            switch (paramType) {
                case QUERY_PARAM:
                    return prepareQueryParam(params);
                default:
                    return preparePathParam(params);
            }
        }
        return "";
    }

    public static String prepareQueryParam(Map<String, String> params) {

        int counter = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (String param : params.keySet()) {
            String value = params.get(param);
            if (value != null) {
                String stringToAppend = null;
                if (counter == 0) {
                    stringToAppend = String.format("?%s=%s", param, value);
                } else {
                    stringToAppend = String.format("&%s=%s", param, value);
                }
                stringBuilder.append(stringToAppend);
                counter++;
            }

        }
        return stringBuilder.toString();
    }

    public static String preparePathParam(Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String param : params.keySet()) {
            String value = params.get(param);
            if (value != null) {
                String stringToAppend = String.format("/%s", value);
                stringBuilder.append(stringToAppend);
            }
        }
        return stringBuilder.toString();
    }

    public static boolean isNotEmpty(String value) {
        return value != null && value.trim().length() > 0;
    }

    public static boolean isNumber(String value) {

        boolean result = false;
        try {
            if (isNotEmpty(value)) {
                Double.valueOf(value);
                result = true;
            }
        } catch (Exception ex) {
            // leave resut false as uts
        }
        return result;
    }

    public static boolean isEmpty(List result) {
        return result == null || result.size() == 0;
    }

    public static String hash(String stringToHash) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        md.update(stringToHash.getBytes("UTF-8"));
        byte[] digest = md.digest();
        BigInteger bigInt = new BigInteger(1, digest);

        return bigInt.toString(16);

    }

    public static String getSPTH(int numberOfPrevoiusDonation) {
        if (numberOfPrevoiusDonation == 1) {
            return " st ";
        } else if (numberOfPrevoiusDonation == 2) {
            return "nd";
        } else if (numberOfPrevoiusDonation == 3) {
            return "rd";
        }
        return " th";
    }

}
