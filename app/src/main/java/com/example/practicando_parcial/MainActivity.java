package com.example.practicando_parcial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public TextView tv1;
    public EditText etContrasenha, etUsuario;

    String usuario, contrasenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tv1 = findViewById(R.id.txtTitulo);
        String R_Usuario = getIntent().getStringExtra("usuario");
        String R_Contrasenha = getIntent().getStringExtra("contrasenha");

        usuario = R_Usuario;
        contrasenha = R_Contrasenha;

        if(usuario == null && contrasenha == null){
            usuario="rasel";
            contrasenha="1234";
        }
//        tv1.setText("datos recibidos: "+ R_Usuario + ","+R_Contrasenha);

        etContrasenha = findViewById(R.id.txtContrasenha);
        etUsuario = findViewById(R.id.txtUsuario);

    }

    public void ingresar(View view){
        if(verificar(usuario, contrasenha)){
            Intent i = new Intent(this, juegoMichi.class);
            i.putExtra("usuario", etUsuario.getText().toString());  // envio de datos
            i.putExtra("contrasenha", etContrasenha.getText().toString());  // envio de datos

            startActivity(i);
            finish();
        }

    }

    public void registrar(View view){
        Intent i = new Intent(this, formularioPersonal.class);

        startActivity(i);
        finish();

    }

    public boolean verificar(String usuario, String contrasenha){
        if(!(etUsuario.getText().toString().equals(usuario))){
            Toast.makeText(this, "Usuario incorrecto", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!(etContrasenha.getText().toString().equals(contrasenha))){
            Toast.makeText(this, "Contrasenha incorrecta", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}