package com.example.practicando_parcial;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    CheckBox checkBoxPerro, checkBoxGato, checkBoxLoro;

    public EditText etUsuario, etContrasenha, etDni, chkMascota;
    RadioGroup radioGroupEstadoCivil;


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
        radioGroupEstadoCivil = findViewById(R.id.radioGroup);
        checkBoxPerro = findViewById(R.id.checkBox);
        checkBoxGato = findViewById(R.id.checkBox3);
        checkBoxLoro = findViewById(R.id.checkBox2);


//        etFNacimiento = findViewById(R.id.txtFNacimiento);
//        chkMascota = findViewById(R.id.txtCorreo);

//        genero = (TextView) findViewById(R.id.spinner);
        comboGeneros = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.combo_genero, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        comboGeneros.setAdapter(adapter);

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

    public void registrar(View view){
        if(verificarNoVacios()){
            mostrarMensajeDatosIngresados();
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("usuario", etUsuario.getText().toString());  // envio de datos
            i.putExtra("contrasenha", etContrasenha.getText().toString());  // envio de datos

            startActivity(i);
            finish();
        }
    }

    public boolean verificarNoVacios(){
        if((etUsuario.getText().toString().isEmpty()
                || etContrasenha.getText().toString().isEmpty()
                || etDni.getText().toString().isEmpty())){
            Toast.makeText(this, "Hay campos vacios", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (radioGroupEstadoCivil.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Debe seleccionar un estado civil", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (comboGeneros.getSelectedItem().toString().equals("Seleccione")) {
            Toast.makeText(this, "Debe seleccionar un género", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!checkBoxPerro.isChecked() && !checkBoxGato.isChecked() && !checkBoxLoro.isChecked()) {
            Toast.makeText(this, "Debe seleccionar al menos una mascota", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void mostrarMensajeDatosIngresados(){
        String usuario = etUsuario.getText().toString();
        String contrasenha = etContrasenha.getText().toString();
        String dni = etDni.getText().toString();
        String genero = comboGeneros.getSelectedItem().toString();

        int selectedEstadoCivilId = radioGroupEstadoCivil.getCheckedRadioButtonId();
        RadioButton selectedEstadoCivil = findViewById(selectedEstadoCivilId);
        String estadoCivil = selectedEstadoCivil.getText().toString();

        StringBuilder mascotas = new StringBuilder();
        if (checkBoxPerro.isChecked()) mascotas.append("Perro ");
        if (checkBoxGato.isChecked()) mascotas.append("Gato ");
        if (checkBoxLoro.isChecked()) mascotas.append("Loro ");

        String mensaje = "Usuario: " + usuario + "\n" +
                "Contraseña: " + contrasenha + "\n" +
                "DNI: " + dni + "\n" +
                "Género: " + genero + "\n" +
                "Estado Civil: " + estadoCivil + "\n" +
                "Mascotas: " + mascotas.toString();

        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }
}