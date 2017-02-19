package com.flowtify.samar.flowtifytest;

import java.io.IOException;
import java.util.HashMap;

public class UserFunctions {


    private static String Authentication ="https://flowtify-dev.westeurope.cloudapp.azure.com/account/authenticate";
    private static String GetDepartments ="https://flowtify-dev.westeurope.cloudapp.azure.com/department/v2/list";


    // constructor
    public UserFunctions(){

    }
    public  String LogIn(String UserName, String Pass) throws IOException {
        String json;
        JsonParser jsonParser = new JsonParser();

        // Building Parameters ( you can pass as many parameters as you want)
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username",UserName );
        params.put("password",Pass );
        json = jsonParser.makeHttpRequest(Authentication, "POST", params);
        return json;

    }

    public String GetDepartments(String Token ) throws IOException{
        String json;
        JsonParser jsonParser = new JsonParser();
//		Log.d("YARAB222", MovieId);
        // Building Parameters ( you can pass as many parameters as you want)
        HashMap<String, String>  params = new HashMap<String, String>();

        params.put("Authorization", Token);
        //params.put("apiSecret", "1b11566f7d9006c693e264f2fe6e30cb");
        json = jsonParser.makeHttpRequest(GetDepartments+"/Authorization"+Token, "GET", params);
        return json;

    }

}