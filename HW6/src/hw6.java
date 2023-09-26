//Homework 6

import tester.Tester;

interface IFunc<Arg, Ret> {
  // apply an operation to Arg and return Ret
  Ret apply(Arg arg);
}

class SquareNum implements IFunc<Double, Double> {

  /*
   * FIELDS: none
   * 
   * METHODS: apply(double) ... double
   * 
   * METHODS FOR FIELDS: none
   */

  // squares a given number
  public Double apply(Double x) {
    return x * x;
  }

}

class SineNum implements IFunc<Double, Double> {

  /*
   * FIELDS: none
   * 
   * METHODS: apply(double) ... double
   * 
   * METHODS FOR FIELDS: none
   */

  // computes the sine of a number
  public Double apply(Double x) {
    return Math.sin(x);
  }
}

class Identity<T> implements IFunc<T, T> {

  /*
   * FIELDS: none
   * 
   * METHODS: apply(<T>) ... double
   * 
   * METHODS FOR FIELDS: none
   */

  // returns the input T
  public T apply(T x) {
    return x;
  }
}

class PlusN implements IFunc<Double, Double> {
  double n;

  // constructor
  PlusN(double n) {
    this.n = n;
  }

  /*
   * FIELDS: this.n ... double
   * 
   * METHODS: apply(double) ... double
   * 
   * METHODS FOR FIELDS: none
   */

  // adds an argument to the field n
  public Double apply(Double x) {
    return this.n + x;
  }
}

class FunctionComposition<Arg, Mid, Ret> implements IFunc<Arg, Ret> {
  IFunc<Arg, Mid> f1;
  IFunc<Mid, Ret> f2;

  FunctionComposition(IFunc<Arg, Mid> f1, IFunc<Mid, Ret> f2) {
    this.f1 = f1;
    this.f2 = f2;
  }

  /*
   * FIELDS: this.f1 ... IFunc<Arg, Mid> this.f2 ... IFunc<Mid, Ret>
   * 
   * METHODS: apply(double) ... double
   * 
   * METHODS FOR FIELDS: none
   */

  //composes two functions
  public Ret apply(Arg arg) {
    return f2.apply(f1.apply(arg));
  }
}

//interface for functions with two arguments
interface IFunc2<Arg1, Arg2, Ret> {
  public Ret apply(Arg1 a1, Arg2 a2);

}

//the implements type means that it takes a function with two arguments, 
//each of which are function objects
class ComposeFunctions<Arg, Mid, Ret>
    implements IFunc2<IFunc<Arg, Mid>, IFunc<Mid, Ret>, IFunc<Arg, Ret>> {

  /*
   * FIELDS: none
   * 
   * METHODS: apply(IFunc2) ... double
   * 
   * METHODS FOR FIELDS: none
   */

  // returns a new function composition,composed of given arguments a1 and a2
  // this composition can be applied to an argument arg through the method
  // apply(arg)
  // in the ComposeFunctions class
  public IFunc<Arg, Ret> apply(IFunc<Arg, Mid> a1, IFunc<Mid, Ret> a2) {
    return new FunctionComposition<>(a1, a2);
  }

}

//interface for lists of any type
interface IList<T> {

  <U> U foldl(IFunc2<T, U, U> converter, U initial);

}

//an empty list
class MtList<T> implements IList<T> {

  public <U> U foldl(IFunc2<T, U, U> converter, U initial) {
    return initial;
  }

}

//an list with elements
class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  public <U> U foldl(IFunc2<T, U, U> converter, U initial) {
    return converter.apply(this.first, this.rest.foldl(converter, initial));
    //return this.rest.foldL(converter, converter.apply(this.first, initial));
  }

}


class ExamplesFunctions {
  IFunc<Double, Double> sqr = new SquareNum();
  IFunc<Double, Double> sin = new SineNum();
  IFunc<Integer, Integer> i1 = new Identity<Integer>();
  IFunc<String, String> i2 = new Identity<String>();
  IFunc<Double, Double> plus1 = new PlusN(1);
  IFunc<Double, Double> plus0 = new PlusN(0);
  IFunc<Double, Double> fc = new FunctionComposition<Double, Double, Double>(sqr, sin);
  IFunc<Double, Double> fc1 = new FunctionComposition<Double, Double, Double>(plus1, sin);

  // tests

  boolean testSquareNum(Tester t) {
    return t.checkExpect(sqr.apply(5.0), 25.0) && t.checkExpect(sqr.apply(3.0), 9.0);
  }

  boolean testSineNum(Tester t) {
    return t.checkExpect(sin.apply(0.0), 0.0) && t.checkInexact(sin.apply(Math.PI / 2), 1.0, 0.1);
  }

  boolean testIdentity(Tester t) {
    return t.checkExpect(i1.apply(5), 5) && t.checkExpect(i2.apply("Hello"), "Hello");
  }

  boolean testPlusN(Tester t) {
    return t.checkExpect(plus1.apply(4.0), 5.0) && t.checkExpect(plus0.apply(0.0), 0.0);
  }
  
  boolean testFunctionComposition(Tester t) {
    return t.checkInexact(fc.apply(1.0), 0.84, 0.1)
        && t.checkInexact(fc1.apply(1.0), 0.91, 0.1);
  }

  /*
   * boolean testFunctionComposition(Tester T) { return
   * t.checkExpect(fc.apply(sin), ) }
   */
  
  //the reason why check expect wont say that the two functions are the same is because
  //java checks if they are the exact same objects and not that they are semantically the same
}
