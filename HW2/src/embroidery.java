//Homework 2

import tester.Tester;

//represents a Motif
interface IMotif {

  // the difficulty of a Motif
  double difficultyMotif();

  // the total number of Motifs in an IMotif
  int numberMotifs();

  // represents a Motif as a String
  String motifString();

}

//represents a List-of Motifs
interface ILoMotif {

  // the number of Motifs in an ILoMotif
  int lengthILoMotif();

  // the sum of the difficulties of all the Motifs in an ILoMotif
  double sumMotifs();

  // represents an ILoMotif as a String
  String iLoMotifString();

}

//an empty List-of Motifs
class MtLoMotif implements ILoMotif {
  /*
   * FIELDS: none
   * 
   * METHODS: 
   * this.lengthILoMotif() ... int 
   * this.sumMotifs() ... int 
   * this.iLoMotifString ... String
   *
   * METHODS FOR FIELDS: none
   *
   */

  // the number of Motifs in an MtLoMotif
  public int lengthILoMotif() {
    return 0;
  }

  public double sumMotifs() {
    return 0;
  }

  public String iLoMotifString() {
    return "";
  }
}

//a non-empty List-of Motifs
class ConsLoMotif implements ILoMotif {
  IMotif first;
  ILoMotif rest;

  // constructor
  ConsLoMotif(IMotif first, ILoMotif rest) {
    this.first = first;
    this.rest = rest;
  }
  /*
   * FIELDS: 
   * this.first ... IMotif 
   * this.rest ... ILoMotif
   * 
   * METHODS: 
   * this.lengthILoMotif() ... int 
   * this.sumMotifs() ... int
   * this.iLoMotifString() ... String
   *
   * METHODS FOR FIELDS: 
   * this.rest.lengthILoMotif() ... int 
   * this.rest.sumMotifs() ... int 
   * this.rest.iLoMotifString() ... String 
   * this.first.numberMotifs() ... int 
   * this.first.difficultyMotif() ... double 
   * this.first.motifString() ... String
   *
   */

  // the number of Motifs in a ConsLoMotif
  public int lengthILoMotif() {
    return this.first.numberMotifs() + this.rest.lengthILoMotif();
  }

  // the sum of difficulties of Motifs in a ConsLoMotif
  public double sumMotifs() {
    return this.first.difficultyMotif() + this.rest.sumMotifs();
  }

  // a String representation of the Motifs in a ConsLoMotif
  public String iLoMotifString() {
    if (this.rest.lengthILoMotif() > 0) {
      return this.first.motifString() + ", " + this.rest.iLoMotifString();
    }
    else {
      return this.first.motifString() + this.rest.iLoMotifString();
    }
  }
}

//represents a Cross-Stitch Motif
class CrossStitchMotif implements IMotif {
  String description;
  double difficulty;

  // constructor
  CrossStitchMotif(String description, double difficulty) {
    this.description = description;
    this.difficulty = difficulty;
  }
  /*
   * FIELDS: 
   * this.description ... String 
   * this.difficulty ... double
   * 
   * METHODS: 
   * this.difficultyMotif() ... double 
   * this.numberMotifs() ... int
   * this.motifString() ... String
   *
   * METHODS FOR FIELDS: none
   * 
   */

  // the difficulty of the CrossStitchMotif
  public double difficultyMotif() {
    return this.difficulty;
  }

  // the number of Motifs in a CrossStitchMotif
  public int numberMotifs() {
    return 1;
  }

  // a CrossStitchMotif as a string
  public String motifString() {
    return this.description + " (cross stitch)";
  }
}

class ChainStitchMotif implements IMotif {
  String description;
  double difficulty;

  // constructor
  ChainStitchMotif(String description, double difficulty) {
    this.description = description;
    this.difficulty = difficulty;
  }
  /*
   * FIELDS: 
   * this.description ... String 
   * this.difficulty ... double
   * 
   * METHODS: 
   * this.difficultyMotif() ... double 
   * this.numberMotifs() ... int
   * this.motifString() ... String
   *
   * METHODS FOR FIELDS: none
   */

  // the difficulty of the ChainStitchMotif
  public double difficultyMotif() {
    return this.difficulty;
  }

  // the number of Motifs in a ChainStitchMotif
  public int numberMotifs() {
    return 1;
  }

  // a ChainStitchMotif as a string
  public String motifString() {
    return this.description + " (chain stitch)";
  }
}

class GroupMotif implements IMotif {
  String description;
  ILoMotif motifs;

  // constructor
  GroupMotif(String description, ILoMotif motifs) {
    this.description = description;
    this.motifs = motifs;
  }
  /*
   * FIELDS: 
   * this.description ... String 
   * this.motifs ... ILoMotif
   * 
   * METHODS: 
   * this.difficultyMotif() ... double 
   * this.numberMotif() ... int
   * this.motifString() ... String
   *
   * METHODS FOR FIELDS: 
   * this.motifs.lengthILoMtif() ... int
   * this.motifs.sumMotifs() ... double 
   * this.motifs.iLoMotifString() ... String
   * 
   */

  // the sum of difficulties in a GroupMotif
  public double difficultyMotif() {
    return this.motifs.sumMotifs();
  }

  // the number of Motifs in a GroupMotif
  public int numberMotifs() {
    return this.motifs.lengthILoMotif();
  }

  // a groupMotif as a String
  public String motifString() {
    return this.motifs.iLoMotifString();
  }
}

class EmbroideryPiece {
  String name;
  IMotif motif;

  // constructor
  EmbroideryPiece(String name, IMotif motif) {
    this.name = name;
    this.motif = motif;
  }
  /*
   * FIELDS: 
   * this.name ... String 
   * this.motif ... IMotif
   * 
   * METHODS: 
   * this.averageDifficulty() ... double 
   * this.embroideryInfo() ... String
   *
   * METHODS FOR FIELDS: 
   * this.motif.difficultyMotif() ... double
   * this.motif.numberMotifs() ... int 
   * this.motif.motifString() ... String
   * 
   */

  // the total number of CrossStitchMotifs and ChainStitchMotifs
  // in an EmbroideryPiece

  // the average difficulty of all the CrossStitch/ChainStitch Motifs
  // in an EmbroideryPiece
  double averageDifficulty() {
    if (this.motif.numberMotifs() == 0) {
      return 0.0;
    }
    else {
      return this.motif.difficultyMotif() / (this.motif.numberMotifs());
    }
  }

  // a String that lists the CrossStitch/ChainStitch Motifs
  // in an EmbroideryPiece
  String embroideryInfo() {
    return this.name + ": " + this.motif.motifString() + ".";
  }
}


class ExamplesEmbroidery {
  IMotif bird = new CrossStitchMotif("bird", 4.5);
  IMotif tree = new ChainStitchMotif("tree", 3.0);
  IMotif rose = new CrossStitchMotif("rose", 5.0);
  IMotif poppy = new ChainStitchMotif("poppy", 4.75);
  IMotif daisy = new CrossStitchMotif("daisy", 3.2);
  ILoMotif mtlist = new MtLoMotif();
  ILoMotif list1 = new ConsLoMotif(daisy, mtlist);
  ILoMotif list2 = new ConsLoMotif(poppy, list1);
  ILoMotif list3 = new ConsLoMotif(rose, list2);
  IMotif flowers = new GroupMotif("flowers", list3);
  ILoMotif list4 = new ConsLoMotif(flowers, mtlist);
  ILoMotif list5 = new ConsLoMotif(tree, list4);
  ILoMotif list6 = new ConsLoMotif(bird, list5);
  IMotif nature = new GroupMotif("nature", list6);
  EmbroideryPiece pillowCover = new EmbroideryPiece("Pillow Cover", nature);
  IMotif mtMotif = new GroupMotif("Empty Motif", mtlist);
  EmbroideryPiece mtPiece = new EmbroideryPiece("Empty Piece", mtMotif);

  // tests for lengthILoMotif
  boolean testLengthILoMotif(Tester t) {
    return t.checkExpect(this.mtlist.lengthILoMotif(), 0)
        && t.checkExpect(this.list1.lengthILoMotif(), 1)
        && t.checkExpect(this.list3.lengthILoMotif(), 3);
  }

  // tests for numberMotifs
  boolean testNumberMotifs(Tester t) {
    return t.checkExpect(this.bird.numberMotifs(), 1) && t.checkExpect(this.tree.numberMotifs(), 1)
        && t.checkExpect(this.flowers.numberMotifs(), 3)
        && t.checkExpect(this.nature.numberMotifs(), 5);
  }

  // tests for difficultyMotifs
  boolean testDifficultyMotifs(Tester t) {
    return t.checkExpect(this.rose.difficultyMotif(), 5.0)
        && t.checkExpect(this.poppy.difficultyMotif(), 4.75)
        && t.checkExpect(this.flowers.difficultyMotif(), 12.95)
        && t.checkExpect(this.nature.difficultyMotif(), 20.45);
  }

  // tests for sumMotifs
  boolean testSumMotifs(Tester t) {
    return t.checkExpect(this.mtlist.sumMotifs(), 0.0) && t.checkExpect(this.list1.sumMotifs(), 3.2)
        && t.checkExpect(this.list6.sumMotifs(), 20.45);
  }

  // tests for averageDifficulty
  boolean testAverageDifficulty(Tester t) {
    return t.checkExpect(this.pillowCover.averageDifficulty(), 4.09)
        && t.checkExpect(this.mtPiece.averageDifficulty(), 0.0);
  }

  // tests for iLoMotifString
  boolean testILoMotifString(Tester t) {
    return t.checkExpect(this.mtlist.iLoMotifString(), "")
        && t.checkExpect(this.list1.iLoMotifString(), "daisy (cross stitch)")
        && t.checkExpect(this.list2.iLoMotifString(), "poppy (chain stitch), daisy (cross stitch)");
  }

  // tests for motifString
  boolean testMotifString(Tester t) {
    return t.checkExpect(this.tree.motifString(), "tree (chain stitch)")
        && t.checkExpect(this.rose.motifString(), "rose (cross stitch)")
        && t.checkExpect(this.flowers.motifString(),
            "rose (cross stitch), poppy (chain stitch), daisy (cross stitch)")
        && t.checkExpect(this.nature.motifString(),
            "bird (cross stitch), tree (chain stitch), rose (cross stitch), "
            + "poppy (chain stitch), daisy (cross stitch)");
  }

  // tests for embroideryInfo
  boolean testembroideryInfo(Tester t) {
    return t.checkExpect(this.pillowCover.embroideryInfo(),
        "Pillow Cover: bird (cross stitch), tree (chain stitch), rose (cross stitch), "
        + "poppy (chain stitch), daisy (cross stitch).");
  }
}
