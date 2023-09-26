//Homework 7 - Registrar



import tester.Tester;


interface IList<T> {
  
  //checks if a given element is in the list
  boolean contains(T t, ICompare<T> pred);
  
  //checks if two lists have any of the same elements
  boolean anySame(IList<T> list, ICompare<T> pred);
  
  //counts the number of times an item is in a list
  int howMany(IPred<T> pred);
  
  
}



class MtList<T> implements IList<T> {
  
  //checks if a given element is in the list
  public boolean contains(T t, ICompare<T> pred) {
    return false;
  }
  
  //checks if two lists have any of the same elements
  public boolean anySame(IList<T> list, ICompare<T> pred) {
    return false;
  }
  
  //counts the number of times an item is in a list
  public int howMany(IPred<T> pred) {
    return 0;
  }
  
}



class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }
  
  //checks if a given element is in the list
  public boolean contains(T t, ICompare<T> pred) {
    return pred.apply(t, this.first) || this.rest.contains(t, pred);
  }
  
  //checks if two lists have any of the same elements
  public boolean anySame(IList<T> list, ICompare<T> pred) {
    return list.contains(this.first, pred) || this.rest.anySame(list, pred);
  }
  
  //counts the number of times an item is in a list
  public int howMany(IPred<T> pred) {
    if (pred.apply(this.first)) {
      return 1 + this.rest.howMany(pred);
    }
    else {
      return this.rest.howMany(pred);
    }
  }
  
}



interface ICompare<T> {
  
  //checks if two elements of the same type are equal
  boolean apply(T t1, T t2);
  
}

class CompareStudent implements ICompare<Student> {
  
  //checks if two students are the same
  public boolean apply(Student s1, Student s2) {
    return s1.id == s2.id;
  }
} 


class CompareCourse implements ICompare<Course> {
  
  //checks if two courses are the same
  public boolean apply(Course c1, Course c2 ) {
    return c1.name.equals(c2.name) 
        && c1.prof.name.equals(c2.prof.name); 
  }
}


interface IPred<T> {
  
  //asks a question about a given type
  boolean apply(T t);
}


class StudentEnrolled implements IPred<Course> {
  Student s;
  
  StudentEnrolled(Student s) {
    this.s = s;
  }
  
  //determines if a student is enrolled in the given course
  public boolean apply(Course c) {
    return c.students.contains(this.s, new CompareStudent());
  }
  
}


class Course {
  String name;
  Instructor prof;
  IList<Student> students;

  Course(String name, Instructor prof, IList<Student> students) {
    this.name = name;
    this.prof = prof;
    this.students = students;
    this.prof.courses = new ConsList<Course>(this, prof.courses);
  }
  
  Course(String name, Instructor prof) {
    this(name, prof, new MtList<Student>());
  }
  
  //adds a given student to the students in a course
  public void addStudent(Student s) {
    this.students = new ConsList<Student>(s, this.students);
  }

}


class Instructor {
  String name;
  IList<Course> courses;

  Instructor(String name, IList<Course> courses) {
    this.name = name;
    this.courses = courses;
  }
  
  Instructor(String name) {
    this(name, new MtList<Course>());
  }
  
  //determines if a given student is in more than one of the instructors courses
  boolean dejavu(Student s) {
    return this.courses.howMany(new StudentEnrolled(s)) > 1;
  }

}



class Student {
  String name;
  int id;
  IList<Course> courses;

  Student(String name, int id, IList<Course> courses) {
    this.name = name;
    this.id = id;
    this.courses = courses;
  }
  
  Student(String name, int id) {
    this(name, id, new MtList<Course>());
  }
  

  //enroll the student in a given course
  void enroll(Course c) {
    this.courses = new ConsList<Course>(c, this.courses);
  }
  
  boolean sameStudent(Student s) {
    return this.id == s.id;
  }
  
  boolean classmates(Student s) {
    return this.courses.anySame(s.courses, new CompareCourse());
  }

}

class ExamplesRegistrar {
  IList<Course> mtcourses = new MtList<Course>();
  IList<Student> mtstudents = new MtList<Student>();

  // comparing predicates
  ICompare<Student> compareStudent = new CompareStudent();
  ICompare<Course> compareCourse = new CompareCourse();
  // IPred<Course> studentEnrolled = new StudentEnrolled(Chloe);

  // examples students
  Student chloe;
  Student alex;
  Student mary;
  Student jane;

  Instructor razzaq;
  Instructor wren;
  Instructor lopez;
  Instructor casbi;

  Course fundies;
  Course bio;
  Course chem;
  Course math;
  Course fundies2;

  // initialize data
  void initiData() {
    chloe = new Student("Chloe", 40);
    alex = new Student("Alex", 20);
    mary = new Student("Mary", 70);
    jane = new Student("Jane", 30);
    razzaq = new Instructor("Razzaq");
    wren = new Instructor("Wren");
    lopez = new Instructor("Lopez");
    casbi = new Instructor("Casbi");
    fundies = new Course("Fundies", this.razzaq);
    bio = new Course("Biology", this.wren);
    chem = new Course("Chemistry", this.lopez);
    math = new Course("Math", this.casbi);
    fundies2 = new Course("Fundies2", this.razzaq);
  }

  // test enroll
  void testEnroll(Tester t) {
    this.initiData();
    // add chloe to fundies
    t.checkExpect(this.chloe.courses, mtcourses);
    this.chloe.enroll(fundies);
    t.checkExpect(this.chloe.courses, new ConsList<Course>(fundies, mtcourses));
    // add alex to bio
    t.checkExpect(this.alex.courses, mtcourses);
    this.alex.enroll(bio);
    t.checkExpect(this.alex.courses, new ConsList<Course>(bio, mtcourses));
    // add alex to math
    this.alex.enroll(math);
    t.checkExpect(this.alex.courses,
        new ConsList<Course>(math, new ConsList<Course>(bio, mtcourses)));
  }

  // test addStudent
  void testAddStudent(Tester t) {
    this.initiData();
    // adding mary to chem
    t.checkExpect(this.chem.students, mtstudents);
    this.chem.addStudent(mary);
    t.checkExpect(this.chem.students, new ConsList<Student>(mary, mtstudents));
    // adding jane to math
    t.checkExpect(this.math.students, mtstudents);
    this.math.addStudent(jane);
    t.checkExpect(this.math.students, new ConsList<Student>(jane, mtstudents));
    // adding chloe to chem
    t.checkExpect(this.chem.students, new ConsList<Student>(mary, mtstudents));
    this.math.addStudent(chloe);
    t.checkExpect(this.math.students,
        new ConsList<Student>(chloe, new ConsList<Student>(jane, mtstudents)));
  }

  // test contains
  void testContains(Tester t) {
    this.initiData();
    // checking if chloe is in bio
    t.checkExpect(this.chloe.courses, mtcourses);
    this.chloe.courses.contains(bio, compareCourse);
    t.checkExpect(this.chloe.courses, mtcourses);
    t.checkExpect(this.chloe.courses.contains(bio, compareCourse), false);
    // checking if chloe is in bio after being added
    this.chloe.enroll(this.bio);
    t.checkExpect(this.chloe.courses, new ConsList<Course>(this.bio, mtcourses));
    t.checkExpect(this.chloe.courses.contains(bio, compareCourse), true);
  }

  // test classmates
  void testClassmates(Tester t) {
    this.initiData();
    // checking if jane and mary are in any classes together
    t.checkExpect(this.jane.courses, mtcourses);
    t.checkExpect(this.mary.courses, mtcourses);
    t.checkExpect(this.jane.classmates(mary), false);
    // checking if mary and jane are in any classes together after mary is added to
    // fundies and jane is added to chem
    this.mary.enroll(fundies);
    this.jane.enroll(chem);
    t.checkExpect(this.mary.classmates(jane), false);
    // checking to see if mary and jane are in any classes together after mary is
    // added
    // to chem
    this.mary.enroll(chem);
    t.checkExpect(this.jane.classmates(this.mary), true);
  }

  // test dejavu
  void testDejavu(Tester t) {
    this.initiData();
    // checking if alex is in more than one of the classes Razzaq teaches
    this.razzaq.courses = new ConsList<Course>(this.fundies,
        new ConsList<Course>(this.fundies2, mtcourses));
    t.checkExpect(this.alex.courses, mtcourses);
    t.checkExpect(this.razzaq.dejavu(this.alex), false);
    // checking if alex is in more than one of the classes Razzaq teaches after Mike
    // is
    // added to fundies2
    this.alex.enroll(this.fundies2);
    t.checkExpect(this.razzaq.dejavu(this.alex), false);
    // checking if alex is in more than one of the classes Razzaq teaches Mike is
    // added to bio
    this.alex.enroll(this.bio);
    t.checkExpect(this.razzaq.dejavu(this.alex), false);
    // checking if alex is in more than one of the classes Razzaq teaches after Mike
    // is
    // added to fundies1
    this.alex.enroll(this.fundies);
    t.checkExpect(this.razzaq.dejavu(this.alex), true);
    // checking if alex is in more than one of the classes casbi teaches
    t.checkExpect(this.casbi.dejavu(this.alex), false);
  }

}
