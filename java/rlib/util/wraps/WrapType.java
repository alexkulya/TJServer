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
package rlib.util.wraps;

import rlib.util.pools.FoldablePool;
import rlib.util.pools.Pools;

public enum WrapType
{
	FLOAT,
	DOUBLE,
	CHAR,
	OBJECT,
	BYTE,
	SHORT,
	LONG,
	INTEGER;
	
	private final FoldablePool<Wrap> pool = Pools.newConcurrentFoldablePool(Wrap.class);
	
	protected void put(Wrap wrap)
	{
		pool.put(wrap);
	}
	
	protected Wrap take()
	{
		return pool.take();
	}
}
