// vim: noet
package com.javafarlander2002.projecthotbreak.iface;

import net.minecraft.server.world.ChunkTicketManager;
import net.minecraft.util.math.ChunkPos;

public interface ChunkManagerExtra {
	public void levelUpdate(ChunkPos pos);
}
