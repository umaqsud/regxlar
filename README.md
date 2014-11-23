[![Build Status](https://travis-ci.org/umaqsud/regxlar.svg?branch=master)](https://travis-ci.org/umaqsud/regxlar)

Regxlar
=======

A Java library for repeatable generating strings at random from a regular grammar. _Regxlar_ is based on the library http://www.brics.dk/~amoeller/automaton/.

## Usage

### Prerequisites
* Apache Maven 3
* Java >= 1.7

### Build from source

```
git clone https://github.com/umaqsud/regxlar.git
cd regxlar
mvn clean package
```

_Regxlar_ is now installed in `regxlar/target`

## Example

Sample usage of libarary:

```java
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
```

## License

_Regxlar_ is licensed under the Apache Software License Version 2.0. For more
information please consult the LICENSE file.
