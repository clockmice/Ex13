import com.googlecode.lanterna.*;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

import java.nio.charset.Charset;

public class Main {

    public static boolean run = true;

    public static void main(String[] args) throws InterruptedException {
        //boolean run = true;

        Player player = new Player(10, 10);
        Monster monster = new Monster(5, 5);

        Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
        terminal.enterPrivateMode();

        do {
            UpdateScreen(player, monster, terminal);
            MovePlayer(player, terminal);
            GameLogic(player, monster, terminal);
        }
        while (run);

        UpdateScreen(player, monster, terminal);
        terminal.clearScreen();
        GameOver(terminal);
        System.exit(0);
    }

    private static void GameOver(Terminal terminal) throws InterruptedException {
        System.out.println("GAME OVER");
        String text = "GAME OVER";
        int x = 9;
        int y = 5;

        for (int i = 0; i <text.length(); i++) {
            Thread.sleep(500);
            terminal.moveCursor(x,y+i);
            terminal.putCharacter(text.charAt(i));
        }
        Thread.sleep(1000);
    }

    private static void UpdateScreen(Player player, Monster monster, Terminal terminal) {
        terminal.clearScreen();
        terminal.moveCursor(player.x, player.y);
        terminal.putCharacter('O');
        terminal.moveCursor(monster.x, monster.y);
        terminal.putCharacter('X');
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

    public static void GameLogic(Player player, Monster monster, Terminal terminal){

        if (Math.abs(player.x - monster.x) >= Math.abs(player.y - monster.y)){
            if (player.x > monster.x){
                monster.x++;
            }
            else if (player.x < monster.x){
                monster.x--;
            }
        }
        else if (Math.abs(player.x - monster.x) < Math.abs(player.y - monster.y)) {
            if (player.y > monster.y) {
                monster.y++;
            } else if (player.y < monster.y) {
                monster.y--;
            }
        }
        if ((player.x == monster.x) && (player.y == monster.y)){
            run = false;
        }
    }
}
