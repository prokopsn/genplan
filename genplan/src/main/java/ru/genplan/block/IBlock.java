package ru.genplan.block;

import java.util.List;

import ru.genplan.equipment.IEquipment;

public interface IBlock {
	public int getX();
	public int getY();
	public int getWidth();
	public int getHeight();
	public String getName();
	public int getType();
	public List<IEquipment> getEquipment();
	
}
