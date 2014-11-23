/**
 * Copyright 2014 Umar Maqsud
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.umaqsud.regxlar;

import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.junit.Ignore;
import org.junit.Test;

import com.umaqsud.regxlar.random.SeedRandomGenerator;

public class RegxlarTest {

    @Test
    public void testGenereateNext() {
        String reqexp = "ab+cd+e";
        Regxlar regxlar = new Regxlar(reqexp);

        Pattern pattern = Pattern.compile(reqexp);

        for (int i = 0; i < 1000; i++) {
            String random = regxlar.generateNext();
            assertTrue(pattern.matcher(random).matches());
        }

        for (int i = 0; i < 1000; i++) {
            String random = regxlar.generateNext(new SeedRandomGenerator(Math.random()), Regxlar.DEFAULT_MAX_LENGTH);
            assertTrue(pattern.matcher(random).matches());
        }

        for (int i = 0; i < 1000; i++) {
            String random = regxlar.generateNext(new SeedRandomGenerator(Math.random()));
            assertTrue(pattern.matcher(random).matches());
        }
    }

    @Test
    public void testRepeatability() throws Exception {

        String regexp = "ab+cd+e";

        // same Regxlar instance

        Regxlar regxlar = new Regxlar(regexp);

        for (int i = 0; i < 1000; i++) {
            double probability = Math.random();
            String random1 = regxlar.generateNext(new SeedRandomGenerator(probability));
            String random2 = regxlar.generateNext(new SeedRandomGenerator(probability));
            assertEquals(random1, random2);
        }

        // different Regxlar instances

        Regxlar regxlar1 = new Regxlar(regexp);
        Regxlar regxlar2 = new Regxlar(regexp);

        for (int i = 0; i < 1000; i++) {
            double probability = Math.random();
            String random1 = regxlar1.generateNext(new SeedRandomGenerator(probability));
            String random2 = regxlar2.generateNext(new SeedRandomGenerator(probability));
            assertEquals(random1, random2);
        }

    }

    @Test
    public void testRandomness() {
        Regxlar regxlar = new Regxlar("(a|b)");

        boolean foundA = false;
        boolean foundB = false;

        for (int i = 0; i < 1000; i++) {
            if (regxlar.generateNext(new SeedRandomGenerator(Math.random())).equals("a"))
                foundA = true;

            if (regxlar.generateNext(new SeedRandomGenerator(Math.random())).equals("b"))
                foundB = true;
        }

        assertTrue(foundA);
        assertTrue(foundB);
    }

    @Test
    public void testDefaultMaxLength() {
        Regxlar regxlar = new Regxlar("(a|b)+");
        String generateNext = regxlar.generateNext(new SeedRandomGenerator(0.6));
        assertEquals(4000, generateNext.length());
    }

    @Ignore("Test about max length is ignored")
    @Test
    public void testMaxLength() {
        String regexp = "ab+cd+e";
        Regxlar regxlar = new Regxlar(regexp);

        Pattern pattern = Pattern.compile(regexp);

        for (int i = 0; i < 1000; i++) {
            String random = regxlar.generateNext(new SeedRandomGenerator(Math.random()), 10);
            assertTrue(pattern.matcher(random).matches());
        }

    }

    @Test
    public void testEmailRegExp() throws Exception {
        String regexp = "[a-z0-9]{5,10}\\.[a-zA-Z0-9]{5,10}\\@[a-z0-9]{5,10}\\.[a-z]{2,4}";
        Regxlar regxlar = new Regxlar(regexp);

        Pattern pattern = Pattern.compile(regexp);

        for (int i = 0; i < 1000; i++) {
            String random = regxlar.generateNext(new SeedRandomGenerator(Math.random()));
            assertTrue(pattern.matcher(random).matches());
        }

    }

    @Test
    public void testIPRegExp() throws Exception {
        String regexp = "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}";
        Regxlar regxlar = new Regxlar(regexp);

        Pattern pattern = Pattern.compile(regexp);

        for (int i = 0; i < 1000; i++) {
            String random = regxlar.generateNext(new SeedRandomGenerator(Math.random()));
            assertTrue(pattern.matcher(random).matches());
        }

    }

    @Test
    public void testLog4jRegExp() throws Exception {
        String regexp = "([0-9]{4}-[0-9]{2}-[0-9]{2}) ([0-9]{2}:[0-9]{2}:[0-9]{2},[0-9]{3}) \\[([a-z]{10})\\] (ERROR|INFO|DEBUG) ([a-z]{10}) - ([a-z]{10})";
        Regxlar regxlar = new Regxlar(regexp);

        Pattern pattern = Pattern.compile(regexp);

        for (int i = 0; i < 1000; i++) {
            String random = regxlar.generateNext(new SeedRandomGenerator(Math.random()));
            assertTrue(pattern.matcher(random).matches());
        }
    }

    @Test
    public void testHttpLogsRegExp() throws Exception {

        String urlExp = "(([a-z]){5,10}([0-9]){0,3}([\\.-])){2,3}([a-z]){2,3}";

        String getExp = "(/([a-z]){2,6}){2,4}(\\.[a-z]{2,4})? HTTP/1\\.0";

        String regexp = urlExp + " \\[([0-9]{2}:[0-9]{2}:[0-9]{2}:[0-9]{2})\\] (\\\"GET " + getExp + "\\\") ([0-9]{3}) ([0-9]{5,10})";

        Regxlar regxlar = new Regxlar(regexp);

        Pattern pattern = Pattern.compile(regexp);

        for (int i = 0; i < 1000; i++) {
            String random = regxlar.generateNext(new SeedRandomGenerator(Math.random()));
            assertTrue(pattern.matcher(random).matches());
        }
    }
}
