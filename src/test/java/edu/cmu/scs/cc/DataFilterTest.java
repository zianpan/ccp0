package edu.cmu.scs.cc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;


/**
 * Usage:
 * mvn test
 *
 * <p>You should pass all the provided test cases before you make a submission.
 *
 * <p>You will need to add more test cases.
 */
class DataFilterTest {

    @Test
    void sortRecords() {
        TreeMap<String, Integer> records = new TreeMap<>();
        records.put("a", 1);
        records.put("b", 1);
        records.put("c", 2);
        records.put("d", 3);
        records.put("e", 4);

        LinkedList<Entry<String, Integer>> res = DataFilter.sortRecords(records);

        LinkedHashMap<String, Integer> expectedResMap = new LinkedHashMap<>();
        expectedResMap.put("e", 4);
        expectedResMap.put("d", 3);
        expectedResMap.put("c", 2);
        expectedResMap.put("a", 1);
        expectedResMap.put("b", 1);

        LinkedList<Entry<String, Integer>> expectedRes = new LinkedList<>();

        for (Entry<String, Integer> entry: expectedResMap.entrySet()) {
            expectedRes.add(entry);
        }

        assertThat(res, equalTo(expectedRes));
    }

    @Test
    void getColumns() {
        assertTrue(Arrays.equals(
                DataFilter.getColumns("en Carnegie_Mellon_University 34 0"),
                new String[] {"en", "Carnegie_Mellon_University", "34", "0"}));
        assertTrue(Arrays.equals(
                DataFilter.getColumns("en User%3AK6ka 34 0"),
                new String[] {"en", "User:K6ka", "34", "0"}));
    }

    @Test
    void checkDataLength() {
        assertTrue(DataFilter.checkDataLength(
                DataFilter.getColumns("en Carnegie_Mellon_University 34 0")));
        assertFalse(DataFilter.checkDataLength(
                DataFilter.getColumns("en 34 0")));
        assertFalse(DataFilter.checkDataLength(
                DataFilter.getColumns("en Carnegie_Mellon_University 34 34 0")));
        assertFalse(DataFilter.checkDataLength(
                DataFilter.getColumns("en Carnegie_Mellon_University%2034 34 0")));
    }

    @Test
    void checkDomain() {
        assertTrue(DataFilter.checkDomain(
                DataFilter.getColumns("en Carnegie_Mellon_University 34 0")));
        assertTrue(DataFilter.checkDomain(
                DataFilter.getColumns("en.m Carnegie_Mellon_University 34 0")));
        assertFalse(DataFilter.checkDomain(
                DataFilter.getColumns("fr Carnegie_Mellon_University 34 0")));
    }

    @Test
    void checkSpecialPage() {
        assertTrue(DataFilter.checkSpecialPage(
                DataFilter.getColumns("en Carnegie_Mellon_University 34 0")));
        assertFalse(DataFilter.checkSpecialPage(
                DataFilter.getColumns("en Main_Page 34 0")));
        assertFalse(DataFilter.checkSpecialPage(
                DataFilter.getColumns("en - 34 0")));
        assertFalse(DataFilter.checkSpecialPage(
                DataFilter.getColumns("en %2D 34 0")));
    }

    @Test
    void checkPrefix() {
        assertTrue(DataFilter.checkPrefix(
                DataFilter.getColumns("en Carnegie_Mellon_University 34 0")));
        assertFalse(DataFilter.checkPrefix(
                DataFilter.getColumns("en User:K6ka 34 0")));
        assertFalse(DataFilter.checkPrefix(
                DataFilter.getColumns("en User%3AK6ka 34 0")));
        assertFalse(DataFilter.checkPrefix(
                DataFilter.getColumns("en User%3aK6ka 34 0")));
    }

    @Test
    void checkSuffix() {
        throw new RuntimeException("add test cases on your own");
    }

    @Test
    void checkFirstLetter() {
        throw new RuntimeException("add test cases on your own");
    }

    @Test
    void checkAllRules() {
        throw new RuntimeException("add test cases on your own");
    }
}
