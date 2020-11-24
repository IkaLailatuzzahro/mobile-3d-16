package com.polinema.kuis2.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Alumni implements Parcelable {
    private String  nim, nama, prodi, tahunMasuk, tahunLulus;

    public Alumni(){
    }

    public Alumni(String nim, String nama, String prodi, String tahunMasuk, String tahunLulus) {
        this.nim = nim;
        this.nama = nama;
        this.prodi = prodi;
        this.tahunMasuk = tahunMasuk;
        this.tahunLulus = tahunLulus;
    }

    protected Alumni(Parcel in) {
        nim = in.readString();
        nama = in.readString();
        prodi = in.readString();
        tahunMasuk = in.readString();
        tahunLulus = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nim);
        dest.writeString(nama);
        dest.writeString(prodi);
        dest.writeString(tahunMasuk);
        dest.writeString(tahunLulus);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Alumni> CREATOR = new Creator<Alumni>() {
        @Override
        public Alumni createFromParcel(Parcel in) {
            return new Alumni(in);
        }

        @Override
        public Alumni[] newArray(int size) {
            return new Alumni[size];
        }
    };

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getTahunMasuk() {
        return tahunMasuk;
    }

    public void setTahunMasuk(String tahunMasuk) {
        this.tahunMasuk = tahunMasuk;
    }

    public String getTahunLulus() {
        return tahunLulus;
    }

    public void setTahunLulus(String tahunLulus) {
        this.tahunLulus = tahunLulus;
    }
}
