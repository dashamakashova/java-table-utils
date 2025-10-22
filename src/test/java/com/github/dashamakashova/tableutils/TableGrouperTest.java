package com.github.dashamakashova.tableutils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

class TableGrouperTest {
    private Table<String> table;
    private TableGrouper<String> grouper;

    @BeforeEach
    void setUp() {
        table = new Table<>();
        table.addRow(Arrays.asList("Apple", "Fruit", "10"));
        table.addRow(Arrays.asList("Banana", "Fruit", "15"));
        table.addRow(Arrays.asList("Carrot", "Vegetable", "8"));
        table.addRow(Arrays.asList("Potato", "Vegetable", "5"));

        grouper = new TableGrouper<>(table);
    }

    @Test
    void testGroupByColumn() {
        Map<String, Table<String>> grouped = grouper.groupByColumn(1);

        assertEquals(2, grouped.size());
        assertTrue(grouped.containsKey("Fruit"));
        assertTrue(grouped.containsKey("Vegetable"));

        Table<String> fruits = grouped.get("Fruit");
        assertEquals(2, fruits.getRowCount());
        assertEquals("Apple", fruits.getCell(0, 0));

        Table<String> vegetables = grouped.get("Vegetable");
        assertEquals(2, vegetables.getRowCount());
        assertEquals("Carrot", vegetables.getCell(0, 0));
    }

    @Test
    void testGroupByRow() {
        Map<String, List<List<String>>> grouped = grouper.groupByRow();

        assertEquals(4, grouped.size()); // 4 уникальные строки

        for (List<List<String>> rows : grouped.values()) {
            assertEquals(1, rows.size()); // каждая группа содержит 1 строку
        }
    }

    @Test
    void testGroupByColumnWithNumbers() {
        Table<Integer> numberTable = new Table<>();
        numberTable.addRow(Arrays.asList(1, 10));
        numberTable.addRow(Arrays.asList(2, 20));
        numberTable.addRow(Arrays.asList(1, 30));

        TableGrouper<Integer> numberGrouper = new TableGrouper<>(numberTable);
        Map<Integer, Table<Integer>> grouped = numberGrouper.groupByColumn(0);

        assertEquals(2, grouped.size());
        assertTrue(grouped.containsKey(1));
        assertTrue(grouped.containsKey(2));

        assertEquals(2, grouped.get(1).getRowCount());
        assertEquals(1, grouped.get(2).getRowCount());
    }

    @Test
    void testGroupByEmptyTable() {
        Table<String> emptyTable = new Table<>();
        TableGrouper<String> emptyGrouper = new TableGrouper<>(emptyTable);

        Map<String, Table<String>> grouped = emptyGrouper.groupByColumn(0);
        assertTrue(grouped.isEmpty());

        Map<String, List<List<String>>> groupedRows = emptyGrouper.groupByRow();
        assertTrue(groupedRows.isEmpty());
    }
}