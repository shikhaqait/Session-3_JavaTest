package com.qait.test.open;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Util {
    public Util() {
        super();
    }
    
    public static void guardNotNull(final Object o) {
        require(o != null);
    }

    public static void require(final boolean expr) {
        if (!expr) {
            throw new IllegalArgumentException();
        }
    }
    
    public static Interval ival(final int start, final int end) {
        return new Interval(start, end);
    }
    
    public static <T> List<T> asList(final Collection<? extends T> things) {
        @SuppressWarnings("unchecked")
        final List<T> result = list();

        result.addAll(things);

        return result;
    }
    
    public static <T> List<T> list(final T... things) {
        final List<T> result = new ArrayList<T>();

        for (final T t : things) {
            result.add(t);
        }

        return result;
    }

    public static <T> Set<T> asSet(final Collection<? extends T> things) {
        @SuppressWarnings("unchecked")
        final Set<T> result = set();

        result.addAll(things);

        return result;
    }

    
    public static <T> Set<T> set(final T... things) {
        final Set<T> result = new HashSet<T>();

        for (final T t : things) {
            result.add(t);
        }

        return result;
    }
}
