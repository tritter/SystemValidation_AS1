/*
 * Test data strategy for Game.
 *
 * Generated by JMLUnitNG 1.4 (116/OpenJML-20131218-REV3178), 2014-10-28 13:53 +0100.
 * (do not modify this comment, it is used by JMLUnitNG clean-up routines)
 */

import org.jmlspecs.jmlunitng.iterator.ObjectArrayIterator;
import org.jmlspecs.jmlunitng.iterator.RepeatedAccessIterator;

/**
 * Test data strategy for Game. Provides
 * test values for parameter "String[] args" 
 * of method "void main(String[])". 
 * 
 * @author JMLUnitNG 1.4 (116/OpenJML-20131218-REV3178)
 * @version 2014-10-28 13:53 +0100
 */
public /*@ nullable_by_default */ class Game_main__String1DArray_args__10__args
  extends Game_ClassStrategy_java_lang_String1DArray {
  /**
   * @return local-scope values for parameter 
   *  "String[] args".
   */
  public RepeatedAccessIterator<?> localValues() {
    return new ObjectArrayIterator<Object>
    (new Object[]
     { /* add local-scope java.lang.String[] values or generators here */ });
  }

  /**
   * Constructor.
   * The maximum length of generated arrays can be controlled here for
   * parameter "String[] args" of method "void main(String[])"
   * by changing the parameter to <code>setMaxLength</code>.
   * In addition, the data generators used can be changed by adding 
   * additional data class lines, or by removing some of the automatically 
   * generated ones. Since this is the lowest level of strategy, the 
   * behavior will be exactly as you specify here if you clear the existing 
   * list of classes first.
   *
   * @see NonPrimitiveStrategy#addDataClass(Class<?>)
   * @see NonPrimitiveStrategy#clearDataClasses()
   * @see ArrayStrategy#setMaxLength(int)
   */
  public Game_main__String1DArray_args__10__args() {
    super();
    // uncomment to control the maximum array length, 1 by default
    // setMaxLength(1); 
    // uncomment to control the maximum reflective instantiation
    // recursion depth, 0 by default
    // setMaxRecursionDepth(0);
  }
}
