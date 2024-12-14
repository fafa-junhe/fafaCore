package cn.fafa.fafaCore.command;

import cn.fafa.fafaCore.CoreJavaPlugin;
import cn.fafa.fafaCore.arg.ArgFactory;
import cn.fafa.fafaCore.arg.IArg;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandRegister implements CommandExecutor {
    private final Map<String, ICommand> commands = new HashMap<>();
    private final Set<ICommand> mainCommands = new HashSet<>();
    private final Map<ICommand, String> commandsParams = new HashMap<>();
    private static final Pattern PARAM_PATTERN = Pattern.compile("^<(.*):(.*)>$");
    public CommandRegister registerCommand(ICommand command){
        commands.putIfAbsent(command.getName(), command);
        return this;
    }

    public void setExecutor(CoreJavaPlugin cjp){
        for (ICommand mainCommand : mainCommands) {
            Objects.requireNonNull(cjp.getCommand(mainCommand.getName())).setExecutor(this);
        }

    }

    public void registerSubCommand(String[] subcommands){
        for(String subcommand : subcommands){
            commands.put(subcommand, null);
        }
    }



    public CommandRegister registerMainCommand(ICommand command, String args){
        mainCommands.add(command);
        return registerCommand(command, args);
    }

    public CommandRegister registerCommand(ICommand command, String args){
        commands.putIfAbsent(command.getName(), command);
        commandsParams.putIfAbsent(command, args);
        if (!args.contains("<")){
            registerSubCommand(args.split("/"));
        }
        return this;
    }

    public void execute(String cmd, CommandSender sender){
        String command = cmd.split(" ")[0];
        String[] args = Arrays.stream(cmd.split(" ")).skip(1).toArray(String[]::new);
        System.out.println(args[0]);
        if (commands.get(command) != null && !commandsParams.get(commands.get(command)).contains("<")){
            execute(commands.get(args[0]), Arrays.stream(args).skip(1).toArray(String[]::new), sender);
        }
    }

    private Map<String, IArg<?>> createArgsFromStrings(String[] args, String[] params){
        if (args.length < params.length) {
            throw new IllegalArgumentException("Too few arguments");
        }
        Map<String, IArg<?>> argList = new HashMap<>();
        for (int i = 0; i < params.length; i++) {
            String param = params[i];
            Matcher matcher = PARAM_PATTERN.matcher(param);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("param " + param + " illegal");
            }
            System.out.println(param);
            String paramType = matcher.group(1);
            String paramName = matcher.group(2);
            String arg = args[i];
            argList.put(paramName, ArgFactory.fromString(paramType, arg));
        }
        return argList;
    }

    public void execute(ICommand iCmd, String[] args, CommandSender sender){
        if (args.length != 0 && !commandsParams.get(iCmd).contains("<")){
            execute(commands.get(args[0]), Arrays.stream(args).skip(1).toArray(String[]::new), sender);
            return;
        }
        Map<String, IArg<?>> iArgs = new HashMap<>();
        if (commandsParams.containsKey(iCmd)){
            iArgs = createArgsFromStrings(args, commandsParams.get(iCmd).split(" "));
        }
        if (sender instanceof Player){
            iCmd.execute((Player) sender, iArgs);
        }else if (sender instanceof ConsoleCommandSender){
            iCmd.execute((ConsoleCommandSender) sender, iArgs);
        }else{
            throw new CommandException("Unknown commandSender");
        }

    }

    @Override
    public boolean onCommand(@Nullable CommandSender commandSender, @Nullable Command command, @Nullable String s, String[] strings) {
        assert command != null;
        String fullCommand = command + Arrays.toString(strings);
        execute(fullCommand, commandSender);
        return false;
    }
}
