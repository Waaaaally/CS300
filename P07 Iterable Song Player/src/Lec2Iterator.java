import java.util.Iterator;
import java.util.NoSuchElementException;
public class Lec2Iterator implements Iterator<String> {
  // data field
  private Lec2Node<String> nextItem;

  public Lec2Iterator(Lec2Node<String> start) {
    nextItem = start;
  }

  @Override
  public boolean hasNext() {
// do i have something to return if you call next()?
    return nextItem != null;
  }

  @Override
  public String next() {
    if (!hasNext()) throw new NoSuchElementException("nope");
// return the current item and update nextItem to point at the next thing
    String toReturn = nextItem.getData();
    nextItem = nextItem.getNext();

    return toReturn;
  }
}