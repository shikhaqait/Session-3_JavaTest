package com.qait.javatest.open;

import static com.qait.test.open.Util.asSet;
import java.util.Scanner;

import org.testng.annotations.Test;
import static com.qait.test.open.Util.ival;
import static com.qait.test.open.Util.list;
import static com.qait.test.open.Util.set;
import java.util.Collections;
import java.util.List;
import junit.framework.TestCase;
import com.qait.test.open.Command;
import com.qait.test.open.Exercises;
import com.qait.test.open.FooException;
import com.qait.test.open.Interval;
import com.qait.test.open.Util;

public final class ExercisesTest extends TestCase {
    public final Exercises exercises = new Exercises();

    @Test
    public void testCensor() {
    	System.out.println("Enter string");
    	Scanner scan = new Scanner (System.in);
    	String text = scan.nextLine();
        try {
          exercises.censor(text, Util.<String>set());  

        } catch (IllegalArgumentException expected) {
        	System.out.println(expected.getMessage());
        }

        {
            final String helloWorld = "Hello world how's it going ?";

            assertEquals(helloWorld, exercises.censor(helloWorld, Util.<String>set()));

            assertEquals(helloWorld, exercises.censor(helloWorld, set("foo", "bar")));

            assertEquals("Hello ***** how's it going ?", exercises.censor(helloWorld, set("world")));

            assertEquals("Hello ***** how's ** ***** ?", exercises.censor(helloWorld, set("world", "it", "going")));

            assertEquals("***** ***** how's ** ***** ?", exercises.censor(helloWorld, set("Hello", "world", "it", "going")));
        }
    }

    public abstract class MockCommand implements Command {
        boolean closeInvoked = false;
        boolean performInvoked = false;

        @Override
        public void close() {
            closeInvoked = true;
        }
    }

    public class AlwaysWorksMockCommand extends MockCommand {

        public final String toReturn;

        public AlwaysWorksMockCommand(final String toReturn) {
            super();
            this.toReturn = toReturn;
        }

        @Override
        public String perform() {
            performInvoked = true;

            return toReturn;
        }
    }

    public final class AlwaysThrowsMockCommand extends AlwaysWorksMockCommand {
        public AlwaysThrowsMockCommand() {
            super(null);
        }

        @Override
        public String perform() {
            super.perform();

            throw new FooException("foo");
        }
    }

    public void assertMethodsInvoked(final MockCommand mockCommand) {
        assertTrue(mockCommand.closeInvoked);
        assertTrue(mockCommand.performInvoked);
    }

    @Test
    public void testExceptionalHandling() {
        // No exception thrown by Command
        {
            final String notExceptionalText = "Not very exceptional";

            final MockCommand notExceptional = new AlwaysWorksMockCommand(notExceptionalText);

            assertEquals(notExceptionalText, exercises.exceptionalHandling(notExceptional, "foo"));

            assertMethodsInvoked(notExceptional);
        }

        // Exception thrown by Command
        {
            final MockCommand exceptional = new AlwaysThrowsMockCommand();

            assertEquals("foo", exercises.exceptionalHandling(exceptional, "foo"));

            assertMethodsInvoked(exceptional);
        }

        // Exception thrown by Command, default text is null
        {
            final MockCommand exceptional = new AlwaysThrowsMockCommand();

            try {
                exercises.exceptionalHandling(exceptional, null);

                //fail("Should have thrown");
            } catch (Exception expected) {
                assertEquals("foo", exercises.exceptionalHandling(exceptional, "foo"));
            }

            assertMethodsInvoked(exceptional);
        }
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(exercises.isPalindrome("Racecar"));

        assertFalse(exercises.isPalindrome("palindrome"));

        assertTrue(exercises.isPalindrome("Able was I ere i saw Elba"));

        assertFalse(exercises.isPalindrome("This is not "));

        assertFalse(exercises.isPalindrome(null));
    }

    @Test
    public void testPascal() {
        assertEquals(1, exercises.pascal(0, 0));

        assertEquals(1, exercises.pascal(0, 2));

        assertEquals(2, exercises.pascal(1, 2));

        assertEquals(3, exercises.pascal(1, 3));

        assertEquals(4, exercises.pascal(1, 4));

        assertEquals(1, exercises.pascal(4, 4));

        assertEquals(28, exercises.pascal(6, 8));

        assertEquals(-1, exercises.pascal(-1, 0));

        assertEquals(-1, exercises.pascal(10, 1));
    }

    @Test
    public void testIntervals() {
        {
            assertEquals(Collections.emptyList(), exercises.mergeIntervals(Collections.<Interval> emptyList()));
        }

        try {
            exercises.mergeIntervals(null);

            fail("Should have thrown");
        } catch (Exception expected) {}

        {
            final List<Interval> intervals = list(ival(1, 2));

            assertEquals(intervals, exercises.mergeIntervals(intervals));
        }

        {
            final List<Interval> intervals = list(ival(-1, 2), ival(6, 8), ival(-3, 0), ival(6, 6), ival(0, 0), ival(7, 9), ival(7, 8));

            final List<Interval> expected = list(ival(-3, 2), ival(6, 9));

            assertEquals(asSet(expected), asSet(exercises.mergeIntervals(intervals)));
        }

        {
            final List<Interval> intervals = list(ival(-1, -1), ival(-1, -1), ival(0, 0), ival(6, 6), ival(0, 0), ival(0, 0), ival(0, 0));

            final List<Interval> expected = list(ival(-1, -1), ival(0, 0), ival(6, 6));

            assertEquals(asSet(expected), asSet(exercises.mergeIntervals(intervals)));
        }
        
        {
        	final List<Interval> testInput= list(ival(11, 13), ival(1,2), ival(1,1), ival(0,9), ival(1,9), ival(6,7), ival(3,10),
        			ival(13,14), ival(12,13));

        	final List<Interval> expected = list(ival(0,10), ival(11,14));
        	
        	assertEquals(asSet(expected), asSet(exercises.mergeIntervals(testInput)));
        }
        
        
    }
}
    
