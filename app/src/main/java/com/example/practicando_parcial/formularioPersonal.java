package com.example.practicando_parcial;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class formularioPersonal extends AppCompatActivity {

//    TextView genero;
    Spinner comboGeneros;
    public EditText etUsuario, etContrasenha, etDni, chkMascota;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formulario_personal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etUsuario = findViewById(R.id.txtUsuarioA2);
        etContrasenha = findViewById(R.id.txtContrasenhaA2);
        etDni = findViewById(R.id.txtDni);
//        etFNacimiento = findViewById(R.id.txtFNacimiento);
//        chkMascota = findViewById(R.id.txtCorreo);

//        genero = (TextView) findViewById(R.id.spinner);
        comboGeneros = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.combo_genero, android.R.layout.simple_spinner_item);
        comboGeneros.setAdapter(adapter);
    }

    public void regresar(View view){
        Intent i = new Intent(this, MainActivity.class);

        startActivity(i);
        finish();
    }

    public void registrar(View view){
//        if(verificarNoVacios()){
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("usuario", etUsuario.getText().toString());  // envio de datos
            i.putExtra("contrasenha", etContrasenha.getText().toString());  // envio de datos

            startActivity(i);
            finish();
//        }

    }

    public boolean verificarNoVacios(){
        if(!(etUsuario.getText().toString().equals("rasel"))){
            Toast.makeText(this, "Hay datos vacios", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}