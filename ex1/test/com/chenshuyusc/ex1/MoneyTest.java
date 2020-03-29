package com.chenshuyusc.ex1;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

/**
 * Money Tester.
 *
 * @author <Chen Shuyu>
 * @version 1.0
 * @since <pre>Mar 2, 2020</pre>
 */
@RunWith(Parameterized.class)
public class MoneyTest {
    private boolean expected;
    private int num;
    private static Money money = null;

    // initialized
    public MoneyTest(boolean expected, int num) {
        this.expected = expected;
        this.num = num;
    }

    @Before
    public void before() throws Exception {
        money = new Money();
    }

    /**
     * this function must be static function
     *
     * @return test case
     */
    @Parameterized.Parameters
    public static Collection<Object[]> getData() {

        ArrayList<Object[]> datas = new ArrayList<Object[]>();

        // get all true case
        Set<Integer> cases = Money.allCase();
        for (int i = 0; i < 100; i++) {
            // i whether in set
            datas.add(new Object[]{cases.contains(i), i});
        }
        for (Object[] temp : datas) {
            System.out.println(temp);
        }
        return datas;
    }

    @After
    public void after() throws Exception {

    }

    /**
     * Method: findMoney(int num)
     */
    @Test
    public void testFindMoney() throws Exception {
        Assert.assertEquals(this.expected, money.findMoney(this.num));
    }


}
