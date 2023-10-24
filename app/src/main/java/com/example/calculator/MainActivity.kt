package com.example.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var calculatorBrain = CalculatorBrain();

    var textViewDisplay : TextView? = null;


    var userIsInTheMiddleOfOperation : Boolean = false;

    var currentDisplay:Double
        get() =  textViewDisplay?.text.toString().toDouble();
        set(value){
            var displayText = ""
            if(value - value.toInt() == 0.0){
                displayText = value.toInt().toString();
            }
            else{
                displayText = value.toString();
            }
            textViewDisplay?.setText(displayText);
        }


    var onOperationPress : (View) -> Unit = {
        userIsInTheMiddleOfOperation = true;

        calculatorBrain.operation?.let { currentDisplay = calculatorBrain.doOperation(currentDisplay) }

        calculatorBrain.operation = when ((it as Button).text.toString()){
            "+" -> CalculatorBrain.Operation.SUM;
            "-" -> CalculatorBrain.Operation.SUBTRACTION;
            "x" -> CalculatorBrain.Operation.MULTIPLICATION;
            "÷" -> CalculatorBrain.Operation.DIVISION;
            else -> null;
        }

        calculatorBrain.accumulator = textViewDisplay?.text.toString().toDouble();
    }

    var onDigitPress : (View) -> Unit = {
        val num = (it as Button).text
        val display = textViewDisplay?.text.toString()

        if(userIsInTheMiddleOfOperation){
            if (display == "0") textViewDisplay?.text = num; //se for 0
            else if(num == "," && !display.contains(",")) textViewDisplay?.text = "$display$num"; //se for ,
            else textViewDisplay?.text = "$display$num"; //se for normal
        }else{
            textViewDisplay?.text = num;
        }
        userIsInTheMiddleOfOperation = false;

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewDisplay = findViewById<TextView>(R.id.textView)

        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)
        val button0 = findViewById<Button>(R.id.button0)
        val buttonDot = findViewById<Button>(R.id.buttonDecimal)

        val buttonPlus = findViewById<Button>(R.id.buttonPlus)
        val buttonMinus = findViewById<Button>(R.id.buttonMinus)
        val buttonDivide = findViewById<Button>(R.id.buttonDivide)
        val buttonMultiply = findViewById<Button>(R.id.buttonX)

        val buttonEqual = findViewById<Button>(R.id.buttonEqual)
        val buttonAC = findViewById<Button>(R.id.buttonAC)

        button1.setOnClickListener(onDigitPress)
        button2.setOnClickListener(onDigitPress)
        button3.setOnClickListener(onDigitPress)
        button4.setOnClickListener(onDigitPress)
        button5.setOnClickListener(onDigitPress)
        button6.setOnClickListener(onDigitPress)
        button7.setOnClickListener(onDigitPress)
        button8.setOnClickListener(onDigitPress)
        button9.setOnClickListener(onDigitPress)
        button0.setOnClickListener(onDigitPress)
        buttonDot.setOnClickListener(onDigitPress)


        buttonPlus.setOnClickListener (onOperationPress)
        buttonMinus.setOnClickListener (onOperationPress)
        buttonDivide.setOnClickListener (onOperationPress)
        buttonMultiply.setOnClickListener (onOperationPress)

        buttonEqual.setOnClickListener {
            currentDisplay = calculatorBrain.doOperation(currentDisplay)
            userIsInTheMiddleOfOperation = false;
        }

        buttonAC.setOnClickListener{
            textViewDisplay?.text = "0"
            userIsInTheMiddleOfOperation = false
            calculatorBrain.accumulator = 0.0;
            calculatorBrain.operation = null;
        }

    }


}