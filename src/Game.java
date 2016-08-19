import com.googlecode.lanterna.terminal.Terminal;

import java.util.List;

public class Game {
    public static boolean run = true;

    public Player player;
    public static Terminal terminal;
    List<Monster> monsters;

    public Game(Player player, List<Monster> monsters, Terminal terminal) {
        this.player = player;
        this.monsters = monsters;
        this.terminal = terminal;
    }

    public void Run() throws InterruptedException {
        terminal.enterPrivateMode();

        do {
            UpdateScreen(player, monsters, terminal);
            player.Move(player, terminal);
            GameLogic(player, monsters);
        }
        while (run);

        UpdateScreen(player, monsters, terminal);
        terminal.clearScreen();
        GameOver(terminal);
        System.exit(0);
    }

    private void UpdateScreen(Player player, List<Monster> monsters, Terminal terminal) {
        terminal.clearScreen();
        terminal.moveCursor(Math.round(player.x), Math.round(player.y));
        terminal.applyForegroundColor(0, 204, 0);
        terminal.putCharacter('O');
        for (Monster monster : monsters) {
            terminal.moveCursor(Math.round(monster.x), Math.round(monster.y));
            terminal.applyForegroundColor(255, 0, 0);
            terminal.putCharacter('ω');
        }
        DrawFrame(terminal);
        terminal.moveCursor(0,0);
    }

    private static void GameLogic(Player player, List<Monster> monsters) throws InterruptedException {

        for (Monster monster : monsters) {
            if (Math.abs(player.x - monster.x) >= Math.abs(player.y - monster.y)){
                if (player.x > monster.x){
                    monster.x += 0.7;
                }
                else if (player.x < monster.x){
                    monster.x-= 0.7;
                }
            }
            else if (Math.abs(player.x - monster.x) < Math.abs(player.y - monster.y)) {
                if (player.y > monster.y) {
                    monster.y+= 0.7;
                } else if (player.y < monster.y) {
                    monster.y-= 0.7;
                }
            }
            if ((Math.round(player.x) == Math.round(monster.x)) && (Math.round(player.y) == Math.round(monster.y))){
                Fail(terminal, monster);
                run = false;
                break;
            }
        }
    }

    private static void Fail(Terminal terminal, Monster monster) throws InterruptedException {
        for (int i = 0; i<5; i++) {
            terminal.clearScreen();
            terminal.moveCursor(Math.round(monster.x), Math.round(monster.y));
            terminal.applyForegroundColor(0, 255, 0);
            terminal.putCharacter('X');
            terminal.moveCursor(0,0);
            Thread.sleep(200);
            terminal.clearScreen();
            Thread.sleep(200);
        }
    }

    private static void GameOver(Terminal terminal) throws InterruptedException {
        System.out.println("GAME OVER");
        String text = "GAME OVER";
        Color[] colors = {new Color(255,0,255), new Color(255,0,0), new Color(255,102,0), new Color(255,255,0), new Color(255,255,255),
        new Color(0,255,0), new Color(0,153,255), new Color(0,0,255), new Color(153,0,255)};

        int x = 9;
        int y = 5;

        for (int i = 0; i <text.length(); i++) {
            Thread.sleep(200);
            terminal.moveCursor(x,y+i);
            terminal.applyForegroundColor(colors[i].color1, colors[i].color2, colors[i].color3);
            terminal.putCharacter(text.charAt(i));
            terminal.moveCursor(0,0);
        }
        Thread.sleep(1500);
    }

    private static void DrawFrame(Terminal terminal){
        for (int i = 0; i<=25; i++) {
            terminal.moveCursor(0, i);
            terminal.applyForegroundColor(255, 255, 255);
            terminal.putCharacter('*');
        }
        for (int i = 0; i<=25; i++) {
            terminal.moveCursor(25, i);
            terminal.applyForegroundColor(255, 255, 255);
            terminal.putCharacter('*');
        }
        for (int i = 0; i<=25; i++) {
            terminal.moveCursor(i, 0);
            terminal.applyForegroundColor(255, 255, 255);
            terminal.putCharacter('*');
        }
        for (int i = 0; i<=25; i++) {
            terminal.moveCursor(i, 25);
            terminal.applyForegroundColor(255, 255, 255);
            terminal.putCharacter('*');
        }
    }
}


