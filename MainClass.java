package ru.foxit.grayfox;

public class MainClass {
    private static class MyClass2 extends MyClass {
        MyClass2() {
            super();
        }
    }

    public static void main(String[] args) {
        int i = 5;
//        byte b = (byte) i;
//
//        MyClass2 m2 = new MyClass2();
//
//        System.out.println((m2 instanceof MyClass) ? "wow!" : "oh, no!");
//
//        System.out.println(b);

        MyClass m = new MyClass();
        m.method(1,2,3,4,5,6,7);
    }
}