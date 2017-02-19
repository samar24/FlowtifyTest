package com.flowtify.samar.flowtifytest;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    String pass="";
    String nam="";
    UserFunctions userFunctions=new UserFunctions();
    public AlertDialog.Builder dlgAlert=new AlertDialog.Builder(this);
    SharedPreferences settings = getSharedPreferences("LOGGEDStatus", 0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Button log = (Button) findViewById(R.id.btnsubmit);
        EditText text1 = (EditText) findViewById(R.id.txtuname);
        EditText text2 = (EditText) findViewById(R.id.txtpassword);
        if(settings.getString("Logged","0").equals("0")){
             //means that the user logging in for the first time
        }
        else {
            Intent intent;
            intent = new Intent(getApplicationContext(), DepartmentsActivity.class)

                    .putExtra("token", settings.getString("Token","0"));
            startActivity(intent);
        }
        nam=text1.getText().toString();
        pass=text2.getText().toString();
        log.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new AsyncTask1().execute();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public class AsyncTask1 extends AsyncTask<String, Void, Integer> {
        JSONObject jObj;
        String json;
        private ProgressDialog progressDialog;



        @Override
        protected Integer doInBackground(String... params) {
            try {
                json=  userFunctions.LogIn(nam,pass);

                jObj = new JSONObject(json);
                  String Token=jObj.getString("token");
                String UserID=jObj.getString("userId");

           if(nam!= null ||!nam.equals("")&& pass != null||!pass.equals("")){
               if(UserID!=null ||!UserID.equals("")){
                //Go to the departments Fragment
                   SharedPreferences settings = getSharedPreferences("LOGGEDStatus", 0);
                   SharedPreferences.Editor editor = settings.edit();
                   editor.putString("Logged","1" );
                   editor.putString("Token",Token );
                   editor.putString("UserID",UserID );
                   editor.commit();

                   Intent intent;
                   intent = new Intent(getApplicationContext(), DepartmentsActivity.class)

                           .putExtra("token", Token);
                   startActivity(intent);


               } else {
                   dlgAlert.setMessage("wrong password or username");
                   dlgAlert.setTitle("Error Message...");
                   dlgAlert.setPositiveButton("OK", null);
                   dlgAlert.setCancelable(true);
                   dlgAlert.create().show();

                   dlgAlert.setPositiveButton("Ok",
                           new DialogInterface.OnClickListener() {
                               public void onClick(DialogInterface dialog, int which) {

                               }
                           });
           }

           }
                else{
               dlgAlert.setMessage("Please Enter the username and password");
               dlgAlert.setTitle("Error Message...");
               dlgAlert.setPositiveButton("OK", null);
               dlgAlert.setCancelable(true);
               dlgAlert.create().show();

               dlgAlert.setPositiveButton("Ok",
                       new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int which) {

                           }
                       });
           }

            }  catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return 0;
        }

        @Override
        protected void onPostExecute(Integer result) {
            if(progressDialog!=null)
                progressDialog.dismiss();






            //  mProgressBar.setVisibility(View.GONE);
        }
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }
    }
}
