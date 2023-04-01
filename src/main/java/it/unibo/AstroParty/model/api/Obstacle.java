package it.unibo.AstroParty.model.api;

/**
 * interface that models the entity obstacle.
 */
public interface Obstacle extends Entity {

    /**
     * the size of the Obstacle
     */
    double size = 3.0;

    /**
     * 
     * @return true if the obstacle is visible/hittable
     */
    boolean isActive();

    /**
     * 
     * @return true if the obstacle damages the spaceship
     */
    boolean isHarmful();

    /**
     * {@inheritDoc}
     */
    @Override
    RectangleHitBox getHitBox();

}
