package com.example.teste;

import android.text.Editable;

public class dados {

    private static Editable ip = null;
    private static Editable porta;


    public Editable getIP(){
        return ip;
    }
    public Editable getPorta() {
        return porta;
    }

    public void setIP(Editable ip){
        ip = this.ip;
    }
    public void setPorta(Editable porta){
        porta = this.porta;
    }


}
