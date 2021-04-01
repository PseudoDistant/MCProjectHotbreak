// vim: noet
package com.javafarlander2002.projecthotbreak.iface;

import net.minecraft.server.world.ThreadedAnvilChunkStorage;

public interface ChunkHolderExtra {
	public void setPromoted(boolean promoted);
	public void tickProxy(ThreadedAnvilChunkStorage tacs);
}
