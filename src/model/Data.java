package model;

import model.operation.Sing;

/**
 * Created by igladush on 16.02.16.
 */
public class Data {
    private boolean isOperand;
    private Sing sing;

    // todo In the fiture use generics
    private Double value;

    public Data(Sing sing) {
        this.sing = sing;
        isOperand = false;
    }

    public Data(Double  value) {
        isOperand = true;
        this.value = value;
    }

    public String getSing() {

        if (isOperand) {
            System.out.println("Whant operration but I can't give it!!!");
            throw new Error();
        }
        return sing.getSing();
    }

    public Double getValue() {
        if (!isOperand) {
            System.out.println("Whant value but I'm a opperation!!!");
            throw new Error();
        }
        return value;
    }

    public void setValue(Double value) {
        if (!isOperand) {
            System.out.println("Whant set value but I'm a operation");
        }
        this.value = value;

    }

    public boolean isOperand() {
        return this.isOperand;
    }
}