package com.polinema.tracerstudy;

public class Config {
    public static final String URL_GET_ALL = "http://192.168.43.180/tracerStudyApp/selectAll.php";
    public static final String URL_GET_ID = "http://192.168.43.180/tracerStudyApp/selectId.php?id=";
    public static final String URL_LOGIN = "http://192.168.43.180/tracerStudyApp/login.php";
    public static final String URL_SEARCH = "http://192.168.43.180/tracerStudyApp/cari.php?nama=";
    public static final String URL_DATA = "http://192.168.43.180/tracerStudyApp/data.php";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_EMP_ID = "id";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAMA = "name";
    public static final String TAG_NIM = "nim";
    public static final String TAG_PRODI = "prodi";
    public static final String TAG_TAHUNMASUK = "tahun_masuk";
    public static final String TAG_TAHUNLULUS = "tahun_lulus";
    public static final String TAG_JUDULSKRIPSI = "judul_skripsi";
    public static final String TAG_EMAIL = "email";
    public static final String TAG_ALAMAT = "alamat";
    public static final String TAG_GAMBAR = "gambar";

    //ID alumni
    public static final String MHS_ID = "mhs_id";
}
