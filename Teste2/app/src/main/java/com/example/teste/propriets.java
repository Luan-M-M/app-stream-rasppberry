package com.example.teste;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teste.db.ControlaBanco;
import com.example.teste.db.Dados_banco;
import com.example.teste.utils.Utils;
import com.google.android.material.snackbar.Snackbar;

public class propriets extends Activity {

    private EditText ip;
    private EditText porta;
    private String dadosSave;
    private String ip_final;
    private String porta_final;
    Bundle bundle;
    private ControlaBanco db;


    //cria a activity
    //aqui tambem defino os metodos Ex: uma Textview é aqui que eu usarei o findviewByid
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        bundle = new Bundle();
        setContentView(R.layout.propriets);
        dados dados = new dados();
        Utils utils = new Utils();
        ImageView btn_prop = (ImageView) findViewById( R.id.imageView );



        if (bundle.isEmpty() == false) {
            Log.d("LuaTeste","Iniciando dados salvos bundle!");
            // Restore value of members from saved state
            ip.setText(bundle.getString("IP"));
            porta.setText(bundle.getString("PORTA"));
        } else {
            // Probably initialize members with default values for a new instance
        }
        btn_prop.setClickable( true );
        btn_prop.setVisibility( View.VISIBLE );
        btn_prop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(propriets.this, MainActivity.class);
                Bundle dados = new Bundle();
                dados.putString( "id", "idlu" ); //passa string entre as telas
                intent.putExtras(dados);
                startActivity(intent);
            }
        });

        Button btn_salvar_prop = (Button) findViewById(R.id.btn_salvar_prop);
        btn_salvar_prop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ip.getText().toString().trim().isEmpty() || porta.getText().toString().trim().isEmpty()){
                    //aqui se for clicado em salvar e não tiver ip / porta
                    utils.Snake(v, "Informe um IP e uma PORTA!");
                } else {
                    ip_final = ip.getText().toString();
                    porta_final = porta.getText().toString();
                    bundle.putString("IP", ip_final);
                    bundle.putString("PORTA", porta_final);

                    //Log.d("teste", "ed");

                    // savedInstanceState.putInt("PORTA", Integer.parseInt(porta.getText().toString()));
                    // Log.d("teste",savedInstanceState.getString("IP"));
                    //executara caso exista dados nos inputs
                    //utils.Snake(v, "Informe um IP e um PORTA!");
                    Intent intent=new Intent();
                    intent.putExtra("IP",ip_final);
                    intent.putExtra("PORTA",porta_final);

                    ControlaBanco db = new ControlaBanco(getBaseContext());
                    String resultado;
                    db.update(1,porta_final,ip_final);
                    //resultado = db.insereDados(ip_final,porta_final);

                    //Log.d("LuaTeste", resultado);
                    //Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT);
                    setResult(RESULT_OK,intent);
                    //propriets.super.onSaveInstanceState(bundle);
                     //onBackPressed();
                    finish();
                }
            }
        });





    }

    //quando está iniciando a activity
    @Override
    public void onStart() {

        Log.d("LuaTeste", "esta iniciando");

        ip = (EditText)findViewById(R.id.editTextTextPersonName);
        porta = findViewById(R.id.editTextNumber);

        db = new ControlaBanco(getBaseContext());
        Dados_banco dados_carregados = db.rotornaIP(1);
        ip.setText(dados_carregados.getIp());
        porta.setText(dados_carregados.getPorta());

        super.onStart();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(bundle);

        Log.d("LuaTeste", "Onsaveinstace foi chamado");
        bundle.putString("IP", ip_final );
        bundle.putString("PORTA", porta_final );

        // call superclass to save any view hierarchy
        super.onSaveInstanceState(savedInstanceState);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(bundle);

        Log.d("LuaTeste", "onRestoreInstace Foi chamado");
        ip.setText(bundle.getString("IP"));
        porta.setText(bundle.getString("PORTA"));
    }

    @Override
    protected void onStop() {
        // call the superclass method first
        super.onStop();

        // save the note's current draft, because the activity is stopping
        // and we want to be sure the current note progress isn't lost.
        //ContentValues values = new ContentValues();
        //values.put(IP_SAVE, String.valueOf( ip.getText() ));
        //values.put(String.valueOf( PORTA_SAVE ), String.valueOf( porta.getText() ) );

    }

}
