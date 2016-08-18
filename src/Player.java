import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

public class Player {
    public float x = 0;
    public float y = 0;

    public Player(float x, float y) {
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
                    player.y--;
                }
                break;
            case 'D':
                if (player.y < 24) {
                    player.y++;
                }
                break;
            case 'L':
                if (player.x > 2) {
                    player.x--;
                }
                break;
            case 'R':
                if (player.x < 24) {
                    player.x++;
                }
                break;
        }

        System.out.println(key.getCharacter() + " " + key.getKind());
    }
}
