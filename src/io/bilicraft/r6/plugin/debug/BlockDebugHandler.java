package io.bilicraft.r6.plugin.debug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.GenerationType;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.RegisteredListener;

public class BlockDebugHandler extends BaseListener {
	
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onBlockPlace(BlockPlaceEvent event) {
		if (event.getPlayer().hasPermission("bilicraft.debugmode")) {
			StringBuilder eventInfo = new StringBuilder("[DEBUG]--->");
			eventInfo.append("Event:" + event.getEventName()+"\n");
			eventInfo.append("|Block:" + event.getBlock().getTypeId()+">>"+event.getBlock().getType()+"\n");
			eventInfo.append("|Player:" + event.getPlayer()+"\n");
			eventInfo.append("|BlockPlace:" + event.getBlockPlaced().getTypeId()+">>"+event.getBlockPlaced().getType()+"\n");
			eventInfo.append("|BlockAgainst:" + event.getBlockAgainst().getTypeId()+">>"+event.getBlockPlaced().getType()+"\n");
			eventInfo.append("|isCancelled:" + event.isCancelled()+"\n");
			eventInfo.append("|Plugin:[");
			for (String s : getRegisterPlugin(event)) {
				eventInfo.append(s + ",");
			}
			eventInfo.append("] \n");
			eventInfo.append("|Handler:[");
			for(String s: getHandlerInfo(getRegisterListener(event)))
			{
				eventInfo.append(s+"||");
			}
			eventInfo.append("]<---");
			event.getPlayer().sendMessage(eventInfo.toString());
		}

	}

	private <T extends Event> Set<String> getRegisterPlugin(T event) {
		Set<String> result = new HashSet<String>();
		for (RegisteredListener register : event.getHandlers().getRegisteredListeners()) {
			result.add(register.getPlugin().getName());
		}
		return result;
	}
	private <T extends Event> Map<String,String> getRegisterListener(T event) {
		Map<String,String> result = new HashMap<String,String>();
		for (RegisteredListener register : event.getHandlers().getRegisteredListeners()) {
			result.put(register.getListener().getClass().getSimpleName(),register.getPriority().name());
		}
		return result;
	}
	private  List<String> getHandlerInfo(Map<String,String> infos)
	{
		List<String> result = new ArrayList<String>();
		for(Map.Entry<String, String> entry:infos.entrySet())
		{
			result.add(entry.getKey()+"->"+entry.getValue());
		}
		return result;
	}
}
