package com.example.mezqu.guia1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView lblEntrada, lblResultado;
    private ArrayList<Button> lstNumeros;
    private Button btnAc, btnDel, btnIgual,btnpunto,btnp1,btnp2;
    private ArrayList<Button> lstOperaciones;
    private ArrayList<Button> lstpp;
    private Boolean opPulsada = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setTitle("MM13I04005");

        lblEntrada = findViewById(R.id.lblEntrada);
        lblResultado = findViewById(R.id.lblResultado);
        btnAc = findViewById(R.id.btnAC);
        btnDel = findViewById(R.id.btnDEL);
        btnIgual = findViewById(R.id.btnIgual);
        btnp1=findViewById(R.id.btnp1);
        btnp2=findViewById(R.id.btnp2);
        btnpunto=findViewById(R.id.btnpunto);

        //numeros aqui se agregaran los restantes
        lstNumeros = new ArrayList<>();
        lstNumeros.add( (Button) findViewById(R.id.btn9));
        lstNumeros.add((Button) findViewById(R.id.btn8));
        lstNumeros.add((Button) findViewById(R.id.btn7));
        lstNumeros.add((Button) findViewById(R.id.btn6));
        lstNumeros.add((Button) findViewById(R.id.btn5));
        lstNumeros.add((Button) findViewById(R.id.btn4));
        lstNumeros.add((Button) findViewById(R.id.btn3));
        lstNumeros.add((Button) findViewById(R.id.btn2));
        lstNumeros.add((Button) findViewById(R.id.btn1));
        lstNumeros.add((Button) findViewById(R.id.btn0));

        
        //operaciones matematicas aqui se agregaran las restantes
        lstOperaciones = new ArrayList<>();
        lstOperaciones.add((Button) findViewById(R.id.btnSUM));
        lstOperaciones.add((Button) findViewById(R.id.btnRES));
        lstOperaciones.add((Button) findViewById(R.id.btnMUL));
        lstOperaciones.add((Button) findViewById(R.id.btnDIV));


        btnAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lblEntrada.setText("");
                lblResultado.setText("");
                opPulsada=true;
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                eliminarUltimo();
            }

        });
        btnIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //evalua la expresion dada en la entrada y lo muestra en txtResultado
                lblResultado.setText(Parser.evaluar(lblEntrada.getText().toString()));
            }
        });

        //llamo las funciones que inicializan los botones de numeros y op mat
        initNumeros();
        initOperaciones();

    }
    private void initNumeros(){
        //recorre todos los botones en la lista y les agrega eventos Onclick
        for (final Button btn:lstNumeros){
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //cada vez que pulse un numero lo concatena al texto
                    lblEntrada.setText(lblEntrada.getText().toString() + btn.getText().toString());
                    opPulsada=false;
                }
            });
        }

    }

    private void initOperaciones(){
        for(final Button btn:lstOperaciones){
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!opPulsada){
                        lblEntrada.setText(lblEntrada.getText().toString() + btn.getText().toString());
                        opPulsada=true;
                    }
                }
            });
        }

    }


    private void eliminarUltimo(){
        //elimina el ultimo caracter en el TextView
        String str = lblEntrada.getText().toString();//obtento el texto del TextView
        if (str != null && str.length() > 0 ) {// verifico que no sea nulo y que tenga mas de 1 caracter
            str = str.substring(0, str.length() - 1); // saco una subcadena del texto total - 1 (esto elimina el ultimo)
            if(str.length()>0)//si la longitud ya cortada es mayor a cero
                opPulsada = esOperacion((str.substring(str.length()-1,str.length())));//evaluo si es operacion
            else//si es menor a cero, es decir esta vacio
                opPulsada = true;//guardo como pulsado para evitar poner op matematicas al inicio
        }
        lblEntrada.setText(str);
    }

    private boolean esOperacion(String txt){//evalua si es operacion matematica
        for (final Button btn:lstOperaciones){//revizo en la lista de botones
            if(btn.getText().equals(txt)){//comparo si el texto que envio es igual al texto de los botones '+' == '+' -> true
                return true;
            }
        }
        return false;//si no hay ningun texto que coincida entonces no es op matematica
    }
}
