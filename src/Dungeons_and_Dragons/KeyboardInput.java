package Dungeons_and_Dragons;

public class KeyboardInput{

    enum Input {
        Left,
        Right,
        Up,
        Down,
        Cast,
        Wait;
    ////לא הצלחתי אבל צריך להוסיך מה כל אחד מהם בפועל כלומר שמאלה זה בעצם a ....
    }


    private char key;
    public KeyboardInput(char key)
    {
        this.key=key;
    }
   /* public string getKey()
    {
        return this.key;
    }*/


}
