package cn.fafa.fafaCore.command;

import cn.fafa.fafaCore.arg.IArg;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.Map;

public interface ICommand {

    /**
     * @return String of command trigger.
     */
    String getName();

    /**
     * @return String of command description.
     */
    default String getDescription(){ return "DESC"; };

    /**
     * @return String of command help.
     */
    default String getHelp(){ return "HELP"; };

//    /**
//     * @return A list of TabCompleter String.
//     */
//    List<String> getCompleterStrings();

    /**
     * @return Permission needed for this command to execute.
     */
    default Permission getPermission(){ return new Permission("admin"); };

    boolean execute(ConsoleCommandSender consoleCommandSender, Map<String, IArg<?>> args);
    boolean execute(Player player, Map<String, IArg<?>> args);
}
