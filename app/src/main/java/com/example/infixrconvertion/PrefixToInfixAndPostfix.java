package com.example.infixrconvertion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

public class PrefixToInfixAndPostfix extends AppCompatActivity {
    Button convert, reset, typeOfConversion;
    EditText enterExpression;
    TextView output;
    Converter converter = new Converter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefix_to_infix_postfix);
        convert = findViewById(R.id.prefixconvertBtn);
        reset = findViewById(R.id.prefixresetBtn);
        enterExpression = findViewById(R.id.prefixexpretionEditText);
        output = findViewById(R.id.prefixoutputExpretion);
        typeOfConversion = findViewById(R.id.prefixConversion);
        int tap[] = {1};

        typeOfConversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tap[0] == 1) {
                    typeOfConversion.setText("Prefix/Infix");
                    tap[0] = 2;
                } else if (tap[0] == 2) {
                    typeOfConversion.setText("Prefix/Postfix");
                    tap[0] = 1;
                }
            }
        });
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String prefix = enterExpression.getText().toString();

                Stack<String > st = new Stack<>();
                int firstElement = prefix.charAt(0);

                if (firstElement == '/' || firstElement == '*' || firstElement == '-' || firstElement == '+' || firstElement == '^')
                {
                    if (tap[0] == 1)
                    {
                        for (int i=prefix.length()-1; i>=0; i--)
                        {
                            char current = prefix.charAt(i);
                            if (Character.isAlphabetic(current) || Character.isDigit(current))
                            {
                                st.push(current +"");
                            }else{
                                String   temp1 = st.peek();
                                st.pop();
                                String  temp2 = st.peek();
                                st.pop();
                                st.push("(" + temp1 + current + temp2 + ")");
                            }
                        }
                        output.setText(st.peek());
                    }else if (tap[0] == 2)
                    {
                        String postfix = "";
                        for (int i=prefix.length()-1; i>=0; i--)
                        {
                            char current = prefix.charAt(i);
                            if (Character.isDigit(current) || Character.isAlphabetic(current))
                            {
                                st.push(current+"");
                            }else{
                                String temp1 = st.peek();
                                st.pop();
                                String temp2 = st.peek();
                                st.pop();
                                st.push(temp1+temp2+current);
                            }
                        }
                        output.setText(st.peek());
                    }
                }else {
                    Toast.makeText(PrefixToInfixAndPostfix.this, "Please right prefix expression", Toast.LENGTH_SHORT).show();
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

    public static String reverseString(String str) {
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