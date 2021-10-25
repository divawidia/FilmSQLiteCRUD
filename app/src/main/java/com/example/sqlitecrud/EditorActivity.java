package com.example.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlitecrud.helper.Helper;

public class EditorActivity extends AppCompatActivity {

    private EditText editJudul, editTahun, editDurasi, editPemeran, editSinopsis;
    private Button btnSave;
    private Helper db = new Helper(this);
    private String id, judul, tahun, durasi, pemeran, sinopsis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        editJudul = findViewById(R.id.edit_judul);
        editTahun = findViewById(R.id.edit_tahun);
        editDurasi = findViewById(R.id.edit_durasi);
        editPemeran = findViewById(R.id.edit_pemeran);
        editSinopsis = findViewById(R.id.edit_sinopsis);
        btnSave = findViewById(R.id.btn_save);

        id = getIntent().getStringExtra("id");
        judul = getIntent().getStringExtra("judul");
        tahun = getIntent().getStringExtra("tahun");
        durasi = getIntent().getStringExtra("durasi");
        pemeran = getIntent().getStringExtra("pemeran");
        sinopsis = getIntent().getStringExtra("sinopsis");

        if (id == null || id.equals("")){
            setTitle("Tambah film");
        }else {
            setTitle("Edit film");
            editJudul.setText(judul);
            editTahun.setText(tahun);
            editDurasi.setText(durasi);
            editPemeran.setText(pemeran);
            editSinopsis.setText(sinopsis);
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (id == null || id.equals("")){
                        save();
                    }else {
                        edit();
                    }
                }catch (Exception e){
                    Log.e("Saving", e.getMessage());
                }
            }
        });
    }

    private void save(){
        if (String.valueOf(editJudul.getText()).equals("") || String.valueOf(editTahun.getText()).equals("") || String.valueOf(editDurasi.getText()).equals("") || String.valueOf(editPemeran.getText()).equals("") || String.valueOf(editSinopsis.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Silahkan isi semua data!", Toast.LENGTH_SHORT).show();
        }else {
            db.insert(editJudul.getText().toString(), editTahun.getText().toString(), editDurasi.getText().toString(), editPemeran.getText().toString(), editSinopsis.getText().toString());
            finish();
        }
    }
    private void edit(){
        if (String.valueOf(editJudul.getText()).equals("") || String.valueOf(editTahun.getText()).equals("") || String.valueOf(editDurasi.getText()).equals("") || String.valueOf(editPemeran.getText()).equals("") || String.valueOf(editSinopsis.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Silahkan isi semua data!", Toast.LENGTH_SHORT).show();
        }else {
            db.update(Integer.parseInt(id), editJudul.getText().toString(), editTahun.getText().toString(), editDurasi.getText().toString(), editPemeran.getText().toString(), editSinopsis.getText().toString());
            finish();
        }
    }
}