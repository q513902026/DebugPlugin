package io.bilicraft.r6.plugin.debug;

import org.bukkit.event.Listener;

public class BaseListener implements Listener{
	public BaseListener()
	{
		DebugPlugin.getInstance().getServer().getPluginManager().registerEvents(this, DebugPlugin.getInstance());
		DebugPlugin.getInstance().getLogger().info("Listener:"+this.getClass().getSimpleName()+"注册完成");
	}
}
