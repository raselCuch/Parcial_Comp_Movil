package com.example.practicando_parcial;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class juegoMichi extends AppCompatActivity {

    public TextView tv1;
    boolean jugador1= true;

    private final Button[][] buttons = new Button[3][3];
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_juegomichi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tv1 = findViewById(R.id.txtRecepcion);
        String R_Usuario = getIntent().getStringExtra("usuario");
//        String R_Contrasenha = getIntent().getStringExtra("contrasenha");
        tv1.setText("hola: "+ R_Usuario + " sea bienvenido al juego de michi");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button" + (i + 1) + (j + 1);
                @SuppressLint("DiscouragedApi") int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
            }
        }
        enableImmersiveMode();
    }

    private void enableImmersiveMode() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }


    public void regresar(View view){
        Intent i = new Intent(this, MainActivity.class);

        startActivity(i);
        finish();
    }

    public void clickBoton(View view) {
        Button button = (Button) view;
//        String buttonID = getResources().getResourceEntryName(button.getId());

        if(jugador1){
            button.setText( "X" );//: "X"
        }else{
            button.setText( "0" );//: "X"
        }
        jugador1 = !jugador1;
        button.setEnabled(false);
    }


    public void refrescar(View view){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(" ");
//                buttons[i][j].setEnabled(true);
            }
        }

        // Restablecer el turno del jugador
//        jugador1 = true;
    }
}