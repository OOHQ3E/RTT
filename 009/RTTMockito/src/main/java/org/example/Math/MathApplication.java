package org.example.Math;

public class MathApplication {
    private CalculatorService calculatorService;

    public MathApplication(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }
    public int add(int a, int b){
        return calculatorService.add(a,b);
    }
    public int subtract(int a, int b){
        return calculatorService.subtract(a,b);
    }
}
