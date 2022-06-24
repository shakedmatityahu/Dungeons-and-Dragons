package Units.Players;

public class Health {
    private int HealthPool;
    private int HealthAmount;

    public Health(int pool, int amount){
        HealthPool = pool;
        HealthAmount = amount;
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
        HealthAmount = healthAmount;
    }
}
