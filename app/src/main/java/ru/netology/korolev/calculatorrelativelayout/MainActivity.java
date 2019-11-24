package ru.netology.korolev.calculatorrelativelayout;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        txtResult = findViewById(R.id.txtResult);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button btn0 = findViewById(R.id.btn0);
        Button btnDot = findViewById(R.id.btnDot);
        Button btnC = findViewById(R.id.btnC);
        Button btnPlusMinus = findViewById(R.id.btnPlusMinus);
        Button btnPercentage = findViewById(R.id.btnPercentage);
        Button btnDivide = findViewById(R.id.btnDivide);
        Button btnMultiply = findViewById(R.id.btnMultiply);
        Button btnMinus = findViewById(R.id.btnMinus);
        Button btnPlus = findViewById(R.id.btnPlus);
        Button btnEqual = findViewById(R.id.btnEqual);
        Button btnToEngine = findViewById(R.id.btnToEngine);
        Button btnToOrdinary = findViewById(R.id.btnToOrdinary);
        View layoutEngine = findViewById(R.id.layoutEngine);
        final View layoutOrdinary = findViewById(R.id.layoutOrdinary);

        clickButton(btn1, getString(R.string.one));
        clickButton(btn2, getString(R.string.two));
        clickButton(btn3, getString(R.string.three));
        clickButton(btn4, getString(R.string.four));
        clickButton(btn5, getString(R.string.five));
        clickButton(btn6, getString(R.string.six));
        clickButton(btn7, getString(R.string.seven));
        clickButton(btn8, getString(R.string.eight));
        clickButton(btn9, getString(R.string.nine));
        clickButton(btn0, getString(R.string.zero));

        clickButton(btnC, getString(R.string.c));
        clickButton(btnPlusMinus, getString(R.string.plus_minus));
        clickButton(btnPercentage, getString(R.string.percentage));
        clickButton(btnDivide, getString(R.string.divide));
        clickButton(btnMultiply, getString(R.string.multiply));
        clickButton(btnMinus, getString(R.string.minus));
        clickButton(btnPlus, getString(R.string.plus));
        clickButton(btnEqual, getString(R.string.equal));
        clickButton(btnDot, getString(R.string.dot));

        changeCalc(btnToEngine, layoutOrdinary, layoutEngine);
        changeCalc(btnToOrdinary, layoutEngine, layoutOrdinary);

    }

    private void clickButton(Button btn, final String digit) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtResult.setText(digit);
            }
        });
    }

    private void changeCalc(Button btn, final View layout1, final View layout2) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
            }
        });
    }

}

