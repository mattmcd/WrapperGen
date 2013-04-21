// Generated from CFunction.g4 by ANTLR 4.0

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.stringtemplate.*;

import java.util.List;
import java.util.LinkedList;

public class CFunctionWalker extends CFunctionBaseListener {
  List<String> scalarArgs = new LinkedList<String>();
  List<String> arrayArgs = new LinkedList<String>();
  String functionName;
  List<String> lengthArgs = new LinkedList<String>();
  StringTemplate t;
  public String out;

  public CFunctionWalker( StringTemplate t_ ) {
    t = t_;
  }

	public void enterSCALAR_ARG(CFunctionParser.SCALAR_ARGContext ctx) { 
    scalarArgs.add( ctx.name().getText());  
  }

  public void enterARRAY_ARG(CFunctionParser.ARRAY_ARGContext ctx) { 
    arrayArgs.add( ctx.name().getText());  
  }

	@Override public void enterSCALAR_RET(CFunctionParser.SCALAR_RETContext ctx) { }
	@Override public void exitSCALAR_RET(CFunctionParser.SCALAR_RETContext ctx) { }

	@Override public void enterLENGTH_ARG(CFunctionParser.LENGTH_ARGContext ctx) { 
    lengthArgs.add( ctx.name().getText());  
  }

	@Override public void enterARRAY_RET(CFunctionParser.ARRAY_RETContext ctx) { }
	@Override public void exitARRAY_RET(CFunctionParser.ARRAY_RETContext ctx) { }

	public void enterFunction(CFunctionParser.FunctionContext ctx) { 
    functionName = ctx.name().getText();  
  }
	
  public void exitFunction(CFunctionParser.FunctionContext ctx) { 
    /*
    System.out.println( "Array arguments : " );
    for ( String array : arrayArgs ) System.out.println( array ); 
    System.out.println( "Scalar arguments : " );
    for ( String scalar : scalarArgs ) System.out.println( scalar ); 
    */

    t.setAttribute("name", functionName );
    t.setAttribute("len", lengthArgs );
    t.setAttribute("scalars", scalarArgs );
    t.setAttribute("arrays", arrayArgs );
    out = t.toString();
  }
}
