package com.github.soursop.matrix.operator;

public class DoubleOperator extends AbstractOperator {
    static DoubleOperator NONE = of(1l);
    private final double value;

    public DoubleOperator(double value) {
        this.value = value;
    }

    public static DoubleOperator of(double value) {
        return new DoubleOperator(value);
    }

    public double getValue() {
        return value;
    }

    @Override
    public DoubleMatrix asDoubleMatrix() {
        return DoubleMatrix.NONE;
    }

    @Override
    public DoubleOperator asDoubleOperator() {
        return this;
    }

    @Override
    public CharSequence asSimple(int depth) {
        return withPadding(depth).append(value);
    }

    public DoubleMatrix toIterator(int height) {
        return this.equals(NONE)? DoubleMatrix.NONE : new DoubleIterator(value, height);
    }

    @Override
    public Operators asOperators() {
        return None;
    }
}
