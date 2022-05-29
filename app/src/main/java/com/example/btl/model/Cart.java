package com.example.btl.model;

public class Cart {
    String idsp;
    String tensp;
    long giasp;
    byte[] hinhsp;
    int soluong;

    public Cart() {
    }

    public String getIdsp() {
        return idsp;
    }

    public void setIdsp(String idsp) {
        this.idsp = idsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public long getGiasp() {
        return giasp;
    }

    public void setGiasp(long giasp) {
        this.giasp = giasp;
    }

    public byte[] getHinhsp() {
        return hinhsp;
    }

    public void setHinhsp(byte[] hinhsp) {
        this.hinhsp = hinhsp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
