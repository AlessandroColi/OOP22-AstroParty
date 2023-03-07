package it.unibo.AstroParty.model.Spaceship.impl;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import it.unibo.AstroParty.common.Direction;
import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.core.api.PlayerId;
import it.unibo.AstroParty.model.Spaceship.api.SimpleSpaceship;
import it.unibo.AstroParty.model.api.CircleHitBox;
import it.unibo.AstroParty.model.api.HitBox;
import it.unibo.AstroParty.model.api.PowerUp;
import it.unibo.AstroParty.model.api.Spaceship;
import it.unibo.AstroParty.model.impl.CircleHitBoxImpl;

public class SpaceshipImpl implements SimpleSpaceship {

	private double speed;								//impostazioni prese dal builder
	private final PlayerId playerId;
	private Position position;							// gestione movimento
	private Direction direction = new Direction(1,-0.5);
	private double angle=0;
	private Optional<Double> rotationStartTime ;
	private double lastTime;
	
	private Optional<PowerUp> powerUp = Optional.empty();
	
	private int bullets;								//proiettili
	private final int maxBullets;
	private final long bulletRegenTime;
	private Timer timer = new Timer();
	
	private boolean shield;								// defensive;
	private boolean immortal;
	
	public SpaceshipImpl(double speed, int maxBullets, boolean startingShield, PlayerId id, long bulletRegenTime){
		
		this.shield = startingShield;
		this.maxBullets = maxBullets;
		this.playerId = id;
		this.speed = speed;
		this.bulletRegenTime = bulletRegenTime;
		this.shield = startingShield;
		this.bullets = this.maxBullets;
	}
	
	// Usati dal gameLoop
	@Override
	public void setPosition(Position pos) {
		this.position = pos ;
	}

	@Override
	public Position getPosition() {
		return new Position( this.position.getX() , this.position.getY() );
	}

	@Override
	public boolean equipPowerUp(PowerUp pUp) {
		
		if ( powerUp.isEmpty()) {
			powerUp = Optional.ofNullable(pUp);
			pUp.pickUp(this);
			return true;
		}
		
		return false;
	}

	@Override
	public CircleHitBox getHitBox() {
		
		return new CircleHitBoxImpl(this.position , Spaceship.relativeSize);
	}
	
	public PlayerId getId() {
		return this.playerId;
	}

	public void update(double currTime) {
		
		double timeDiff = currTime - this.lastTime;
		this.lastTime = currTime;
		
		this.move(timeDiff);
	}

	public void shoot() {
		
		if( this.bullets <= 0) {
			return;
		}

		if ( this.powerUp.isPresent() && this.powerUp.get().isOffensive() ) {
			
			switch ( this.powerUp.get().getType() ) {
				
				case DOUBLESHOT:
					this.createProjectile();
					this.createProjectile();
					
					break;
					
				default :
					this.createProjectile();
			}
			
		}else {
			this.createProjectile();
		}
		this.bullets -- ;
		this.startTimer(); 								// non e' contenuto in createProjectile in quanto in caso di DuobleShot deve toglierne uno solo al counter
	}

	//usati dai PowerUp
	
	@Override
	public void removePowerUp(PowerUp pUp) {

		if( this.powerUp.isPresent() &&  this.powerUp.get().equals(pUp)) {
			this.powerUp = Optional.empty();
		}
	}

	@Override
	public void makeImmortal() {

		this.immortal = true;
	}
	

	@Override
	public void makeMortal() {
		
		this.immortal = false;
	}
	

	@Override
	public void newShield() {

		this.shield = true;
	}
	

	@Override
	public void upgradeSpeed() {
		
		this.speed = this.speed * PowerUp.speedModifier;
	}
	

	@Override
	public void normalSpeed() {

		this.speed = this.speed / PowerUp.speedModifier;
	}
	
	//usati per input da tastiera
	
	@Override
	public boolean hit() {			// basta dire che soso stato ucciso a gamestate, che lascia il mio riferimento e avvisa InputControl 
		

		if( this.immortal ) {
			return false;
		}
		
		if( this.shield ) {
			this.shield = false;
			return false;
		}
		return true;
	}

	public void startTurn() {
		this.rotationStartTime = Optional.ofNullable(this.lastTime);
	}

	public void stopTurn() {
		
		if (this.rotationStartTime.isEmpty() ) {
			return;
		}
	
		double turnTime = this.rotationStartTime.get() - this.lastTime;
		this.rotationStartTime = Optional.empty();   
		this.updateDirection(turnTime);
	}

	// metodi ad Uso interno

	private void updateDirection(double turnTime) {
		
		// uso le formule per trovare le coordinate di un punto dala la distanza dall'origine del piano e l'angolo rispetto all'asse x a velocita 1x
		
		this.angle = ( this.angle + turnTime * Spaceship.rotationSpeed ) % 360;
		
		double dirX = Math.cos( this.angle ) ;
		double dirY = Math.sin( this.angle ) ;
		
		this.direction = new Direction( dirX , dirY) ;		
	}

	private void move(double timeDiff) {
		
		this.position = this.position.move( this.direction.multiply( this.speed * timeDiff ) );
	}

	private void createProjectile() {
											// TODO Auto-generated method stub
		
	}
	
	private void startTimer() {

		timer.schedule( new TimerTask() {

			@Override
			public void run() {
				addBullet();
			}
			
		}, this.bulletRegenTime);
	}
	
	private void addBullet() {
		
		this.bullets ++;
		
		if(this.bullets < this.maxBullets) {
			this.startTimer();
		}
	}

}
