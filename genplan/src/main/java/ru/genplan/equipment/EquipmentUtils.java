package ru.genplan.equipment;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ru.genplan.equipment.data.FixtureData;
import ru.genplan.equipment.data.SectionData;
/**
 * ����� ����������� ������� ��� ������ � �������������
 *
 * @author  Sergey Prokopchik
 */

public class EquipmentUtils {
	private static final Logger LOG = LogManager.getLogger(EquipmentUtils.class);
	private static Comparator<IFixture> fixtureByX  = 
	  new Comparator<IFixture>() {
		@Override
		public int compare(IFixture arg0, IFixture arg1) {
			return (arg0.getX()-arg1.getX());
		}
	};
	  
	private static Comparator<FixtureData> fixtureByY = 
	  new Comparator<FixtureData>() {
		    @Override
			public int compare(FixtureData arg0, FixtureData arg1) {
				return (arg0.getY()-arg1.getY());
			}
	};
	
	private static Comparator<FixtureData> fixtureBySectionNo = 
			  new Comparator<FixtureData>() {
				    @Override
					public int compare(FixtureData arg0, FixtureData arg1) {
						return (arg0.getFirstSectionNo()-arg1.getFirstSectionNo());
					}
			};
	
	private static Comparator<SectionData> sectionByX = 
	  new Comparator<SectionData>() {
		    @Override
			public int compare(SectionData arg0, SectionData arg1) {
				return (arg0.getX()-arg1.getX());
			}
	};
	
	private static Comparator<SectionData> sectionByNo = 
			  new Comparator<SectionData>() {
				    @Override
					public int compare(SectionData arg0, SectionData arg1) {
						return (arg0.getSectionNo()-arg1.getSectionNo());
					}
			};
	
    /**
     * �����, ���������� ��������� Comparator-��
     * @param comparators
     * @return
     */
	@SafeVarargs
	public static Comparator<FixtureData> fixtureChainedComparator(Comparator<FixtureData>... comparators) {
		return new Comparator<FixtureData>() {
			public int compare(FixtureData arg0, FixtureData arg1) {
				for (Comparator<FixtureData> comparator : Arrays.asList(comparators)) {
		            int result = comparator.compare(arg0, arg1);
		            if (result != 0) {
		                return result;
		            }
		        }
		        return 0;
			}
		};
	}
	
	/**
	 * ���������� FixtureData �� ���������� X
	 * @return Comparator
	 */
	public static Comparator<IFixture> fixtureByX() {
		return fixtureByX;
	}
	/**
	 * ���������� FixtureData �� ���������� Y
	 * @return Comparator
	 */
	public static Comparator<FixtureData> fixtureByY() {
		return fixtureByY;
	}
	
	/**
	 * ���������� SectionData �� ���������� X
	 * @return Comparator
	 */
	public static Comparator<SectionData> sectionByX() {
		return sectionByX;
	}
	/**
	 * ���������� SectionData �� ������
	 * @return Comparator
	 */
	public static Comparator<SectionData> sectionByNo() {
		return sectionByNo;
	}
	/**
	 * 
	 * @param order
	 * @return
	 */
	public static Comparator<FixtureData> fixtureBySectionNo() {
		return fixtureBySectionNo;
	}
	/**
	 * �������� ������� ����� �����, ����� ������������ � ������� 
	 * 
	 * @param tag
	 * @return
	 */
	public static Predicate<FixtureData> checkFixTag(String[] tag) {
		LOG.debug(tag);
		return p -> {
			if (p.getFixtureTag()==null) return false;
			for(String s:tag) {
				if (p.getFixTag().contains(s)) {
					return true;
				}
			}
			return false;
		};
	}
	
	/**
	 * �������� ��������� ����� � ��������� �������
	 * 
	 * @param x ��������� �����
	 * @param y ����������
	 * @param width ������ �����
	 * @param height ������ �����
	 * @return true ���� ����� ����������� ��������� �������
	 */
	public static Predicate<FixtureData> checkCoordinate(Integer x, Integer y, Integer width, Integer height) {
		LOG.debug("x="+x+", y="+y+", width="+width+", height="+height);
		return p -> {
			LOG.debug("x="+p.getX()+", y="+p.getX()+", width="+p.getWidth()+", height="+p.getHeight());
			if (   p.getX() < x+width
			    && p.getX() + p.getWidth() > x
			    && p.getY() < y+height
			    && p.getY() + p.getHeight() > y) {
				return true;
			}
			return false;
		};
	}
}
