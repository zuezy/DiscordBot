package me.Salt.Handlers.Games.CardsAgainstDiscord;

import me.Salt.Parser.Command.CommandParser;

/**
 * Created by 15122390 on 18/11/2016.
 */
public class CaDParser {

    public CaDContainer parse(CommandParser.CommandContainer cmd){

        return new CaDContainer(null, null, 0, 0, 0, false);
    }
}
