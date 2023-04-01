package it.unibo.AstroParty.graphics.impl;

import java.util.Optional;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.graphics.api.GraphicEntity;
import it.unibo.AstroParty.input.api.GameId;
import it.unibo.AstroParty.model.api.EntityType;

/**
 * {@inheritDoc}
 */
public class GraphicEntityImpl implements GraphicEntity {
	
	private double angle = 0;
	private final EntityType type;
	private GameId id;
	private final double height;
	private final double length ;
	private final Position corner;
	
	/**
	 * all the basic parameters for drqwing an entity
	 * 
	 * @param topLetfCorner
	 * @param height
	 * @param length
	 * @param type
	 */
	public GraphicEntityImpl(Position topLetfCorner, double height, double length, EntityType type){
		this.corner = topLetfCorner;
		this.height = height;
		this.length = length;
		this.type = type;
	}

    /**
     * {@inheritDoc}
     */
    @Override
	public EntityType getType() {
		
		return this.type;
	}
    
    /**
     * {@inheritDoc}
     */
    @Override
	public Optional<GameId> getId() {
		
		return Optional.ofNullable( this.id );
	}

    /**
     * {@inheritDoc}
     */
    @Override
	public void setId(GameId id) {
		this.id = id;
	}

    /**
     * {@inheritDoc}
     */
    @Override
	public void setAngle(double angle) {
		this.angle = angle;
	}
    
    /**
     * {@inheritDoc}
     */
    @Override
	public Position getPosition() {
		return this.corner.copy();
	}
    
    /**
     * {@inheritDoc}
     */
    @Override
	public double getAngle() {
		return this.angle;
	}
    
    /**
     * {@inheritDoc}
     */
    @Override
	public double getHeight() {
		return this.height;
	}

    /**
     * {@inheritDoc}
     */
    @Override
	public double getLength() {
		return this.length;
	}

}