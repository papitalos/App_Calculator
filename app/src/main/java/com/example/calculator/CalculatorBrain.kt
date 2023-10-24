package com.example.calculator

class CalculatorBrain {

    enum class Operation(op: String){
        SUM("+"),
        SUBTRACTION("-"),
        MULTIPLICATION("x"),
        DIVISION("÷")
    }

    var operation : Operation? = null
    var accumulator : Double = 0.0;


    fun doOperation (currentNum: Double):Double{

        return when (operation) {
            Operation.SUM -> { accumulator + currentNum}
            Operation.SUBTRACTION -> { accumulator - currentNum}
            Operation.MULTIPLICATION -> { accumulator * currentNum}
            Operation.DIVISION -> {  accumulator / currentNum}
            else -> {0.0}
        }



    }
}