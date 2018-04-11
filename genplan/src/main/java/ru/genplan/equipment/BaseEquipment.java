package ru.genplan.equipment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseEquipment implements IEquipment {
	private static final Logger LOG = LogManager.getLogger(BaseEquipment.class);
	private final List<IFixture> fixtures;

	int x;
	int y;
	int width;
	int height;
	int sectionCount = 0;
	
	public BaseEquipment(List<IFixture> fixtures) {
		LOG.trace("BaseEquipment entered");
		Set<Integer> sectionCounter = new HashSet<>();
		this.fixtures = fixtures;
		int max_x = 0;
		int max_y = 0;
		for(IFixture f:fixtures) {
			x = Math.min(x, f.getX());
			y = Math.min(y, f.getY());
			max_x = Math.max(max_x, f.getX() + f.getWidth()); 
			max_y = Math.max(max_y, f.getY() + f.getHeight()); 
			sectionCounter.add(f.getSectionNo());
		}
		sectionCount = sectionCounter.size();
		width = max_x - x;
		height = max_y - y;
		LOG.trace("BaseEquipment finished x={}, y={}, width={}, height={}",x,y,width,height);
	}
	
	@Override
	public int getAvailableWidth() {
		int aWidth = 0;
		for(IFixture fix:fixtures) {
			aWidth += fix.getAvailableWidth();
		}
		return aWidth;
	}
	
	@Override
	public int getAvailableWidthLeft(int x) {
		int aWidth = 0;
		for(IFixture fix:fixtures) {
			aWidth += fix.getAvailableWidthLeft(x);
		}
		return aWidth;
	}
	
	@Override
	public int getAvailableWidthRight(int x) {
		int aWidth = 0;
		for(IFixture fix:fixtures) {
			aWidth += fix.getAvailableWidthRight(x);
		}
		return aWidth;
	}

	

	@Override
	public void remove() {
		for(IFixture fix:fixtures) {
			fix.remove();
		}
	}
	
	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}
	
	@Override
	public int getFixtureCount() {
		return fixtures==null?0:fixtures.size();
	}

	@Override
	public int getSectionCount() {
		return sectionCount;
	}

	@Override
	public IEquipment generate(int x, int y, int width, int height) {
		List<? extends IFixture> temp_fixs = null;
		List<IFixture> fixs = null;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		for(IFixture fix:fixtures) {
			temp_fixs = fix.generate(x, y, width, height);
			if (temp_fixs!= null) {
			  if (fixs==null) {
				  fixs = new ArrayList<IFixture>();
			  }
			  fixs.addAll(temp_fixs);
			}
		}
		return new BaseEquipment(fixs);
	}

	@Override
	public IEquipment generate(Predicate<IFixture> pred, int x, int y, int width, int height) {
		List<? extends IFixture> temp_fixs = null;
		List<IFixture> local_fixs = fixtures.stream().filter(pred).collect(Collectors.toList());
		LOG.debug("local_fixs.size() ={}",local_fixs.size());
		List<IFixture> fixs = null;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		for(IFixture fix:local_fixs) {
			temp_fixs = fix.generate(x, y, width, height);
			if (temp_fixs!= null) {
			  if (fixs==null) {
				  fixs = new ArrayList<IFixture>();
			  }
			  LOG.debug("Fixture Y={}",temp_fixs.get(0).getY());
			  fixs.addAll(temp_fixs);
			}
		}
		return new BaseEquipment(fixs);
	}

	@Override
	public IEquipment generate(Predicate<IFixture> pred) {
		List<IFixture> local_fixs = fixtures.stream().filter(pred).collect(Collectors.toList());
		return new BaseEquipment(local_fixs);
	}
	
	public List<IFixture> getFixtures() {
		return fixtures;
	}
}
