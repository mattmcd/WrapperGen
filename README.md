WrapperGen
==========

Code generator for function wrapper code to interface between languages e.g. C to Python

This project was mainly intended as a personal learning experience in using 
[ANTLR](http://www.antlr.org),
[StringTemplate](http://www.stringtemplate.org) and 
[Google App Engine](http://developers.google.com/appengine/).

C wrapper for Python is based on the blog post by [Dan Foreman-Mackey](http://danfm.ca/posts/python-c-extensions/).  
Initial iteration only supports C functions returning scalar double and
having arguments (all scalars, all arrays, single length argument). 

Dependencies
============
[ANTLRWorks](http://www.antlr.org/works/index.html) must be on the Java
classpath.  Version 1.4.3 was the version used.

Online
======
Try it at [wrappergen.appspot.com](http://wrappergen.appspot.com) or POST
directly e.g.

    curl http://wrappergen.appspot.com/Wrapper -d input="double test(double a, double b, double *c, int n)"

Example
=======

Input
-----

    double test(double a, double b, double *c, int n)

Output
------
    
    #include <Python.h>
    #include <numpy/arrayobject.h>
    #include "test.h"
    
    /* Docstrings */
    static char module_docstring[] = "";
    static char test_docstring[] = "";
    
    /* Available functions */
    static PyObject *test_test(PyObject *self, PyObject *args);
    
    /* Module specification */
    static PyMethodDef module_methods[] = {
          {"test", test_test, METH_VARARGS, test_docstring},
          {NULL, NULL, 0, NULL}
    };
    
    /* Initialize the module */
    PyMODINIT_FUNC init_test(void)
    {
      PyObject *m = Py_InitModule3("_test", module_methods, module_docstring);
      if (m == NULL)
        return;
    
      /* Load `numpy` functionality. */
      import_array();
    }
    
    static PyObject *test_test(PyObject *self, PyObject *args)
    {
      /* Declare function arguments */
      double a, b; 
      PyObject *c_obj;
    
      /* Parse the input tuple */
      if (!PyArg_ParseTuple(args, "dd0", &a, &b, &c_obj))
        return NULL;
    
      /* Interpret the input objects as numpy arrays. */
      PyObject *c_array = PyArray_FROM_OTF(c_obj, NPY_DOUBLE, NPY_IN_ARRAY);
    
      /* If that didn't work, throw an exception. */
      if ( c_array == NULL ) {
        Py_XDECREF(c_array);
          return NULL;
      }  
    
      /* Array length argument */
      int n = (int) PyArray_DIM(c_array, 0);
    
      /* Get pointers to the data as C-types. */
      double *c = (double *)PyArray_DATA(c_array);
    
      /* Call the external C function */
      double value = test( a, b, c, n );
    
      /* Clean up */ 
      Py_DECREF(c_array);
    
      /* Build the output tuple */
      PyObject *ret = Py_BuildValue( "d", value );
      return ret;
    }
