//Homework 8 - permutation 

import java.util.*;
import tester.Tester;

/**
 * A class that defines a new permutation code, as well as methods for encoding
 * and decoding of the messages that use this code.
 */
class PermutationCode {
  // The original list of characters to be encoded
  ArrayList<Character> alphabet = 
      new ArrayList<Character>(Arrays.asList(
          'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
          'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 
          't', 'u', 'v', 'w', 'x', 'y', 'z'));

  ArrayList<Character> code = new ArrayList<Character>(26);

  // A random number generator
  Random rand = new Random();

  // Create a new instance of the encoder/decoder with a new permutation code 
  PermutationCode() {
    this.code = this.initEncoder();
  }

  // Create a new instance of the encoder/decoder with the given code 
  PermutationCode(ArrayList<Character> code) {
    this.code = code;
  }

  //The copy of original list of characters to be encoded and mutated
  ArrayList<Character> alphabetToChange = 
      new ArrayList<Character>(alphabet);

  // Initialize the encoding permutation of the characters
  ArrayList<Character> initEncoder() {
    ArrayList<Character> ar0 = code;

    while (ar0.size() != alphabet.size()) {
      Character c = alphabetToChange.get(rand.nextInt(alphabetToChange.size()));
      alphabetToChange.remove(c);
      ar0.add(c);
    }
    return ar0;
  }

  // produce an encoded String from the given String
  String encode(String source) {
    if (source.equals("")) {
      return "";
    } else { 
      return String.valueOf(code.get(alphabet.indexOf(source.charAt(0))))
          + encode(source.substring(1));
    }
  }
  // produce a decoded String from the given String
  String decode(String code) {
    if (code.equals("")) {
      return "";
    } else { 
      return String.valueOf(alphabet.get(this.code.indexOf(code.charAt(0))))
          + decode(code.substring(1));
    }
  }
}
















