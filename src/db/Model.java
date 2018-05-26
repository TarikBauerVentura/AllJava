package db;

public class Model {

    private int value, result;

    public Model(int value, int result) {
        this.value = value;
        this.result = result;
    }

    int getValue() {
        return value;
    }

    int getResult() {
        return result;
    }
}
