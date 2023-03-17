package it.unibo.AstroParty.model.Obstacle.impl;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.Obstacle.api.ObstacleFactory;
import it.unibo.AstroParty.model.api.Obstacle;

public class ObstacleFactoryImpl implements ObstacleFactory {

    private static int laserInterval = 2; // seconds requiered to make the variable "active" change status (in a laser)

    /**
     * {@inheritDoc}}
     */
    @Override
    public Obstacle createSimpleObstacle(Position pos) {
        return new ObstacleImpl(pos,true,false, new Timer(laserInterval));
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public Obstacle createLaser(Position pos) {
        return new ObstacleImpl(pos, false, true);
    }

}