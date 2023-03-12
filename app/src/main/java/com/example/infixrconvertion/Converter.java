package com.example.infixrconvertion;

import android.util.Log;

import java.util.Stack;

public class Converter {

    public int precedence(char op) {
        if ((op == '+') || (op == '-')) {
            return 1;
        } else if ((op == '*') || (op == '/')) {
            return 2;
        } else if (op == '^') {
            return 3;
        } else {
            return -1;
        }
    }

    public String infixToPostfix(String inputExpression) {
        String outputExpression = "";
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < inputExpression.length(); i++) {
            char current = inputExpression.charAt(i);

            if (Character.isAlphabetic(current) || Character.isDigit(current)) {
                outputExpression += current;
            } else if (current == '(') {
                st.push(current);
            } else if (current == ')') {
                while (!st.empty() && st.peek() != '(') {
                    outputExpression += st.peek();
                    st.pop();
                }
                st.pop();
            } else {
                while (!st.empty() && precedence(st.peek()) >= precedence(current)) {
                    outputExpression += st.peek();
                    st.pop();
                }
                st.push(current);
            }
        }
        while (!st.empty()) {
            outputExpression += st.peek();
            st.pop();
        }
        return outputExpression;
    }
}
