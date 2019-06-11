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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        result =(TextView) findViewById(R.id.result);
        number =(EditText) findViewById(R.id.number);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Operation = savedInstanceState.getString("OPERATION");
        operand = savedInstanceState.getDouble("OPERAND");
        result.setText(operand.toString());
    }
    public void onNumberClick(View view){
        Button button = (Button) view;
        number.append((button.getText()));
        if(Operation.equals("=") && operand!=null){
            operand = null;
        }
    }
    public void onOperationClick(View view){
        Button button = (Button) view;
        String oper = button.getText().toString();
        String num = number.getText().toString();
        if(num.length()>0)
        {
                Operations(Double.valueOf(num), oper);
        }
        Operation = oper;
    }
    private void Operations(Double num, String operation)
    {
        if(operand == null) {
            operand = num;
        }
        else{
            if(Operation.equals("=")){
                Operation = operation;
            }
            switch (Operation){
                case "=":
                    operand = num;
                    break;
                case "/":
                    if(num == 0){
                        operand = 0.0;
                    }
                    else{
                        operand /=num;
                    }
                    break;
                case "*":
                    operand *=num;
                    break;
                case "+":
                    operand +=num;
                    break;
                case "-":
                    operand -=num;
                    break;
            }
        }
        result.setText(operand.toString().replace('.',','));
        number.setText("");
    }
}
