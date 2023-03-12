package com.example.infixrconvertion;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button infixToPostfixAndPrefix, postfixToInfixAndPrefix, prefixToInfixAndPostfix;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        infixToPostfixAndPrefix = findViewById(R.id.infixToPostfixAndPrefix);
        postfixToInfixAndPrefix = findViewById(R.id.postfixToInfixAndPrefix);
        prefixToInfixAndPostfix = findViewById(R.id.prefixToInfixAndPostfix);

        infixToPostfixAndPrefix.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, InfixToPostfixAndPrefix.class);
                startActivity(intent);
            }
        });
        postfixToInfixAndPrefix.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
//                expressionType.setText("INFIX TO POSTFIX");
                Intent intent = new Intent(MainActivity.this, PostfixToInfixAndPrefix.class);
                startActivity(intent);
            }
        });
        prefixToInfixAndPostfix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                expressionType.setText("POSTFIX TO PREFIX");
                Intent intent = new Intent(MainActivity.this, PrefixToInfixAndPostfix.class);
                startActivity(intent);
            }
        });
    }
}