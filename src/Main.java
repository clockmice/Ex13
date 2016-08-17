import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;

import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Player player = new Player(10, 10);
        Monster monster = new Monster(5, 5);
        Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
        Game game = new Game(player, monster, terminal);

        game.Run();
    }
}