package com.example.fabricio.lembretes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddNewLembreteActivity extends AppCompatActivity {

    public static final String TITULO_KEY = "titulo";
    public static final String CONTEUDO_KEY = "conteudo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_lembrete);
    }

    public void saveLembrete(View view) {
        EditText tituloEditText = findViewById(R.id.titulo_edittext_activity_add_new_lembrete);
        EditText conteudoEditText = findViewById(R.id.conteudo_edittext_activity_add_new_lembrete);

        Intent intent = new Intent();
        intent.putExtra(TITULO_KEY, tituloEditText.getText().toString());
        intent.putExtra(CONTEUDO_KEY, conteudoEditText.getText().toString());

        setResult(RESULT_OK, intent);

        finish();
    }
}
