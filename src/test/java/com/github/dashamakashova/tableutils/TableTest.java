package com.github.dashamakashova.tableutils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

class TableTest {
    private Table<String> table;

    @BeforeEach
    void setUp() {
        table = new Table<>();
        table.addRow(Arrays.asList("Apple", "Fruit", "10"));
        table.addRow(Arrays.asList("Banana", "Fruit", "15"));
        table.addRow(Arrays.asList("Carrot", "Vegetable", "8"));
    }

    @Test
    void testTableCreation() {
        assertEquals(3, table.getRowCount());
        assertEquals(3, table.getColumnCount());
    }

    @Test
    void testGetRow() {
        List<String> row = table.getRow(1);
        assertEquals(Arrays.asList("Banana", "Fruit", "15"), row);
    }

    @Test
    void testGetColumn() {
        List<String> column = table.getColumn(1);
        assertEquals(Arrays.asList("Fruit", "Fruit", "Vegetable"), column);
    }

    @Test
    void testGetCell() {
        assertEquals("Carrot", table.getCell(2, 0));
        assertEquals("8", table.getCell(2, 2));
    }

    @Test
    void testSetCell() {
        table.setCell(0, 1, "Berry");
        assertEquals("Berry", table.getCell(0, 1));
    }

    @Test
    void testInvalidRowIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> table.getRow(5));
    }

    @Test
    void testInvalidColumnIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> table.getColumn(5));
    }

    @Test
    void testEmptyTable() {
        Table<String> emptyTable = new Table<>();
        assertEquals(0, emptyTable.getRowCount());
        assertEquals(0, emptyTable.getColumnCount());
    }
}