package com.example.teste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.teste.db.ControlaBanco;
import com.example.teste.db.CriaBanco;
import com.example.teste.db.Dados_banco;

import java.net.InetAddress;

public class MainActivity extends AppCompatActivity {

    //private String ip_sock = "192.168.1.10";
    //private int porta_sock = 2004;

    private String ip_sock;
    private int porta_sock;
    private socket sock;
    private String ip;
    private String https;
    private WebView browser;
    private ControlaBanco db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicia(); //aqui chama classe inicia que dara start ao codigo
    }
    private void ini_elements(){
        teste();
        browser = (WebView) findViewById(R.id.web_cam);
        browser.getSettings().setLoadWithOverviewMode(true);
        browser.getSettings().setUseWideViewPort(true);
        dados dados = new dados();
        ip = "http://" +dados.getIP() + dados.getPorta();
        ImageView btn_prop = (ImageView) findViewById( R.id.imageView );
        btn_prop.setClickable( true );
        btn_prop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this, propriets.class);
                Bundle dados = new Bundle();
                dados.putString( "id", "idlu" ); //passa string entre as telas
                intent.putExtras(dados);
                startActivityForResult(intent, 1);
            }
        });
        //sock.ini();
        //cam cam = new cam();
        //cam.Start();
    }

    public void inicia(){

        //inicia thread para criar conexão socket em background
       // new Thread(new socket.ClientThread()).start();
        ini_elements();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("LuaTeste", "resultado");
        // Verfica se o requestCode é o mesmo que foi passado
        if(requestCode==1 && resultCode == RESULT_OK) {
            String ip=data.getStringExtra("IP");
            String porta=data.getStringExtra("PORTA");
            https = "http://" + ip + ":"+porta+"/camera.php";

            browser.loadUrl(https);

        }
    }

    public void teste(){

        ControlaBanco db = new ControlaBanco(getBaseContext());
        //db.carregaDados();
        //db.rotornaIP(5);
        //db.update(1,"teste","mudei");
    }
    @Override
    public void onStart() {

        db = new ControlaBanco(getBaseContext());
        Dados_banco dados_carregados = db.rotornaIP(1);
        https = "http://" + dados_carregados.getIp() + ":" + dados_carregados.getPorta() + "/camera.php";
        browser.loadUrl(https);

        super.onStart();
    }
}
