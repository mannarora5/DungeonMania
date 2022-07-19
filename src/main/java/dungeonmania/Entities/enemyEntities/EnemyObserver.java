package dungeonmania.Entities.enemyEntities;

import dungeonmania.Entities.Player.PlayerStateSubject;

public interface EnemyObserver {
    
    public void updateMovement(PlayerStateSubject player);
    
}
