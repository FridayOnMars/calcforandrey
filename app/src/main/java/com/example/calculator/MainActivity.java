package com.example.calculator;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
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
    String operation; // последняя операция
    private int clickable = 0;
    private int clicktouch = 0;
    double num1 = 0;
    int b=0;

    private void changewidth(int button_name, int width){
        Button button = (Button) findViewById(button_name);
        RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams) button.getLayoutParams();
        param.width = (width-110)/4;
        button.setLayoutParams(param);
    }
    private void operations(Double num, Double num1) {
        switch (operation) {
            case "*": operand = num1 * num; break;
            case "/": operand = num1 / num; break;
            case "+": operand = num1 + num; break;
            case "-": operand = num1 - num; break;
            case "%": operand = num1 % num; break;
            case "^": operand = Math.pow(num1,num); break;
        }
        result.setText(String.format("%s", String.format("%s",operand.toString())).replace('.',','));
    }
    private void cleaning() {
        num1=0.0;
        b=0;
        clicktouch = 0;
        operand = 0.0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        result =(TextView) findViewById(R.id.tvResult);
        number =(EditText) findViewById(R.id.etNumber);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        changewidth(R.id.btnStep, width);
        changewidth(R.id.btnPercent, width);
        changewidth(R.id.btnDivision, width);
        changewidth(R.id.btnClear, width);

        Bundle bundle = new Bundle();
        bundle.putString("edttext", "From Activity");
// set Fragmentclass Arguments
//        Fragmentclass fragobj = new Fragmentclass();
//        fragobj.setArguments(bundle);
//        Intent intent = new Intent(this,frg_panel.class);
//        intent.putExtra("time", System.currentTimeMillis());
        //startActivity(intent);
    }
    public void onNumberClick(View view){
        Button button = (Button) view;
        number.append(button.getText());
        switch (clicktouch){
            case 0: clicktouch = 1; break;
            case 2: clicktouch = 3; break;
        }
        clickable = 2;
    }
    public void onTouchClick(View view){
        switch (clicktouch){
            case 0: return;
            case 1: number.setText(String.format("%s%s", number.getText().toString() ,".")); clicktouch=2; clickable=0; break;
        }
    }
    public void onClearClick(View view){
        cleaning();
        result.setText("");
        operation = "=";
        clickable = 0;
        number.setText("");
    }
    public void onOperationClick(View view){
        Button button = (Button) view;
        String op = button.getText().toString();
        if(clicktouch==2 || clicktouch == 0){
                switch (clickable){
                    case 0: return;
                    case 1: operation = op;
                        number.setText(String.format("%s%s", number.getText().toString().substring(0, number.getText().toString().length() - 1), String.format("%s", op)));
                        return;
                }
        }
        String num = number.getText().toString();
        if(num.length()>0 && b==0) {
            num1 = Double.valueOf(num);
            b++;
            operation = op;
            number.setText(String.format("%s%s", number.getText().toString(), op));
        }
        else if(num.length()>0 && b>0){
            String []a = num.split(String.format("%s","\\") + operation);
            num = num.split(String.format("%s","\\") + String.format("%s",operation))[a.length-1];
            if(op.equals("=")){
                operations(Double.valueOf(num), num1);
                number.setText(String.format("%s", operand.toString()));
                cleaning();
                operation = op;
                clickable = 2;
                clicktouch = 0;
                return;
            }
            else{
                operations(Double.valueOf(num), num1);
                num1 = Double.valueOf(String.format("%s", operand));
                operation = op;
            }
            number.setText(String.format("%s%s", number.getText().toString(), op));
        }
        clickable = 1;
        clicktouch = 0;
    }
}
