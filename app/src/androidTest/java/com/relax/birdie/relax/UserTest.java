package com.relax.birdie.relax;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void isStressed() {
        User userForTest = new User("", "", "", 20, 1, 3);
        assertTrue(userForTest.isStressed(160));
    }
}