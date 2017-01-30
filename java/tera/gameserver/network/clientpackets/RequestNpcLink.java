/*
 * This file is part of TJServer.
 * 
 * TJServer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * TJServer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package tera.gameserver.network.clientpackets;

import tera.gameserver.model.npc.Npc;
import tera.gameserver.model.npc.interaction.Link;
import tera.gameserver.model.playable.Player;

/**
 * @author Ronn
 */
public class RequestNpcLink extends ClientPacket
{
	private Player player;
	private int index;
	
	/**
	 * Method finalyze.
	 * @see rlib.util.pools.Foldable#finalyze()
	 */
	@Override
	public void finalyze()
	{
		player = null;
	}
	
	/**
	 * Method isSynchronized.
	 * @return boolean
	 * @see rlib.network.packets.ReadeablePacket#isSynchronized()
	 */
	@Override
	public boolean isSynchronized()
	{
		return true;
	}
	
	@Override
	protected void readImpl()
	{
		player = owner.getOwner();
		readInt();
		index = readByte();
	}
	
	@Override
	protected void runImpl()
	{
		if (player == null)
		{
			return;
		}
		
		final Npc npc = player.getLastNpc();
		Link link = player.getLink(--index);
		
		if ((link != null) && (npc != null) && link.test(npc, player))
		{
			player.setLastLink(link);
			player.clearLinks();
			link.reply(npc, player);
		}
		else if (npc != null)
		{
			link = player.getLastLink();
			player.setLastLink(null);
			
			if ((link != null) && link.test(npc, player))
			{
				link.reply(npc, player);
			}
		}
	}
}