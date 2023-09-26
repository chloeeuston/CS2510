//Problem 2

interface IIceCream {

}

class EmptyServing implements IIceCream {
  boolean cone;
  
  // constructor
  EmptyServing(boolean cone) {
    this.cone = cone;
  }
}

class Scooped implements IIceCream {
  IIceCream more;
  String flavor;
  
  // constructor
  Scooped(IIceCream more, String flavor) {
    this.more = more;
    this.flavor = flavor;
  }
}

class ExamplesIceCream {
  IIceCream cup = new EmptyServing(false);
  IIceCream scoop1 = new Scooped(cup, "mint chip");
  IIceCream scoop2 = new Scooped(scoop1, "coffee");
  IIceCream scoop3 = new Scooped(scoop2, "black raspberry");
  IIceCream order1 = new Scooped(scoop3, "caramel swirl");
  IIceCream cone = new EmptyServing(true);
  IIceCream s1 = new Scooped(cone, "chocolate");
  IIceCream s2 = new Scooped(s1, "vanilla");
  IIceCream order2 = new Scooped(s2, "strawberry");
}
