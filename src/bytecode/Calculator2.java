package bytecode;
@Marker("Class")
public class Calculator2 {
  @Marker("Field")
  private int result;

  @Marker("Method")
  private void sum( int i1, @Marker("Parameter") int i2) {
    result = i1 + i2;
  }
}