import java.io.IOException;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.ANTLRStringStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;

public class MyServlet extends HttpServlet {
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws IOException {
      resp.setContentType("text/plain");
      resp.getWriter().println("Hello, world");
    }
  
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws IOException {
      String name = req.getParameter( "name" );

      CharStream is = new ANTLRStringStream( name );

      String processed;
      String filename = "/WEB-INF/PythonWrapper.stg";
      ServletContext context = this.getServletContext();
      String pathname = context.getRealPath( filename );
      try {
        processed = WrapperGen.generate( is, pathname );
      }
      catch ( Exception e ) {
        processed = "FAIL:" + e.getMessage();
      };

      resp.setContentType("text/plain");
      resp.getWriter().println( processed );
    }
}
