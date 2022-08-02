package GameTiles.Units.Resource;

import GameTiles.Units.Enemies.Enemy;
import GameTiles.Units.Players.Player;

import java.util.List;

public class Blizzard extends Ability
{
    private int manaPool;
    private int currentMana;
    private int spellPower;
    private int hitsCount;
    private  int manaCost;

    private final int MAGE_MANA_MULTIPLAYER = 25;
    private final int ENERGY_RAISE = 10;
    private final int MAGE_SPELL_MULTIPLAYER = 10;
    private final int MAGE_MANA_DIV = 4;


    public Blizzard(String name, int range, int manaPool, int spellPower,int hitsCount, int manaCost) {
        super(name, range);
        this.manaPool=manaPool;
        this.currentMana=manaPool/MAGE_MANA_DIV;
        this.spellPower=spellPower;
        this.hitsCount=hitsCount;
        this.manaCost=manaCost;
    }

    @Override
    public Ability clone() {
        Ability copy= new Blizzard(this.getName(),this.getRange(),manaPool,spellPower,hitsCount,manaCost);
        return copy;
    }

    public boolean canCastAbility ()
    {
        return(currentMana > manaCost);
    }

    @Override
    public void levelUp(int level) {
        this.manaPool += MAGE_MANA_MULTIPLAYER*level;
        this.currentMana = (int) Math.min((double) currentMana+(currentMana/MAGE_MANA_DIV),(double)manaPool);
        this.spellPower += (MAGE_SPELL_MULTIPLAYER*level);
    }

    @Override
    public void gameTick(int level) {
        this.currentMana=Math.min(manaPool,(currentMana+1)*level);
    }

    @Override
    public void abilityCast(Player p, List<Enemy> enemyList)
    {
        int random=0;
        while(!enemyList.isEmpty()&& canCastAbility())
        {
            int hits=0;
            random=p.randomNumber(enemyList.size());
            Enemy enemy=enemyList.get(random);
            while(hitsCount>hits) {
                p.castAbility(enemy, spellPower);
                if(enemy.isDead())
                    break;
                hits++;
            }
            this.currentMana -= this.manaCost;
        }

    }

    private String describeMana()
    {
        return manaCost+ "/"+ manaPool;
    }

    /*private String describeSpeelPower()
    {
        return
    }*/
    public String describe(){
        return "Mana: "+currentMana+ "/"+ manaPool +"         "+ "SpellPower: "+spellPower;
    }

}
