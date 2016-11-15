package me.Salt.Handlers;

import me.Salt.Commands.*;
import me.Salt.Commands.Admin.DeafenCommand;
import me.Salt.Commands.Admin.MuteCommand;
import me.Salt.Listeners.EventListener;
import me.Salt.Parser.Command.CommandParser;
import me.Salt.Util.Command;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.JDABuilder;

import javax.security.auth.login.LoginException;
import java.util.Date;
import java.util.HashMap;

public class Main {
    public static final Date startTime = new Date();
    public static HashMap<String, Command> commands = new HashMap<>();
    public static String cmdPrefix = ".";
    public static JDA jda;
    public static EventListener eventListener = new EventListener();
    public static int TotalMessageCount = 0;
    public static int BotMessageCount = 0;

    public static void main(String[] args) {
        try {
            jda = new JDABuilder().setBotToken("MjQ2MzA5NDI1OTAyNjQ5MzQ1.CwZHLw.SDE5mlbpbOm0kjvbjNE3hO7gReI").buildBlocking();
            jda.addEventListener(eventListener);
            jda.setAutoReconnect(true);
        } catch (LoginException e) {
            System.out.println("Login Exception");
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception");
        }

        commands.put("mute", new MuteCommand());
        commands.put("deafen", new DeafenCommand());
        commands.put("changename", new ChangeNameCommand());
        commands.put("stats", new StatisticsCommand());
        commands.put("ping", new PingCommand());
        commands.put("search", new SearchCommand());
        commands.put("eval", new EvalCommand());
        commands.put("help", new HelpCommand());
    }

    public static void handleCommand(CommandParser.CommandContainer cmd) {
        if (commands.containsKey(cmd.getCmd().toLowerCase())) {
            boolean safe = commands.get(cmd.getCmd().toLowerCase()).preExecution(cmd, eventListener);

            if (safe) {
                commands.get(cmd.getCmd().toLowerCase()).execution(cmd);
                commands.get(cmd.getCmd().toLowerCase()).postExecution(safe);
            } else {
                commands.get(cmd.getCmd().toLowerCase()).postExecution(safe);
            }
        }
    }
}