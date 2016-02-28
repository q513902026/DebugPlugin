package io.bilicraft.r6.plugin.debug;

import org.bukkit.plugin.java.JavaPlugin;

public class DebugPlugin extends JavaPlugin {
	private static DebugPlugin instance;
	@Override
	public void onEnable()
	{
		instance = this;
		this.getLogger().info("插件已启用 给予bilicraft.debugmode 权限 查看信息");
		loadDebugListener();
	}

	private void loadDebugListener() {
		new BlockDebugHandler();
		
	}

	public static DebugPlugin getInstance() {
		return instance;
		
	}
}
