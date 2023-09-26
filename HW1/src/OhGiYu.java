//Problem 3

interface IResource {

}

class Monster implements IResource {
  String name;
  int hp;
  int attack;

  // constructor
  Monster(String name, int hp, int attack) {
    this.name = name;
    this.hp = hp;
    this.attack = attack;
  }
}

class Fusion implements IResource {
  String name;
  IResource monster1;
  IResource monster2;

  // constructor
  Fusion(String name, IResource monster1, IResource monster2) {
    this.name = name;
    this.monster1 = monster1;
    this.monster2 = monster2;
  }
}

class Trap implements IResource {
  String description;
  boolean continuous;

  // constructor
  Trap(String description, boolean continuous) {
    this.description = description;
    this.continuous = continuous;
  }
}

interface IAction {

}

class Attack implements IAction {
  IResource attacker;
  IResource defender;

  // constructor
  Attack(IResource attacker, IResource defender) {
    this.attacker = attacker;
    this.defender = defender;
  }
}

class Activate implements IAction {
  IResource trap;
  IResource target;

  // constructor
  Activate(IResource trap, IResource target) {
    this.trap = trap;
    this.target = target;
  }
}

class ExamplesGame {
  IResource kuriboh = new Monster("Kuriboh", 200, 100);
  IResource jinzo = new Monster("Jinzo", 500, 400);
  IResource kurizo = new Fusion("Kurizo", kuriboh, jinzo);
  IResource trapHole = new Trap("Kills a monster", false);
  IResource cage = new Trap("Traps a Monster", true);
  IResource bob = new Monster("Bob", 100, 50);
  IAction attack1 = new Attack(jinzo, kuriboh);
  IAction attack2 = new Attack(jinzo, bob);
  IAction activate1 = new Activate(trapHole, kuriboh);
  IAction activate2 = new Activate(cage, jinzo);
}

