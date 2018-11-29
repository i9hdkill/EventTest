/*
 * Copyright (C) 2015-2018 McCluster - All rights reserved.
 */
package eu.mccluster.plugin.eventtest;

import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;
import org.spongepowered.api.plugin.Plugin;

@Plugin(id = "eventtest", name = "EventTest", description = "Let players have their own world.", version = "1.0.0")
public class EventTestPlugin {

    @Listener
    public void load(GameInitializationEvent event) {
        EventTest.load(this);
    }

    @Listener
    public void enable(GameStartedServerEvent event) {
        EventTest.enable(this);
    }

    @Listener
    public void disable(GameStoppedServerEvent event) {
        EventTest.disable(this);
    }

}
