package com.example.glup.loadobjfromurl;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        testGoogle();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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

    public void testGoogle(){
        String URL="http://108.179.236.242/~glupserver/API/orquestadorServiciosApp.php";
        AsyncHttpClient httpClient= new AsyncHttpClient();
        RequestParams params=new RequestParams();
        params.put("tag","obtenerOBJTextura");
        params.put("codigo_usuario","000000000010");
        params.put("codigo_prenda","000000000055");
        httpClient.post(this, URL, params,new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Gson gson = new Gson();
                Log.e("Coneccion",response.toString());
                /*Catalogo catalogo = gson.fromJson(response.toString(), Catalogo.class);
                prendas = catalogo.getPrendas();*/
                Model model= gson.fromJson(response.toString(), Model.class);
                String url=model.detalleOBJTextura.get(0).getImagen();
                String urlObj = model.detalleOBJTextura.get(0).getNom_obj();
                Log.e("URL", url);
                Download miDownload=new Download(url,true);
                miDownload.execute();
                Download secondDownload= new Download(urlObj,false);
                secondDownload.execute();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e("Error", responseString);

            }
        });
    }
    public class Model{
        public int success;public int error; public String tag;
        public ArrayList<DetalleOBJTextura> detalleOBJTextura;
    }

    private class Download extends AsyncTask<String,Void,Void>{
        private String url;
        private boolean isTextura;
        public Download(String url, boolean texture){
            this.url=url;
            this.isTextura=texture;
        }
        @Override
        protected Void doInBackground(String... params) {
            ImageManager imageManager= new ImageManager();
            String filename=url.substring(url.lastIndexOf("/") + 1, url.length());
            Log.e("nom",filename);
            imageManager.DownloadFromUrl(url,filename,isTextura);
            return null;
        }

    }

}
