public class Lec2Node <MyType> {
  // data fields
  private Lec2Node<MyType> next;
  //private Lec2Node<MyType> prev;
  private MyType data;

  // constructors
  public Lec2Node(MyType data) {
    this(/*null,*/ null, data);
  }

  public Lec2Node(/*Lec2Node<MyType> prev,*/ Lec2Node<MyType> next, MyType
          data) {
    this.next = next;
//this.prev = prev;
    this.data = data;
  }

  // accessors
  public Lec2Node<MyType> getNext() {
    return this.next;
  }

  //public Lec2Node<MyType> getPrev() { return this.prev; }
  public MyType getData() {
    return this.data;
  }

  // mutators
  public void setNext(Lec2Node<MyType> newNext) {
    this.next = newNext;
  }
//public void setPrev(Lec2Node<MyType> newPrev) { this.prev = newPrev; }
}