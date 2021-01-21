package com.ct.api.helper;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
/**
 * Created with Eclipse
 * Created By: Puneet Kamboj
 * Created Date: 15/05/2020
 * Modified By: Puneet
 * Modified Date: 18/05/2020
 * To change this template use File | Settings | File Templates.
 */
public class RestAPIHelper {


/****
 * 
 * @param path
 * @return
 */
    public Response restGet(String path) {
        return given().when().get(path);
    }

    /****
     * 
     * @param token
     * @param path
     * @return
     */
        public Response restDelete( String path) {
        	 RequestSpecification request = RestAssured.given();
        	 Response response = request.delete(path);
        	 return response;
        }

        
        /****
         * 
         * @param bodyText
         * @param path
         * @return
         */
        public Response restPost(String bodyText, String path) {
            RequestSpecification request = (RequestSpecification) RestAssured.given()
                    .body(bodyText);
            Response response = request.post(path);
            return response;
        }
        
   
        
        /****
         * 
         * @param bodyText
         * @param path
         * @return
         */
        public Response restPut(String bodyText, String path) {
            RequestSpecification request = (RequestSpecification) RestAssured.given()
                    .body(bodyText);
            Response response = request.put(path);
            return response;
        }
        
        /****
         * 
         * @param path
         * @param userName
         * @param password
         * @return
         */
          public Response restGetWithBasicAuthentication(String path, String userName ,String password) {        	
          	return given().auth().preemptive().basic(userName, password)
              .when()
              .when().get(path);
               
          } 

}