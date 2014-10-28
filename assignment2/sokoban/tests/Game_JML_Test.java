/*
 * Test Oracle Class for Game
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
 * Game.
 * 
 * @author JMLUnitNG 1.4 (116/OpenJML-20131218-REV3178)
 * @version 2014-10-28 15:53 +0100
 */

public /*@ nullable_by_default */ class Game_JML_Test {
  /**
   * The main method. Allows the tests to be run without a testng.xml or
   * the use of the TestNG executable/plugin.
   *
   * @param the_args Command line arguments, ignored.
   */
  public static void main(String[] the_args) {
    final TestNG testng_runner = new TestNG();
    final Class<?>[] classes = {Game_JML_Test.class};
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
    (Utils.isRACCompiled(Game_JML_Test.class),
     "JMLUnitNG tests must not be RAC-compiled when using OpenJML RAC.");
    Assert.assertTrue
    (Utils.isRACCompiled(Game.class),
     "JMLUnitNG tests can only run on RAC-compiled code.");
  } 

  /**
   * A test for method toString.
   *
   * @param the_test_object The Game to call the test method on.
   */
  @Test(dependsOnMethods = { "test_racEnabled" }, 
        dataProvider = "p_instance_only")
  public void test_toString__0
  (final Game the_test_object ) {
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
   * A test for method wonGame.
   *
   * @param the_test_object The Game to call the test method on.
   */
  @Test(dependsOnMethods = { "test_racEnabled" }, 
        dataProvider = "p_instance_only")
  public void test_wonGame__0
  (final Game the_test_object ) {
      if (the_test_object == null) {
        throw new PreconditionSkipException
        ("could not construct an object to test");
      }
    try {
      the_test_object.wonGame();
    }
    catch (final JmlAssertionError $e) {
      if ($e.jmlAssertionType.equals("Precondition") &&
          $e.getStackTrace().length >= 4 &&
          "test_wonGame__0".equals($e.getStackTrace()[3].getMethodName())) {
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
   * A test for method movePlayer.
   *
   * @param the_test_object The Game to call the test method on.
   * @param newPosition The Position to be passed.
   */
  @Test(dependsOnMethods = { "test_racEnabled" }, 
        dataProvider = "p_movePlayer__Position_newPosition__0")
  public void test_movePlayer__Position_newPosition__0
  (final Game the_test_object, 
   final Position newPosition) {
      if (the_test_object == null) {
        throw new PreconditionSkipException
        ("could not construct an object to test");
      }
    try {
      the_test_object.movePlayer(newPosition);
    }
    catch (final JmlAssertionError $e) {
      if ($e.jmlAssertionType.equals("Precondition") &&
          $e.getStackTrace().length >= 4 &&
          "test_movePlayer__Position_newPosition__0".equals($e.getStackTrace()[3].getMethodName())) {
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
   * A test for method main.
   *
   * @param the_test_object The Game to call the test method on.
   * @param args The String[] to be passed.
   */
  @Test(dependsOnMethods = { "test_racEnabled" }, 
        dataProvider = "p_main__String1DArray_args__10")
  public void test_static_main__String1DArray_args__10
  ( final java.lang.String[] args) {
    try {
      Game.main(args);
    }
    catch (final JmlAssertionError $e) {
      if ($e.jmlAssertionType.equals("Precondition") &&
          $e.getStackTrace().length >= 4 &&
          "test_static_main__String1DArray_args__10".equals($e.getStackTrace()[3].getMethodName())) {
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
   * @param board The Board to be passed.
   * @param player The Player to be passed.
   */
  @Test(dependsOnMethods = { "test_racEnabled" }, 
        dataProvider = "p_Game__Board_board__Player_player__0")
  public void test_Game__Board_board__Player_player__0
  (final Board board, final Player player) {
    try {
      new Game(board, player);
    }
    catch (final JmlAssertionError $e) {
      if ($e.jmlAssertionType.equals("Precondition") &&
          $e.getStackTrace().length >= 4 &&
          "test_Game__Board_board__Player_player__0".equals($e.getStackTrace()[3].getMethodName())) {
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
   * Data provider for method boolean movePlayer(Position).
   * @return An iterator over strategies to use for parameter generation.
   */
  @SuppressWarnings({"unchecked"})
  @DataProvider(name = "p_movePlayer__Position_newPosition__0", 
                parallel = false)
  public static IteratorWrapper<Object[]> p_movePlayer__Position_newPosition__0() {
    return new IteratorWrapper<Object[]>
    (new ParameterArrayIterator
         (Game_InstanceStrategy.class,
          Game_movePlayer__Position_newPosition__0__newPosition.class));
  }


  /**
   * Data provider for method void main(String[]).
   * @return An iterator over strategies to use for parameter generation.
   */
  @SuppressWarnings({"unchecked"})
  @DataProvider(name = "p_main__String1DArray_args__10", 
                parallel = false)
  public static IteratorWrapper<Object[]> p_main__String1DArray_args__10() {
    return new IteratorWrapper<Object[]>
    (new ParameterArrayIterator
         (Game_main__String1DArray_args__10__args.class));
  }


  /**
   * Data provider for constructor Game(Board, Player).
   * @return An iterator over strategies to use for parameter generation.
   */
  @SuppressWarnings({"unchecked"})
  @DataProvider(name = "p_Game__Board_board__Player_player__0", 
                parallel = false)
  public static IteratorWrapper<Object[]> p_Game__Board_board__Player_player__0() {
    return new IteratorWrapper<Object[]>
    (new ParameterArrayIterator
         (Game_Game__Board_board__Player_player__0__board.class,
          Game_Game__Board_board__Player_player__0__player.class));
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
    (new ParameterArrayIterator(Game_InstanceStrategy.class));
  }
}