package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView result; // текстовое поле для вывода результата
    EditText number;   // поле для ввода числа
    Double operand = null;  // операнд операции
    String Operation = "="; // последняя операция
    //TextView test;

    double num1 = 0;
    int b=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        result =(TextView) findViewById(R.id.result);
        number =(EditText) findViewById(R.id.number);
        //test = (TextView) findViewById(R.id.test);
    }
    public void onNumberClick(View view){
        Button button = (Button) view;
        number.append(button.getText());
        if(Operation.equals("=") && operand!=null){
            operand = null;
        }
    }
    public void onClearClick(View view){
        number.setText("");
        operand = 0.0;
        result.setText("");
        Operation = "=";
        num1=0;
    }
    public void onOperationClick(View view){
        Button button = (Button) view;
        String op = button.getText().toString();
        String num = number.getText().toString();
        if(num.length()>0 && b==0) {
            num1 = Double.valueOf(num);
            b++;
            Operation = op;
            number.append(op);
            //test.append(num + op);
        }
        else if(num.length()>0 && b>0){
            String []a = num.split("\\" + Operation);
            num = num.split("\\" + Operation)[a.length-1];
            if(op.equals("=")){
                Operations(Double.valueOf(num), num1);
                num1=0.0;
                b=0;
                number.setText("");
                Operation = op;
            }
            else{
                Operations(Double.valueOf(num), num1);
                num1 = Double.valueOf(operand);
                Operation = op;
            }
            number.append("\\" + op);
            //test.append(num + op);
        }
    }
    private void Operations(Double num, Double num1)
    {
        switch (Operation) {
            case "*":
                operand = num1 * num;
                break;
            case "/":
                operand = num1 / num;
                break;
            case "+":
                operand = num1 + num;
                break;
            case "-":
                operand = num1 - num;
                break;
            case "%":
                operand = num1 % num;
                break;
            case "^":
                operand = Math.pow(num1,num);
                break;
        }

        result.setText(operand.toString().replace('.',','));
    }
}
