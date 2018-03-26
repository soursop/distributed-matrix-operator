package com.github.soursop.matrix.operator;

import java.util.List;

public class Append extends AbstractOperators {
    protected Append(List<Operator> operators) {
        super(operators);
    }

    private DoubleMatrix asAppend(DoubleMatrix one, DoubleMatrix another) {
        return new LinkedDoubleMatrix(one, another);
    }

    private Operator append(DoubleMatrix matrix, DoubleOperator other) {
        return asAppend(matrix, other.toVector(matrix.height()));
    }

    private Operator append(DoubleOperator base, DoubleMatrix matrix) {
        return asAppend(base.toVector(matrix.height()), matrix);
    }

    private Operator append(DoubleMatrix base, DoubleMatrix matrix) {
        return base.isNone()? matrix : matrix.isNone()? base : asAppend(base, matrix);
    }

    private Operator option(Operator base, Operator another) {
        return base.isNone()? another : another.isNone()? base : search(base, another);
    }

    private Operator search(Operator base, Operator another) {
        if (base.asDoubleMatrix().isNone()) {
            return append(base.asDoubleOperator(), another.asDoubleMatrix());
        }
        if (another.asDoubleMatrix().isNone()) {
            return append(base.asDoubleMatrix(), another.asDoubleOperator());
        }
        return append(base.asDoubleMatrix(), another.asDoubleMatrix());
    }

    @Override
    public DoubleMatrix invoke(Operator prev) {
        Operator base = prev;
        for (Operator op : getOperators()) {
            base = op.asOperators().invoke(base);
            base = option(base, op);
        }
        return base.asDoubleMatrix();
    }

    public DoubleMatrix invoke() {
        return invoke(DoubleMatrix.NONE);
    }

    @Override
    public CharSequence asSimple(int depth) {
        return asSimple("::", depth);
    }

    @Override
    public DoubleMatrix asDoubleMatrix() {
        return DoubleMatrix.NONE;
    }

    @Override
    public DoubleOperator asDoubleOperator() {
        return DoubleOperator.NONE;
    }
}
