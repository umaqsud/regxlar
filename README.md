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

Let's take a simple regular expression for email addresses: `[a-z0-9]{5,10}\\.[a-zA-Z0-9]{5,10}\\@[a-z0-9]{5,10}\\.[a-z]{2,4}`. Using Regxlar you can now generate strings matching this regular expression. If you need generate repeatable strings you can use a seeded random number generator.

```java
  Regxlar regxlar = new Regxlar("[a-z0-9]{5,10}\\.[a-zA-Z0-9]{5,10}\\@[a-z0-9]{5,10}\\.[a-z]{2,4}");

  // Generate string at random
  String random = regxlar.generateNext();
  System.out.println(random);
  
  // it prints e.g. 0xl8k.xi0q7a@6xo0fc8ut8.adgp
  
  // Generate repeatable string using a seeded random number generator
  double seed = 0.5;
  String random1 = regxlar.generateNext(new SeedRandomGenerator(seed));
  String random2 = regxlar.generateNext(new SeedRandomGenerator(seed));
  System.out.println(random1);
  System.out.println(random2);
  System.out.println(random1.equals(random2));
  
  // it prints
  // a80w2n.WC92MB3@bil03i2hs.pnqy
  // a80w2n.WC92MB3@bil03i2hs.pnqy
  // true
```
## Features and limitations

* generates strings at random matching a regular expression
* generates repeatable strings using a seeded random number generator

Regxlar does not support all valid regular expressions. Future versions might support further regular expression syntaxes. Regular expressions are built from the following abstract syntax:

```
 regexp       ::=	unionexp		
              |			
 unionexp		  ::=	interexp | unionexp							(union)	
              |	interexp		
 interexp     ::=	concatexp & interexp						(intersection)							[OPTIONAL]
              |	concatexp		
 concatexp		::=	repeatexp concatexp							(concatenation)	
              |	repeatexp		
 repeatexp		::=	repeatexp ?									(zero or one occurrence)	
              |	repeatexp *									(zero or more occurrences)	
              |	repeatexp +									(one or more occurrences)	
              |	repeatexp {n}								(n occurrences)	
              |	repeatexp {n,}								(n or more occurrences)	
              |	repeatexp {n,m}								(n to m occurrences, including both)	
              |	complexp		
 complexp     ::=	~ complexp									(complement)							[OPTIONAL]
              |	charclassexp		
 charclassexp ::=	[ charclasses ]								(character class)	
              |	[^ charclasses ]							(negated character class)	
              |	simpleexp		
 charclasses	::=	charclass charclasses		
              |	charclass		
 charclass		::=	charexp - charexp							(character range, including end-points)	
              |	charexp		
 simpleexp		::=	charexp		
              |	.											(any single character)	
              |	#											(the empty language)					[OPTIONAL]
              |	@											(any string)							[OPTIONAL]
              |	" <Unicode string without double-quotes> "	(a string)	
              |	( )											(the empty string)	
              |	( unionexp )								(precedence override)	
              |	< <identifier> >							(named automaton)						[OPTIONAL]
              |	<n-m>										(numerical interval)					[OPTIONAL]
 charexp			::=	<Unicode character>							(a single non-reserved character)	
              |	\ <Unicode character> 						(a single character)
```

For further information about this syntax consider this documentation: http://www.brics.dk/automaton/doc/index.html?dk/brics/automaton/Automaton.html.

## License

_Regxlar_ is licensed under the Apache Software License Version 2.0. For more
information please consult the LICENSE file.
