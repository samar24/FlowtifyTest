package com.flowtify.samar.flowtifytest;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Samar on 2/19/2017.
 */
public class DepartmentsFragments  extends Fragment {

    public static final String TAG = DepartmentsFragments.class.getSimpleName();
String Token="";
    UserFunctions userFunctions=new UserFunctions();
    private ArrayList<String> Departments=new ArrayList<String>();
    private GridViewAdapter mGridAdapter;
     GridView mGridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.departmentsfragments, container, false);
        mGridView = (GridView) view.findViewById(R.id.gridView);
        Bundle bundle = getArguments();
        if (bundle != null) {
            Token = bundle.getString("token");



            new ProgressTask().execute();
        }

        return view;

    }
    class ProgressTask extends AsyncTask<String, Integer, String> {

        private ProgressDialog progressDialog;
        private String json;
        private JSONObject jObj;
        JSONArray Jarr=new JSONArray();

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                json = userFunctions.GetDepartments(Token);
                jObj = new JSONObject(json);
                Jarr = jObj.names();
                for (int i = 0; i < Jarr.length(); i++) {
                    try {

                        JSONObject obj1 = Jarr.getJSONObject(i);
                        String Department = obj1.toString();


                        Departments.add(Department);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
            catch (IOException e) {
                e.printStackTrace();
            }
            catch (JSONException e) {
                    e.printStackTrace();
                }

            getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {
                }


            });
            return"done";
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            mGridAdapter = new GridViewAdapter(getActivity(), R.layout.grid_item,Departments);
            mGridView.setAdapter(mGridAdapter);
                 }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    }


    }

