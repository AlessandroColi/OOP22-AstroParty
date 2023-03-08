package it.unibo.AstroParty.model.PowerUp.impl;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.PowerUp.PowerUpTypes;
import it.unibo.AstroParty.model.api.Spaceship;

public class TemporaryImmortality extends BasicPowerUp {
	private final static double duration = 5;
	private final static double epsilon = 0.000001;
	
	private boolean inUse;
	
	private double startingTime = 0;
	
	public TemporaryImmortality(Position position) {
		super(position);
	}
	
	@Override
	public boolean pickUp(Spaceship owner) {
		
		super.pickUp(owner);
		this.use();
		
		return super.pickedUp;	
	}

	@Override
	public void use() {
		this.inUse=true;
		super.owner.makeImmortal();;
	}

	@Override
	public boolean isOffensive() {

		return false;
	}

	@Override
	public PowerUpTypes getType() {

		return PowerUpTypes.UPGRADEDSPEED;
	}

	@Override
	public void update(double time) {
		
		if ( super.pickedUp && this.inUse && Math.abs(this.startingTime) < TemporaryImmortality.epsilon ) {
			this.startingTime = time;
		}
		
		if ( this.inUse && this.startingTime + TemporaryImmortality.duration  <= time ) {
			super.owner.makeMortal();
			super.owner.removePowerUp( this );
		}
	}
}