package com.example.demo;

import java.util.List;

public class HashTableEntity {
private int id;
private int size;
private List<HashTableRowEntity> rows;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public List<HashTableRowEntity> getRows() {
        return rows;
    }
    public void setRows(List<HashTableRowEntity> rows) {
        this.rows = rows;
    }
}
