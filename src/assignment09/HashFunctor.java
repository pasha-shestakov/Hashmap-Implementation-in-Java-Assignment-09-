package assignment09;

/**
 * Serves as a guide for how to define a functor that contains a hashing
 * function for String items (i.e., the hash method).
 * 
 * A functor (aka function object) is a function embedded in an object, 
 * so that it may be passed as a parameter to another function.
 * 
 * @author Erin Parker
 * @version March 15, 2018
 */
public interface HashFunctor {

  public int hash(String item);

}