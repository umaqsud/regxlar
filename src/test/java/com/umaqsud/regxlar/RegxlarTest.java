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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.umaqsud.regxlar.random.SeedRandomGenerator;

public class RegxlarTest {

    @Test
    public void testGenereate() {
        String regex = "ab(c|d){10,20}";
        Regxlar generator = new Regxlar(regex);
        for (int i = 0; i < 100; i++) {
            String random = generator.generateNext(new SeedRandomGenerator(Math.random()));
            assertTrue(random.matches(regex));
        }
    }

    @Test
    public void testRepeatability() throws Exception {
        Regxlar regxlar = new Regxlar("ab(c|d)*");

        for (int i = 0; i < 1000; i++) {
            double probability = Math.random();
            String random1 = regxlar.generateNext(new SeedRandomGenerator(probability));
            String random2 = regxlar.generateNext(new SeedRandomGenerator(probability));
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
        Regxlar regxlar = new Regxlar("(a|b){1,}");

        String generateNext = regxlar.generateNext(new SeedRandomGenerator(0.6));

        assertEquals(4000, generateNext.length());
    }

    @Test
    public void testEmailRegExp() throws Exception {
        Regxlar regxlar = new Regxlar("[a-z0-9]{5,10}\\.[a-zA-Z0-9]{5,10}\\@[a-z0-9]{5,10}\\.[a-z]{2,4}");

        for (int i = 0; i < 1000; i++) {
            System.out.println(regxlar.generateNext(new SeedRandomGenerator(Math.random())));
        }

    }

    @Test
    public void testIPRegExp() throws Exception {
        Regxlar regxlar = new Regxlar("(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}");

        for (int i = 0; i < 1000; i++) {
            System.out.println(regxlar.generateNext(new SeedRandomGenerator(Math.random())));
        }

    }

    @Test
    public void testLog4jRegExp() throws Exception {
        Regxlar regxlar = new Regxlar(
                "([0-9]{4}-[0-9]{2}-[0-9]{2}) ([0-9]{2}:[0-9]{2}:[0-9]{2},[0-9]{3}) \\[([a-z]{10})\\] (ERROR|INFO|DEBUG) ([a-z]{10}) - ([a-z]{10})");

        for (int i = 0; i < 1000; i++) {
            System.out.println(regxlar.generateNext(new SeedRandomGenerator(Math.random())));
        }
    }

    @Test
    public void testHttpLogsRegExp() throws Exception {

        String urlExp = "(([a-z]){5,10}([0-9]){0,3}([\\.-])){2,3}([a-z]){2,3}";

        String get = "(/([a-z]){2,6}){2,4}(\\.[a-z]{2,4})? HTTP/1\\.0";

        Regxlar regxlar = new Regxlar(urlExp + " \\[([0-9]{2}:[0-9]{2}:[0-9]{2}:[0-9]{2})\\] (\\\"GET " + get + "\\\") ([0-9]{3}) ([0-9]{5,10})");

        for (int i = 0; i < 1000; i++) {
            System.out.println(regxlar.generateNext(new SeedRandomGenerator(Math.random())));
        }
    }

}
