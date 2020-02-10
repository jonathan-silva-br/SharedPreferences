package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private Button btnSalvar;
    private TextInputEditText txtNome;
    private TextView textResultado;
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSalvar = findViewById(R.id.btnSalvar);
        txtNome = findViewById(R.id.textNome);
        textResultado = findViewById(R.id.textResultado);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                SharedPreferences.Editor editor = preferences.edit(); //Retorna um objeto do tipo Editor que permite alterar o arquivo para salvar.

                if(txtNome.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Preencha o nome", Toast.LENGTH_LONG);
                }else{
                    String nome = txtNome.getText().toString();
                    editor.putString("nome", nome); //chave e o parâmetro que desejo salvar
                    editor.commit(); //salva os dados
                    textResultado.setText("Olá " + nome);
                }

            }
        });

        //Recupera dados salvos
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
        //Verifica se a chave existe no arquivo xml preferences
        if(preferences.contains("nome")){
            String nome = preferences.getString("nome", "usuário não definido");
            textResultado.setText("Olá, " + nome);
        }else{
            textResultado.setText("Olá, usuário não definido");
        }
    }
}
