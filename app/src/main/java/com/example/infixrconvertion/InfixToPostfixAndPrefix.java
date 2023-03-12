package com.example.infixrconvertion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InfixToPostfixAndPrefix extends AppCompatActivity {

    Button convert, reset, conversion;
    EditText enterExpression;
    TextView output;
    String postfixExpression, prefixExpression;
    Converter converter = new Converter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infix_to_postfix_prefix);

        final int[] tap = {1};
        convert = findViewById(R.id.postfixconvertBtn);
        reset = findViewById(R.id.postfixresetBtn);
        enterExpression = findViewById(R.id.postfixexpretionEditText);
        output = findViewById(R.id.postfixoutputExpretion);
        conversion = findViewById(R.id.infixConversion);
        conversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tap[0] == 1) {
                    conversion.setText("Infix/Postfix");
                    tap[0] = 2;
                } else {
                    conversion.setText("Infix/Prefix");
                    tap[0] = 1;
                }
            }
        });
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String infix = enterExpression.getText().toString();
                int last = infix.charAt(infix.length() - 1);

                if (last == ')' || last == '(' || Character.isDigit(last) || Character.isAlphabetic(last)) {
                    if (tap[0] == 1) {
                        postfixExpression = converter.infixToPostfix(infix);
                        output.setText(postfixExpression);
                    } else if (tap[0] == 2) {
                        String reverseInfix = reverseString(infix);
                        postfixExpression = converter.infixToPostfix(reverseInfix);
                        prefixExpression = reverseString(postfixExpression);
                        output.setText(prefixExpression);
                    }
                } else {
                    Toast.makeText(InfixToPostfixAndPrefix.this, "Please right infix expression", Toast.LENGTH_LONG).show();
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

    public String reverseString(String str) {
        String reverse = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            char current = str.charAt(i);
            if (current == '(') {
                current = ')';
            } else if (current == ')') {
                current = '(';
            }
            reverse += current;
        }
        return String.valueOf(reverse);
    }

}