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
package tera.gameserver.model.regenerations;

import tera.gameserver.model.Character;

/**
 * @author Ronn
 * @created 11.04.2012
 */
public class NpcRegenMp extends AbstractRegen<Character>
{
	/**
	 * Constructor for NpcRegenMp.
	 * @param actor Character
	 */
	public NpcRegenMp(Character actor)
	{
		super(actor);
	}
	
	/**
	 * Method checkCondition.
	 * @return boolean
	 * @see tera.gameserver.model.regenerations.Regen#checkCondition()
	 */
	@Override
	public boolean checkCondition()
	{
		final Character actor = getActor();
		return actor.getCurrentMp() < actor.getMaxMp();
	}
	
	/**
	 * Method doRegen.
	 * @see tera.gameserver.model.regenerations.Regen#doRegen()
	 */
	@Override
	public void doRegen()
	{
		final Character actor = getActor();
		actor.setCurrentMp(actor.getCurrentMp() + actor.getRegenMp());
		actor.updateMp();
	}
}
