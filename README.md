WrapperGen
==========

Code generator for function wrapper code to interface between languages e.g. C to Python

C wrapper for Python is based on the blog post by [Dan Foreman-Mackey](http://danfm.ca/posts/python-c-extensions/).  
Initial iteration only supports C functions returning scalar double and
having arguments (all scalars, all arrays, single length argument).

Dependencies
============
[ANTLRWorks](http://www.antlr.org/works/index.html) must be on the Java
classpath.  Version 1.4.3 was the version used.
