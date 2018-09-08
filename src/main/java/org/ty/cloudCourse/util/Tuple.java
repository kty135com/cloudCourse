package org.ty.cloudCourse.util;

/**
 * Created by kangtaiyang on 2018/6/8.
 */
public class Tuple<A, B, C, D> {

    private OneTuple one;
    private TwoTuple two;
    private ThreeTuple three;
    private FourTuple four;

    public A getA() {
        if (one != null) return (A) one.getA();
        if (two != null) return (A) two.getA();
        if (three != null) return (A) three.getA();
        if (four != null) return (A) four.getA();
        return null;
    }

    public B getB() {
        if (two != null) return (B) two.getB();
        if (three != null) return (B) three.getB();
        if (four != null) return (B) four.getB();
        return null;
    }

    public C getC() {
        if (three != null) return (C) three.getC();
        if (four != null) return (C) four.getC();
        return null;
    }

    public D getD() {
        if (four != null) return (D) four.getD();
        return null;
    }

    public Tuple() {
    }

    public <A> Tuple(A a) {
        this.one = getTuple(a);
    }

    public <A, B> Tuple(A a, B b) {
        this.two = getTuple(a, b);
    }

    public <A, B, C> Tuple(A a, B b, C c) {
        this.three = getTuple(a, b, c);
    }

    public <A, B, C, D> Tuple(A a, B b, C c, D d) {
        this.four = getTuple(a, b, c, d);
    }

    public static <A> OneTuple getTuple(A a) {
        return new OneTuple(a);
    }

    public static <A, B> TwoTuple getTuple(A a, B b) {
        return new TwoTuple(a, b);
    }

    public static <A, B, C> ThreeTuple getTuple(A a, B b, C c) {
        return new ThreeTuple(a, b, c);
    }

    public static <A, B, C, D> FourTuple getTuple(A a, B b, C c, D d) {
        return new FourTuple(a, b, c, d);
    }

    @Override
    public String toString() {
        if (two != null) {
            return two.toString();
        }
        if (three != null) {
            return three.toString();
        }
        if (four != null) {
            return four.toString();
        }
        return null;
    }

    public <T> void add(T t) {
        if (one == null && two == null && three == null && four == null) one = new OneTuple(t);
        else if (one != null) {
            two = new TwoTuple(one.a, t);
            one = null;
        } else if (two != null) {
            three = new ThreeTuple(two.a, two.b, t);
            two = null;
        } else if (three != null) {
            four = new FourTuple(three.a, three.b, three.c, t);
            three = null;
        }
    }

    static class OneTuple<A> {
        public final A a;

        public OneTuple(A a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return "OneTuple{" +
                    "a=" + a +
                    '}';
        }

        public A getA() {
            return a;
        }
    }

    static class TwoTuple<A, B> extends OneTuple {
        public final B b;

        public TwoTuple(A a, B b) {
            super(a);
            this.b = b;
        }

        @Override
        public String toString() {
            return "TwoTuple{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }

        public B getB() {
            return b;
        }
    }

    static class ThreeTuple<A, B, C> extends TwoTuple {
        public final C c;

        public ThreeTuple(A a, B b, C c) {
            super(a, b);
            this.c = c;
        }

        @Override
        public String toString() {
            return "ThreeTuple{" +
                    "a=" + a +
                    ", b=" + b +
                    ", c=" + c +
                    '}';
        }

        public C getC() {
            return c;
        }
    }

    static class FourTuple<A, B, C, D> extends ThreeTuple {
        public final D d;

        public FourTuple(A a, B b, C c, D d) {
            super(a, b, c);
            this.d = d;
        }

        @Override
        public String toString() {
            return "FourTuple{" +
                    "a=" + a +
                    ", b=" + b +
                    ", c=" + c +
                    ", d=" + d +
                    '}';
        }

        public D getD() {
            return d;
        }
    }

    public TwoTuple getTwo() {
        return two;
    }

    public ThreeTuple getThree() {
        return three;
    }

    public FourTuple getFour() {
        return four;
    }

}
