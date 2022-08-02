package Dungeons_and_Dragons;

import GameTiles.Tile;
import GameTiles.Units.Enemies.Enemy;
import GameTiles.Units.Players.Player;
import GameTiles.Units.Unit;
import UI.UserInterface;

import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class GameController {


    ArrayList<GameBoard> gameBoards;
    ArrayList<ArrayList<Enemy>> enemyList;
    Player player;
    private static final List<Character> ENEMY_LIST = List.of('s', 'k', 'q', 'z', 'b', 'g', 'w', 'M', 'C', 'K','B','Q','D');
    private static final List<Character> PLANE_TILES = List.of('.', '#');
    private final int UP =0;
    private final int DOWN =1;
    private final int LEFT = 2;
    private final int RIGHT=3;
    private final int STAY =4;
    private UserInterface userInterface = new UserInterface();





    public GameController(String path)
    {
        player= getPlayer();
        player.setMessageCallBack((msg)-> System.out.println(msg));
        List<File> fileList = getLevelFiles(path);
        int size = fileList.size();
        gameBoards = new ArrayList<GameBoard>(size);
        enemyList = new ArrayList<ArrayList<Enemy>>(size);

        for (int i=0; i<size; i++){
            gameBoards.add(null);
            enemyList.add(new ArrayList<Enemy>());
        }
        /*gameBoards = new ArrayList<GameBoard>(size);

        enemyList = new ArrayList<ArrayList<Enemy>>(size);
        for (List<Enemy> list : enemyList) {
            list = new ArrayList<Enemy>();
        }*/

        //check that we don't miss last level
        for (int i = 0; i < size; i++) {
            File level = fileList.get(i);
            List<String> levelCharsRow = fileToRowList(level);
            List<Enemy> levelEnemies = enemyList.get(i);
            GameBoard board = initGameBoard(levelCharsRow,levelEnemies, player);
            gameBoards.set(i, board);

        }
    }


    public void play() {
        Player player1 = player;
        for (int i = 0; i < gameBoards.size(); i++) {
            GameBoard cuurentLevel = gameBoards.get(i);
            cuurentLevel.setPlayer(player1);
            List<Enemy> cuurentEnemyList = enemyList.get(i);
            while (!(cuurentEnemyList.isEmpty())) {
                List<Enemy> deadList = new ArrayList<Enemy>();

                //print board
                System.out.println(cuurentLevel);

                //print data about player
                player.send(player.describe());

                playerMove(player1, cuurentLevel, cuurentEnemyList);
                for (Enemy enemy: cuurentEnemyList)
                {
                 if (enemy.isDead()){
                     cuurentLevel.removeEnemy(enemy);
                     deadList.add(enemy);
                 }
                }
                if (!(deadList.isEmpty()))
                {
                    for (Enemy deadEnemy : deadList) {
                        cuurentEnemyList.remove(deadEnemy);
                    }
                    //deadList = null;
                }

                /*for (Enemy enemy : cuurentEnemyList) {
                    EnemyMove(player1,cuurentLevel,enemy);
                    if ((player1.isDead())) {
                        player1.setTile('X');
                        player1.send(String.format("%s was killed by  %s", player1.getName(), enemy.getName()));
                        System.out.println(cuurentLevel);
                        System.out.println("YOU LOST");
                        return;
                    }
                }*/
            }

        }
        System.out.println(you_won);
    }

    private Player getPlayer() {
        Map<Integer,Player > player = Player.playerFactory();
        choosePlayer(player);
        int playerInt=userInterface.readInt();
        if(player.get(playerInt) != null){
            userInterface.print("You hav selected");
            userInterface.print(player.get(playerInt).describe());
            return player.get(playerInt);
        }
        System.out.println("Ygritte is unavailable at the moment would you like to choose another player?");
        return getPlayer();
    }

    private void choosePlayer(Map<Integer, Player> player) {
        System.out.println("Please select your player");
        for (int i = 1; i <= player.size(); i++)
        {
            Player tmp = player.get(i);
            if (tmp != null)
                System.out.println(i+"."+" "+ tmp.describe());
        }

    }

    private void playerMove(Player p, GameBoard board, List<Enemy> enemyList) {
        try {
            char command = userInterface.readChar();
            switch (command) {
                case 'w': // up
                    Move(board, player, UP);
                    break;
                case 's': // down
                    Move(board, player, DOWN);
                    break;
                case 'a': //left
                    Move(board, player, LEFT);
                    break;
                case 'd': // right
                    Move(board, player, RIGHT);
                    break;
                case 'e': // cast
                    try {
                        player.OnAbilityCast(enemyList);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'q':
                    break;
                case 'K': // Burn them all   ;-)
                    BurnThemAll(enemyList, board);
                    break;
                default:
                    System.out.println("");
                    playerMove(p, board, enemyList);
                    break;
            }
        }
        catch(Exception e){
            System.out.println("");
            playerMove(p, board, enemyList);

        }
    }

    private void BurnThemAll(List<Enemy> enemyList, GameBoard board) {
        for (Enemy enemy : enemyList) {
            board.removeEnemy(enemy);
            enemyList.remove(enemy);
        }
    }

    private void EnemyMove(Player player, GameBoard board, Enemy enemy) {
        Position position = new Position(player.getPosition());
        Tile tile;
        if (enemy.Distance(player) < enemy.getRange()) {
            int dx;
            int dy;
            dx = Math.abs(player.getPosition().getX() - enemy.getPosition().getX());
            dy = Math.abs(player.getPosition().getY() - enemy.getPosition().getY());
            if (dx > dy)
                if (dx > 0) {
                    Move(board,enemy,LEFT);
                } else {
                    Move(board,enemy,RIGHT);
                }
            else if (dy > 0) {
                Move(board,enemy,UP);
            } else {
                Move(board,enemy,DOWN);
            }
        }
        else
        {
            Move(board,enemy,new Random().nextInt(STAY));
        }
    }

        ///Return a list of all txt files in desired directory
        private static List<File> getLevelFiles (String path){
            String dir = System.getProperty("user.dir");
            dir += "\\"+path;
            String directory = dir;
            List<File> levelList = new ArrayList<>();
            try {
                List<File> files = Files.list(Paths.get(directory)).sorted()
                        .map(Path::toFile)
                        .filter(File::isFile)
                        .filter(File -> File.getName().toString().endsWith(".txt"))
                        .collect(Collectors.toList());

                levelList = files;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return levelList;
        }



    ///Returns list of strings when every string is a row in a file.
    private static List<String> fileToRowList(File file) {
        List<String> rowList = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file.getPath()));
            String line = reader.readLine();
            while (line != null) {
                rowList.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rowList;
    }

    //
    private static GameBoard initGameBoard(List<String> rows, List<Enemy> enemyList, Player player){
        int rowNum = rows.size();
        int colNum = rows.get(0).length();
        List<Tile> AllTiles = new ArrayList<>();
        Player dummy = Player.playerFactory().get(-1).clone();
        for(int i=0; i < rowNum; i++)
        {
            String tileRow = rows.get(i);
            for (int j=0; j < colNum; j++)
            {
                Position position = new Position(i,j);
                char c = tileRow.charAt(j);

                if(ENEMY_LIST.contains(c))
                {

                    Enemy tmp = Enemy.enemyFactory(c,position);
                    if (tmp != null)
                    {
                        tmp.setMessageCallBack((msg)-> System.out.println(msg) );
                        AllTiles.add(tmp);
                        enemyList.add(tmp);
                    }
                    else
                    {
                        System.out.println(c+ " Is not a legal char");
                        AllTiles.add(Tile.tileFactory(c, position));
                    }
                }
                else if (PLANE_TILES.contains(c))
                {
                    AllTiles.add(Tile.tileFactory(c, position));
                }
                else if(c == '@')
                {
                    dummy.initialize(position);
                }
            }


        }

        return new GameBoard(AllTiles,rowNum,colNum,dummy);
    }

    public void Move(GameBoard board, Unit unit, int move){
        Position position = new Position(unit.getPosition());
        Tile tile;
        try {
        switch (move) {
            case 0:// up
                position.setX(position.getX() - 1);
                tile = board.get(position.getX(), position.getY());
                unit.Up(tile);
                break;
            case 1:// down
                position.setX(position.getX() + 1);
                tile = board.get(position.getX(), position.getY());
                unit.Down(tile);
                break;
            case 2://left
                position.setY(position.getY() - 1);
                tile = board.get(position.getX(), position.getY());
                unit.Left(tile);
                break;
            case 3:// right
                position.setY(position.getY() + 1);
                tile = board.get(position.getX(), position.getY());
                unit.Right(tile);
                break;
            case 4:
                break;
            default:
                throw new RuntimeException("something is fishy not a legal direction");
            }
        }
        catch (Exception e)
        {
            System.out.println("something is fishy");
            System.out.println(e.getMessage());
        }
    }


    private String you_won = " __    __                              __      __                      __     \n" +
            "/\\ \\  /\\ \\                            /\\ \\  __/\\ \\                    /\\ \\    \n" +
            "\\ `\\`\\\\/'/  ___    __  __             \\ \\ \\/\\ \\ \\ \\    ___     ___    \\ \\ \\   \n" +
            " `\\ `\\ /'  / __`\\ /\\ \\/\\ \\             \\ \\ \\ \\ \\ \\ \\  / __`\\ /' _ `\\   \\ \\ \\  \n" +
            "   `\\ \\ \\ /\\ \\L\\ \\\\ \\ \\_\\ \\             \\ \\ \\_/ \\_\\ \\/\\ \\L\\ \\/\\ \\/\\ \\   \\ \\_\\ \n" +
            "     \\ \\_\\\\ \\____/ \\ \\____/              \\ `\\___x___/\\ \\____/\\ \\_\\ \\_\\   \\/\\_\\\n" +
            "      \\/_/ \\/___/   \\/___/                '\\/__//__/  \\/___/  \\/_/\\/_/    \\/_/\n" +
            "                                                                              \n" +
            "                                                                              ";

}
