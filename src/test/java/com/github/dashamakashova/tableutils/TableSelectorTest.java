package com.github.dashamakashova.tableutils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

class TableSelectorTest {
    private Table<String> table;
    private TableSelector<String> selector;

    @BeforeEach
    void setUp() {
        table = new Table<>();
        table.addRow(Arrays.asList("A", "Fruit", "10"));
        table.addRow(Arrays.asList("B", "Fruit", "15"));
        table.addRow(Arrays.asList("C", "Vegetable", "8"));
        table.addRow(Arrays.asList("D", "Vegetable", "5"));

        selector = new TableSelector<>(table);
    }

    @Test
    void testGetRow() {
        List<String> row = selector.getRow(2);
        assertEquals(Arrays.asList("C", "Vegetable", "8"), row);
    }

    @Test
    void testGetColumn() {
        List<String> column = selector.getColumn(1);
        assertEquals(Arrays.asList("Fruit", "Fruit", "Vegetable", "Vegetable"), column);
    }

    @Test
    void testGetCell() {
        assertEquals("B", selector.getCell(1, 0));
        assertEquals("Vegetable", selector.getCell(2, 1));
    }

    @Test
    void testSelectRows() {
        Table<String> selected = selector.selectRows(Arrays.asList(0, 2));
        assertEquals(2, selected.getRowCount());
        assertEquals("A", selected.getCell(0, 0));
        assertEquals("C", selected.getCell(1, 0));
    }

    @Test
    void testSelectColumns() {
        Table<String> selected = selector.selectColumns(Arrays.asList(0, 2));
        assertEquals(4, selected.getRowCount());
        assertEquals(2, selected.getColumnCount());
        assertEquals("A", selected.getCell(0, 0));
        assertEquals("10", selected.getCell(0, 1));
    }

    @Test
    void testSelectAllColumns() {
        Table<String> selected = selector.selectColumns(Arrays.asList(0, 1, 2));
        assertEquals(table.getRowCount(), selected.getRowCount());
        assertEquals(table.getColumnCount(), selected.getColumnCount());
    }
}