package com.theappland.mycalculatorapp.view.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import net.objecthunter.exp4j.ExpressionBuilder

class CalculateViewModel : BaseObservable() {
    var expressionText = ObservableField<String>()
    var resultText = ObservableField<String>()

    private var expressionStringBuffer =  StringBuffer()

    private val specials = arrayListOf(".", "+", "-", "*", "/")

    fun evaluateExpression(int: Int) {
        if (int >= 0) {
            expressionStringBuffer.append(int.toString())
            expressionText.set(expressionStringBuffer.toString())
        } else {
            when (int) {
                -1 -> {
                    if (expressionStringBuffer.toString() != "" && expressionStringBuffer.substring(expressionStringBuffer.length - 1) != ".") {
                        expressionStringBuffer.append(".")
                        expressionText.set(expressionStringBuffer.toString())
                    }
                    if (expressionStringBuffer.toString() == "") {
                        expressionStringBuffer.append(".")
                        expressionText.set(expressionStringBuffer.toString())
                    }
                }
                -2 -> {
                    if (expressionStringBuffer.toString() != "" && !checkIfContains(expressionStringBuffer.substring(expressionStringBuffer.length - 1),specials)) {
                        expressionStringBuffer.append("+")
                        expressionText.set(expressionStringBuffer.toString())
                    }
                }
                -3 -> {
                    if (expressionStringBuffer.toString() != "" && !checkIfContains(expressionStringBuffer.substring(expressionStringBuffer.length - 1),specials)) {
                        expressionStringBuffer.append("-")
                        expressionText.set(expressionStringBuffer.toString())
                    }
                }
                -4 -> {
                    if (expressionStringBuffer.toString() != "" && !checkIfContains(expressionStringBuffer.substring(expressionStringBuffer.length - 1),specials)) {
                        expressionStringBuffer.append("*")
                        expressionText.set(expressionStringBuffer.toString())
                    }
                }
                -5 -> {
                    if (expressionStringBuffer.toString() != "" && !checkIfContains(expressionStringBuffer.substring(expressionStringBuffer.length - 1),specials)) {
                        expressionStringBuffer.append("/")
                        expressionText.set(expressionStringBuffer.toString())
                    }
                }
            }
        }
    }

    fun clear() {
        expressionText.set("")
        resultText.set("")
        expressionStringBuffer.delete(0, expressionStringBuffer.length)
    }

    fun del() {
        if (expressionStringBuffer.toString() != "") {
            expressionStringBuffer.deleteCharAt(expressionStringBuffer.length - 1)
            expressionText.set(expressionStringBuffer.toString())
            resultText.set("")
        }
    }

    fun equalsClicked() {
        if (expressionStringBuffer.toString() != "" && !checkIfContains(expressionStringBuffer.substring(expressionStringBuffer.length - 1),specials)) {
            val text = expressionStringBuffer.toString()
            val expression = ExpressionBuilder(text).build()

            val result = expression.evaluate()
            val longResult = result.toLong()
            if (result == longResult.toDouble()) {
                resultText.set(longResult.toString())
            } else {
                resultText.set(result.toString())
            }
        }
    }

    private fun checkIfContains(string: String, arrayList: ArrayList<String>) : Boolean {
        for (char in arrayList) {
            if (string == char) {
                return true
            }
        }
        return false
    }
}