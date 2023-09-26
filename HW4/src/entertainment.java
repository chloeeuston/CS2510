//Homework 4
//entertainment 



import tester.Tester;



//represents a type of entertainment
interface IEntertainment {
  
  //compute the total price of this Entertainment
  double totalPrice();
  
  //computes the minutes of entertainment of this IEntertainment
  int duration();
  
  //produce a String that shows the name and price of this IEntertainment
  String format();
  
  //is this IEntertainment the same as that Magazine?
  public boolean sameMagazine(Magazine that);
  
  //is this IEntertainment the same as that TV series?
  public boolean sameTV(TVSeries that);
  
  //is this IEntertainment the same as that podcast?
  public boolean samePodcast(Podcast that);
  
  //is this IEntertainment the same as that one?
  boolean sameEntertainment(IEntertainment that);
}
  
  
  
abstract class AEntertainment implements IEntertainment {
  String name;
  double price;
  int installments;
  
  //constructor
  AEntertainment(String name, double price, int installments) {
    this.name = name;
    this.price = price;
    this.installments = installments;
  }
  
  /*
   * FIELDS:
   * this.name ... String
   * this.price ... double
   * this.installments ... int
   * 
   * METHODS: 
   * this.totalPrice() ... double
   * this.format() ... String
   * this.duration() ... int
   * this.sameMagazine(Magazine) ... boolean
   * this.sameTVSeries(TVSeries) ... boolean
   * this.samePodcast(Podcast) ... boolean
   * this.sameEntertainment(IEntertainment) ... boolean
   *
   * METHODS FOR FIELDS: none
   */
  
  
  //computes the price of a yearly subscription to any IEntertainment 
  public double totalPrice() {
    return this.price * this.installments;
  }
  
  //produce a String that shows the name and price of an IEntertainment
  public String format() {
    return this.name + ", " + Double.toString(this.price) + ".";
  }
    
  //computes the minutes of entertainment of a TVSeries or Podcast
  public int duration() {
    return 50 * this.installments;
  }
  
  //is this IEntertainment the same as that Magazine?
  public boolean sameMagazine(Magazine that) {
    return false;
  }  
  
  //is this IEntertainment the same as that TV series?
  public boolean sameTV(TVSeries that) {
    return false;
  }
  
  //is this IEntertainment the same as that podcast?
  public boolean samePodcast(Podcast that) {
    return false;
  }
  
  //is this IEntertainment the same as that IEntertainment?
  abstract public boolean sameEntertainment(IEntertainment that);
  
}




//represents a magazine
class Magazine extends AEntertainment {
  String genre;
  int pages;
  
  //constructor
  Magazine(String name, double price, String genre, int pages, int installments) {
    super(name, price, installments);
    this.genre = genre;
    this.pages = pages;
  }
  
  /*
   * FIELDS:
   * this.name ... String
   * this.price ... double
   * this.genre ... String
   * this.pages ... int
   * this.installments ... int
   * 
   * METHODS: 
   * this.totalPrice() ... double
   * this.format() ... String
   * this.duration() ... int
   * this.sameMagazine(Magazine) ... boolean
   * this.sameTVSeries(TVSeries) ... boolean
   * this.samePodcast(Podcast) ... boolean
   * this.sameEntertainment(IEntertainment) ... boolean
   *
   * METHODS FOR FIELDS: none
   */
  
  //computes the minutes of entertainment of this Magazine, (includes all installments)
  public int duration() {
    return 5 * this.pages * this.installments;
  }
  
  //is this Magazine the same as that Magazine?
  public boolean sameMagazine(Magazine that) {
    return this.name.equals(that.name) 
        && this.price == that.price 
        && this.genre.equals(that.genre) 
        && this.pages == that.pages
        && this.installments == that.installments;
  } 
  
  //is this Magazine the same as that IEntertainment?
  public boolean sameEntertainment(IEntertainment that) {
    return that.sameMagazine(this);
  }    
    
}




//represents a Television series
class TVSeries extends AEntertainment {
  String corporation;
  
  //constructor 
  TVSeries(String name, double price, int installments, String corporation) {
    super(name, price, installments);
    this.corporation = corporation;
  }
    
  /*
   * FIELDS:
   * this.name ... String
   * this.price ... double
   * this.installments ... int
   * this.corporation ... String 
   * 
   * METHODS: 
   * this.totalPrice() ... double
   * this.format() ... String
   * this.duration() ... int
   * this.sameMagazine(Magazine) ... boolean
   * this.sameTVSeries(TVSeries) ... boolean
   * this.samePodcast(Podcast) ... boolean
   * this.sameEntertainment(IEntertainment) ... boolean
   *
   * METHODS FOR FIELDS: none
   */
  
  
  //is this TV series the same as that TV series?
  public boolean sameTV(TVSeries that) {
    return this.name.equals(that.name) 
        && this.price == that.price 
        && this.installments == that.installments
        && this.corporation.equals(that.corporation);
  } 
    
  //is this TV Series the same as that IEntertainment?
  public boolean sameEntertainment(IEntertainment that) {
    return that.sameTV(this);
  }
  
}


//represents a podcast
class Podcast extends AEntertainment {
    
  //constructor 
  Podcast(String name, double price, int installments) {
    super(name, price, installments);
  }
  
  /*
   * FIELDS:
   * this.name ... String
   * this.price ... double
   * this.installments ... int
   * 
   * METHODS: 
   * this.totalPrice() ... double
   * this.format() ... String
   * this.duration() ... int
   * this.sameMagazine(Magazine) ... boolean
   * this.sameTVSeries(TVSeries) ... boolean
   * this.samePodcast(Podcast) ... boolean
   * this.sameEntertainment(IEntertainment) ... boolean
   *
   * METHODS FOR FIELDS: none
   */
  
  
  //is this podcast the same as that podcast?
  public boolean samePodcast(Podcast that) {
    return this.name.equals(that.name) 
        && this.price == that.price 
        && this.installments == that.installments;
  }
  
  //is this podcast the same as that IEntertainment?
  public boolean sameEntertainment(IEntertainment that) {
    return that.samePodcast(this);
  }
  
}




class ExamplesEntertainment {
  IEntertainment rollingStone = new Magazine("Rolling Stone", 2.55, "Music", 60, 12);
  IEntertainment houseOfCards = new TVSeries("House of Cards", 5.25, 13, "Netflix");
  IEntertainment serial = new Podcast("Serial", 0.0, 8);
  IEntertainment people = new Magazine("People", 3.0, "Media", 40, 24);
  IEntertainment breakingBad = new TVSeries("Breaking Bad", 4.50, 20, "AMC");
  IEntertainment theDaily = new Podcast("The Daily", 1.0, 52);
    
  // testing total price method
  boolean testTotalPrice(Tester t) {
    return t.checkInexact(this.rollingStone.totalPrice(), 2.55 * 12, .0001)
        && t.checkInexact(this.houseOfCards.totalPrice(), 5.25 * 13, .0001)
        && t.checkInexact(this.serial.totalPrice(), 0.0, .0001)
        && t.checkInexact(this.people.totalPrice(), 3.0 * 24, .0001)
        && t.checkInexact(this.breakingBad.totalPrice(), 4.50 * 20, .0001)
        && t.checkInexact(this.theDaily.totalPrice(), 1.0 * 52, .0001);
  }
  
  //testing duration method
  boolean testDuration(Tester t) {
    return t.checkExpect(this.rollingStone.duration(), 3600)
        && t.checkExpect(this.houseOfCards.duration(), 650)
        && t.checkExpect(this.serial.duration(), 400);
  }
  
  //testing format method
  boolean testFormat(Tester t) {
    return t.checkExpect(this.breakingBad.format(), "Breaking Bad, 4.5.")
        && t.checkExpect(this.people.format(), "People, 3.0.")
        && t.checkExpect(this.theDaily.format(), "The Daily, 1.0.");
  }
  
  //testing same entertainment 
  boolean testSameEntertainment(Tester t) {
    return t.checkExpect(this.breakingBad.sameEntertainment(breakingBad), true)
        && t.checkExpect(this.breakingBad.sameEntertainment(rollingStone), false);
  }
   
}


