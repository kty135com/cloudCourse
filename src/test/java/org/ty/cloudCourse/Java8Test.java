package org.ty.cloudCourse;

import org.junit.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author kangtaiyang
 * @date 2018/7/29
 */
public class Java8Test {
    //BinaryOperator
    BinaryOperator<Integer> add = (x, y) -> x + y;
    BinaryOperator<String> str = (x, y) -> x + "::" + y;
    //取最小
    BinaryOperator<Integer> minBy = BinaryOperator.minBy(Comparator.naturalOrder());
    //取最大
    BinaryOperator<String> maxBy = BinaryOperator.maxBy(Comparator.naturalOrder());

    @Test
    public void BinaryOperator() {
        System.out.println(add.apply(1, 2));
        System.out.println(str.apply("a", "b"));
        System.out.println(minBy.apply(1, 3));
        System.out.println(maxBy.apply("3", "a"));
    }

    @Test
    public void predicate() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        System.out.println(list);
        List<Integer> evenList = getEven(list, x -> x % 2 == 0);
        System.out.println(evenList);
    }

    public static List<Integer> getEven(List<Integer> list, Predicate<Integer> predicate) {
        List<Integer> numList = new ArrayList<>(5);
        for (Integer num :
                list) {
            if (predicate.test(num)) {
                numList.add(num);
            }
        }
        return numList;
    }

    @Test
    public void testToArray() {
        List list = new ArrayList();
        list.add(0, "a");
        list.add(1, "b");
        String[] array = new String[list.size()];
        list.toArray(array);
        System.out.println(list);
        System.out.println(array[0] + array[1]);
        list.remove(1);
        System.out.println(list);
        System.out.println(array[0] + array[1]);
    }

    @Test
    public void testStream() {
        User u1 = new User("小王", "软件");
        User u2 = new User("小李", "会计");
        User u3 = new User("小红", "会计");
        User u4 = new User("小方", "会计");
        List<User> list = new ArrayList<>();
        list.add(u1);
        list.add(u2);
        list.add(u3);
        list.add(u4);
        // 惰性求值
        list.stream().filter(x -> {
            System.out.println(x);
            return x.getClazz().equals("会计");
        });
        // 及早求值1
        Stream<User> stream = list.stream().filter(x -> x.getClazz().equals("会计"));
        list = stream.collect(Collectors.toList());
        System.out.println(list);
        // 及早求值2
        long i = list.stream().filter(x -> {
            System.out.println("1" + x.getClazz());
            return x.getClazz().equals("会计");
        }).count();
    }

    class User {
        private String name;
        private String clazz;

        public User() {
        }

        public User(String name, String clazz) {
            this.name = name;
            this.clazz = clazz;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getClazz() {
            return clazz;
        }

        public void setClazz(String clazz) {
            this.clazz = clazz;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", clazz='" + clazz + '\'' +
                    '}';
        }
    }

    /**
     * 从小到大排序
     */
    @Test
    public void maopao() {
        int[] arrs = new int[]{3, 9, 4, 7, 1, 6, 2};
        for (int temp :
                arrs) {
            System.out.print(temp + " ");
        }
        System.out.println();
        for (int i = 0; i < arrs.length - 1; i++) {
            for (int j = 0; j < arrs.length - 1 - i; j++) {
                if (arrs[j] > arrs[j + 1]) {
                    int temp = arrs[j];
                    arrs[j] = arrs[j + 1];
                    arrs[j + 1] = temp;
                }
                for (int temp :
                        arrs) {
                    System.out.print(temp + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
        for (int temp :
                arrs) {
            System.out.print(temp + " ");
        }
    }

    /**
     * 从大到小排序
     */
    @Test
    public void maoPao2() {
        int[] arrs = new int[]{1, 5, 2, 6, 3, 7, 8};
        for (int i = 0; i < arrs.length - 1; i++) {
            for (int j = 0; j < arrs.length - i - 1; j++) {
                if (arrs[j] < arrs[j + 1]) {
                    int temp = arrs[j];
                    arrs[j] = arrs[j + 1];
                    arrs[j + 1] = temp;
                }
            }
        }
        for (int i :
                arrs) {
            System.out.println(i+" ");
        }
    }

    /**
     * 二分法查找
     */
    @Test
    public void search() {
        int[] arrs = new int[]{-1, -2, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int key = -1;
        int start = 0;
        int end = arrs.length - 1;
        int answer = 0;
        while (start <= end) {
            int mid = (end + start) / 2;
            if (key < arrs[mid]) {
                end = mid - 1;
                System.out.println("end = " + end);
            } else if (key > arrs[mid]) {
                start = mid + 1;
                System.out.println("start = " + start);
            } else {
                System.out.println(mid);
                answer = mid;
                break;
            }
        }
        System.out.println(key + " 的位置是第 " + answer + " 位");
    }

    /**
     * 去重
     */
    @Test
    public void searchRepeatIndex() {
        int[] arrs = new int[]{1, 4, 4, 7, 3, 7, 9, 4};
        for (int i = 0; i < arrs.length - 1; i++) {
            for (int j = i + 1; j < arrs.length; j++) {
                if (arrs[i] == arrs[j]) {
                    System.out.println("重复元素角标： " + i);
                }
            }
        }
    }

    @Test
    public void suibianTest() {
        Random rand = new Random(System.currentTimeMillis());
        while (true) {
            int i = rand.nextInt(4);
            System.out.println(i);
        }
    }
}
