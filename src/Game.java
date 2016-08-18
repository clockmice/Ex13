import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

public class Game {
    public static boolean run = true;

    public Player player;
    public Monster monster;
    public Terminal terminal;

    public Game(Player player, Monster monster, Terminal terminal) {
        this.player = player;
        this.monster = monster;
        this.terminal = terminal;
    }

    public void Run() throws InterruptedException {
        terminal.enterPrivateMode();

        do {
            UpdateScreen(player, monster, terminal);
            player.Move(player, terminal);
            GameLogic(player, monster);
        }
        while (run);

        UpdateScreen(player, monster, terminal);
        terminal.clearScreen();
        GameOver(terminal);
        System.exit(0);
    }

    private void UpdateScreen(Player player, Monster monster, Terminal terminal) {
        terminal.clearScreen();
        terminal.moveCursor(player.x, player.y);
        terminal.applyForegroundColor(0, 204, 0);
        terminal.putCharacter('O');
        terminal.moveCursor(monster.x, monster.y);
        terminal.applyForegroundColor(255, 0, 0);
        terminal.putCharacter('ω');
        DrawFrame(terminal);
        terminal.moveCursor(0,0);
    }

    private static void GameLogic(Player player, Monster monster) throws InterruptedException {

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
    private static void GameOver(Terminal terminal) throws InterruptedException {
        System.out.println("GAME OVER");
        String text = "GAME OVER";
        Color[] colors = {new Color(255,0,255), new Color(255,0,0), new Color(255,102,0), new Color(255,255,0), new Color(255,255,255),
        new Color(0,255,0), new Color(0,153,255), new Color(0,0,255), new Color(153,0,255)};

        int x = 9;
        int y = 5;

        for (int i = 0; i <text.length(); i++) {
            Thread.sleep(300);
            terminal.moveCursor(x,y+i);
            terminal.applyForegroundColor(colors[i].color1, colors[i].color2, colors[i].color3);
            terminal.putCharacter(text.charAt(i));
        }
        Thread.sleep(1000);
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


