package it.unibo.AstroParty.model.PowerUp.impl;

import java.util.EnumSet;
import it.unibo.AstroParty.model.PowerUp.PowerUpTypes;
import it.unibo.AstroParty.model.PowerUp.api.SpawnerSettings;
import it.unibo.AstroParty.model.api.PowerUpSpawner;

/** 
 * 
 * @author Alessandro Coli
 * 
 * a concrete implementation of {@link SpawnerSettings }
 */
public class SpawnerSettingsImpl implements SpawnerSettings {
	
	EnumSet<PowerUpTypes> possible = EnumSet.noneOf( PowerUpTypes.class );
	long spawnDelay;
	
	public SpawnerSettingsImpl(){
		this.DisableAll();
		this.spawnDelay = SpawnerSettings.basic_spawn_delay;
	}
	@Override
	public PowerUpSpawner startGame() {
		
		return new PowerUpSpawnerImpl( this.possible , this.spawnDelay );
	}
	
	private void enable( PowerUpTypes type ) {
		
		this.possible.add(type);
	}
	
	private void disable( PowerUpTypes type ) {
		
		this.possible.remove(type);
	}

	@Override
	public void setSpawnDelay(long timeInterval) {

		this.spawnDelay = timeInterval;
	}

	@Override
	public void enableDoubleShot(boolean enable) {
		
		if( enable ) {
			this.enable(  PowerUpTypes.DOUBLESHOT );
		}else {
			this.disable(  PowerUpTypes.DOUBLESHOT );
		}
	}

	@Override
	public void enableTemporaryImmortality(boolean enable) {
		
		if( enable ) {
			this.enable(  PowerUpTypes.IMMORTALITY );
		}else {
			this.disable(  PowerUpTypes.IMMORTALITY );
		}
	}

	@Override
	public void enableUpgradedSpeed(boolean enable) {
		
		if( enable ) {
			this.enable(  PowerUpTypes.UPGRADEDSPEED );
		}else {
			this.disable(  PowerUpTypes.UPGRADEDSPEED );
		}
	}

	@Override
	public void enableShield(boolean enable) {
		
		if( enable ) {
			this.enable( PowerUpTypes.SHIELD );
		}else {
			this.disable(  PowerUpTypes.SHIELD );
		}
	}

	@Override
	public void enableAll() {

		possible = EnumSet.allOf( PowerUpTypes.class );
	}

	@Override
	public void DisableAll() {

		this.possible = EnumSet.noneOf( PowerUpTypes.class );
	}

}
