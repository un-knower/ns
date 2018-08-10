package com.newsee.common.utils;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * lambdas工具方法
 *
 * @author mu.jie
 * @Date 2018/6/15 15:23
 */
public class LambdasUtils {
    public static <T> void ifNullThen(T t, Consumer<T> consumer) {
        if (t == null) {
            consumer.accept(t);
        }
    }

    public static <T> void ifNotNullThen(T t, Consumer<T> consumer) {
        if (t != null) {
            consumer.accept(t);
        }
    }

    public static void ifBlankThen(String str, Consumer<String> consumer) {
        if (isBlank(str)) {
            consumer.accept(str);
        }
    }

    public static void ifNotBlankThen(String str, Consumer<String> consumer) {
        if (isNotBlank(str)) {
            consumer.accept(str);
        }
    }

    public static void ifTrueThen(Boolean flag, Consumer<Boolean> consumer) {
        if (flag) {
            consumer.accept(flag);
        }
    }

    public static void ifFalseThen(Boolean flag, Consumer<Boolean> consumer) {
        if (!flag) {
            consumer.accept(flag);
        }
    }

    public static <T, R> List<R> streamMap(Collection<T> collection, Function<? super T, ? extends R> mapper) {
        return collection.stream().map(mapper).collect(Collectors.toList());
    }

    public static <T> List<T> streamFilter(Collection<T> collection, Predicate<? super T> predicate) {
        return collection.stream().filter(predicate).collect(Collectors.toList());
    }

    public static <T, R> List<R> streamMapFilter(Collection<T> collection, Function<? super T, ? extends R> mapper, Predicate<? super R> predicate) {
        return collection.stream().map(mapper).filter(predicate).collect(Collectors.toList());
    }

    public static <T, R> List<R> streamFilterMap(Collection<T> collection, Predicate<? super T> predicate, Function<? super T, ? extends R> mapper) {
        return collection.stream().filter(predicate).map(mapper).collect(Collectors.toList());
    }

    public static <T> List<T> streamSort(Collection<T> collection, Comparator<? super T> comparator) {
        return collection.stream().sorted(comparator).collect(Collectors.toList());
    }

    public static <T> List<T> streamDistinct(Collection<T> collection) {
        return collection.stream().distinct().collect(Collectors.toList());
    }
}
