package rip.diamond.practice.events.command;

import org.bukkit.entity.Player;
import rip.diamond.practice.events.EdenEvent;
import rip.diamond.practice.events.EventState;
import rip.diamond.practice.events.menu.EventCreateMenu;
import rip.diamond.practice.util.Common;
import rip.diamond.practice.util.command.Command;
import rip.diamond.practice.util.command.CommandArgs;
import rip.diamond.practice.util.command.argument.CommandArguments;

public class EventCommand extends Command {
    @CommandArgs(name = "event", permission = "eden.command.event")
    public void execute(CommandArguments command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("create")) {
                new EventCreateMenu().openMenu(player);
            } else if (args[0].equalsIgnoreCase("forcestart")) {
                EdenEvent event = EdenEvent.getOnGoingEvent();

                if (event == null) {
                    Common.sendMessage(player, "&c現時並沒有一個正在進行的活動!");
                    return;
                }
                if (event.getState() != EventState.WAITING && event.getState() != EventState.STARTING) {
                    Common.sendMessage(player, "&c活動已經開始!");
                    return;
                }
                event.start();
            }
        }
    }
}
