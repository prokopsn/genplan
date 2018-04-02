package ru.genplan.mybatis.mappers;

import java.util.HashSet;
import java.util.Set;


public class FixtureData {
	/**
	 * Идентификатор полки.
	 */
	private int id;
	
	/**
	 * Первый номер секции к которой относится полка
	 */
	private int firstSectionNo;
	
	/**
	 * Последний номер секции к которой относится полка
	 */
	private int lastSectionNo;
	
	/**
	 * Номер планограммы
	 */
	private int planogramId;
	
	/**
	 * Ноименование полки
	 */
	private String name;
	
	/**
	 * Список тегов. Разделитель ;
	 */
	private String fixtureTag;
	
	/**
	 * Тип полки
	 */
	private int fixtureType;
	
	/**
	 * Координата X
	 */

	private int x;
	
	/**
	 * Координата Y
	 */
	private int y;
	
	/**
	 * Координата Z
	 */
	private int z;
	private int width;
	private int height;
	private int depth;
	private int leftOverHang = -1;
	private int rightOverHang = -1;
	private int frontOverHang = 0;
	private int backOverHang = 0;
	private boolean leftBorder;
	private boolean rightBorder;
	private int xspacing = -1;
	private int yspacing = -1;
	private Set<String> fixTag;
	private int merchymax = -1;
	private int spaceAvailable = -1;
	private int wallWidth;
	
	/**
	 * Абсолютный номер полки сверху на оборудовании (секции).
	 */
	private int upFixNo = -1;
	
	/**
	 * Абсолютный номер полки снизу на оборудовании (секции).
	 */
	private int downFixNo = -1;
	
	private SectionData section;
	
	public FixtureData() {
		super();
	}
	
	public FixtureData(FixtureData fixData) {
		super();
		this.x = fixData.getX();
		this.y = fixData.getY();
		this.z = fixData.getZ();
		this.width = fixData.getWidth();
		this.depth = fixData.getDepth();
		this.height = fixData.getHeight();
		
		this.firstSectionNo = fixData.getFirstSectionNo();
		this.lastSectionNo = fixData.getLastSectionNo();
		
		this.fixtureTag = fixData.getFixtureTag();
		this.fixtureType = fixData.getFixtureType();
		this.frontOverHang = fixData.getFrontOverHang();
		this.leftOverHang = fixData.getLeftOverHang();
		this.rightOverHang = fixData.getRightOverHang();
		this.backOverHang = fixData.getBackOverHang();
		
		this.id = fixData.getId();
		
		this.leftBorder = fixData.isLeftBorder();
		this.rightBorder = fixData.isRightBorder();
		
		this.merchymax = fixData.getMerchymax();
		this.name = fixData.getName();
		this.planogramId = fixData.getPlanogramId();
				
		this.spaceAvailable = fixData.getSpaceAvailable();
		this.wallWidth = fixData.getWallWidth();
				
		this.xspacing = fixData.getXspacing();
		this.yspacing = fixData.getYspacing();
		
		this.downFixNo = fixData.getDownFixNo();
		this.upFixNo = fixData.getUpFixNo();
		this.section = fixData.getSection();
	}
	
	
	public int getSpaceAvailable() {
		return spaceAvailable;
	}
	
	public void setSpaceAvailable(int spaceAvailable) {
		this.spaceAvailable = spaceAvailable;
	}
	
	
	public int getMerchymax() {
		return merchymax;
	}
	
	
	public void setMerchymax(int merchymax) {
		this.merchymax = merchymax;
	}

	/**
     * Возвращает теги, ассоциированные с полкой в виде Set
     *
     * @return 
     */
	public Set<String> getFixTag() {
		if (this.fixTag==null && fixtureTag!= null) {
			this.fixTag = new HashSet<String>();
			for(String s:fixtureTag.split(";")) {
				this.fixTag.add(s);
			}
		}
		return fixTag;
	}
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getFirstSectionNo() {
		return firstSectionNo;
	}
	
	public void setFirstSectionNo(int firstSectionNo) {
		this.firstSectionNo = firstSectionNo;
	}
	
	public int getLastSectionNo() {
		return lastSectionNo;
	}
	
	public void setLastSectionNo(int lastSectionNo) {
		this.lastSectionNo = lastSectionNo;
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
	
	public String getFixtureTag() {
		return fixtureTag;
	}
	
	public void setFixtureTags(String fixtureTag) {
		this.fixtureTag = fixtureTag;
		if (this.fixTag != null) {
			this.fixTag.clear();
		}
	}
	
	public int getFixtureType() {
		return fixtureType;
	}
	
	public void setFixtureType(int fixtureType) {
		this.fixtureType = fixtureType;
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
	
	public int getLeftOverHang() {
		if (leftOverHang != -1)
		   return leftOverHang;
		if (section != null)
		   return section.getLeftOverHang();
		return 0;
	}
	
	public void setLeftOverHang(int leftOverHang) {
		this.leftOverHang = leftOverHang;
	}
	
	public int getRightOverHang() {
		if (rightOverHang != -1)
		   return rightOverHang;
		if (section != null)
		   return section.getRightOverHang();
		return 0;
	}
	
	public void setRightOverHang(int rightOverHang) {
		this.rightOverHang = rightOverHang;
	}
	
	public int getFrontOverHang() {
		return frontOverHang;
	}
	
	public void setFrontOverHang(int frontOverHang) {
		this.frontOverHang = frontOverHang;
	}
	
	public int getBackOverHang() {
		return backOverHang;
	}
	
	public void setBackOverHang(int backOverHang) {
		this.backOverHang = backOverHang;
	}
	
	public boolean isLeftBorder() {
		return leftBorder;
	}
	
	public void setLeftBorder(boolean leftBorder) {
		this.leftBorder = leftBorder;
	}
	
	public boolean isRightBorder() {
		return rightBorder;
	}
	
	public void setRightBorder(boolean rightBorder) {
		this.rightBorder = rightBorder;
	}
	
	public int getXspacing() {
		return xspacing;
	}
	
	public void setXspacing(int xspacing) {
		this.xspacing = xspacing;
	}
	
	public int getYspacing() {
		return yspacing;
	}
	
	public void setYspacing(int yspacing) {
		this.yspacing = yspacing;
	}
	
	public SectionData getSection() {
		return section;
	}
	public void setSection(SectionData section) {
		this.section = section;
	}
	
	public int getUpFixNo() {
		return upFixNo;
	}
	
	public void setUpFixNo(int upFixNo) {
		this.upFixNo = upFixNo;
	}
	
	
	public int getDownFixNo() {
		return downFixNo;
	}
	
	public void setDownFixNo(int downFixNo) {
		this.downFixNo = downFixNo;
	}
	
	public int getWallWidth() {
		return wallWidth;
	}
	
	public void setWallWidth(int wallWidth) {
		this.wallWidth = wallWidth;
	}
	
}
