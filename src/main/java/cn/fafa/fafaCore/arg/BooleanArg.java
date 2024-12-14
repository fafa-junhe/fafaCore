package cn.fafa.fafaCore.arg;

import java.util.regex.Pattern;

public class BooleanArg implements IArg<Boolean> {
    private final boolean value;

    private static final Pattern TRUE_PATTERN = Pattern.compile("(?i)^(true|yes|1|是|确定)$");
    private static final Pattern FALSE_PATTERN = Pattern.compile("(?i)^(false|no|0|是|取消)$");

    public BooleanArg(boolean value) {
        this.value = value;
    }

    @Override
    public Boolean getArg() {
        return value;
    }

    public static BooleanArg fromString(String arg) {
        if (TRUE_PATTERN.matcher(arg).matches()) {
            return new BooleanArg(true);
        } else if (FALSE_PATTERN.matcher(arg).matches()) {
            return new BooleanArg(false);
        } else {
            throw new IllegalArgumentException("Invalid boolean value: " + arg);
        }
    }
}