package cn.fafa.fafaCore.arg;

public class NumberArg implements IArg<Long> {
    private final long value;

    public NumberArg(long value) {
        this.value = value;
    }

    @Override
    public Long getArg() {
        return value;
    }

    public static NumberArg fromString(String arg) {
        try {
            long parsedValue = Long.parseLong(arg);
            return new NumberArg(parsedValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid long value: " + arg, e);
        }
    }
}