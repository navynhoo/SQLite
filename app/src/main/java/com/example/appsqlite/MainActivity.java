package com.example.appsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;

    EditText txtEmail, txtSenha, txtConfirmaSenha, txtCpf, txtTelefone, txtRg, txtNome;
    Button btnRegistar, btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        txtEmail = findViewById(R.id.idEmail);
        txtSenha = findViewById(R.id.idLoginSenha);
        txtCpf = findViewById(R.id.idCpf);
        txtRg = findViewById(R.id.idRg);
        txtNome = findViewById(R.id.idNome);
        txtTelefone = findViewById(R.id.idTelefone);
        txtConfirmaSenha = findViewById(R.id.idConfirmarSenha);

        btnLogin = findViewById(R.id.idBtnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        btnRegistar = findViewById(R.id.idBtnRegistrar);

        btnRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, senha, cpf, nome, rg, telefone, confirmaSenha;

                email = txtEmail.getText().toString();
                senha = txtSenha.getText().toString();
                cpf = txtCpf.getText().toString();
                nome = txtNome.getText().toString();
                rg= txtRg.getText().toString();
                telefone = txtTelefone.getText().toString();
                confirmaSenha = txtConfirmaSenha.getText().toString();




                if (email.equals("") || senha.equals("") || confirmaSenha.equals("")) {
                    Toast.makeText(getApplicationContext(), "Favor inserir valores!!", Toast.LENGTH_SHORT).show();
                } else {
                    if (senha.equals(confirmaSenha)) {
                        Boolean checharEmail = db.validarEmail(email);
                        if (checharEmail == true) {
                            Boolean inserir = db.insert(String nome, String email, String senha, String cpf, String rg, String confirmaSenha, String telefone);
                            if (inserir == true) {
                                Toast.makeText(getApplicationContext(), "Registro inserido com sucesso!!!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Email inserido já existe!!", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Senha não confere!!!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }''

}
