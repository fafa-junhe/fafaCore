package cn.fafa.fafaCore;

import cn.fafa.fafaCore.arg.IArg;
import cn.fafa.fafaCore.command.CommandRegister;
import cn.fafa.fafaCore.command.ICommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Map;


public class CoreJavaPlugin extends JavaPlugin {
    // 泛型方法，返回当前插件实例
    @SuppressWarnings("unchecked")
    public static <T extends CoreJavaPlugin> T getThisPlugin() {
        return (T) JavaPlugin.getPlugin(CoreJavaPlugin.class);
    }

    public void InitAPI(){

    }
}
