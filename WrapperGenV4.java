import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.stringtemplate.*;
import java.io.FileReader;

public class WrapperGenV4 {

  public static String generate( String input, String templateFile ) 
  throws Exception {
      CharStream is = new ANTLRInputStream( input );
      return generate( is, templateFile );
  }

  public static String generate( String input ) 
  throws Exception {
      CharStream is = new ANTLRInputStream( input );
      return generate( is );
  }

  public static String generate( CharStream input ) throws Exception {
    return generate( input, "PythonWrapper.stg");
  }

  public static String generate( CharStream input, String templateFile ) throws Exception {
    CFunctionLexer lex = new CFunctionLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lex);
    CFunctionParser parser = new CFunctionParser(tokens);
    ParseTree tree = parser.function();

    FileReader groupFileR = new FileReader( templateFile );
    StringTemplateGroup templates = new StringTemplateGroup( groupFileR );
    groupFileR.close();
    StringTemplate t = templates.getInstanceOf( "function" );
    CFunctionWalker listener = new CFunctionWalker( t );
    
    ParseTreeWalker walker = new ParseTreeWalker( );
    walker.walk( listener, tree );

    return listener.out;
  }
}
