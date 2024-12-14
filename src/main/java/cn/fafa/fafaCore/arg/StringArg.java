package cn.fafa.fafaCore.arg;

public class StringArg implements IArg<String> {
    private final String value;

    StringArg(String value) {
        this.value = value;
    }

    @Override
    public String getArg() {
        return value;
    }

    public static StringArg fromString(String arg) {
        return new StringArg(arg);
    }
}
