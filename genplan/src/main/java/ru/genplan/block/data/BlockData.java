package ru.genplan.block.data;

public class BlockData {
	int blockId;
	int parentBlockId;
	int templId;
	String name;
	int orientType;
	int x;
	int y;
	int width;
	int height;
	public int getBlockId() {
		return blockId;
	}
	public void setBlockId(int blockId) {
		this.blockId = blockId;
	}
	public int getParentBlockId() {
		return parentBlockId;
	}
	public void setParentBlockId(int parentBlockId) {
		this.parentBlockId = parentBlockId;
	}
	public int getTemplId() {
		return templId;
	}
	public void setTemplId(int templId) {
		this.templId = templId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOrientType() {
		return orientType;
	}
	public void setOrientType(int orientType) {
		this.orientType = orientType;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}
