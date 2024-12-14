package cn.fafa.fafaCore.arg;

public class DoubleArg implements IArg<Double> {
    private final double value;

    public DoubleArg(double value) {
        this.value = value;
    }

    @Override
    public Double getArg() {
        return value;
    }

    public static DoubleArg fromString(String arg) {
        try {
            double parsedValue = Double.parseDouble(arg);
            return new DoubleArg(parsedValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid double value: " + arg, e);
        }
    }
}