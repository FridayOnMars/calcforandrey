package com.example.calculator;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ContentFragment extends Fragment implements View.OnClickListener{

    TextView tvResult; // текстовое поле для вывода результата
    EditText etNumber;   // поле для ввода числа
    private Double operand = null;  // операнд операции
    private String operation; // последняя операция
    private int clickable = 0;
    private int clicktouch = 0;
    private double num1 = 0;
    private int b=0;

    private void operations(Double num, Double num1) {
        switch (operation) {
            case "*": operand = num1 * num; break;
            case "/": operand = num1 / num; break;
            case "+": operand = num1 + num; break;
            case "-": operand = num1 - num; break;
            case "%": operand = num1 % num; break;
            case "^": operand = Math.pow(num1,num); break;
        }
        tvResult.setText(String.format(getString(R.string.format_result), operand.toString().replace('.',',')));
    }
    private void cleaning() {
        num1=0.0;
        b=0;
        clicktouch = 0;
        operand = 0.0;
    }
    private static void changewidth(int button_name, int width, View rootView){
        Button button = rootView.findViewById(button_name);
        RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams) button.getLayoutParams();
        param.width = (width-dpControl(50))/4;
        button.setLayoutParams(param);
    }
    public static int dpControl(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.fragment_content, container, false);
        if(getArguments() != null){
            int width = getArguments().getInt("size");
            changewidth(R.id.btnStep, width,rootView);
            changewidth(R.id.btnPercent, width,rootView);
            changewidth(R.id.btnDivision, width,rootView);
            changewidth(R.id.btnClear, width,rootView);
        }
        tvResult = rootView.findViewById(R.id.tvResult);
        tvResult.setText(String.format(getString(R.string.format_result), ""));
        etNumber = rootView.findViewById(R.id.etNumber);
        rootView.findViewById(R.id.btn0).setOnClickListener(this);
        rootView.findViewById(R.id.btn1).setOnClickListener(this);
        rootView.findViewById(R.id.btn2).setOnClickListener(this);
        rootView.findViewById(R.id.btn3).setOnClickListener(this);
        rootView.findViewById(R.id.btn4).setOnClickListener(this);
        rootView.findViewById(R.id.btn5).setOnClickListener(this);
        rootView.findViewById(R.id.btn6).setOnClickListener(this);
        rootView.findViewById(R.id.btn7).setOnClickListener(this);
        rootView.findViewById(R.id.btn8).setOnClickListener(this);
        rootView.findViewById(R.id.btn9).setOnClickListener(this);
        rootView.findViewById(R.id.btnStep).setOnClickListener(this);
        rootView.findViewById(R.id.btnDivision).setOnClickListener(this);
        rootView.findViewById(R.id.btnPlus).setOnClickListener(this);
        rootView.findViewById(R.id.btnMinus).setOnClickListener(this);
        rootView.findViewById(R.id.btnPercent).setOnClickListener(this);
        rootView.findViewById(R.id.btnClear).setOnClickListener(this);
        rootView.findViewById(R.id.btnEnd).setOnClickListener(this);
        rootView.findViewById(R.id.btnTouch).setOnClickListener(this);
        rootView.findViewById(R.id.btnUmn).setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
//        Toast.makeText(getActivity(), "Вы нажали на кнопку",
//                Toast.LENGTH_SHORT).show();
        Button bt = v.findViewById(v.getId());
        switch (v.getId()){
            case R.id.btn0:
            case R.id.btn1:
            case R.id.btn2:
            case R.id.btn3:
            case R.id.btn4:
            case R.id.btn5:
            case R.id.btn6:
            case R.id.btn7:
            case R.id.btn8:
            case R.id.btn9:
                etNumber.setText(String.format("%s%s", etNumber.getText().toString(),bt.getText()));
                switch (clicktouch){
                    case 0: clicktouch = 1; break;
                    case 2: clicktouch = 3; break;
                }
                clickable = 2;
                break;
            case R.id.btnPlus:
            case R.id.btnMinus:
            case R.id.btnUmn:
            case R.id.btnDivision:
            case R.id.btnStep:
            case R.id.btnPercent:
            case R.id.btnEnd:
                String op = String.format("%s",bt.getText());
                if(clicktouch==2 || clicktouch == 0){
                    switch (clickable){
                        case 0: return;
                        case 1: operation = op;
                            etNumber.setText(String.format("%s%s", etNumber.getText().toString().substring(0, etNumber.getText().toString().length() - 1), String.format("%s", op)));
                            return;
                    }
                }
                String num = etNumber.getText().toString();
                if(num.length()>0 && b==0) {
                    num1 = Double.valueOf(num);
                    b++;
                    operation = op;
                    etNumber.setText(String.format("%s%s", etNumber.getText().toString(), op));
                }
                else if(num.length()>0 && b>0){
                    String []a = num.split(String.format("%s%s","\\" ,operation));
                    num = num.split(String.format("%s%s", "\\", operation))[a.length-1];
                    if(op.equals("=")){
                        operations(Double.valueOf(num), num1);
                        etNumber.setText(String.format("%s", operand.toString()));
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
                    etNumber.setText(String.format("%s%s", etNumber.getText().toString(), op));
                }
                clickable = 1;
                clicktouch = 0;
                break;
            case R.id.btnClear:
                cleaning();
                tvResult.setText(String.format(getString(R.string.format_result), ""));
                operation = "";
                clickable = 0;
                etNumber.setText("");
                break;
            case R.id.btnTouch:
                switch (clicktouch){
                    case 0: return;
                    case 1: etNumber.setText(String.format("%s%s", etNumber.getText().toString() ,".")); clicktouch=2; clickable=0; break;
                }
                break;
        }
    }
}
