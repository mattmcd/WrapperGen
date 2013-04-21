import org.antlr.v4.runtime.*;
import java.io.FileReader;

public class MainV4 {
  public static void main(String[] args) throws Exception {
    CharStream input = new ANTLRFileStream(args[0]);
    String templateFile;
    if ( args.length > 1 ) {
        templateFile = args[1];
    } else {
        templateFile = "PythonWrapper.stg";
    }
    String output = WrapperGenV4.generate( input, templateFile );
    System.out.println( output );
  }
}
