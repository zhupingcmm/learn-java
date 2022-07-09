package com.mf.java.core.lambda;

public class LambdaDemo {
    public static void main(String[] args) {
//        MathOperation op = new MathOperation() {
//            @Override
//            public int operation(int a, int b) {
//                return 0;
//            }
//        };

        MathOperation<Integer> operation = (a, b) -> a + b;

        System.out.println(operation.operation(1, 2));
    }
}
