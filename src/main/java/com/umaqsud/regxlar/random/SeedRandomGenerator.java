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

package com.umaqsud.regxlar.random;

import java.util.Random;

/**
 * 
 * @author Umar Maqsud (umar.maqsud@campus.tu-berlin.de)
 * 
 */
public class SeedRandomGenerator implements RandomGenerator {

    private Random random;

    public SeedRandomGenerator(double seed) {
        random = new Random(("" + seed).hashCode());
    }

    @Override
    public Double nextDouble() {
        return random.nextDouble();
    }

    @Override
    public Integer nextInt(int n) {
        return random.nextInt(n);
    }

    @Override
    public Integer nextInt() {
        return random.nextInt();
    }

}