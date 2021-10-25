package com.example.sqlitecrud.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sqlitecrud.R;
import com.example.sqlitecrud.model.Data;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> lists;

    public Adapter(Activity activity, List<Data> lists){
        this.activity = activity;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null){
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null && inflater != null){
            convertView = inflater.inflate(R.layout.list_film, null);
        }
        if (convertView != null){
            TextView judul = convertView.findViewById(R.id.text_judul);
            TextView tahun = convertView.findViewById(R.id.text_tahun);
            TextView durasi = convertView.findViewById(R.id.text_durasi);
            TextView pemeran = convertView.findViewById(R.id.text_pemeran);
            TextView sinopsis = convertView.findViewById(R.id.text_sinopsis);
            Data data = lists.get(position);
            judul.setText(data.getJudul());
            tahun.setText(data.getTahun());
            durasi.setText(data.getDurasi());
            pemeran.setText(data.getPemeran());
            sinopsis.setText(data.getSinopsis());
        }

        return convertView;
    }
}
