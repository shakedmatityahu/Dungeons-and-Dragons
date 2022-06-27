package Dungeons_and_Dragons;

public enum KeyboardInput{
        Left("a"),
        Right("d"),
        Up("w"),
        Down("s"),
        Cast("e"),
        Wait("q");

    private String key;
    KeyboardInput(String key)
    {
        this.key=key;
    }
    public String getKey()
    {
        return this.key;
    }




}
