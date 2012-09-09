import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import org.antlr.stringtemplate.*;
import java.io.FileReader;

public class WrapperGen {

  
  public static String generate( CharStream input ) throws Exception {
    return generate( input, "PythonWrapper.stg");
  }

  public static String generate( CharStream input, String templateFile ) throws Exception {
    CFunctionLexer lex = new CFunctionLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lex);
    // System.out.println("tokens="+tokens);
    CFunctionParser parser = new CFunctionParser(tokens);
    CFunctionParser.function_return r = parser.function();

    CommonTree t = (CommonTree) r.getTree();
    CommonTreeNodeStream nodes = new CommonTreeNodeStream( t );
    nodes.setTokenStream( tokens );

    FileReader groupFileR = new FileReader( templateFile );
    StringTemplateGroup templates = new StringTemplateGroup( groupFileR );
    groupFileR.close();

    WrapperWalker walker = new WrapperWalker( nodes );
    walker.setTemplateLib( templates );
    WrapperWalker.function_return ret = walker.function();
    return ret.getTemplate().toString();
  }
}
