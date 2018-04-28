package com.example.chuy_eva2_examen_v2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Principal extends AppCompatActivity implements ListView.OnItemClickListener {
    ListView lstVwRes;
    DatosRes[] drDatos={
            new DatosRes( "Barra Fina", "Restaurant Gourmet","Periferico de la juventud #312", "Distrito 1","4254169",R.drawable.chihua,R.drawable.chihua),
            new DatosRes( "Cafe Dulce Pecado", "Restaurant Cafe","Francisco villa #430", "Plaza Arboledas","4254896",R.drawable.chihua,R.drawable.chihua),
            new DatosRes( "cafe Canela Fina", "Restaurant Cafe","Periferico de la juventud #522", "Plaza Soberano","4156895",R.drawable.chihua,R.drawable.chihua),
            new DatosRes( "italianismo", "Restaurant Gourmet","Periferico de la juventud #322", "Distrito 1","4264686",R.drawable.chihua,R.drawable.chihua),
            new DatosRes( "Sirloin stockade", "Restaurant Gourmet","Periferico de la juventud #517", "Plaza Holliwood","4195426",R.drawable.chihua,R.drawable.chihua)};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        lstVwRes=(ListView)findViewById(R.id.LstVwLista);
        lstVwRes.setAdapter(new CustomAdapter(this, R.layout.lista_res,drDatos));
        lstVwRes.setOnItemClickListener(this);


    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
        Intent inDetalle = new Intent(this,Detalle.class);

        int iImg =drDatos[i].iImagen;

        String sRe=drDatos[i].sRes;
        String sTRes=drDatos[i].sTRes;
        String sDi=drDatos[i].sDir;
        String sPl=drDatos[i].sPlaza;
        String sTe= drDatos[i].sTel;
        inDetalle.putExtra("IMAGEN",iImg);
        inDetalle.putExtra("RESTAURANT",sRe);
        inDetalle.putExtra("TIPORESTAURANT",sTRes);
        inDetalle.putExtra("DIRECCION",sDi);
        inDetalle.putExtra("PLAZA",sPl);
        inDetalle.putExtra("TELEFONO",sTe);
        startActivity(inDetalle);
    }
    class JSONConnect extends AsyncTask<Void, Void, String> {

        final String endpoint = "dirip";

        @Override
        protected String doInBackground(Void... voids) {
            String resultado = "";
            try {
                URL url = new URL(endpoint);
                HttpURLConnection httpConCLima = (HttpURLConnection) url.openConnection();

                if (httpConCLima.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader buffer =
                            new BufferedReader(new InputStreamReader(httpConCLima.getInputStream()));
                    resultado = buffer.readLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultado;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


        }
    }

    class JSONPost extends AsyncTask<String, Void, String> {
        final String endpoint = "dirip";


        @Override
        protected String doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


        }
    }

    class JSONInsert extends AsyncTask<String, Void, String> {
        final String sEnlace = "dirip";

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


        }

        @Override
        protected String doInBackground(String... s) {
            BufferedWriter bwInsertJSON=null;
            String sResu="";
            try{
                JSONObject jsDatos= new JSONObject();
                jsDatos.put("Producto", s[0]);
                jsDatos.put("UnitPrice", s[1]);
                URL url=new URL(sEnlace);
                HttpURLConnection httpCon=(HttpURLConnection)url.openConnection();
                httpCon.setDoOutput(true);
                httpCon.setRequestMethod("POST");
                httpCon.setRequestProperty("Content-Type","aplication/json");
                httpCon.connect();
                OutputStream usConnect =httpCon.getOutputStream();
                bwInsertJSON = new BufferedWriter(new OutputStreamWriter(usConnect));
                bwInsertJSON.write(jsDatos.toString());
                bwInsertJSON.flush();
                //leer respuesta
                InputStream inLeerRespuesta= httpCon.getInputStream();
                BufferedReader bfLeeRespuesta= new BufferedReader(new InputStreamReader(inLeerRespuesta));
                String sLinea;
                while ((sLinea = bfLeeRespuesta.readLine())!=null){
                    sResu=sResu+sLinea;
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(bwInsertJSON!=null){
                    try {
                        bwInsertJSON.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            return sResu;
        }
    }

}

