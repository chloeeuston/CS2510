//Homework 3 : word lists



import java.awt.Color;
import javalib.funworld.WorldScene;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldImage;
import tester.Tester;



//represents a list of words
interface ILoWord {

  // adds a word correctly into a sorted list of words
  ILoWord insertWord(IWord w);

  // sorts a list of words alphabetically
  ILoWord sort();

  // checks if a given string comes before the first in a list
  boolean isSortedHelper(IWord w);

  // determines if a list is sorted alphabetically
  boolean isSorted();

  // combines to lists by alternating the words in each list
  ILoWord interleave(ILoWord that);

  // merges two sorted lists into one sorted list
  ILoWord merge(ILoWord that);

  // removes the first letter of words in a list that start with
  // a given letter
  ILoWord checkAndReduce(String s);

  // adds a given word to the end of a list of words
  ILoWord addToEnd(IWord word);

  // filters out an empty words in a list of words
  ILoWord filterOutEmpties();

  // draws the words in a list of worlds onto a given WorldScene
  WorldScene draw();

}

//represents an empty list of words
class MtLoWord implements ILoWord {
  /*
   * FIELDS: none
   * 
   * METHODS: 
   * this.insertWord(IWord)... ILoWord
   * this.sort()... ILoWord
   * this.isSortedHelper(IWord)... boolean
   * this.isSorted()... boolean
   * this.interleave(ILoWord)... ILoWord
   * this.merge(ILoWord)... ILoWord
   * this.checkAndReduce(String)... ILoWord
   * this.addToEnd(IWord)... ILoWord
   * this.filterOutEmpties()... ILoWord
   * this.draw()... WorldScene
   *
   * METHODS FOR FIELDS: none
   */

  // adds a word correctly into a sorted list of words
  public ILoWord insertWord(IWord w) {
    return new ConsLoWord(w, this);
  }

  // sorts a list of words alphabetically
  public ILoWord sort() {
    return new MtLoWord();
  }

  // checks if a given string comes before the first in a list
  public boolean isSortedHelper(IWord w) {
    return true;
  }

  // determines if a list is sorted alphabetically
  public boolean isSorted() {
    return true;
  }

  //
  public ILoWord interleave(ILoWord that) {
    return that;
  }

  // merges two sorted lists into one sorted list
  public ILoWord merge(ILoWord that) {
    return that;
  }

  // removes the first letter of words in a list that start with
  // a given letter
  public ILoWord checkAndReduce(String s) {
    /*
     * FIELDS OF s: none 
     * 
     * METHODS: 
     * s.toLowerCase()... String
     *
     * METHODS FOR FIELDS: none
     */
    return this;
  }

  // adds a given word to the end of a list of words
  public ILoWord addToEnd(IWord word) {
    return new ConsLoWord(word, new MtLoWord());
  }

  // filters out an empty words in a list of words
  public ILoWord filterOutEmpties() {
    return this;
  }

  // draws the words in a list of worlds onto a given WorldScene
  public WorldScene draw() {
    return new WorldScene(20, 20);
  }

}

class ConsLoWord implements ILoWord {
  IWord first;
  ILoWord rest;

  ConsLoWord(IWord first, ILoWord rest) {
    this.first = first;
    this.rest = rest;
  }
  /*
   * FIELDS: 
   * this.first... IWord
   * this.rest... ILoWord
   * 
   * METHODS: 
   * this.insertWord(IWord)... ILoWord
   * this.sort()... ILoWord
   * this.isSortedHelper(IWord)... boolean
   * this.isSorted()... boolean
   * this.interleave(ILoWord)... ILoWord
   * this.merge(ILoWord)... ILoWord
   * this.checkAndReduce(String)... ILoWord
   * this.addToEnd(IWord)... ILoWord
   * this.filterOutEmpties()... ILoWord
   * this.draw()... WorldScene
   *
   * METHODS FOR FIELDS: 
   * this.first.stringComesBefore(String)... boolean
   * this.first.wordComesBefore(IWord)... boolean
   * this.first.firstLetter()... String
   * this.first.checkFirstLetter(String)... boolean
   * this.first.emptyWord()... boolean
   * this.first.reduce()... IWord
   * this.first.makeText()... WorldImage
   * this.first.drawHelper()... WorldScene
   * this.rest.insertWord(IWord)... ILoWord
   * this.rest.sort()... ILoWord
   * this.rest.isSortedHelper(IWord)... boolean
   * this.rest.isSorted()... boolean
   * this.rest.interleave(ILoWord)... ILoWord
   * this.rest.merge(ILoWord)... ILoWord
   * this.rest.checkAndReduce(String)... ILoWord
   * this.rest.addToEnd(IWord)... ILoWord
   * this.rest.filterOutEmpties()... ILoWord
   * this.rest.draw()... WorldScene
   * 
   */
  
  
  // adds a word correctly into a sorted list of words
  public ILoWord insertWord(IWord word) {
    if (this.first.wordComesBefore(word)) {
      return new ConsLoWord(word, this);
    }
    else {
      return new ConsLoWord(this.first, this.rest.insertWord(word));
    }
  }

  // sorts a list of words alphabetically
  public ILoWord sort() {
    return this.rest.sort().insertWord(this.first);
  }

  // checks if a given string comes before the first in a list
  public boolean isSortedHelper(IWord w) {
    return this.first.wordComesBefore(w);
  }

  // determines if a list is sorted alphabetically
  public boolean isSorted() {
    return this.rest.isSortedHelper(this.first)
        && this.rest.isSorted();
  }

  // alternates two lists of words
  public ILoWord interleave(ILoWord that) {
    return new ConsLoWord(this.first, that.interleave(this.rest));
  }

  // merges two sorted lists into one sorted list
  public ILoWord merge(ILoWord that) {
    return this.rest.merge(that.insertWord(this.first));
  }

  // removes the first letter of words in a list that start with
  // a given letter
  public ILoWord checkAndReduce(String s) {
    /*
     * FIELDS OF s: none 
     * 
     * METHODS: 
     * s.toLowerCase()... String
     *
     * METHODS FOR FIELDS: none
     */
    if (this.first.checkFirstLetter(s)) {
      return new ConsLoWord(this.first.reduce(), this.rest.checkAndReduce(s));
    }
    else {
      return new ConsLoWord(this.first, this.rest.checkAndReduce(s));
    }
  }

  // adds a given word to the end of a list of words
  public ILoWord addToEnd(IWord word) {
    return new ConsLoWord(this.first, this.rest.addToEnd(word));
  }

  // filters out an empty words in a list of words
  public ILoWord filterOutEmpties() {
    if (this.first.emptyWord()) {
      return this.rest.filterOutEmpties();
    }
    else {
      return new ConsLoWord(this.first, this.rest.filterOutEmpties());
    }
  }

  // draws the words in a list of worlds onto a given WorldScene
  public WorldScene draw() {
    return (this.first.drawHelper(this.rest.draw()));

  }
}

//represents a word in the ZType game
interface IWord {

  // determines if a given string comes before this word
  boolean stringComesBefore(String s);
  
  // determines if a given word comes before this word
  boolean wordComesBefore(IWord w);

  // checks if the first letter in a word is the same as a given letter
  boolean checkFirstLetter(String s);

  // takes the first letter off of a word
  IWord reduce();

  // returns a given character in a string
  String firstLetter();
  
  // checks if a word is empty 
  boolean emptyWord();

  // creates a text image of a given word
  WorldImage makeText();
  
  //places a text onto a world scene
  WorldScene drawHelper(WorldScene world);

}

//represents an active word in the ZType game
class ActiveWord implements IWord {
  String word;
  int x;
  int y;

  ActiveWord(String word, int x, int y) {
    this.word = word;
    this.x = x;
    this.y = y;
  }
  /*
   * FIELDS: 
   * this.word... String
   * this.x... int
   * this.y... int
   * 
   * METHODS: 
   * this.stringComesBefore(String)... boolean
   * this.wordComesBefore(IWord)... boolean
   * this.firstLetter()... String
   * this.checkFirstLetter(String)... boolean
   * this.emptyWord()... boolean
   * this.reduce()... IWord
   * this.makeText()... WorldImage
   * this.drawHelper(WorldScene)... WorldScene
   *
   * METHODS FOR FIELDS: none
   * 
   */
  

  // determines if a given string comes before this word
  public boolean stringComesBefore(String s) {
    /*
     * FIELDS OF s: none 
     * 
     * METHODS: 
     * s.toLowerCase()... String
     *
     * METHODS FOR FIELDS: none
     */
    return this.word.toLowerCase().compareTo(s.toLowerCase()) <= 0;
  }
  
  // determines if a given word comes before this word
  public boolean wordComesBefore(IWord w) {
    return w.stringComesBefore(this.word.toLowerCase());
  }

  // returns the first character in a string
  public String firstLetter() {
    return this.word.substring(0, 1);
  }

  // checks if the first letter in a word is the same as a given letter
  public boolean checkFirstLetter(String s) {
    /*
     * FIELDS OF s: none 
     * 
     * METHODS: 
     * s.toLowerCase()... String
     *
     * METHODS FOR FIELDS: none
     */
    return this.firstLetter().equals(s);
  }
  
  //checks if a word is empty 
  public boolean emptyWord() {
    return (this.word.equals(""));
  }

  // takes the first letter off of a word
  public IWord reduce() {
    return new ActiveWord(this.word.substring(1), this.x, this.y);
  }

  //creates a text image of a given word 
  public WorldImage makeText() {
    return new TextImage(this.word, 10, Color.BLACK);
  }
  
  //places a text onto a world scene
  public WorldScene drawHelper(WorldScene world) {
    return (world.placeImageXY(this.makeText(),
        this.x, this.y));
  }

}

//represents an inactive word in the ZType game
class InactiveWord implements IWord {
  String word;
  int x;
  int y;

  // constructor
  InactiveWord(String word, int x, int y) {
    this.word = word;
    this.x = x;
    this.y = y;
  }
  /*
   * FIELDS: 
   * this.word... String
   * this.x... int
   * this.y... int
   * 
   * METHODS: 
   * this.stringComesBefore(String)... boolean
   * this.wordComesBefore(IWord)... boolean
   * this.firstLetter()... String
   * this.checkFirstLetter(String)... boolean
   * this.emptyWord()... boolean
   * this.reduce()... IWord
   * this.makeText()... WorldImage
   * this.drawHelper(WorldScene)... WorldScene
   *
   * METHODS FOR FIELDS: none
   * 
   */
  

  // determines if a given string comes before this word
  public boolean stringComesBefore(String s) {
    /*
     * FIELDS OF s: none 
     * 
     * METHODS: 
     * s.toLowerCase()... String
     *
     * METHODS FOR FIELDS: none
     */
    return this.word.toLowerCase().compareTo(s.toLowerCase()) <= 0;
  }
  
  // determines if a given word comes before this word
  public boolean wordComesBefore(IWord w) {
    return w.stringComesBefore(this.word.toLowerCase());
  }

  //returns the first character in a string
  public String firstLetter() {
    return this.word.substring(0, 1);
  }

  // checks if the first letter in a word is the same as a given letter
  public boolean checkFirstLetter(String s) {
    /*
     * FIELDS OF s: none 
     * 
     * METHODS: 
     * s.toLowerCase()... String
     *
     * METHODS FOR FIELDS: none
     */
    return this.firstLetter().equals(s);
  }
  
  //checks if a word is empty 
  public boolean emptyWord() {
    return (this.word.equals(""));
  }

  // takes the first letter off of a word
  public IWord reduce() {
    return new ActiveWord(this.word.substring(1), this.x, this.y);
  }

  //creates a text image of a given word 
  public WorldImage makeText() {
    return new TextImage(this.word, 10, Color.BLACK);
  }
  
  //places a text onto a world scene
  public WorldScene drawHelper(WorldScene world) {
    return (world.placeImageXY(this.makeText(),
        this.x, this.y));
  }
}

//all examples and tests for ILoWord
class ExamplesWordLists {
  IWord apple = new ActiveWord("apple", 5, 3);
  IWord pple = new ActiveWord("pple", 5, 3);
  IWord banana = new InactiveWord("banana", 8, 1);
  IWord strawberry = new ActiveWord("strawberry", 2, 9);
  IWord kiwi = new ActiveWord("kiwi", 3, 4);
  IWord mango = new InactiveWord("mango", 7, 7);
  IWord orange = new InactiveWord("orange", 5, 2);
  IWord raspberry = new ActiveWord("raspberry", 4, 6);
  IWord mtword = new InactiveWord("", 0, 0);
  IWord grape = new ActiveWord("Grape", 6, 8);
  ILoWord mtlist = new MtLoWord();
  ILoWord list1 = new ConsLoWord(banana, new ConsLoWord(apple, new ConsLoWord(strawberry, mtlist)));
  ILoWord list2 = new ConsLoWord(apple, new ConsLoWord(banana, new ConsLoWord(strawberry, mtlist)));
  ILoWord list3 = new ConsLoWord(apple,
      new ConsLoWord(banana, new ConsLoWord(kiwi, new ConsLoWord(strawberry, mtlist))));
  ILoWord list4 = new ConsLoWord(banana, mtlist);
  ILoWord list5 = new ConsLoWord(mango, new ConsLoWord(orange, new ConsLoWord(raspberry, mtlist)));
  ILoWord list6 = new ConsLoWord(apple, new ConsLoWord(mango, new ConsLoWord(banana,
      new ConsLoWord(orange, new ConsLoWord(strawberry, new ConsLoWord(raspberry, mtlist))))));
  ILoWord list7 = new ConsLoWord(mango,
      new ConsLoWord(banana, new ConsLoWord(orange, new ConsLoWord(raspberry, mtlist))));
  ILoWord list8 = new ConsLoWord(raspberry, new ConsLoWord(orange, new ConsLoWord(mango, mtlist)));
  ILoWord list9 = new ConsLoWord(apple, new ConsLoWord(banana, new ConsLoWord(mango,
      new ConsLoWord(orange, new ConsLoWord(raspberry, new ConsLoWord(strawberry, mtlist))))));
  ILoWord list10 = new ConsLoWord(apple,
      new ConsLoWord(banana, new ConsLoWord(banana, new ConsLoWord(strawberry, mtlist))));
  ILoWord list11 = new ConsLoWord(pple, new ConsLoWord(banana, new ConsLoWord(strawberry, mtlist)));
  ILoWord list12 = new ConsLoWord(banana, new ConsLoWord(kiwi, mtlist));
  ILoWord list13 = new ConsLoWord(apple, new ConsLoWord(banana, new ConsLoWord(mtword,
      new ConsLoWord(strawberry, mtlist))));
  TextImage kiwiimage = new TextImage("kiwi", 10, Color.BLACK);
  TextImage bananaimage = new TextImage("banana", 10, Color.BLACK);
  ILoWord list14 = new ConsLoWord(kiwi, list4);
  WorldScene mtworld = new WorldScene(20, 20);

  // tests
  
  boolean testStringComesBefore(Tester t) {
    return t.checkExpect(this.apple.stringComesBefore("mango"), true)
        && t.checkExpect(this.strawberry.stringComesBefore("mango"), false)
        && t.checkExpect(this.kiwi.stringComesBefore("Mango"), true);
  }
  
  boolean testWordComesBefore(Tester t) {
    return t.checkExpect(this.apple.wordComesBefore(this.banana), false)
        && t.checkExpect(this.strawberry.wordComesBefore(this.banana), true)
        && t.checkExpect(this.grape.wordComesBefore(this.banana), true);
  }

  boolean testInsertWord(Tester t) {
    return t.checkExpect(this.list2.insertWord(kiwi), list3)
        && t.checkExpect(this.mtlist.insertWord(banana), list4);
  }

  boolean testSort(Tester t) {
    return t.checkExpect(this.list1.sort(), this.list2)
        && t.checkExpect(this.list8.sort(), this.list5);
  }

  boolean testIsSorted(Tester t) {
    return t.checkExpect(this.mtlist.isSorted(), true)
        && t.checkExpect(this.list1.isSorted(), false)
        && t.checkExpect(this.list5.isSorted(), true)
        && t.checkExpect(this.list2.isSorted(), true);

  }

  boolean testInterleave(Tester t) {
    return t.checkExpect(this.list2.interleave(list5), list6)
        && t.checkExpect(this.mtlist.interleave(list3), list3)
        && t.checkExpect(this.list5.interleave(list4), list7);
  }

  boolean testMerge(Tester t) {
    return t.checkExpect(this.list5.merge(list2), list9)
        && t.checkExpect(this.list2.merge(list4), list10);

  }

  boolean firstLeter(Tester t) {
    return t.checkExpect(this.apple.firstLetter(), "a")
        && t.checkExpect(this.mtword.firstLetter(), "");

  }

  boolean testCheckFirstLetter(Tester t) {
    return t.checkExpect(this.apple.checkFirstLetter("a"), true)
        && t.checkExpect(this.banana.checkFirstLetter("a"), false);

  }

  boolean testReduce(Tester t) {
    return t.checkExpect(this.apple.reduce(), pple);

  }

  boolean testCheckAndReduce(Tester t) {
    return t.checkExpect(this.list2.checkAndReduce("a"), list11)
        && t.checkExpect(this.list5.checkAndReduce("a"), list5);

  }
  
  boolean testEmptyWord(Tester t) {
    return t.checkExpect(this.apple.emptyWord(), false)
        && t.checkExpect(this.mtword.emptyWord(), true);
  }
  
  boolean testAddToEnd(Tester t) {
    return t.checkExpect(this.list4.addToEnd(kiwi), list12)
        && t.checkExpect(this.mtlist.addToEnd(banana), list4);
  }
  
  boolean testFilterOutEmpties(Tester t) {
    return t.checkExpect(this.list13.filterOutEmpties(), list2)
        && t.checkExpect(this.list2.filterOutEmpties(), list2);
  }
  
  boolean testMakeText(Tester t) {
    return t.checkExpect(this.kiwi.makeText(), kiwiimage)
        && t.checkExpect(this.mtword.makeText(), new TextImage("", 10, Color.BLACK));
  }
  
  boolean testDrawHelper(Tester t) {
    return t.checkExpect(this.kiwi.drawHelper(mtworld),
        (new WorldScene(20, 20).placeImageXY(kiwiimage,
            3, 4)));
  }
  
  boolean testDraw(Tester t) {
    return t.checkExpect(this.mtlist.draw(), mtworld)
        && t.checkExpect(this.list14.draw(), 
            new WorldScene(20, 20).placeImageXY(bananaimage, 8, 1)
            .placeImageXY(kiwiimage, 3, 4));
  }

}
