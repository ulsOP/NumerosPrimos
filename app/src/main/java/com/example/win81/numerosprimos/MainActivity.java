package com.example.win81.numerosprimos;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import static java.lang.Math.sqrt;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    private static final int id=1;
    public static final String NOTIFICACION= "NOTIFICACION";
    ProgressDialog progressDialog;
    private int progressStatus=0;
    private Handler handler=new Handler();
    //TextView resultado=(TextView) findViewById(R.id.textView2);
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button calcular=(Button) findViewById(R.id.button);
        calcular.setOnClickListener(this);


        Spinner spinner_colors = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource( this, R.array.color , android.R.layout.simple_spinner_item);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_colors.setAdapter(spinner_adapter);

        /* spinner_colors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                      //  resultado.setTextColor(Color.BLUE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }); */



        }
    public void list(){



       /* spinner_colors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         //   @Override
           // public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



*/

    }



    @Override
    public void onClick(View v) {

        EditText argumento=(EditText) findViewById(R.id.editText);

        primo(Integer.parseInt(argumento.getText().toString()));






    }

    public void primo(Integer numero){
        TextView resultado=(TextView) findViewById(R.id.textView2);

        resultado.setText("");
        int i=0, j=0, div=0,raiz=0;
        showProgressDialog(numero);
        for (i=2;i<numero;i++) {
            div=0; //variable para contar cuantas veces es el residuo de dividir es 0
            raiz=(int)sqrt(i);//la raiz del número a buscarle los primos
            for (j=1;j<=raiz;j++) { //ciclo para recorrer los numeros hasta la raiz de i (estos seran los divisores)
                if (i%j==0)//evalua la condicion de que el residuo de dividir i entre j es igual a cero
                    div++;// si la condicion anterior se cumple entonces entonces suma 1 a esta variable
            }
            if (div<=1){
                //Si existe más de un divisor, entonces el número no es primo
                resultado.append(i+" ");



                lanzarActivity(i);
            }

        }
    }
    public void showProgressDialog(int numero){

        final int a=numero;

        progressDialog= new ProgressDialog(this);
        progressDialog.setMax(100);
        progressDialog.setMessage("Buscando números primos");
        progressDialog.setTitle("Espere por favor");

        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.show();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (progressStatus <progressDialog.getMax()){
                            try{
                                Thread.sleep(a);
                                progressStatus += 3;
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                            handler.post(new Runnable() {
                                @Override
                                public void run() {

                                    progressDialog.setProgress(progressStatus);
                                }
                            });
                        }
                        progressDialog.dismiss();
                        progressStatus = 0;

                        progressDialog.setProgress(progressStatus);
                    }}
        ).start();

    }




    public void lanzarActivity(int i) {
        NotificationCompat.Builder notificacion;
        String no=String.valueOf(i);
        notificacion = new NotificationCompat.Builder(this);
        notificacion.setAutoCancel(true);

        notificacion.setSmallIcon(R.mipmap.ic_launcher);
        notificacion.setTicker("Nueva notificación");
        notificacion.setWhen(System.currentTimeMillis());
        notificacion.setContentTitle("Número primo encontrado!");
        notificacion.setContentText(String.valueOf(i));

        Intent intent=new Intent(MainActivity.this,SegundaActivity.class);
        intent.putExtra("NOTIFICACION",no);

        PendingIntent pendingIntent=PendingIntent.getActivity(MainActivity.this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        notificacion.setContentIntent(pendingIntent);
        NotificationManager mn=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mn.notify(id,notificacion.build());
    }



}

