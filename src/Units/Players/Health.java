package Units.Players;

public class Health {
    private int HealthPool;
    private int HealthAmount;

    public Health(){
        HealthPool = 0;
        HealthAmount = 0;
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
        if(healthAmount > this.HealthPool){
            healthAmount = this.getHealthPool();
        }
        else if(healthAmount < 0){
            
        }
        HealthAmount = healthAmount;
    }
}
