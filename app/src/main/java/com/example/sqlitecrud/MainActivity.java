package com.example.sqlitecrud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.sqlitecrud.adapter.Adapter;
import com.example.sqlitecrud.helper.Helper;
import com.example.sqlitecrud.model.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    AlertDialog.Builder dialog;
    List<Data> list = new ArrayList<>();
    Adapter adapter;
    Helper db = new Helper(this);
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Helper(getApplicationContext());
        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        listView = findViewById(R.id.list_item);
        adapter = new Adapter(MainActivity.this, list);
        listView.setAdapter((ListAdapter) adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long l) {
                final String id = list.get(position).getId();
                final String judul = list.get(position).getJudul();
                final String tahun = list.get(position).getTahun();
                final String durasi = list.get(position).getDurasi();
                final String pemeran = list.get(position).getPemeran();
                final String sinopsis = list.get(position).getSinopsis();
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("judul", judul);
                                intent.putExtra("tahun", tahun);
                                intent.putExtra("durasi", durasi);
                                intent.putExtra("pemeran", pemeran);
                                intent.putExtra("sinopsis", sinopsis);
                                startActivity(intent);
                                break;
                            case 1:
                                db.delete(Integer.parseInt(id));
                                list.clear();
                                getData();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
    }
    private void getData(){
        ArrayList<HashMap<String, String>> rows = db.getAll();
        for (int i = 0; i<rows.size(); i++){
            String id = rows.get(i).get("id");
            String judul = rows.get(i).get("judul");
            String tahun = rows.get(i).get("tahun");
            String durasi = rows.get(i).get("durasi");
            String pemeran = rows.get(i).get("pemeran");
            String sinopsis = rows.get(i).get("sinopsis");

            Data data = new Data();
            data.setId(id);
            data.setJudul(judul);
            data.setTahun(tahun);
            data.setDurasi(durasi);
            data.setPemeran(pemeran);
            data.setSinopsis(sinopsis);
            list.add(data);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        getData();
    }
}