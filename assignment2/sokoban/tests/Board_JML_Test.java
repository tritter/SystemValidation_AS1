/*
 * Test Oracle Class for Board
 * For Use With OpenJML RAC
 *
 * Generated by JMLUnitNG 1.4 (116/OpenJML-20131218-REV3178), 2014-10-28 15:53 +0100.
 * (do not modify this comment, it is used by JMLUnitNG clean-up routines)
 */

import java.io.PrintWriter;
import java.util.ArrayList;

import org.jmlspecs.jmlunitng.iterator.IteratorWrapper;
import org.jmlspecs.jmlunitng.iterator.ParameterArrayIterator;
import org.jmlspecs.jmlunitng.testng.BasicTestListener;
import org.jmlspecs.jmlunitng.testng.PreconditionSkipException;
import org.testng.Assert;
import org.testng.TestException;
import org.testng.TestNG;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlSuite;

import org.jmlspecs.utils.JmlAssertionError;
import org.jmlspecs.utils.Utils; 

/**
 * Test oracles generated by JMLUnitNG for OpenJML RAC of class
 * Board.
 * 
 * @author JMLUnitNG 1.4 (116/OpenJML-20131218-REV3178)
 * @version 2014-10-28 15:53 +0100
 */

public /*@ nullable_by_default */ class Board_JML_Test {
  /**
   * The main method. Allows the tests to be run without a testng.xml or
   * the use of the TestNG executable/plugin.
   *
   * @param the_args Command line arguments, ignored.
   */
  public static void main(String[] the_args) {
    final TestNG testng_runner = new TestNG();
    final Class<?>[] classes = {Board_JML_Test.class};
    final BasicTestListener listener =
      new BasicTestListener(new PrintWriter(System.out));
    testng_runner.setUseDefaultListeners(false);
    testng_runner.setXmlSuites(new ArrayList<XmlSuite>());
    testng_runner.setTestClasses(classes);
    testng_runner.addListener(listener);
    testng_runner.run();
  }

  /** 
   * A test to ensure that RAC is enabled before running other tests;
   * this also turns on RAC exceptions if they were not already turned on.
   */
  @Test
  public void test_racEnabled() {
    Utils.useExceptions = true;
    Assert.assertFalse
    (Utils.isRACCompiled(Board_JML_Test.class),
     "JMLUnitNG tests must not be RAC-compiled when using OpenJML RAC.");
    Assert.assertTrue
    (Utils.isRACCompiled(Board.class),
     "JMLUnitNG tests can only run on RAC-compiled code.");
  } 

  /**
   * A test for method isOpen.
   *
   * @param the_test_object The Board to call the test method on.
   * @param p The Position to be passed.
   */
  @Test(dependsOnMethods = { "test_racEnabled" }, 
        dataProvider = "p_isOpen__Position_p__0")
  public void test_isOpen__Position_p__0
  (final Board the_test_object, 
   final Position p) {
      if (the_test_object == null) {
        throw new PreconditionSkipException
        ("could not construct an object to test");
      }
    try {
      the_test_object.isOpen(p);
    }
    catch (final JmlAssertionError $e) {
      if ($e.jmlAssertionType.equals("Precondition") &&
          $e.getStackTrace().length >= 4 &&
          "test_isOpen__Position_p__0".equals($e.getStackTrace()[3].getMethodName())) {
        // meaningless test because precondition failed
        throw new PreconditionSkipException($e.getMessage());
      } else {
        // test failure because something else failed
        throw new TestException($e.getMessage());
      }
    } catch (final Throwable $e) {
      // test failure for some reason other than assertion violation
      throw new TestException($e.getMessage());
    }
  }

  /**
   * A test for method toString.
   *
   * @param the_test_object The Board to call the test method on.
   */
  @Test(dependsOnMethods = { "test_racEnabled" }, 
        dataProvider = "p_instance_only")
  public void test_toString__0
  (final Board the_test_object ) {
      if (the_test_object == null) {
        throw new PreconditionSkipException
        ("could not construct an object to test");
      }
    try {
      the_test_object.toString();
    }
    catch (final JmlAssertionError $e) {
      if ($e.jmlAssertionType.equals("Precondition") &&
          $e.getStackTrace().length >= 4 &&
          "test_toString__0".equals($e.getStackTrace()[3].getMethodName())) {
        // meaningless test because precondition failed
        throw new PreconditionSkipException($e.getMessage());
      } else {
        // test failure because something else failed
        throw new TestException($e.getMessage());
      }
    } catch (final Throwable $e) {
      // test failure for some reason other than assertion violation
      throw new TestException($e.getMessage());
    }
  }

  /**
   * A test for a constructor.
   *
   * @param sizeX The int to be passed.
   * @param sizeY The int to be passed.
   */
  @Test(dependsOnMethods = { "test_racEnabled" }, 
        dataProvider = "p_Board__int_sizeX__int_sizeY__0")
  public void test_Board__int_sizeX__int_sizeY__0
  (final int sizeX, final int sizeY) {
    try {
      new Board(sizeX, sizeY);
    }
    catch (final JmlAssertionError $e) {
      if ($e.jmlAssertionType.equals("Precondition") &&
          $e.getStackTrace().length >= 4 &&
          "test_Board__int_sizeX__int_sizeY__0".equals($e.getStackTrace()[3].getMethodName())) {
        // meaningless test because precondition failed
        throw new PreconditionSkipException($e.getMessage());
      } else {
        // test failure because something else failed
        throw new TestException($e.getMessage());
      }
    } catch (final Throwable $e) {
      // test failure for some reason other than assertion violation
      throw new TestException($e.getMessage());
    }
  }

  /**
   * A test for method onBoard.
   *
   * @param the_test_object The Board to call the test method on.
   * @param p The Position to be passed.
   */
  @Test(dependsOnMethods = { "test_racEnabled" }, 
        dataProvider = "p_onBoard__Position_p__0")
  public void test_onBoard__Position_p__0
  (final Board the_test_object, 
   final Position p) {
      if (the_test_object == null) {
        throw new PreconditionSkipException
        ("could not construct an object to test");
      }
    try {
      the_test_object.onBoard(p);
    }
    catch (final JmlAssertionError $e) {
      if ($e.jmlAssertionType.equals("Precondition") &&
          $e.getStackTrace().length >= 4 &&
          "test_onBoard__Position_p__0".equals($e.getStackTrace()[3].getMethodName())) {
        // meaningless test because precondition failed
        throw new PreconditionSkipException($e.getMessage());
      } else {
        // test failure because something else failed
        throw new TestException($e.getMessage());
      }
    } catch (final Throwable $e) {
      // test failure for some reason other than assertion violation
      throw new TestException($e.getMessage());
    }
  }

  /**
   * A test for method onBoard.
   *
   * @param the_test_object The Board to call the test method on.
   * @param x The int to be passed.
   * @param y The int to be passed.
   */
  @Test(dependsOnMethods = { "test_racEnabled" }, 
        dataProvider = "p_onBoard__int_x__int_y__0")
  public void test_onBoard__int_x__int_y__0
  (final Board the_test_object, 
   final int x, final int y) {
      if (the_test_object == null) {
        throw new PreconditionSkipException
        ("could not construct an object to test");
      }
    try {
      the_test_object.onBoard(x, y);
    }
    catch (final JmlAssertionError $e) {
      if ($e.jmlAssertionType.equals("Precondition") &&
          $e.getStackTrace().length >= 4 &&
          "test_onBoard__int_x__int_y__0".equals($e.getStackTrace()[3].getMethodName())) {
        // meaningless test because precondition failed
        throw new PreconditionSkipException($e.getMessage());
      } else {
        // test failure because something else failed
        throw new TestException($e.getMessage());
      }
    } catch (final Throwable $e) {
      // test failure for some reason other than assertion violation
      throw new TestException($e.getMessage());
    }
  }

  /**
   * A test for method isOpen.
   *
   * @param the_test_object The Board to call the test method on.
   * @param x The int to be passed.
   * @param y The int to be passed.
   */
  @Test(dependsOnMethods = { "test_racEnabled" }, 
        dataProvider = "p_isOpen__int_x__int_y__0")
  public void test_isOpen__int_x__int_y__0
  (final Board the_test_object, 
   final int x, final int y) {
      if (the_test_object == null) {
        throw new PreconditionSkipException
        ("could not construct an object to test");
      }
    try {
      the_test_object.isOpen(x, y);
    }
    catch (final JmlAssertionError $e) {
      if ($e.jmlAssertionType.equals("Precondition") &&
          $e.getStackTrace().length >= 4 &&
          "test_isOpen__int_x__int_y__0".equals($e.getStackTrace()[3].getMethodName())) {
        // meaningless test because precondition failed
        throw new PreconditionSkipException($e.getMessage());
      } else {
        // test failure because something else failed
        throw new TestException($e.getMessage());
      }
    } catch (final Throwable $e) {
      // test failure for some reason other than assertion violation
      throw new TestException($e.getMessage());
    }
  }

  /**
   * Data provider for method boolean isOpen(Position).
   * @return An iterator over strategies to use for parameter generation.
   */
  @SuppressWarnings({"unchecked"})
  @DataProvider(name = "p_isOpen__Position_p__0", 
                parallel = false)
  public static IteratorWrapper<Object[]> p_isOpen__Position_p__0() {
    return new IteratorWrapper<Object[]>
    (new ParameterArrayIterator
         (Board_InstanceStrategy.class,
          Board_isOpen__Position_p__0__p.class));
  }



  /**
   * Data provider for constructor Board(int, int).
   * @return An iterator over strategies to use for parameter generation.
   */
  @SuppressWarnings({"unchecked"})
  @DataProvider(name = "p_Board__int_sizeX__int_sizeY__0", 
                parallel = false)
  public static IteratorWrapper<Object[]> p_Board__int_sizeX__int_sizeY__0() {
    return new IteratorWrapper<Object[]>
    (new ParameterArrayIterator
         (Board_Board__int_sizeX__int_sizeY__0__sizeX.class,
          Board_Board__int_sizeX__int_sizeY__0__sizeY.class));
  }


  /**
   * Data provider for method boolean onBoard(Position).
   * @return An iterator over strategies to use for parameter generation.
   */
  @SuppressWarnings({"unchecked"})
  @DataProvider(name = "p_onBoard__Position_p__0", 
                parallel = false)
  public static IteratorWrapper<Object[]> p_onBoard__Position_p__0() {
    return new IteratorWrapper<Object[]>
    (new ParameterArrayIterator
         (Board_InstanceStrategy.class,
          Board_onBoard__Position_p__0__p.class));
  }


  /**
   * Data provider for method boolean onBoard(int, int).
   * @return An iterator over strategies to use for parameter generation.
   */
  @SuppressWarnings({"unchecked"})
  @DataProvider(name = "p_onBoard__int_x__int_y__0", 
                parallel = false)
  public static IteratorWrapper<Object[]> p_onBoard__int_x__int_y__0() {
    return new IteratorWrapper<Object[]>
    (new ParameterArrayIterator
         (Board_InstanceStrategy.class,
          Board_onBoard__int_x__int_y__0__x.class,
                  Board_onBoard__int_x__int_y__0__y.class));
  }


  /**
   * Data provider for method boolean isOpen(int, int).
   * @return An iterator over strategies to use for parameter generation.
   */
  @SuppressWarnings({"unchecked"})
  @DataProvider(name = "p_isOpen__int_x__int_y__0", 
                parallel = false)
  public static IteratorWrapper<Object[]> p_isOpen__int_x__int_y__0() {
    return new IteratorWrapper<Object[]>
    (new ParameterArrayIterator
         (Board_InstanceStrategy.class,
          Board_isOpen__int_x__int_y__0__x.class,
                  Board_isOpen__int_x__int_y__0__y.class));
  }


  /**
   * Data provider for methods with no parameters.
   * @return An iterator over the main class strategy.
   */
  @SuppressWarnings({"unchecked"})
  @DataProvider(name = "p_instance_only", 
                parallel = false)
  public static IteratorWrapper<Object[]> p_instance_only() {
    return new IteratorWrapper<Object[]>
    (new ParameterArrayIterator(Board_InstanceStrategy.class));
  }
}