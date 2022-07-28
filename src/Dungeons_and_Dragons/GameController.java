package Dungeons_and_Dragons;

import GameTiles.Tile;
import GameTiles.Units.Enemies.Enemy;
import GameTiles.Units.Players.Player;

import javax.naming.directory.DirContext;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameController {

    ArrayList<GameBoard> levelList;
    ArrayList<ArrayList<Enemy>> enemyList;
    Player player;


    /*public GameController(String path, Player player1) {
        // path
        // לכל קובץ בפאת' תקרא אותו ותיצור ממנו בורד
        String dir = System.getProperty("user.dir");
        dir += path;


        String directory = dir;

        List<Path> pathList = new ArrayList<>();


        List<Path> pathList = new ArrayList<>();

        try (Stream<Path> stream = Files.walk(Paths.get(directory))) {
            // Do something with the stream.
            pathList = stream.map(Path::normalize)
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().endsWith(".java"))
                    .collect(Collectors.toList());


            // פקודה שיוצרת מהתקיה בורדים
            levelList = new ArrayList<GameBoard>();

            // מהוברד שיצרת תיצור רשימת אוייבים
            enemyList = new ArrayList<Enemy>();

            if (levelList.get(1) != null) {


            }
        }

        public void tick ( char a){
            System.out.println(a);
        }


        // פקודה שיוצר מהבורד את הenmylist
        player = player1;
    }*/

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

    private static Tile[][] initGameboard(List<String> rows, List<Enemy> enemyList){
        int rowNum = rows.size();
        int colNum = rows.get(0).length();
        Tile[][] initBoard = new Tile[rowNum][colNum];
        for(int i=0; i <= rowNum; i++)
        {
            String tileRow = rows.get(i);
            for (int j=0; j<=colNum; j++)
            {
                char c = tileRow.charAt(j);
                Tile tmp = Tile.tileFactory(c,i,j);
                initBoard[i][j] = tmp;

            }

        }

        return initBoard;
    }

}
