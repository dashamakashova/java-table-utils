package com.github.dashamakashova.tableutils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableGrouper<T> {
    private Table<T> table;

    public TableGrouper(Table<T> table) {
        this.table = table;
    }

    public Map<T, Table<T>> groupByColumn(int columnIndex) {
        Map<T, List<List<T>>> groupedData = new HashMap<>();

        for (int i = 0; i < table.getRowCount(); i++) {
            List<T> row = table.getRow(i);
            T key = row.get(columnIndex);
            groupedData.computeIfAbsent(key, k -> new ArrayList<>())
                    .add(row);
        }

        Map<T, Table<T>> result = new HashMap<>();
        for (Map.Entry<T, List<List<T>>> entry : groupedData.entrySet()) {
            result.put(entry.getKey(), new Table<>(entry.getValue()));
        }
        return result;
    }

    public Map<String, List<List<T>>> groupByRow() {
        Map<String, List<List<T>>> groupedData = new HashMap<>();

        for (int i = 0; i < table.getRowCount(); i++) {
            List<T> row = table.getRow(i);
            String key = row.toString();
            groupedData.computeIfAbsent(key, k -> new ArrayList<>())
                    .add(row);
        }

        return groupedData;
    }
}