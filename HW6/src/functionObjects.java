//HOMEWORK 6


import tester.Tester;


interface IFunc<Arg, Ret> {

  // applies something to an argument and returns a Ret
  Ret apply(Arg arg);
}


class SquareNum implements IFunc<Double, Double> {

  public Double apply(Double number) {
    return number * number;
  }
}



class SineNum implements IFunc<Double, Double> {

  public Double apply(Double number) {
    return Math.sin(number);
  }
}



class Identity<Arg> implements IFunc<Arg, Arg> {
  
  public Arg apply(Arg arg) {
    return arg;
  }
}



class PlusN implements IFunc<Double, Double> {
  Double n;

  PlusN(Double n) {
    this.n = n;
  }



  public Double apply(Double arg) {
    return arg + n;
  }
}



class FunctionComposition<Arg, Mid, Ret> implements IFunc<Arg, Ret> {
  IFunc<Arg, Mid> func1;
  IFunc<Mid, Ret> func2;

  FunctionComposition(IFunc<Arg, Mid> func1, IFunc<Mid, Ret> func2) {
    this.func1 = func1;
    this.func2 = func2;   
  }

  public Ret apply(Arg arg) {
    return func2.apply(func1.apply(arg));
  }
}



interface IFunc2<Arg1, Arg2, Ret> {
  Ret apply(Arg1 arg1, Arg2 arg2);

}


class ComposeFunctions<Arg, Mid, Ret> 
    implements IFunc2<IFunc<Arg, Mid>, IFunc<Mid, Ret>, IFunc<Arg, Ret>> {

  public IFunc<Arg, Ret> apply(IFunc<Arg, Mid> arg1, IFunc<Mid, Ret> arg2) {
    return new FunctionComposition<>(arg1, arg2);
  }
}



interface IList<T> {
 
  <U> U foldl(IFunc2<T,U,U> func, U base);
}

class MtList<T> implements IList<T> {
  
  @Override
  public <U> U foldl(IFunc2<T, U, U> func, U base) {
    return base;
  }
}



class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  public <U> U foldl(IFunc2<T, U, U> func, U base) {
    return func.apply(this.first, this.rest.foldl(func, base));
  }

}



class ExamplesFunctions {
  IFunc<Double, Double> sqr = new SquareNum();
  IFunc<Double, Double> sin = new SineNum();
  IFunc<Integer, Integer> i1 = new Identity<Integer>();
  IFunc<String, String> i2 = new Identity<String>();
  IFunc<Double, Double> plus1 = new PlusN(1.0);
  IFunc<Double, Double> plus0 = new PlusN(0.0);
  IFunc<Double, Double> fc = new FunctionComposition<Double, Double, Double>(sqr, sin);
  IFunc<Double, Double> fc1 = new FunctionComposition<Double, Double, Double>(plus1, sin);
  
  // tests

  boolean testSquareNum(Tester t) {
    return t.checkExpect(sqr.apply(5.0), 25.0) 
        && t.checkExpect(sqr.apply(3.0), 9.0);
  }

  boolean testSineNum(Tester t) {
    return t.checkExpect(sin.apply(0.0), 0.0) 
        && t.checkInexact(sin.apply(Math.PI / 2), 1.0, 0.1);
  }

  boolean testIdentity(Tester t) {
    return t.checkExpect(i1.apply(5), 5) 
        && t.checkExpect(i2.apply("Hello"), "Hello");
  }

  boolean testPlusN(Tester t) {
    return t.checkExpect(plus1.apply(4.0), 5.0) 
        && t.checkExpect(plus0.apply(0.0), 0.0);
  }
  
  boolean testFunctionComposition(Tester t) {
    return t.checkInexact(fc.apply(1.0), 0.84, 0.1)
        && t.checkInexact(fc1.apply(1.0), 0.91, 0.1);
  }

 
  
  //the reason why check expect wont say that the two functions are the same is because
  //java checks if they are the exact same objects and not that they are semantically the same
}