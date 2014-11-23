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

package com.umaqsud.regxlar.examples;

import com.umaqsud.regxlar.Regxlar;
import com.umaqsud.regxlar.random.SeedRandomGenerator;

public class Example {
    public static void main(String[] args) {
        Regxlar regxlar = new Regxlar("[a-z0-9]{5,10}\\.[a-zA-Z0-9]{5,10}\\@[a-z0-9]{5,10}\\.[a-z]{2,4}");

        // Generate string at random
        String random = regxlar.generateNext();
        System.out.println(random);

        // Generate repeatable string using a seeded random number generator
        double seed = 0.5;
        String random1 = regxlar.generateNext(new SeedRandomGenerator(seed));
        String random2 = regxlar.generateNext(new SeedRandomGenerator(seed));
        System.out.println(random1);
        System.out.println(random2);
    }
}
