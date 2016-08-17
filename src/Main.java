import com.googlecode.lanterna.*;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

import java.nio.charset.Charset;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Player player = new Player(10, 10);
        Monster monster = new Monster(5, 5);

        Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
        terminal.enterPrivateMode();

        while (true) {
            UpdateScreen(player, terminal);
            MovePlayer(player, terminal);
        }
    }

    private static void UpdateScreen(Player player, Terminal terminal) {
        terminal.clearScreen();
        terminal.moveCursor(player.x, player.y);
        terminal.putCharacter('O');
        terminal.moveCursor(0,0);
    }

    public static void MovePlayer (Player player, Terminal terminal) throws InterruptedException {
        Key key;
        do {
            Thread.sleep(5);
            key = terminal.readInput();
        } while (key == null);

        switch (key.getCharacter()){
            case 'U':
                if (player.y > 0) {
                    player.y--;
                }
                break;
            case 'D':
                if (player.y < 20) {
                    player.y++;
                }
                break;
            case 'L':
                if (player.x > 0) {
                    player.x--;
                }
                break;
            case 'R':
                if (player.x < 20) {
                    player.x++;
                }
                break;
        }

        System.out.println(key.getCharacter() + " " + key.getKind());
    }
}
