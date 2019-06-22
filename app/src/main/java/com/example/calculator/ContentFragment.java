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
    private int flagOperand = 0; // флаг для определения операции
    private int flagPoint = 0; // флаг для опеределения точки
    private double nextNumb = 0; // второе число в операции
    private int flagForNextNumb=0; // флаг для определения второго числа в операции

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
        nextNumb=0.0;
        flagForNextNumb=0;
        flagPoint = 0;
        operand = 0.0;
    }

    private static void width(int button_name, int width, View rootView){
        Button button = rootView.findViewById(button_name);
        RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams) button.getLayoutParams();
        param.width = (width-dpControl(50))/4;
        button.setLayoutParams(param);
    }

    public static int dpControl(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_content, container, false);
        if(getArguments() != null){
            int widthSize = getArguments().getInt("size");
            width(R.id.btnStep, widthSize,rootView);
            width(R.id.btnPercent, widthSize,rootView);
            width(R.id.btnDivision, widthSize,rootView);
            width(R.id.btnClear, widthSize,rootView);
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
                switch (flagPoint){
                    case 0: flagPoint = 1; break;
                    case 2: flagPoint = 3; break;
                }
                flagOperand = 2;
                break;
            case R.id.btnPlus:
            case R.id.btnMinus:
            case R.id.btnUmn:
            case R.id.btnDivision:
            case R.id.btnStep:
            case R.id.btnPercent:
            case R.id.btnEnd:
                String op = String.format("%s",bt.getText());
                if(flagPoint==2 || flagPoint == 0){
                    switch (flagOperand){
                        case 0: return;
                        case 1: operation = op;
                            etNumber.setText(String.format("%s%s", etNumber.getText().toString().substring(0, etNumber.getText().toString().length() - 1), String.format("%s", op)));
                            return;
                    }
                }
                String num = etNumber.getText().toString();
                if(num.length()>0 && flagForNextNumb==0) {
                    nextNumb = Double.valueOf(num);
                    flagForNextNumb++;
                    operation = op;
                    etNumber.setText(String.format("%s%s", etNumber.getText().toString(), op));
                }
                else if(num.length()>0 && flagForNextNumb>0){
                    String []a = num.split(String.format("%s%s","\\" ,operation));
                    num = num.split(String.format("%s%s", "\\", operation))[a.length-1];
                    if(op.equals("=")){
                        operations(Double.valueOf(num), nextNumb);
                        etNumber.setText(String.format("%s", operand.toString()));
                        cleaning();
                        operation = op;
                        flagOperand = 2;
                        flagPoint = 0;
                        return;
                    }
                    else{
                        operations(Double.valueOf(num), nextNumb);
                        nextNumb = Double.valueOf(String.format("%s", operand));
                        operation = op;
                    }
                    etNumber.setText(String.format("%s%s", etNumber.getText().toString(), op));
                }
                flagOperand = 1;
                flagPoint = 0;
                break;
            case R.id.btnClear:
                cleaning();
                tvResult.setText(String.format(getString(R.string.format_result), ""));
                operation = "";
                flagOperand = 0;
                etNumber.setText("");
                break;
            case R.id.btnTouch:
                switch (flagPoint){
                    case 0: return;
                    case 1: etNumber.setText(String.format("%s%s", etNumber.getText().toString() ,".")); flagPoint=2; flagOperand=0; break;
                }
                break;
        }
    }
}
