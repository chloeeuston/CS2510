//Homework 7 - Visitors 


import java.util.function.BiFunction;
import java.util.function.Function;
import tester.Tester;



interface IArith {
  <R> R accept(IArithVisitor<R> visitor);
}



class Const implements IArith {
  double num;

  // Constructor
  Const(double num) {
    this.num = num;
  }

  // result of apply visitor to given Const
  public <R> R accept(IArithVisitor<R> visitor) {
    return visitor.visitConst(this);
  }
}




class UnaryFormula implements IArith {
  Function<Double, Double> func;
  String name;
  IArith child;

  UnaryFormula(Function<Double, Double> func, 
      String name, IArith child) {
    this.func = func;
    this.name = name;
    this.child = child;
  }
  public <R> R accept(IArithVisitor<R> visitor) {
    return visitor.visitUnary(this);
  }
  
}


class BinaryFormula implements IArith {
  BiFunction<Double, Double, Double> func;
  String name;
  IArith left;
  IArith right;

  // Constructor
  BinaryFormula(
      BiFunction<Double, Double, Double> func, 
      String name, 
      IArith left, 
      IArith right) {
    this.func = func;
    this.name = name;
    this.left = left;
    this.right = right;

  }

  public <R> R accept(IArithVisitor<R> visitor) {
    return visitor.visitBinary(this);
  }

}



//interface for VISITORS
interface IArithVisitor<R> {
 
  R visitConst(Const iObj);
  R visitUnary(UnaryFormula iObj);
}


class EvalVisitor implements IArithVisitor<Double> {

  public Double apply(IArith arith) {
    return arith.accept(this);
  }
  public Double visitConst(Const iObj) {
    return iObj.num;
  }
  public Double visitUnary(UnaryFormula iObj) {
    return iObj.func.apply(this.apply(iObj.child));
  }
  public Double visitBinary(BinaryFormula iObj) {
    return iObj.func.apply(this.apply(iObj.left), 
        this.apply(iObj.right));
  }
}

class DoublerVisitor implements IArithVisitor<IArith> {

  public IArith apply(IArith arith) {
    return arith.accept(this);
  }
  public Const visitConst(Const iObj) {
    return new Const(2 * iObj.num);
  }
  public UnaryFormula visitUnary(UnaryFormula iObj) {
    return new UnaryFormula(iObj.func, iObj.name, 
        this.apply(iObj.child));
  }
  public BinaryFormula visitBinary(BinaryFormula iObj) {
    return new BinaryFormula(iObj.func, iObj.name, 
        this.apply(iObj.left), this.apply(iObj.right));
  }


}

class AllSmallVisitor implements IArithVisitor<Boolean> {  
  public Boolean apply(IArith arith) {
    return arith.accept(this);
  }
  public Boolean visitConst(Const iObj) {
    return iObj.num < 10;
  }
  public Boolean visitUnary(UnaryFormula iObj) {
    return this.apply(iObj.child); 
  }
  public Boolean visitBinary(BinaryFormula iObj) {
    return (this.apply(iObj.left) && this.apply(iObj.right));
  }
}



class ExamplesVisitors {
  
  IArith five = new Const(5.0);
  IArith ten = new Const(10.0);
  IArith two = new Const(2.0);
  IArith twelve = new Const(12.0);
  
  
}



