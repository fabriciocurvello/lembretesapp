package com.example.fabricio.lembretes;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LembreteListActivity extends AppCompatActivity {

    private RecyclerView lembreteRecyclerView;

    private final int ADD_LEMBRETE_REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lembrete_list);

        lembreteRecyclerView = findViewById(R.id.recyclerview);

        List<Lembrete> lembreteNames = readLembretesFromFile();

        //Criar um Adapter para a RecyclerView
        LembreteAdapter adapter = new LembreteAdapter(lembreteNames);

        //Associar RecyclerView a um Adapter
        lembreteRecyclerView.setAdapter(adapter);

        //Dizer a "forma" da RecyclerView
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        lembreteRecyclerView.setLayoutManager(lm);

        lembreteRecyclerView.addItemDecoration(
                new DividerItemDecoration(this,
                        DividerItemDecoration.VERTICAL));
    }

    public void addLembrete(View view) {
        Intent intent = new Intent(this, AddNewLembreteActivity.class);
        startActivityForResult(intent, ADD_LEMBRETE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_LEMBRETE_REQUEST_CODE && resultCode == RESULT_OK){
            String titulo = data.getStringExtra(AddNewLembreteActivity.TITULO_KEY);
            String conteudo = data.getStringExtra(AddNewLembreteActivity.CONTEUDO_KEY);

            Lembrete newLembrete = new Lembrete(titulo, conteudo);

            LembreteAdapter adapter = (LembreteAdapter) lembreteRecyclerView.getAdapter();
            adapter.addItem(newLembrete);

            saveLembreteToFile(newLembrete);
        }
    }

    private void saveLembreteToFile(Lembrete lembrete) {
        File myFile = new File(getFilesDir(), "savedlembretes.txt");

        try {
            FileOutputStream outputStream = new FileOutputStream(myFile, true);
            outputStream.write(lembrete.toString().getBytes());
            outputStream.close();
        } catch (FileNotFoundException exception) {

        } catch (IOException exception) {

        }
    }

    public List<Lembrete> readLembretesFromFile() {
        File myFile = new File(getFilesDir(), "savedlembretes.txt");
        List<Lembrete> lembretes = new ArrayList<>();

        try {
            FileReader reader = new FileReader(myFile);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            while (line != null) {
                if (line.equals("#")){
                    String titulo = bufferedReader.readLine();
                    String conteudo = bufferedReader.readLine();
                    Lembrete newLembrete = new Lembrete(titulo, conteudo);
                    lembretes.add(newLembrete);
                }
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException exception) {

        } catch (IOException exception) {

        }

        return lembretes;
    }
}
