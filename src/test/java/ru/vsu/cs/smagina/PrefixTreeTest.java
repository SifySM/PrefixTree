package ru.vsu.cs.smagina;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PrefixTreeTest {
    private final PrefixTree testTree = new PrefixTree();

    @Test
    void testAddStringAndFindAll() {
        minInitialization();
        testTree.add("abd");
        Collection<String> trueResult = new HashSet<>(Arrays.asList("abb", "aba", "abd", "abc"));
        assertEquals(testTree.findAll("ab"), trueResult);
    }

    @Test
    void testFindString() {
        minInitialization();
        assertTrue(testTree.find("abc"));
    }

    @Test
    void testFindStringNotCorrect() {
        minInitialization();
        assertTrue(testTree.find(" "));
    }

    @Test
    void testRemove() {
        minInitialization();
        testTree.remove("aba");
        Collection<String> trueResult = new HashSet<>(Arrays.asList("abb", "abc"));
        assertEquals(testTree.findAll("ab"), trueResult);
    }

    private void minInitialization() {
        testTree.add("aba");
        testTree.add("abb");
        testTree.add("abc");
    }
}