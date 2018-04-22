package com.github.soursop.matrix.operator;

import org.junit.Ignore;
import org.junit.Test;


import static com.github.soursop.matrix.operator.Functions.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author soursop
 * @created 2018. 4. 3.
 */
public class OperatorsTest {
    private double[] sample = new double[]{ 1l, 2l, 3l, 4l, 5l, 6l };
    private double[] transpose = new double[]{ 1l, 3l, 5l, 2l, 4l, 6l };

    @Test
    public void testProductWithMatrix() {
        DoubleMatrix one = new DenseDoubleMatrix(2, sample);
        DoubleMatrix other = new DenseDoubleMatrix(3, transpose);
        DoubleMatrix result = one.product(other).invoke();
        assertThat(asList(result.values()), is(asList(
                1d * 1d + 2d * 2d
                , 1d * 3d + 2d * 4d
                , 1d * 5d + 2d * 6d
                , 3d * 1d + 4d * 2d
                , 3d * 3d + 4d * 4d
                , 3d * 5d + 4d * 6d
                , 5d * 1d + 6d * 2d
                , 5d * 3d + 6d * 4d
                , 5d * 5d + 6d * 6d
        )));
    }

    @Test
    public void testMultiplyWithMatrix() {
        DoubleMatrix one = new DenseDoubleMatrix(2, sample);
        DoubleMatrix result = one.multiply(one).invoke();
        assertThat(asList(result.values()), is(asList(
                1d * 1d
                , 2d * 2d
                , 3d * 3d
                , 4d * 4d
                , 5d * 5d
                , 6d * 6d
        )));
    }

    @Test
    public void testPlusWithMatrix() {
        DoubleMatrix one = new DenseDoubleMatrix(2, sample);
        DoubleMatrix other = new DenseDoubleMatrix(2, sample);
        DoubleMatrix result = one.plus(other).invoke();
        assertThat(asList(result.values()), is(asList(
                1d + 1d
                , 2d + 2d
                , 3d + 3d
                , 4d + 4d
                , 5d + 5d
                , 6d + 6d
        )));
    }

    @Test
    public void testMinusWithMatrix() {
        DoubleMatrix one = new DenseDoubleMatrix(2, sample);
        DoubleMatrix other = new DenseDoubleMatrix(2, sample);
        DoubleMatrix result = one.minus(other).invoke();
        assertThat(asList(result.values()), is(asList(
                1d - 1d
                , 2d - 2d
                , 3d - 3d
                , 4d - 4d
                , 5d - 5d
                , 6d - 6d
        )));
    }

    @Test
    public void testDivideWithOperator() {
        DoubleMatrix one = new DenseDoubleMatrix(2, sample);
        DoubleMatrix result = one.divide(new DoubleOperator(2)).invoke();
        assertThat(asList(result.values()), is(asList(
                1d / 2d
                , 2d / 2d
                , 3d / 2d
                , 4d / 2d
                , 5d / 2d
                , 6d / 2d
        )));
    }

    @Test
    public void testMinusWithIdentity() {
        DenseDoubleMatrix one = new DenseDoubleMatrix(2, sample);
        DoubleMatrix result = one.minus();
        assertThat(asList(result.values()), is(asList(
                -1d
                , -2d
                , -3d
                , -4d
                , -5d
                , -6d
        )));
    }

    @Test
    public void testDivideWithIdentity() {
        DenseDoubleMatrix one = new DenseDoubleMatrix(2, sample);
        DoubleMatrix result = one.divide();
        assertThat(asList(result.values()), is(asList(
                1/1d
                , 1/2d
                , 1/3d
                , 1/4d
                , 1/5d
                , 1/6d
        )));
    }

    @Ignore
    @Test
    public void testAsSimple() {
        DenseDoubleMatrix one = new DenseDoubleMatrix(2, sample);
        DenseDoubleMatrix other = new DenseDoubleMatrix(2, sample);
        Plus minus = one.divide().minus(other).plus(other);
        System.out.println(minus.asSimple(0));
    }

    @Ignore
    @Test
    public void testProductElaps() {
        DoubleRandomIterator a = new DoubleRandomIterator(5000, 25, 0);
        DoubleRandomIterator b = new DoubleRandomIterator(25, 5000, 0);
        DoubleRandomIterator c = new DoubleRandomIterator(5000, 401, 0);
        DoubleMatrix d = new DoubleIterator(1, 25, 5000);
        DoubleMatrix e = new DoubleIterator(1, 5000, 401);
        DenseDoubleMatrix f = new DenseDoubleMatrix(a.width(), a.values());
        DenseDoubleMatrix g = new DenseDoubleMatrix(b.width(), b.values());
        DenseDoubleMatrix h = new DenseDoubleMatrix(c.width(), c.values());

        long s1 = System.currentTimeMillis();
        DoubleMatrix invoke1 = g.product(h).invoke();
        System.out.println("elaps time 1: " + (System.currentTimeMillis() - s1) + " > " + invoke1.asSimple(0));
        DoubleMatrix transpose = f.transpose();
        long s2 = System.currentTimeMillis();
        DoubleMatrix invoke2 = transpose.product(h).invoke();
        System.out.println("elaps time 2: " + (System.currentTimeMillis() - s2) + " > " + invoke2.asSimple(0));
    }

}
