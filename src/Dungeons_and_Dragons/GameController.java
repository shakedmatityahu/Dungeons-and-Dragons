package Dungeons_and_Dragons;

import GameTiles.Tile;
import GameTiles.Units.Enemies.Enemy;
import GameTiles.Units.Players.Player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameController {

    ArrayList<GameBoard> gameBoards;
    ArrayList<ArrayList<Enemy>> enemyList;
    Player player;
    private static final List<Character> ENEMY_LIST= List.of('s','k','q','z','b','g','w','M','C','K');
    private static final List<Character> PLANE_TILES= List.of('.','#');


    public GameController(String path, Player player1)
    {
        player1 = Player.playerFactory("Demo");
        List<File> fileList = getLevelFiles(path);
        gameBoards = new ArrayList<>(fileList.size());
        enemyList = new ArrayList<>(fileList.size());
        for (List<Enemy> list :enemyList)
        {
         list = new ArrayList<Enemy>();
        }

        //check that we don't miss last level
        for (int i=0; i <fileList.size(); i++)
        {
            File level = fileList.get(i);
            List<String> levelCharsRow = fileToRowList(level);
            GameBoard board= new GameBoard(
                    initGameBoard(levelCharsRow,enemyList.get(i),player1))
                    ;
            gameBoards.set(i,board);

        }
    }


    public void play(Player player1){
        for (int i = 0; i< gameBoards.size(); i++){
            GameBoard cuurentLevel = gameBoards.get(i);
            cuurentLevel.setPlayer(player1);
            List<Enemy> cuurentEnemyList = enemyList.get(i);
            while (!(cuurentEnemyList.isEmpty()))
            {
                String command = "fill with player input";

                playerMove(player1,command,cuurentLevel,cuurentEnemyList);
                if(!(player.isAlive)){
                    System.out.println("YOU LOST");
                }
                player1.onTick();
                for (Enemy enemy: cuurentEnemyList) {
                    enemy.onTick(player1);
                }
            }
        }
    }

    private void playerMove(Player p,String command, GameBoard board,List<Enemy> enemyList){
        Position position = new Position(player.getPosition());
        Tile tile;
        switch (command) {
            case "w":
                position.setX(position.getX() + 1);
                tile = board.finedTile(position);
                p.interact(tile);
                break;
            case "s":
                position.setX(position.getX() - 1);
                tile = board.finedTile(position);
                p.interact(tile);
                break;
            case "a":
                position.setY(position.getY() - 1);
                tile = board.finedTile(position);
                p.interact(tile);
                break;
            case "d":
                position.setY(position.getY() + 1);
                tile = board.finedTile(position);
                p.interact(tile);
                break;
            case "e":
                //player.castability(enemyList);
                break;
        }
    }

    ///Return a list of all txt files in desired directory
    private static List<File> getLevelFiles(String path) {
        String dir = System.getProperty("user.dir");
        dir += path;
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
    private static List<Tile> initGameBoard(List<String> rows, List<Enemy> enemyList, Player player){
        int rowNum = rows.size();
        int colNum = rows.get(0).length();
        List<Tile> AllTiles = new ArrayList<>();
        for(int i=0; i <= rowNum; i++)
        {
            String tileRow = rows.get(i);
            for (int j=0; j<=colNum; j++)
            {
                Position position = new Position(i,j);
                char c = tileRow.charAt(j);

                if(ENEMY_LIST.contains(c))
                {
                    //Might cause runTime error
                    Enemy tmp = Enemy.enemyFactory(c,position);
                    AllTiles.add(tmp);
                    enemyList.add(tmp);
                }
                else if (PLANE_TILES.contains(c))
                {
                    AllTiles.add(Tile.tileFactory(c, position));
                }
                else if(c == '@')
                {
                    player.initialize(position);
                    AllTiles.add(player);
                }

            }

        }

        return AllTiles;
    }

}
