package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HashTableService {

    private final HashTableRowEntityRepo rowRepo;
    private final HashTableCellEntityRepo cellRepo;
    private final int TABLE_SIZE = 10;
    @Autowired
    public HashTableService(HashTableRowEntityRepo rowRepo, HashTableCellEntityRepo cellRepo) {
        this.rowRepo = rowRepo;
        this.cellRepo = cellRepo;
    }
    public int f(String data) {
        int hashvalue = 0;
        for (int i = 0; i < data.length(); i++) {
            hashvalue = ((hashvalue + data.charAt(i))* 31 ) % TABLE_SIZE;
        }
        return hashvalue;
    }
    public void add(String data){
        int value = f(data);
        HashTableRowEntity row=getOrCreateRow(value);
        if (containsInRow(row, data)){
            System.out.println(data + ">> Already exists in the HashTable !!");
            return;
        }
        HashTableCellEntity newCellintheTable = new HashTableCellEntity();
        newCellintheTable.setData(data);
        newCellintheTable.setRow(row);
        if (row.getFirstCell()==null){
            row.setFirstCell(newCellintheTable);
        }
        else {
            HashTableCellEntity current = row.getFirstCell();
            while (current.getNext()!=null){
                current=current.getNext();
            }
            current.setNext(newCellintheTable);
        }
        row.getCells().add(newCellintheTable);
        cellRepo.save(newCellintheTable);
        rowRepo.save(row);
    }
    public boolean remove(String data){
        int value = f(data);
        Optional<HashTableRowEntity> rowOptional=rowRepo.findByIndex(value);
        if (rowOptional.isPresent()){
            HashTableRowEntity row = rowOptional.get();
            HashTableCellEntity currentCell = row.getFirstCell();
            HashTableCellEntity previousCell = null;
            while (currentCell!=null){
                if (currentCell.getData().equals(data)){
                    if (previousCell!=null){
                        row.setFirstCell(currentCell.getNext());
                    }
                    else {
                        previousCell.setNext(currentCell.getNext());
                    }
                    row.getCells().remove(currentCell);
                    cellRepo.delete(currentCell);
                    rowRepo.save(row);
                    return true;
                }
            }




        }
        return false;
    }
    public boolean contains(String data){
        int value = f(data);
        Optional<HashTableRowEntity> rowOptional=rowRepo.findByIndex(value);
        if (rowOptional.isPresent()){
            HashTableCellEntity cell =rowOptional.get().getFirstCell();
            while (cell!=null){
                if (cell.getData().equals(data)){
                    return true;
                }
                cell=cell.getNext();
            }
        }
        return false;
    }
    private HashTableRowEntity getOrCreateRow(int index) {
        return rowRepo.findByIndex(index).orElseGet(() -> {
            HashTableRowEntity newRow = new HashTableRowEntity();
            newRow.setIndex(index);
            rowRepo.save(newRow);
            return newRow;
        });
    }

    private  boolean containsInRow(HashTableRowEntity row, String data) {
        HashTableCellEntity cell = row.getFirstCell();
        while (cell!=null){
            if (cell.getData().equals(data)){
                return true;
            }
            cell=cell.getNext();
        }
        return false;
    }
    private HashTableCellEntity findLastCell(HashTableRowEntity row) {
        HashTableCellEntity cell = row.getFirstCell();
        if (cell== null){
            return null;
        }
        while (cell.getNext()!=null){
            cell=cell.getNext();
        }
        return cell;
    }
    private void printRow(HashTableRowEntity row) {
        HashTableCellEntity cell = row.getFirstCell();
        while (cell!=null){
            System.out.println(cell.getData()+" ");
            cell=cell.getNext();
        }
        System.out.println();
    }
    public int size(){
        return (int) cellRepo.count();
    }
    public List<HashTableRowEntity> getTable() {
        return rowRepo.findAll();
    }
    public void print(){
        for (int i = 0; i < TABLE_SIZE; i++) {
            Optional<HashTableRowEntity> rowOptional=rowRepo.findByIndex(i);
            if (rowOptional.isPresent()){
                HashTableRowEntity row = rowOptional.get();
                System.out.println("Row "+ i + ": ");
                HashTableCellEntity cell = row.getFirstCell();
                while (cell != null){
                    System.out.print(cell.getData() + " ");
                    cell = cell.getNext();
                }
                System.out.println();
                }
            else {
                System.out.println("Row " + i + ": ");
            }
        }

    }

}


