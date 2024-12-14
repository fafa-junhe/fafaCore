package cn.fafa.fafaCore.arg;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ArgFactory {
    // 注册表：类型 -> 解析函数
    private static final Map<String, Function<String, ? extends IArg<?>>> registry = new HashMap<>();

    // 静态代码块：注册默认的 Arg 类型
    static {
        register("n", NumberArg::fromString);
        register("b", BooleanArg::fromString);
        register("d", DoubleArg::fromString);
        register("s", StringArg::fromString);
    }

    /**
     * 注册新的 Arg 类型及其解析逻辑
     *
     * @param type    新类型的 Class 对象
     * @param parser  解析函数，接收 String 返回对应的 IArg 实例
     * @param <T>     泛型类型
     */
    public static <T> void register(String type, Function<String, ? extends IArg<T>> parser) {
        registry.put(type, parser);
    }

    /**
     * 根据类型和字符串解析生成 Arg 实例
     *
     * @param arg  要解析的字符串
     * @param type 目标类型的 Class 对象
     * @param <T>  泛型类型
     * @return 对应的 IArg 实例
     */
    @SuppressWarnings("unchecked")
    public static <T> IArg<T> fromString(String type, String arg) {
        Function<String, ? extends IArg<?>> parser = registry.get(type);
        if (parser == null) {
            throw new IllegalArgumentException("No parser registered for type: " + type);
        }
        return (IArg<T>) parser.apply(arg);
    }
}