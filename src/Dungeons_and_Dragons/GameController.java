package Dungeons_and_Dragons;

import GameTiles.Units.Enemies.Enemy;
import GameTiles.Units.Players.Player;

import javax.naming.directory.DirContext;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameController {

    ArrayList<GameBoard> levelList;
    ArrayList<Enemy> enemyList;
    Player player;

///
    String args1 = LEVEL_FILE_path;
    Char playr = new player (a);
///


    public GameController(String path, Player player1) {
        // path
        // לכל קובץ בפאת' תקרא אותו ותיצור ממנו בורד

        try {
            List<String> lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // פקודה שיוצרת מהתקיה בורדים
        levelList = new ArrayList<GameBoard>();

        // מהוברד שיצרת תיצור רשימת אוייבים
        enemyList = new ArrayList<Enemy>();

        if (levelList.get(1) != null) {


        }
    }

    public void tick(char a){
        System.out.println(a);
    }


        // פקודה שיוצר מהבורד את הenmylist
        player = player1;
    }





}
