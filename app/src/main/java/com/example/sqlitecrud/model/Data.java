package com.example.sqlitecrud.model;

import java.util.PrimitiveIterator;

public class Data {
    private String id, judul, tahun, durasi, pemeran, sinopsis;

    public Data(){

    }

    public Data(String id, String judul, String tahun, String durasi, String pemeran, String sinopsis){
        this.id = id;
        this.judul = judul;
        this.tahun = tahun;
        this.durasi = durasi;
        this.pemeran = pemeran;
        this.sinopsis = sinopsis;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }
    public String getDurasi() {
        return durasi;
    }

    public void setDurasi(String durasi) {
        this.durasi = durasi;
    }
    public String getPemeran() {
        return pemeran;
    }

    public void setPemeran(String pemeran) {
        this.pemeran = pemeran;
    }
    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
}
