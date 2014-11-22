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

import java.util.List;

import com.umaqsud.regxlar.random.RandomGenerator;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import dk.brics.automaton.State;
import dk.brics.automaton.Transition;

public class Regxlar {

    private int DEFAULT_MAX_LENGTH = 4000;

    private RegExp regExp;
    private Automaton automaton;

    public Regxlar(String regularExpression) {
        regExp = new RegExp(regularExpression);
        automaton = regExp.toAutomaton();
    }

    public String generateNext(RandomGenerator randomGenerator, int maxLength) {

        String randomString = "";

        State currentState = automaton.getInitialState();

        for (int i = 0; i < maxLength; i++) {

            List<Transition> transitions = currentState.getSortedTransitions(false);

            if (transitions.size() == 0) {
                return randomString;
            }

            Transition randomTransition = transitions.get(randomGenerator.nextInt(transitions.size()));

            char randomChar = (char) (randomGenerator.nextInt((randomTransition.getMax() - randomTransition.getMin()) + 1) + randomTransition.getMin());

            randomString += randomChar;

            currentState = randomTransition.getDest();
        }

        return randomString;
    }

    public String generateNext(RandomGenerator randomGenerator) {
        return generateNext(randomGenerator, DEFAULT_MAX_LENGTH);
    }
}
