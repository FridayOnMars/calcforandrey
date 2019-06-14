package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView result; // текстовое поле для вывода результата
    EditText number;   // поле для ввода числа
    Double operand = null;  // операнд операции
    String operation = "="; // последняя операция
    private int clickable = 0;
    private int clickpoint = 0;
    double num1 = 0;
    int b=0;

    private void changewidth(int button_name, int width){
        Button button = (Button) findViewById(button_name);
        RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams) button.getLayoutParams();
        param.width = (width-110)/4;
        button.setLayoutParams(param);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        result =(TextView) findViewById(R.id.result);
        number =(EditText) findViewById(R.id.number);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        changewidth(R.id.button_step, width);
        changewidth(R.id.button_percent, width);
        changewidth(R.id.button_division, width);
        changewidth(R.id.button_clear, width);
    }
    public void onNumberClick(View view){
        Button button = (Button) view;
        number.append(button.getText());
        clickable = 2;
        switch (clickpoint){
            case 0: clickpoint = 1; break;
            case 2: clickpoint = 3; break;
        }
    }
    public void onPointClick(View view){
        switch (clickpoint){
            case 0: return;
            case 1: number.setText(String.format("%s%s", number.getText().toString() ,".")); clickpoint=2; break;
        }
    }
    public void onClearClick(View view){
        Cleaning();
        result.setText("");
        operation = "=";
        clickable = 0;
        number.setText("");
    }
    public void onOperationClick(View view){
        Button button = (Button) view;
        String op = button.getText().toString();
        switch (clickpoint){
            case 2: return;
        }
        switch (clickable){
            case 0: return;
            case 1: operation = op;
                number.setText(String.format("%s%s", number.getText().toString().substring(0, number.getText().toString().length() - 1), String.format("%s", op)));
                return;
        }
        String num = number.getText().toString();
        if(num.length()>0 && b==0) {
            num1 = Double.valueOf(num);
            b++;
            operation = op;
            number.setText(String.format("%s%s", number.getText().toString(), op));
        }
        else if(num.length()>0 && b>0){
            String []a = num.split("\\" + operation);
            num = num.split("\\" + String.format("%s",operation))[a.length-1];
            if(op.equals("=")){
                Operations(Double.valueOf(num), num1);
                number.setText(operand.toString());
                Cleaning();
                operation = op;
                clickable = 2;
                return;
            }
            else{
                Operations(Double.valueOf(num), num1);
                num1 = Double.valueOf(operand);
                operation = op;
            }
            number.append(String.format("%s", op));
        }
        clickable = 1;
    }
    private void Operations(Double num, Double num1)
    {
        switch (operation) {
            case "*": operand = num1 * num; break;
            case "/": operand = num1 / num; break;
            case "+": operand = num1 + num; break;
            case "-": operand = num1 - num; break;
            case "%": operand = num1 % num; break;
            case "^": operand = Math.pow(num1,num); break;
        }
        clickpoint = 2;
        result.setText(operand.toString().replace('.',','));
    }
    private void Cleaning()
    {
        num1=0.0;
        b=0;
        clickpoint = 0;
        operand = 0.0;
    }
}
