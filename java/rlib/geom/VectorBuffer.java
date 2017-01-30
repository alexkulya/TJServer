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
package rlib.geom;

public final class VectorBuffer
{
	private final Vector[] vectors;
	private int index;
	
	public static VectorBuffer newInstance(int size)
	{
		return new VectorBuffer(size);
	}
	
	private VectorBuffer(int size)
	{
		this.vectors = new Vector[size];
		int i = 0;
		int length = this.vectors.length;
		while (i < length)
		{
			this.vectors[i] = Vector.newInstance();
			++i;
		}
	}
	
	public Vector getNextVector()
	{
		if (this.index == this.vectors.length)
		{
			this.index = 0;
		}
		return this.vectors[this.index++];
	}
}