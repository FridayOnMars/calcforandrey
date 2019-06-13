package com.example.calculator;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView result; // текстовое поле для вывода результата
    EditText number;   // поле для ввода числа
    Double operand = null;  // операнд операции
    String Operation = "="; // последняя операция
    private boolean clickable = false;
    private int clickpoint = 0;
    TextView test;
    double num1 = 0;
    int b=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        result =(TextView) findViewById(R.id.result);
        number =(EditText) findViewById(R.id.number);
        test = (TextView) findViewById(R.id.test);
    }
    public void onNumberClick(View view){
        Button button = (Button) view;
        clickable = false;
        if(clickpoint!=2)
        {
            clickpoint = 1;
        }
        else
        {
            clickpoint = 3;
        }
        number.append(button.getText());
        if(Operation.equals("=") && operand!=null){
            operand = null;
        }
    }
    public void onPointClick(View view){
        switch (clickpoint){
            case 0: return;
            case 1: number.setText(String.format("%s%s", number.getText().toString(), ".")); clickpoint=2; break;
            
        }
    }
    public void onClearClick(View view){
        number.setText("");
        operand = 0.0;
        result.setText("");
        Operation = "=";
        num1=0;
        b=0;
        clickpoint = 0;
    }
    public void onOperationClick(View view){
        Button button = (Button) view;
        String op = button.getText().toString();
        if(clickpoint == 2)
        {
            return;
        }
        if (clickable == true) {
            Operation = op;
            number.setText(String.format("%s%s", number.getText().toString().substring(0, number.getText().toString().length() - 1), String.format("%s", op)));
            return;
        }
        String num = number.getText().toString();
        if(num.length()>0 && b==0) {
            num1 = Double.valueOf(num);
            b++;
            Operation = op;
            number.setText(String.format("%s%s", number.getText().toString(), op));
        }
        else if(num.length()>0 && b>0){
            String []a = num.split("\\" + Operation);
            num = num.split("\\" + Operation)[a.length-1];
            if(op.equals("=")){
                Operations(Double.valueOf(num), num1);
                num1=0.0;
                b=0;
                number.setText("");
                operand = 0.0;
                Operation = op;
                clickable = false;
            }
            else{
                Operations(Double.valueOf(num), num1);
                num1 = Double.valueOf(operand);
                Operation = op;
            }
            number.append("\\" + op);
        }
        clickable = true;
    }
    private void Operations(Double num, Double num1)
    {
        switch (Operation) {
            case "*": operand = num1 * num; break;
            case "/": operand = num1 / num; break;
            case "+": operand = num1 + num; break;
            case "-": operand = num1 - num; break;
            case "%": operand = num1 % num; break;
            case "^": operand = Math.pow(num1,num); break;
        }
        clickpoint = 0;
        result.setText(operand.toString().replace('.',','));
    }
}
