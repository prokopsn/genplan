package ru.genplan.mybatis.mappers;

import java.util.HashSet;
import java.util.Set;

public class SectionData {
	private int id;
	private int planogramId;
	private String name;
	private String sectionTag;
	private int equipmentType;
	private int sectionNo;
	private int x;
	private int y;
	private int z;
	private int width;
	private int height;
	private int depth;
	private int effectiveX = -1;
	private int effectiveY = -1;
	private int effectiveZ = -1;
	private int effectiveWidth = -1;
	private int effectiveHeight = -1;
	private int effectiveDepth = -1;
	private boolean leftBorder;
	private boolean rightBorder;
	private int leftOverHang;
	private int rightOverHang;
	private Set<String> secTag;
	/**
	 * Номер группы секций слева.
	 * Группой является оборудование стоящее рядом
	 */
	private int groupLeftNo;
	
	public int getGroupLeftNo() {
		return groupLeftNo;
	}
	
	public void setGroupLeftNo(int groupLeftNo) {
		this.groupLeftNo = groupLeftNo;
	}
	
	public int getGroupRightNo() {
		return groupRightNo;
	}
	
	public void setGroupRightNo(int groupRightNo) {
		this.groupRightNo = groupRightNo;
	}
	
	public int getLeftNo() {
		return leftNo;
	}
	
	public void setLeftNo(int leftNo) {
		this.leftNo = leftNo;
	}
	
	public int getRightNo() {
		return rightNo;
	}
	
	public void setRightNo(int rightNo) {
		this.rightNo = rightNo;
	}
	
	public boolean getLeftBorder() {
		return leftBorder;
	}
	
	public boolean getRightBorder() {
		return rightBorder;
	}

	/**
	 * Номер группы секций справа.
	 * Группой является оборудование стоящее рядом
	 */
	private int groupRightNo;
	/**
	 * Номер секции слева.
	 * Совпадает с sectionNo
	 */
	private int leftNo;
	/**
	 * Номер секции справа.
	 * Совпадает с sectionNo
	 */
	private int rightNo;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getPlanogramId() {
		return planogramId;
	}
	
	public void setPlanogramId(int planogramId) {
		this.planogramId = planogramId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSectionTag() {
		return sectionTag;
	}
	
	public void setSectionTag(String sectionTag) {
		this.sectionTag = sectionTag;
		if (this.secTag != null)
			this.secTag.clear();
	}
	
	public int getEquipmentType() {
		return equipmentType;
	}
	
	public void setEquipmentType(int equipmentType) {
		this.equipmentType = equipmentType;
	}
	
	public int getSectionNo() {
		return sectionNo;
	}
	
	public void setSectionNo(int sectionNo) {
		this.sectionNo = sectionNo;
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
	
	public int getZ() {
		return z;
	}
	
	public void setZ(int z) {
		this.z = z;
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
	
	public int getDepth() {
		return depth;
	}
	
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public int getEffectiveX() {
		return effectiveX;
	}
	
	public void setEffectiveX(int effectiveX) {
		this.effectiveX = effectiveX;
	}
	
	public int getEffectiveY() {
		return effectiveY;
	}
	
	public void setEffectiveY(int effectiveY) {
		this.effectiveY = effectiveY;
	}
	
	public int getEffectiveZ() {
		return effectiveZ;
	}
	
	public void setEffectiveZ(int effectiveZ) {
		this.effectiveZ = effectiveZ;
	}
	
	public int getEffectiveWidth() {
		return effectiveWidth;
	}
	
	public void setEffectiveWidth(int effectiveWidth) {
		this.effectiveWidth = effectiveWidth;
	}
	
	public int getEffectiveHeight() {
		return effectiveHeight;
	}
	
	public void setEffectiveHeight(int effectiveHeight) {
		this.effectiveHeight = effectiveHeight;
	}
	
	public int getEffectiveDepth() {
		return effectiveDepth;
	}
	
	public void setEffectiveDepth(int effectiveDepth) {
		this.effectiveDepth = effectiveDepth;
	}
	
	public void setLeftBorder(boolean leftBorder) {
		this.leftBorder = leftBorder;
	}
	
	
	public void setRightBorder(boolean rightBorder) {
		this.rightBorder = rightBorder;
	}
	
	public int getLeftOverHang() {
		return leftOverHang;
	}
	
	public void setLeftOverHang(int leftOverHang) {
		this.leftOverHang = leftOverHang;
	}
	
	public int getRightOverHang() {
		return rightOverHang;
	}
	
	public void setRightOverHang(int rightOverHang) {
		this.rightOverHang = rightOverHang;
	}
	
	public Set<String> getSecTag() {
		if (secTag==null && sectionTag != null) {
			secTag = new HashSet<String>();
			for(String s:sectionTag.split(";")) {
				secTag.add(s);
			}
		}
		return secTag;
	}
}
