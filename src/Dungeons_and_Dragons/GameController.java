package Dungeons_and_Dragons;

import GameTiles.Tile;
import GameTiles.Units.Enemies.Enemy;
import GameTiles.Units.Players.Player;
import GameTiles.Units.Players.Warrior;
import GameTiles.Units.Unit;
import UI.UserInterface;

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
            GameBoard board = initGameBoard(levelCharsRow, levelEnemies, player);
            gameBoards.set(i, board);



        }
    }


    public void play() {

        for (int i = 0; i < gameBoards.size(); i++) {
            GameBoard cuurentLevel = gameBoards.get(i);
            cuurentLevel.setPlayer(player);
            List<Enemy> cuurentEnemyList = enemyList.get(i);
            player.initialize(gameBoards.get(i).findPlayerPosition().getPosition());
            while (!(cuurentEnemyList.isEmpty())) {

                //print board
                System.out.println(cuurentLevel);

                playerMove(player, cuurentLevel, cuurentEnemyList);
                for (Enemy enemy: cuurentEnemyList)
                {
                    if (enemy.isDead()){
                        cuurentLevel.Replace(enemy,Tile.tileFactory('.',enemy.getPosition()));
                        cuurentEnemyList.remove(enemy);
                        //enemy.setEnemyDeathCallBack();
                    }
                }
                if ((player.isDead())) {
                    player.setTile('X');
                    System.out.println(cuurentLevel);
                    System.out.println("YOU LOST");
                    return;
                }
                for (Enemy enemy : cuurentEnemyList) {
                    EnemyMove(player,cuurentLevel,enemy);
                }
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
                    Move(board, p, UP);
                    break;
                case 's': // down
                    Move(board, p, DOWN);
                    break;
                case 'a': //left
                    Move(board, p, LEFT);
                    break;
                case 'd': // right
                    Move(board, p, RIGHT);
                    break;
                case 'e': // cast
                    try {
                        player.OnAbilityCast(enemyList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'K': // Burn them all   ;-)
                    BurnThemAll(enemyList, board);
                    break;
            }
        }
        catch (Exception e)
        {
            System.out.println();
            playerMove(p,  board, enemyList);
        }
    }

    private void BurnThemAll(List<Enemy> enemyList, GameBoard board) {
        for (Enemy enemy : enemyList) {
            board.Replace(enemy,Tile.tileFactory('.',enemy.getPosition()));
            enemyList.remove(enemy);
        }
    }

    private void EnemyMove(Player player, GameBoard board, Enemy enemy) {
    /*    Position position = new Position(player.getPosition());
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
        }*/
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
        Tile [][] boardArray=new Tile[rowNum][colNum];
        List<Tile> listTile=new ArrayList<>();
        Player dummy = new Warrior("Jon Snow",300,30,4,3);
        for(int i=0; i < rowNum; i++)
        {
            String tileRow = rows.get(i);
            for (int j=0; j < colNum; j++)
            {
                Position position = new Position(i,j);
                char c = tileRow.charAt(j);
                if(ENEMY_LIST.contains(c))
                {

                    //Might cause runTime error
                    Enemy tmp = Enemy.enemyFactory(c,position);
                    if(tmp!=null){
                        boardArray[i][j]=tmp;
                        enemyList.add(tmp);
                        listTile.add(tmp);
                    }

                }
                else if (PLANE_TILES.contains(c))
                {
                    boardArray[i][j]=Tile.tileFactory(c, position);
                    listTile.add(boardArray[i][j]);
                }
                else if(c == '@')
                {
                    dummy.initialize(position);
                    boardArray[i][j]=dummy;
                    listTile.add(boardArray[i][j]);
                }

            }


        }
        return new GameBoard(boardArray,rowNum,colNum,player,dummy,listTile);
    }

    public void Move(GameBoard board, Unit unit, int move){
        Position position = new Position(unit.getPosition());
        Tile tile;
        switch (move){
            case 0:// up
                position.setX(position.getX() - 1);
                tile = board.finedTile(position);
                unit.Move(tile);
                board.sortTiles();
                board.sortArray();
                break;
            case 1:// down
                position.setX(position.getX() + 1);
                tile = board.finedTile(position);
                unit.Move(tile);
                board.sortArray();
                break;
            case 2://left
                position.setY(position.getY() - 1);
                tile = board.finedTile(position);
                unit.Move(tile);
                board.sortArray();
                break;
            case 3:// right
                position.setY(position.getY() + 1);
                tile = board.finedTile(position);
                player.Move(tile);
                board.sortArray();
                break;
            case 4:
                break;


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
