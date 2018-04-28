package com.example.chuy_eva2_examen_v2;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class
CustomAdapter extends ArrayAdapter<DatosRes> {
    Context cntApp;
    int iLayout;
    DatosRes[] drDatos;

    public CustomAdapter(Context context, int resource, DatosRes[] objects) {
        super(context, resource, objects);
        cntApp=context;
        iLayout=resource;
        drDatos=objects;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vFila = convertView;
        ImageView imgRes;
        TextView TxtVwNombreRes, TxtVwTipoRes, TxtVwDirreccion, TxtVwPlaza, TxtVwTel;
        if(vFila==null){
            LayoutInflater liCrearLayout=((Activity)cntApp).getLayoutInflater();
            vFila= liCrearLayout.inflate(iLayout, parent, false);
        }

        imgRes=(ImageView)vFila.findViewById(R.id.imgVwRestau);
        TxtVwNombreRes=(TextView) vFila.findViewById(R.id.TxtVwNRes);
        TxtVwTipoRes=(TextView) vFila.findViewById(R.id.TxtVwTipoRes);
//        TxtVwDirreccion=(TextView)vFila.findViewById(R.id.TxtVwDireccion);
//        TxtVwPlaza=(TextView)vFila.findViewById(R.id.TxtVwPlaza);
//        TxtVwTel=(TextView)vFila.findViewById(R.id.TxtVwTel);
        DatosRes drOb =drDatos[position];
        imgRes.setImageResource(drOb.iImagenmini);
        TxtVwNombreRes.setText(drOb.sRes);
        TxtVwTipoRes.setText(drOb.sTRes);
//        TxtVwDirreccion.setText(drOb.sDir);
//        TxtVwPlaza.setText(drOb.sPlaza);
//        TxtVwTel.setText(drOb.sTel);



        return vFila;
    }
}
