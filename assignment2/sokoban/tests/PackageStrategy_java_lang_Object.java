/*
 * Test data strategy for package .
 *
 * Generated by JMLUnitNG 1.4 (116/OpenJML-20131218-REV3178), 2014-10-28 13:23 +0100.
 * (do not modify this comment, it is used by JMLUnitNG clean-up routines)
 */
import org.jmlspecs.jmlunitng.iterator.ObjectArrayIterator;
import org.jmlspecs.jmlunitng.iterator.RepeatedAccessIterator;
import org.jmlspecs.jmlunitng.strategy.ObjectStrategy;

/**
 * Test data strategy for package <default>. Provides
 * package-scope test values for type java.lang.Object.
 * 
 * @author JMLUnitNG 1.4 (116/OpenJML-20131218-REV3178)
 * @version 2014-10-28 13:23 +0100
 */
public /*@ nullable_by_default */ class PackageStrategy_java_lang_Object 
  extends ObjectStrategy {
  /**
   * @return package-scope values for type java.lang.Object.
   */
  public RepeatedAccessIterator<?> packageValues() {
    return new ObjectArrayIterator<Object>
    (new Object[] 
     { /* add package-scope java.lang.Object values or generators here */ });
  }

  /**
   * Constructor. 
   * The use of reflection can be controlled here for method 
   * parameters of type java.lang.Object
   * in this package by changing the parameters to <code>setReflective</code>
   * and <code>setMaxRecursionDepth<code>.
   * In addition, the data generators used can be changed by adding 
   * additional data class lines, or by removing some of the automatically 
   * generated ones. Note that lower-level strategies can override any 
   * behavior specified here by calling the same control methods in 
   * their own constructors.
   *
   * @see NonPrimitiveStrategy#addDataClass(Class<?>)
   * @see NonPrimitiveStrategy#clearDataClasses()
   * @see ObjectStrategy#setReflective(boolean)
   * @see ObjectStrategy#setMaxRecursionDepth(int)
   */
  public PackageStrategy_java_lang_Object() {
    super(java.lang.Object.class);
    setReflective(true);
    // uncomment to control the maximum reflective instantiation
    // recursion depth, 0 by default
    // setMaxRecursionDepth(0);
  }
}
