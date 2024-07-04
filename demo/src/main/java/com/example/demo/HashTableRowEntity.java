package com.example.demo;

import java.util.LinkedList;
import java.util.List;

public class HashTableRowEntity {
    int id;
    int index;
    HashTableCellEntity firstCell;
    private List<HashTableCellEntity> cells=new LinkedList<>();

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public int getIndex() {
        return index;
    }
    public HashTableCellEntity getFirstCell(){
        return firstCell;
    }
    public void setFirstCell(HashTableCellEntity firstCell) {
        this.firstCell=firstCell;

    }

    public List<HashTableCellEntity> getCells() {
        return cells;
    }
    public void setCells(List<HashTableCellEntity> cells) {
        this.cells = cells;
    }

}

