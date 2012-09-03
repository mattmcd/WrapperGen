import org.antlr.runtime.*;
import java.io.FileReader;

public class Main {
  public static void main(String[] args) throws Exception {
    CharStream input = new ANTLRFileStream(args[0]);
    String output = WrapperGen.generate( input );
    System.out.println( output );
  }
}
