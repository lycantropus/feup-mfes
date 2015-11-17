package Stack;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class StackTest {
    private Stack s = new Stack(4L);

    public StackTest() {
    }

    private void assertTrue(final Boolean cond) {
        return;
    }

    private void testPushPop() {
        s.clear();
        s.push(1L);
        s.push(2L);
        assertTrue(Utils.equals(s.top(), 2L));
        s.pop();
        assertTrue(Utils.equals(s.top(), 1L));
        s.pop();
    }

    public static void main() {
        new StackTest().testPushPop();
    }

    public String toString() {
        return "StackTest{" + "s := " + Utils.toString(s) + "}";
    }
}
