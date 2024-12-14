package cn.fafa.fafaCore;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.List;

public interface ICommand<T> {

    /**
     * @return String of command trigger.
     */
    String getName();

    /**
     * @return String of command description.
     */
    String getDescription();

    /**
     * @return String of command help.
     */
    String getHelp();

//    /**
//     * @return A list of TabCompleter String.
//     */
//    List<String> getCompleterStrings();

    /**
     * @return Permission needed for this command to execute.
     */
    Permission getPermission();

    /**
     *
     * Trigger this method when a player call the command.
     *
     * @param player The player called the command.
     * @param args Args of command.
     * @return Whether the execution of the command is successful or not.
     */
    boolean execute(Player player, String[] args);

    /**
     *
     * Trigger this method when a player call the command.
     *
     * @param consoleSender The player called the command.
     * @param args Args of command.
     * @return Whether the execution of the command is successful or not.
     */
    boolean execute(ConsoleCommandSender consoleSender, String[] args);
}
