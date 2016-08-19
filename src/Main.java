import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
        Player player = new Player(10, 10);
        List<Monster> monsters = new ArrayList<>();
        monsters.add(new SlowMonster(5, 5));
        monsters.add(new SlowMonster(20, 5));
        monsters.add(new FastMonster(5, 20));
        monsters.add(new FastMonster(20, 20));
        Game game = new Game(player, monsters, terminal);

        game.Run();
    }
}