import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

public class Player {
    public int x = 0;
    public int y = 0;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static void Move (Player player, Terminal terminal) throws InterruptedException {
        Key key;
        do {
            Thread.sleep(5);
            key = terminal.readInput();
        } while (key == null);

        switch (key.getCharacter()){
            case 'U':
                if (player.y > 1) {
                    player.y-=1;
                }
                break;
            case 'D':
                if (player.y < 24) {
                    player.y+=1;
                }
                break;
            case 'L':
                if (player.x > 2) {
                    player.x-=2;
                }
                break;
            case 'R':
                if (player.x < 24) {
                    player.x+=2;
                }
                break;
        }

        System.out.println(key.getCharacter() + " " + key.getKind());
    }
}
