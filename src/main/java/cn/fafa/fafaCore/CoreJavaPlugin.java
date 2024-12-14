package cn.fafa.fafaCore;

import org.bukkit.plugin.java.JavaPlugin;

public class CoreJavaPlugin extends JavaPlugin {
    // 泛型方法，返回当前插件实例
    @SuppressWarnings("unchecked")
    public static <T extends CoreJavaPlugin> T getThisPlugin() {
        return (T) JavaPlugin.getPlugin(CoreJavaPlugin.class);
    }
}
