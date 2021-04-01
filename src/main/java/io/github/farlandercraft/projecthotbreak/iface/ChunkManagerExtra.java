// vim: noet
package io.github.farlandercraft.projecthotbreak.iface;

import net.minecraft.server.world.ChunkTicketManager;
import net.minecraft.util.math.ChunkPos;

public interface ChunkManagerExtra {
	public void levelUpdate(ChunkPos pos);
}
