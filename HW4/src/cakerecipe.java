//Homework 4
//cake recipe 

import tester.Tester;

class Utils {

  // checks if a number is in a given range
  // returns the number if true, returns a message if false
  double checkRange(double n, double lo, double hi, String message) {
    if (n >= lo && n <= hi) {
      return n;
    }
    else {
      throw new IllegalArgumentException(message);
    }
  }
}

//represents a cake recipe
class CakeRecipe {
  // the weight of each ingredient in ounces
  double flour;
  double sugar;
  double eggs;
  double butter;
  double milk;
  boolean areVolumes;

  // constructor
  CakeRecipe(double flour, double sugar, double eggs, double butter, double milk) {
    Utils u = new Utils();
    this.flour = u.checkRange(flour, sugar, sugar, "not a perfect cake");
    this.sugar = u.checkRange(sugar, flour, flour, "not a perfect cake");
    this.eggs = u.checkRange(eggs, butter, butter, "not a perfect cake");
    this.butter = u.checkRange(butter, eggs, eggs, "not a perfect cake");
    this.milk = u.checkRange(milk, sugar - butter, sugar - butter, "not a perfect cake");
  }

  // constructor
  CakeRecipe(double flour, double eggs, double milk) {
    Utils u = new Utils();
    this.flour = u.checkRange(flour, eggs + milk, eggs + milk, "not a perfect cake");
    this.sugar = flour;
    this.eggs = eggs;
    this.butter = eggs;
    this.milk = milk;
  }

  // constructor
  CakeRecipe(double flour, double eggs, double milk, boolean areVolumes) {
    Utils u = new Utils();
    this.flour = flour * 4.25;
    this.sugar = flour * 4.25;
    this.eggs = eggs * 1.75;
    this.butter = eggs * 1.75;
    this.milk = u.checkRange(milk * 8, (flour * 4.25) - (eggs * 1.75),
        (flour * 4.25) - (eggs * 1.75), "not a perfect cake");
    this.areVolumes = areVolumes;
  }

  /*
   * FIELDS: 
   * this.flour ... double 
   * this.sugar ... double 
   * this.eggs ... double
   * this.butter ... double 
   * this.milk ... double
   * 
   * METHODS: 
   * this.sameRecipe(CakeRecipe) ... boolean
   *
   * METHODS FOR FIELDS: none
   */

  // determines if the ingredients have the same weights in two recipes
  boolean sameRecipe(CakeRecipe other) {    
    return this.flour == other.flour 
        && this.sugar == other.sugar 
        && this.eggs == other.eggs
        && this.butter == other.butter 
        && this.milk == other.milk;
    
  }
}

class ExamplesCakes {
  CakeRecipe cake1 = new CakeRecipe(6, 6, 2, 2, 4);
  CakeRecipe cake2 = new CakeRecipe(6, 6, 1, 1, 5);
  CakeRecipe cake3 = new CakeRecipe(6, 2, 4);
  CakeRecipe cake4 = new CakeRecipe(4, 4, 1.25, true);
  CakeRecipe cake5 = new CakeRecipe(17, 7, 10);

  // test for sameRecipe
  boolean testSameRecipe(Tester t) {
    return t.checkExpect(this.cake1.sameRecipe(cake2), false)
        && t.checkExpect(this.cake1.sameRecipe(cake3), true)
        && t.checkExpect(this.cake4.sameRecipe(cake5), true)
        && t.checkExpect(this.cake4.sameRecipe(cake1), false);
  }
}
