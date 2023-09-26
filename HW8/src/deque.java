//Homework 8 - Deque 


import java.util.function.Predicate;
import tester.Tester;


class Deque<T> {
  Sentinel<T> header;

  Deque(Sentinel<T> header) {
    this.header = header;
  }

  Deque() {
    this.header = new Sentinel<T>();
  }

  
  // counts the number of nodes in a deque not including the header
  int size() {
    return this.header.sizeHelp();
  }

  // adds a value of type T to the front of the list
  void addAtHead(T value) {
    this.header.next.addBetween(value, this.header);
  }

  // adds a value of type T to the end of the list
  void addAtTail(T value) {
    this.header.addBetween(value, this.header.prev);
  }

  // removes the first node from the deque and returns what has been removed
  T removeFromHead() {
    if (this.header.next == null) {
      throw new RuntimeException("Not Allowed");
    }
    return this.header.next.removeBetween(this.header, this.header.next.next);
  }

  // removes the last node from the deque and returns what was removed
  T removeFromTail() {
    if (this.header.prev == null) {
      throw new RuntimeException("Not Allowed");
    }
    return this.header.prev.removeBetween(this.header.prev.prev, this.header);
  }

  // produces the first node in the deque that returns true from a given
  // predicate, returns header if none are true
  ANode<T> find(Predicate<T> pred) {
    return this.header.next.findHelp(pred);
  }

  // removes the given node from a deque, does nothing if the given
  // is the header
  void removeNode(ANode<T> toRemove) {
    toRemove.removeNodeHelp();
  }
  
  
  //adds the items from a given deque to this deque 
  void appendDeques(Deque<T> other) {  
    int sizeOfOther = other.size();
    
    for (int i = 0; i < sizeOfOther; i ++) {
      T otherData = other.removeFromHead();
      this.addAtTail(otherData);
    }
    
    
}



abstract class ANode<T> { 
  ANode<T> next;
  ANode<T> prev;

  // returns the given acc 
  // acc: tracks how many nodes gone over so far
  int count(int acc) {
    return acc;
  }

  // adds the given value before the given node and after this node
  void addBetween(T value, ANode<T> before) {
    Node<T> newNode = new Node<T>(value, this, before);

    this.next = newNode;
  }

  // throws a RuntimeException when the sentinel cannot be removes from a deque
  T removeBetween(ANode<T> before, ANode<T> after) {
    throw new RuntimeException("Not Allowed");
  }

  // returns this 
  ANode<T> findHelp(Predicate<T> pred) {
    return this;
  }

  // removes this node
  void removeNodeHelp() {
    this.removeBetween(this.prev, this.next);
  }

}



class Node<T> extends ANode<T> { // represents a Node
  T data;

  Node(T data) {
    this.data = data;
    this.next = null;
    this.prev = null;
  }

  Node(T data, ANode<T> next, ANode<T> prev) {
    this.data = data;
    if (next == null) {
      throw new IllegalArgumentException();
    }

    else {
      this.next = next;
      this.next.prev = this;
    }

    if (prev == null) {
      throw new IllegalArgumentException();
    }

    else {
      this.prev = prev;
      prev.next = this;
    }
  }

  // adds 1 to the acc and continues counting the number of nodes
  // acc: tracks of how many nodes gone over so far
  int count(int acc) {
    return this.next.count(1 + acc);
  }

  // removes this node and links the prev node of this and the next node
  T removeBetween(ANode<T> before, ANode<T> after) {
    this.prev.next = after;
    this.next.prev = before;
    return this.data;
  }

  // returns the first node that passes the given predicate
  ANode<T> findHelp(Predicate<T> pred) {
    if (pred.test(this.data)) {
      return this;
    }
    else {
      return this.next.findHelp(pred);
    }
  }
  
  
  
}


class Sentinel<T> extends ANode<T> { 

  Sentinel() {
    this.next = this;
    this.prev = this;
  }

  // counts the number of nodes that come after this
  int sizeHelp() {
    return this.next.count(0);
  }

  // does not change anything because the sentinel cannot be removed
  void removeNodeHelp() {
    return;
  }

}


class Abc implements Predicate<String> {
  
  public boolean test(String s) {
    return s.equals("abc");
  }
}

class Bcd implements Predicate<String> {
  
  public boolean test(String s) {
    return s.equals("bcd");
  }
}

class Cde implements Predicate<String> {

  public boolean test(String s) {
    return s.equals("cde");
  }
}

class Def implements Predicate<String> {

  public boolean test(String s) {
    return s.equals("def");
  }
}

class ExamplesDeque {

  Predicate<String> pred1;
  Predicate<String> pred2;
  Predicate<String> pred3;
  Predicate<String> pred4;
  Deque<String> deque1;
  Deque<String> deque2;
  Deque<String> deque3;
  Sentinel<String> s1;
  Sentinel<String> s2;
  Sentinel<String> s3;
  Sentinel<String> mt;
  ANode<String> node1;
  ANode<String> node2;
  ANode<String> node3;
  ANode<String> node4;
  ANode<String> node2A;
  ANode<String> node3A;
  ANode<String> node4A;
  ANode<String> node1B;
  ANode<String> node2B;
  ANode<String> node3B;

  void initData() {
    s1 = new Sentinel<String>();
    mt = new Sentinel<String>();
    deque1 = new Deque<String>(this.s1);
    node1 = new Node<String>("abc", this.s1, this.s1);
    node2 = new Node<String>("bcd", this.s1, this.node1);
    node3 = new Node<String>("cde", this.s1, this.node2);
    node4 = new Node<String>("def", this.s1, this.node3);
    s2 = new Sentinel<String>();
    deque2 = new Deque<String>(this.s2);
    node2A = new Node<String>("bcd", this.s2, this.s2);
    node3A = new Node<String>("cde", this.s2, this.node2A);
    node4A = new Node<String>("def", this.s2, this.node3A);
    s3 = new Sentinel<String>();
    deque3 = new Deque<String>(this.s3);
    node1B = new Node<String>("abc", this.s3, this.s3);
    node2B = new Node<String>("bcd", this.s3, this.node1B);
    node3B = new Node<String>("cde", this.s3, this.node2B);
    pred1 = new Abc();
    pred2 = new Bcd();
    pred3 = new Cde();
    pred4 = new Def();
  }

  // tests addAtHead
  void testAddAtHead(Tester t) {
    this.initData();
    t.checkExpect(this.deque1.header, this.s1);
    this.deque1.addAtHead("abc");
    t.checkExpect(this.deque1.header.next, new Node<String>("abc", this.node1, this.s1));
    this.initData();
    this.deque1.addAtHead("bcd");
    t.checkExpect(this.deque1.header.next, new Node<String>("bcd", this.node1, this.s1));
    this.initData();
    this.deque1.addAtHead("cde");
    t.checkExpect(this.deque1.header.next, new Node<String>("cde", this.node1, this.s1));
  }

  // tests addAtTail
  void testAddAtTail(Tester t) {
    this.initData();
    t.checkExpect(this.deque1.header, this.s1);
    this.deque1.addAtTail("abc");
    t.checkExpect(this.deque1.header.next, new Node<String>("abc", this.s1, this.node4));
    this.initData();
    this.deque1.addAtTail("bcd");
    t.checkExpect(this.deque1.header.next, new Node<String>("bcd", this.s1, this.node4));
    this.initData();
    this.deque1.addAtTail("cde");
    t.checkExpect(this.deque1.header.next, new Node<String>("cde", this.s1, this.node4));
  }

  // tests removeFromHead
  void testRemoveFromHead(Tester t) {
    this.initData();
    t.checkExpect(this.deque1.header, this.s1);
    t.checkExpect(this.deque1.removeFromHead(), "abc");
    t.checkExpect(this.deque1, this.deque2);
    t.checkExpect(this.deque1.removeFromHead(), "bcd");
    t.checkExpect(this.deque1.header.next, this.node3);
    t.checkExpect(this.deque1.removeFromHead(), "cde");
    t.checkExpect(this.deque1.header.next, this.node4);
    t.checkExpect(this.deque1.removeFromHead(), "def");
    t.checkExpect(this.deque1.header.next, this.mt);
    t.checkException(new RuntimeException("Not Allowed"), this.deque1, "removeFromHead");
  }

  // tests removeFromTail
  void testRemoveFromTail(Tester t) {
    this.initData();
    t.checkExpect(this.deque1.header, this.s1);
    t.checkExpect(this.deque1.removeFromTail(), "def");
    t.checkExpect(this.deque1, this.deque3);
    t.checkExpect(this.deque1.removeFromTail(), "cde");
    t.checkExpect(this.deque1.header.prev, this.node2);
    t.checkExpect(this.deque1.removeFromTail(), "bcd");
    t.checkExpect(this.deque1.header.prev, this.node1);
    t.checkExpect(this.deque1.removeFromTail(), "abc");
    t.checkExpect(this.deque1.header.prev, this.mt);
    t.checkException(new RuntimeException("Not Allowed"), this.deque1, "removeFromTail");

  }

  // tests find
  void testFind(Tester t) {
    this.initData();
    t.checkExpect(this.deque1.header, this.s1);
    t.checkExpect(this.deque1.find(pred1), this.node1);
    t.checkExpect(this.deque1.find(pred2), this.node2);
    t.checkExpect(this.deque1.find(pred3), this.node3);
    t.checkExpect(this.deque1.find(pred4), this.node4);
    t.checkExpect(this.deque1.header.next, this.node1);
    t.checkExpect(this.deque1.removeFromHead(), "abc");
    t.checkExpect(this.deque1.find(pred1), this.s1);
    t.checkExpect(this.deque1.removeFromHead(), "bcd");
    t.checkExpect(this.deque1.find(pred2), this.s1);
    t.checkExpect(this.deque1.removeFromHead(), "cde");
    t.checkExpect(this.deque1.find(pred3), this.s1);
    t.checkExpect(this.deque1.removeFromHead(), "def");
    t.checkExpect(this.deque1.find(pred4), this.s1);
  }

  // tests removeNode
  void testRemoveNode(Tester t) {
    this.initData();
    t.checkExpect(this.deque1.header, this.s1);
    this.deque1.removeNode(this.s1);
    t.checkExpect(this.deque1.header, this.s1);
    t.checkExpect(this.deque1.header.next, this.node1);
    this.deque1.removeNode(this.node1);
    t.checkExpect(this.deque1.header.next, this.node2);
    t.checkExpect(this.deque1.header.prev, this.node4);
    this.deque1.removeNode(this.node2);
    t.checkExpect(this.deque1.header.prev, this.node4);
    this.deque1.removeNode(this.node3);
    t.checkExpect(this.deque1.header.prev, this.node4);
    this.deque1.removeNode(this.node4);
    t.checkExpect(this.deque1.header.prev, this.mt);
  }

  // tests size
  void testSize(Tester t) {
    this.initData();
    t.checkExpect(this.deque1.header, this.s1);
    t.checkExpect(this.deque1.size(), 4);
    this.deque1.removeFromHead();
    t.checkExpect(this.deque1.size(), 3);
    this.deque1.removeNode(this.node3);
    t.checkExpect(this.deque1.size(), 2);
    this.deque1.removeFromTail();
    t.checkExpect(this.deque1.size(), 1);
    this.deque1.removeFromTail();
    t.checkExpect(this.deque1.size(), 0);
    this.deque1.removeNode(this.s1);
    t.checkExpect(this.deque1.size(), 0);
  }

  // tests sizeHelp
  void testSizeHelp(Tester t) {
    this.initData();
    t.checkExpect(this.deque1.header, this.s1);
    t.checkExpect(this.deque1.header.sizeHelp(), 4);
    this.deque1.removeFromHead();
    t.checkExpect(this.deque1.header.sizeHelp(), 3);
    this.deque1.removeNode(this.node3);
    t.checkExpect(this.deque1.header.sizeHelp(), 2);
    this.deque1.removeFromTail();
    t.checkExpect(this.deque1.header.sizeHelp(), 1);
    this.deque1.removeFromTail();
    t.checkExpect(this.deque1.header.sizeHelp(), 0);
    this.deque1.removeNode(this.s1);
    t.checkExpect(this.deque1.header.sizeHelp(), 0);
  }

  // tests count
  void testCount(Tester t) {
    this.initData();
    t.checkExpect(this.deque1.header, this.s1);
    t.checkExpect(this.deque1.header.next.count(0), 4);
    this.deque1.removeFromHead();
    t.checkExpect(this.deque1.header.next.count(1), 4);
    this.deque1.removeNode(this.node3);
    t.checkExpect(this.deque1.header.next.count(0), 2);
    this.deque1.removeFromTail();
    t.checkExpect(this.deque1.header.next.count(2), 3);
    this.deque1.removeFromTail();
    t.checkExpect(this.deque1.header.next.count(0), 0);
    this.deque1.removeNode(this.s1);
    t.checkExpect(this.deque1.header.sizeHelp(), 0);
  }

  // tests addBetween
  void testAddBetween(Tester t) {
    this.initData();
    t.checkExpect(this.deque1.header, this.s1);
    this.node1.addBetween("abc", this.s1);
    t.checkExpect(this.deque1.header.next, new Node<String>("abc", this.node1, this.s1));
    this.initData();
    this.node1.addBetween("bcd", this.s1);
    t.checkExpect(this.deque1.header.next, new Node<String>("bcd", this.node1, this.s1));
    this.initData();
    this.node1.addBetween("cde", this.s1);
    t.checkExpect(this.deque1.header.next, new Node<String>("cde", this.node1, this.s1));
    this.initData();
    this.node1.addBetween("def", this.s1);
    t.checkExpect(this.deque1.header.next, new Node<String>("def", this.node1, this.s1));
    this.initData();
    this.s1.addBetween("abc", this.node4);
    t.checkExpect(this.deque1.header.next, new Node<String>("abc", this.s1, this.node4));
    this.initData();
    this.s1.addBetween("bcd", this.node4);
    t.checkExpect(this.deque1.header.next, new Node<String>("bcd", this.s1, this.node4));
    this.initData();
    this.s1.addBetween("cde", this.node4);
    t.checkExpect(this.deque1.header.next, new Node<String>("cde", this.s1, this.node4));
    this.initData();
    this.s1.addBetween("def", this.node4);
    t.checkExpect(this.deque1.header.next, new Node<String>("def", this.s1, this.node4));
  }

  // tests removeBetween
  void testRemoveBetween(Tester t) {
    this.initData();
    t.checkExpect(this.deque1.header, this.s1);
    this.node1.removeBetween(this.s1, this.node2);
    t.checkExpect(this.deque1.header, this.s1);
    t.checkExpect(this.deque1.header.next, this.node2);
    t.checkExpect(this.deque1.header.prev, this.node4);
    this.node2.removeBetween(this.s1, this.node3);
    t.checkExpect(this.deque1.header.next, this.node3);
    t.checkExpect(this.deque1.header.prev, this.node4);
    this.node3.removeBetween(this.s1, this.node4);
    t.checkExpect(this.deque1.header.next, this.node4);
    t.checkExpect(this.deque1.header.prev, this.node4);
    this.node4.removeBetween(this.s1, this.s1);
    t.checkExpect(this.deque1.header.prev, this.mt);
    this.initData();
    t.checkException(new RuntimeException("Not Allowed"), this.s1, "removeBetween", this.node4,
        this.node1);
  }

  // tests findHelp
  void testFindHelp(Tester t) {
    this.initData();
    t.checkExpect(this.deque1.header, this.s1);
    t.checkExpect(this.deque1.header.next.findHelp(pred1), this.node1);
    t.checkExpect(this.deque1.header.next.findHelp(pred2), this.node2);
    t.checkExpect(this.deque1.header.next.findHelp(pred3), this.node3);
    t.checkExpect(this.deque1.header.next.findHelp(pred4), this.node4);
    t.checkExpect(this.deque1.removeFromHead(), "abc");
    t.checkExpect(this.deque1.header.next.findHelp(pred1), this.s1);
    t.checkExpect(this.deque1.removeFromHead(), "bcd");
    t.checkExpect(this.deque1.header.next.findHelp(pred2), this.s1);
    t.checkExpect(this.deque1.removeFromHead(), "cde");
    t.checkExpect(this.deque1.header.next.findHelp(pred3), this.s1);
    t.checkExpect(this.deque1.removeFromHead(), "def");
    t.checkExpect(this.deque1.header.next.findHelp(pred4), this.s1);
  }

  // tests removeNodeHelp
  void testRemoveNodeHelp(Tester t) {
    this.initData();
    t.checkExpect(this.deque1.header, this.s1);
    this.deque1.header.removeNodeHelp();
    t.checkExpect(this.deque1.header, this.s1);
    t.checkExpect(this.deque1.header.next, this.node1);
    this.deque1.header.next.removeNodeHelp();
    t.checkExpect(this.deque1.header.next, this.node2);
    t.checkExpect(this.deque1.header.prev, this.node4);
    this.deque1.header.next.removeNodeHelp();
    t.checkExpect(this.deque1.header.next, this.node3);
    t.checkExpect(this.deque1.header.prev, this.node4);
    this.deque1.header.next.removeNodeHelp();
    t.checkExpect(this.deque1.header.next, this.node4);
    t.checkExpect(this.deque1.header.prev, this.node4);
    this.deque1.header.next.removeNodeHelp();
    t.checkExpect(this.deque1.header.prev, this.mt);
  }

}
