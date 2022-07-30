package GameTiles.Units.Resource;

public class Health {
    private int HealthPool;
    private int HealthAmount;

    public Health(){
        HealthPool = 0;
        HealthAmount = 0;
    }

    public Health(int pool, int amount)
    {
        HealthPool = pool;
        HealthAmount = amount;
    }
    public Health(int pool) {
        HealthPool = pool;
        HealthAmount = pool;
    }

    public int getHealthPool() {
        return HealthPool;
    }

    public void setHealthPool(int healthPool) {
        HealthPool = healthPool;
    }

    public int getHealthAmount() {
        return HealthAmount;
    }

    public void setHealthAmount(int healthAmount) {
        HealthAmount = Math.min(HealthPool,healthAmount);
    }
    public void inflictDamage(int damage)
    {
        HealthAmount = Math.max(0,HealthAmount-damage);
    }
}
