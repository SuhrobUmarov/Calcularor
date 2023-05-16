package com.example.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {

    @FXML
    private TextField display;

    private StringBuilder input = new StringBuilder();
    private double result = 0.0;
    private String operator = "";

    @FXML
    private void handleDigitButtonAction(ActionEvent event) {
        Button button = (Button) event.getSource();
        input.append(button.getText());
        display.setText(input.toString());
    }

    @FXML
    private void handleOperatorButtonAction(ActionEvent event) {
        Button button = (Button) event.getSource();
        String newOperator = button.getText();
        if (!input.toString().isEmpty()) {
            if (!operator.isEmpty()) {
                calculate();
            }
            result = Double.parseDouble(input.toString());
            operator = newOperator;
            input.setLength(0);
        }
    }

    @FXML
    private void handleEqualsButtonAction(ActionEvent event) {
        if (!operator.isEmpty() && !input.toString().isEmpty()) {
            calculate();
            operator = "";
        }
    }

    @FXML
    private void handleClearButtonAction(ActionEvent event) {
        input.setLength(0);
        result = 0.0;
        operator = "";
        display.clear();
    }

    private void calculate() {
        double inputValue = Double.parseDouble(input.toString());
        switch (operator) {
            case "+":
                result += inputValue;
                break;
            case "-":
                result -= inputValue;
                break;
            case "*":
                result *= inputValue;
                break;
            case "/":
                if (inputValue != 0) {
                    result /= inputValue;
                } else {
                    display.setText("Error: Division by zero");
                    return;
                }
                break;
        }
        input.setLength(0);
        input.append(result);
        display.setText(input.toString());
    }
}
