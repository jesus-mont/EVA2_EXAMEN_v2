package com.example.chuy_eva2_examen_v2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Detalle extends AppCompatActivity {

    Intent inLlamar;
    ImageView imgVwRes;
    TextView TxtVwNombreRes,TextVwTipoRes, TxtVwDireccion,TxtVwPlaza, TxtVwTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        imgVwRes=(ImageView)findViewById(R.id.imgVwRes);
        TxtVwNombreRes=(TextView)findViewById(R.id.TxtVwNombreRes);
        TextVwTipoRes=(TextView)findViewById(R.id.TxtVwTipoRes);
        TxtVwDireccion=(TextView)findViewById(R.id.TxtVwDir);
        TxtVwPlaza=(TextView)findViewById(R.id.TxtVwPlaza);
        TxtVwTel=(TextView)findViewById(R.id.TxtVwTel);
        //leer los datos
        Intent inDatos = getIntent();
        int iIma= inDatos.getIntExtra("IMAGEN", R.drawable.chihua);
        String sRes= inDatos.getStringExtra("RESTAURANT");
        String sTRes= inDatos.getStringExtra("TIPORESTAURANT");
        String sDir= inDatos.getStringExtra("DIRECCION");
        String sPl= inDatos.getStringExtra("PLAZA");
        final String sTel= inDatos.getStringExtra("TELEFONO");

        imgVwRes.setImageResource(iIma);
        TxtVwNombreRes.setText(sRes);
        TextVwTipoRes.setText(sTRes);
        TxtVwDireccion.setText(sDir);
        TxtVwPlaza.setText(sPl);
        TxtVwTel.setText(sTel);




        TxtVwTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inLlamar=new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+sTel));
                startActivity(inLlamar);
            }
        });
    }
}