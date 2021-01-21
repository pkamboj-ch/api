package com.ct.api.utils;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

/**
 * Created with Eclipse
 * Created By: Puneet Kamboj
 * Created Date: 16/05/2020
 * Modified By: Puneet
 * Modified Date: 18/05/2020
 * To change this template use File | Settings | File Templates.
 */
public class Utils {

    private static Properties prop = new Properties();

   
/****
 * 
 * @param PropertyFileName
 */
    public  void loadPropertyFile(String PropertyFileName) {
        try {
        	prop.load(new FileInputStream(PropertyFileName));
        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/****
 * 
 * @param key
 * @return
 */
    public static String getValueOf(String key) {
        return prop.getProperty(key);
    }

    public static String getProjectBaseDirectory()
    {
    	return System.getProperty("user.dir");
    }

    
    /****
     * 
     * @param response
     * @return
     */
    public static String getResponseAllData(Response response )
    {
    	return String.valueOf(response.getBody().asString());
    }
    /****
     * 
     * @param response
     * @return
     */
    public static int getResponseStatusCode(Response response )
    {
    	return  response.getStatusCode();
    }


}
