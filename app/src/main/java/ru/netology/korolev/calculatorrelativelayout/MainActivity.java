package ru.netology.korolev.calculatorrelativelayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txtResult;
    TextView txtResultEngine;
    Bitmap bitmap;
    ImageView imageBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("log", "main, onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(
                getString(R.string.my_string),
                txtResult.getText().toString());
        savedInstanceState.putString(
                getString(R.string.my_string_engine),
                txtResultEngine.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String myString = savedInstanceState.getString(getString(R.string.my_string));
        txtResult.setText(myString);
        String myStringEngine = savedInstanceState.getString(
                getString(R.string.my_string_engine));
        txtResultEngine.setText(myStringEngine);
        txtResultEngine.setText(myStringEngine);
    }

    private void initView() {
        txtResult = findViewById(R.id.txtResult);
        txtResultEngine = findViewById(R.id.txtResultEngine);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button btn9Engine = findViewById(R.id.btn9Engine);
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
        View layoutOrdinary = findViewById(R.id.layoutOrdinary);
        Button btnSettingsOrdinary = findViewById(R.id.btnSettingsOrdinary);
        Button btnSettingsEngine = findViewById(R.id.btnSettingsEngine);
        imageBackground = findViewById(R.id.imageBackground);

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
        btn9Engine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtResultEngine.setText(getString(R.string.nine));
            }
        });


        changeCalc(btnToEngine, layoutOrdinary, layoutEngine, txtResult, txtResultEngine);
        changeCalc(btnToOrdinary, layoutEngine, layoutOrdinary, txtResultEngine, txtResult);

        clickSettings(btnSettingsOrdinary);
        clickSettings(btnSettingsEngine);
        
    }

    private void clickButton(Button btn, final String digit) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtResult.setText(digit);
            }
        });
    }

    private void changeCalc(Button btn,
                            final View layout1,
                            final View layout2,
                            final TextView view1,
                            final TextView view2) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
                String result = view1.getText().toString();
                view2.setText(result);
            }
        });
    }

    private void clickSettings(Button btn) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d("log", "main, onActivityResult");
        if (resultCode == RESULT_OK) {
            if (data != null) {
                bitmap = data.getParcelableExtra("BitmapImage");
                imageBackground.setImageBitmap(bitmap);
            } else {
                Toast.makeText(this,
                        getString(R.string.data_null),
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this,
                    getString(R.string.error_return_intent_data),
                    Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}

