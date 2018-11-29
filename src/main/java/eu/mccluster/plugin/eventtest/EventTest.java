/*
 * Copyright (C) 2015-2018 McCluster - All rights reserved.
 */
package eu.mccluster.plugin.eventtest;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.cause.entity.damage.source.DamageSource;
import org.spongepowered.api.event.entity.DamageEntityEvent;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.world.ExplosionEvent;
import org.spongepowered.api.text.Text;

public class EventTest {

    private static EventTestPlugin _plugin;
    private static EventTest _instance;

    public static void load(EventTestPlugin plugin) {
        if (_plugin == null)
            _plugin = plugin;
        if (_instance == null)
            _instance = new EventTest();
    }

    public static void enable(EventTestPlugin plugin) {
        load(plugin);
        _instance.onEnable();
    }

    public static void disable(EventTestPlugin plugin) {
        load(plugin);
    }
    
    private void onEnable() {
        Sponge.getEventManager().registerListeners(_plugin, this);
    }
    
    @Listener
    public void onBreakBlock(ChangeBlockEvent.Break event) {
        if (event.getSource().toString().contains("flowing_water")) {
            return;
        }
        Sponge.getGame().getServer().getConsole().sendMessage(Text.of(event.getSource() + " destroyed a block"));
    }
    
    @Listener
    public void onEntityDamage(DamageEntityEvent event, @First DamageSource damageSource) {
        Sponge.getGame().getServer().getConsole().sendMessage(Text.of(event.getTargetEntity() + " was hit from " + event.getSource() + " by " + damageSource));
    }
    
    @Listener(order = Order.FIRST, beforeModifications = true)
    public void onEntityExplosionPre(ExplosionEvent.Pre event, @Root Object source) {
        Sponge.getGame().getServer().getConsole().sendMessage(Text.of(event.getSource() + " exploded (Pre). Source " + source));
    }

    @Listener(order = Order.FIRST, beforeModifications = true)
    public void onEntityExplosionDetonate(ExplosionEvent.Detonate event) {
        Sponge.getGame().getServer().getConsole().sendMessage(Text.of(event.getSource() + " exploded (detonated)"));
    }

}
