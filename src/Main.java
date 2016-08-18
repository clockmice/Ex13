import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
        Player player = new Player(10, 10);
        Monster monster1 = new Monster(5, 5);
        Monster monster2 = new Monster(5, 20);
        List<Monster> monsters = new ArrayList<>();
        monsters.add(monster1);
        monsters.add(monster2);
        Game game = new Game(player, monsters, terminal);

        game.Run();
    }
}