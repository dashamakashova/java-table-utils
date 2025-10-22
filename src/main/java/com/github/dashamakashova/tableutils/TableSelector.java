package com.github.dashamakashova.tableutils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class TableSelector<T> {
    private Table<T> table;

    public TableSelector(Table<T> table) {
        this.table = table;
    }

    public List<T> getRow(int rowIndex) {
        return table.getRow(rowIndex);
    }

    public List<T> getColumn(int columnIndex) {
        return table.getColumn(columnIndex);
    }

    public T getCell(int rowIndex, int columnIndex) {
        return table.getCell(rowIndex, columnIndex);
    }

    public Table<T> selectRows(List<Integer> rowIndices) {
        List<List<T>> selected = new ArrayList<>();
        for (int rowIndex : rowIndices) {
            selected.add(table.getRow(rowIndex));
        }
        return new Table<>(selected);
    }

    public Table<T> selectColumns(List<Integer> columnIndices) {
        List<List<T>> selected = new ArrayList<>();

        for (int i = 0; i < table.getRowCount(); i++) {
            List<T> row = table.getRow(i);
            List<T> newRow = new ArrayList<>();
            for (int colIndex : columnIndices) {
                newRow.add(row.get(colIndex));
            }
            selected.add(newRow);
        }

        return new Table<>(selected);
    }
}