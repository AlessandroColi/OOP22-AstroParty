package it.unibo.astroparty.game.powerup.impl;

import java.util.EnumSet;

import it.unibo.astroparty.game.EntityType;
import it.unibo.astroparty.game.powerup.api.PowerUpSpawner;
import it.unibo.astroparty.game.powerup.api.SpawnerSettings;

/** 
 * a concrete implementation of {@link SpawnerSettings }.
 */
public class SpawnerSettingsImpl implements SpawnerSettings {

    private EnumSet<EntityType> possible = EnumSet.noneOf(EntityType.class);
    private long spawnDelay;

    /**
     * the basic setting is {@link #disableAll()}.
     */
    public SpawnerSettingsImpl() {
        this.spawnDelay = SpawnerSettings.BASIC_SPAWN_DELAY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PowerUpSpawner startGame() {
        return new PowerUpSpawnerImpl(this.possible, this.spawnDelay);
    }

    /**
     * @param type the type of PowerUp to be enabled.
     */
    private void enable(final EntityType type) {
        this.possible.add(type);
    }

    /**
     * @param type the type of PowerUp to be disabled.
     */
    private void disable(final EntityType type) {
        this.possible.remove(type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpawnDelay(final long timeInterval) {
        this.spawnDelay = timeInterval;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enableDoubleShot(final boolean enable) {

        if (enable) {
            this.enable(EntityType.DOUBLESHOT);
        } else {
            this.disable(EntityType.DOUBLESHOT);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enableTemporaryImmortality(final boolean enable) {

        if (enable) {
            this.enable(EntityType.IMMORTALITY);
        } else {
            this.disable(EntityType.IMMORTALITY);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enableUpgradedSpeed(final boolean enable) {

        if (enable) {
            this.enable(EntityType.UPGRADEDSPEED);
        } else {
            this.disable(EntityType.UPGRADEDSPEED);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enableShield(final boolean enable) {

        if (enable) {
            this.enable(EntityType.SHIELD);
        } else {
            this.disable(EntityType.SHIELD);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enableAll() {
        this.possible.addAll(EnumSet.allOf(EntityType.class));
        this.possible.removeIf(e -> e.getGeneralType() != EntityType.POWERUP);
        //System.out.println("-- " + this.possible);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disableAll() {
        this.possible = EnumSet.noneOf(EntityType.class);
    }

}
