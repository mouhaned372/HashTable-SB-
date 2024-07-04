package com.example.demo;

public class HashTableCellEntity {
    private int id;
    private String data;
    public HashTableCellEntity next ;
    private HashTableRowEntity row;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public HashTableRowEntity getRow() {
        return row;
    }
    public void setRow(HashTableRowEntity row) {
        this.row=row;
    }

    public HashTableCellEntity getNext(
    ) {
        return next;
    }
    public void setNext(HashTableCellEntity next) {
        this.next = next;
    }

}
