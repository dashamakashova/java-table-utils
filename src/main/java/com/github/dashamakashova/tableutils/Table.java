package com.github.dashamakashova.tableutils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Table<T> {
    private List<List<T>> data;
    private int rows;
    private int columns;

    public Table() {
        this.data = new ArrayList<>();
        this.rows = 0;
        this.columns = 0;
    }

    public Table(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.data = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<T> row = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                row.add(null);
            }
            data.add(row);
        }
    }

    public Table(List<List<T>> data) {
        this.data = new ArrayList<>();
        for (List<T> row : data) {
            this.data.add(new ArrayList<>(row));
        }
        this.rows = data.size();
        this.columns = data.isEmpty() ? 0 : data.get(0).size();
    }

    public void addRow(List<T> row) {
        if (columns == 0) {
            columns = row.size();
        } else if (row.size() != columns) {
            throw new IllegalArgumentException("Row size must match table columns");
        }
        data.add(new ArrayList<>(row));
        rows++;
    }

    public void setCell(int rowIndex, int columnIndex, T value) {
        checkRowIndex(rowIndex);
        checkColumnIndex(columnIndex);
        data.get(rowIndex).set(columnIndex, value);
    }

    // Геттеры
    public List<T> getRow(int rowIndex) {
        checkRowIndex(rowIndex);
        return new ArrayList<>(data.get(rowIndex));
    }

    public List<T> getColumn(int columnIndex) {
        checkColumnIndex(columnIndex);
        List<T> column = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            column.add(data.get(i).get(columnIndex));
        }
        return column;
    }

    public T getCell(int rowIndex, int columnIndex) {
        checkRowIndex(rowIndex);
        checkColumnIndex(columnIndex);
        return data.get(rowIndex).get(columnIndex);
    }

    public int getRowCount() { return rows; }
    public int getColumnCount() { return columns; }
    public List<List<T>> getData() {
        return data.stream()
                .map(ArrayList::new)
                .collect(Collectors.toList());
    }

    private void checkRowIndex(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= rows) {
            throw new IndexOutOfBoundsException("Row index out of bounds: " + rowIndex);
        }
    }

    private void checkColumnIndex(int columnIndex) {
        if (columnIndex < 0 || columnIndex >= columns) {
            throw new IndexOutOfBoundsException("Column index out of bounds: " + columnIndex);
        }
    }

    @Override
    public String toString() {
        return data.stream()
                .map(row -> row.stream()
                        .map(obj -> obj != null ? obj.toString() : "null")
                        .collect(Collectors.joining("\t")))
                .collect(Collectors.joining("\n"));
    }
}