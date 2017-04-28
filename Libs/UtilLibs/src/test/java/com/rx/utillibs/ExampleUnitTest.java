package com.rx.utillibs;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        Date date = new Date("Thu Apr 25 23:01:03 +0800 2016");
        System.out.println(DateFormatUtil.format(date));
    }
}