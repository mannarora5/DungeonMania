package dungeonmania.Entities.Player;

import dungeonmania.Entities.Player.PlayerState.PlayerState;
import dungeonmania.Entities.enemyEntities.EnemyObserver;

public interface PlayerStateSubject {
    
    public void attach(EnemyObserver enemy);
	public void detach(EnemyObserver enemy);
	public void notifyObservers();

    public PlayerState getState();
	
}
