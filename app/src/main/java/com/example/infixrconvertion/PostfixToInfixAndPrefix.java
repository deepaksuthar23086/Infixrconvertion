package com.example.infixrconvertion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

public class PostfixToInfixAndPrefix extends AppCompatActivity {
    Button convert, reset, typeOfConversion;
    EditText enterExpression;
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postfix_to_infix_prefix);
        convert = findViewById(R.id.postfixtoprefixconvertBtn);
        reset = findViewById(R.id.postfixtoprefixresetBtn);
        enterExpression = findViewById(R.id.PostfixtoprefixexpretionEditText);
        output = findViewById(R.id.postfixtoprefixoutputExpretion);
        typeOfConversion = findViewById(R.id.typeOfConversion);
        final int[] tap = {1};

        typeOfConversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (tap[0] == 1) {
                    typeOfConversion.setText("Postfix/Infix");
                    tap[0] = 2;
                } else {
                    typeOfConversion.setText("Postfix/Prefix");
                    tap[0] = 1;
                }
            }
        });
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String postfix = enterExpression.getText().toString();
                Stack<String> st = new Stack<>();
                int last = postfix.charAt(postfix.length() - 1);
                if (last == '/' || last == '*' || last == '-' || last == '+' || last == '^') {
                    if (tap[0] == 1) {
                        for (int i = 0; i < postfix.length(); i++) {
                            char current = postfix.charAt(i);
                            if (Character.isAlphabetic(current) || Character.isDigit(current)) {
                                st.push(current + "");
                            } else {
                                String temp1 = st.peek();
                                st.pop();
                                String temp2 = st.peek();
                                st.pop();
                                st.push("(" + temp2 + current + temp1 + ")");
                            }
                        }
                        output.setText(st.peek());
                    } else {
                        for (int i = 0; i < postfix.length(); i++) {
                            char current = postfix.charAt(i);
                            if (Character.isDigit(current) || Character.isAlphabetic(current)) {
                                st.push(current+ "");
                            } else {
                                String temp1 = st.peek();
                                st.pop();
                                String temp2 = st.peek();
                                st.pop();
                                st.push(current + temp2 + temp1);
                            }
                        }
                        output.setText(st.peek());
                    }
                }else {
                    Toast.makeText(PostfixToInfixAndPrefix.this, "Please right postfix expression", Toast.LENGTH_SHORT).show();
                }


            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText("");
                enterExpression.setText("");
            }
        });
    }
}