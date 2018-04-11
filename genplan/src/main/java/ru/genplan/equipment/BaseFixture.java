package ru.genplan.equipment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;

import ru.genplan.equipment.data.FixtureData;

public class BaseFixture implements IFixture {
	

    private static final Logger LOG = LogManager.getLogger(BaseFixture.class);
	private FixtureUsed<IFixture> fixtureUsed;
	private IFixture parent;
	private int availableWidth;
	
    /* (non-Javadoc)
	 * @see ru.genplan.equipment.IFixture#getAvailableWidthLeft(int)
	 * Функция возвращает свободное место на полке 
	 * слева от указанной координаты
	 */
	@Override
	public int getAvailableWidthLeft(int x) {
		int result = x - this.getX();
		if (x <= this.getX()) {
			return 0;
		} else if (x >= this.getX()+this.getWidth()) {
			return availableWidth;
		}
		Iterator <? extends IFixture> it = fixtureUsed.iterator();
		IFixture fix = null;
		for(;it.hasNext();) {
			fix = it.next();
			if (fix.getX() < x) {
				result -= Math.min(x,fix.getX() + fix.getWidth()) - fix.getX();
				if (fix.getX()+fix.getWidth() >= x) {
					return result;
				}
			}
		};
		return result;
	}

	/* (non-Javadoc)
	 * @see ru.genplan.equipment.IFixture#getAvailableWidthRight(int)
	 * Функция возвращает свободное место на полке 
	 * справа от указанной координаты
	 */
	@Override
	public int getAvailableWidthRight(int x) {
		int result = this.getX() + this.getWidth() - x;
		if (x >= this.getX()+this.getWidth()) {
			return 0;
		} else if (x < this.getX()) {
			return availableWidth;
		}
		LOG.debug("getAvailableWidthRight result="+result);
		Iterator <? extends IFixture> it = fixtureUsed.iterator();
		IFixture fix = null;
		for(;it.hasNext();) {
			fix = it.next();
			if (fix.getX() + fix.getWidth() > x) {
				result -= fix.getX() + fix.getWidth() - Math.max(x,fix.getX());
			}
		};
		return result;
	}
	
	FixtureData fixtureData;
	private int x = -1;
	private int width = -1;
	
	/*Конструктор для инициализации на основе данных*/
	public BaseFixture(FixtureData fixtureData) {
		this.fixtureData = fixtureData;
		this.x = fixtureData.getX();
		this.width = fixtureData.getWidth();
		this.availableWidth = this.width;
		this.fixtureUsed = new FixtureUsed<IFixture>();
	}
	
	/*Конструктор копии*/
	public BaseFixture(BaseFixture fix, int x, int width) {
		LOG.debug("BaseFixture x="+x+" width="+width);
		this.fixtureData = fix.fixtureData;
		this.fixtureUsed = new FixtureUsed<IFixture>();
		if (x < fix.getX() + fix.getWidth()
		    && x >= fix.getX()) {
			this.x = x;
		} else {
			LOG.error("Координата x ={} выходит за пределы полки [{}:{}]",x,fix.getX(),fix.getX()+fix.getWidth());
		}
		if (x + width <= fix.getX() + fix.getWidth()
		    && x + width > fix.getX()) {
			this.width = width;
			this.availableWidth = this.width;
		} else {
			LOG.error("Координата x + width ={} выходит за пределы полки [{}:{}]",x,fix.getX(),fix.getX()+fix.getWidth());
		}
	}
	
	@Override
	public int getId() {
		return fixtureData.getId();
	}

	@Override
	public String getName() {
		return fixtureData.getName();
	}


	@Override
	public int getPlanogramId() {
		return fixtureData.getPlanogramId();
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return fixtureData.getY();
	}

	@Override
	public int getZ() {
		return fixtureData.getZ();
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getDepth() {
		return fixtureData.getDepth();
	}

	@Override
	public int getMerchymax() {
		return fixtureData.getMerchymax();
	}

	@Override
	public int getSpaceAvailable() {
		return fixtureData.getSpaceAvailable();
	}

	@Override
	public int getAbsoluteUpFixNo() {
		return fixtureData.getUpFixNo();
	}

	@Override
	public int getAbsoluteDownFixNo() {
		return fixtureData.getDownFixNo();
	}
	
	@Override
	public String getSectionName() {
		return fixtureData.getSection().getName();
	}

	@Override
	public Set<String> getSectionTags() {
		return fixtureData.getSection().getSecTag();
	}

	@Override
	public Set<String> getFixtureTags() {
		return fixtureData.getFixTag();
	}

	@Override
	public int getFixtureType() {
		return fixtureData.getFixtureType();
	}

	@Override
	public int getLeftOverHang() {
		if (x==fixtureData.getX()) {
		  return fixtureData.getLeftOverHang();
		};
		return 0;
	}

	@Override
	public int getRightOverHang() {
		if (x+width == fixtureData.getX()+fixtureData.getWidth()) {
		  return fixtureData.getRightOverHang();
		};
		return 0;
	}

	@Override
	public int getFrontOverHang() {
		return fixtureData.getFrontOverHang();
	}

	@Override
	public int getBackOverHang() {
		return fixtureData.getBackOverHang();
	}

	@Override
	public boolean isLeftBorder() {
		if (x==fixtureData.getX()) {
		  return fixtureData.isLeftBorder();
		};
		return false;
	}

	@Override
	public boolean isRightBorder() {
		if (x+width == fixtureData.getX()+fixtureData.getWidth()) {
		  return fixtureData.isRightBorder();
		};
		return false;
	}

	@Override
	public int getXspacing() {
		return fixtureData.getXspacing();
	}

	@Override
	public int getYspacing() {
		return fixtureData.getYspacing();
	}

	@Override
	public int getAvailableWidth() {
		return availableWidth;
	}


	@Override
	public List<IFixture> generate(int x, int y, int width, int height) {
	    if (this.getY() < y 
			|| this.getY() > y+height) {
			return null;
		}
	    // Результат 
	    List<IFixture> result = null;
		// Получаем итератор по использованным полкам
		Iterator <? extends IFixture> it = fixtureUsed.iterator();
		// Новая полка
		IFixture fix;
		// Используемая полка
		IFixture useFix = null;
		int min_x = Math.max(x, this.getX()); 
		int max_x = Math.min(x + width, this.getX() + this.getWidth()); 
		int from_x = min_x;
		int to_x = max_x;
		//Выполняем пока не достигли конца полки
		for (;from_x < max_x;) {
			//from_x += useFix==null?to_x:useFix.getWidth();
			useFix = it.hasNext()?it.next():null;
			if (useFix==null) {
				to_x = max_x;
			} else {
				if (useFix.getX()+useFix.getWidth()<=x) {
					continue;
				} else if (x+width <= useFix.getX()) {
					continue;
				}
				to_x = useFix.getX();
			};
			LOG.debug("(1) from_x="+from_x+" : to_x="+to_x);
			if (from_x < to_x) {
				// Гнереним новую полку
				fix =  new BaseFixture(this,Math.max(from_x, min_x), to_x-Math.max(from_x, min_x));
			    // Устанавливаем для нее родителя
			    fix.setParent(this);
			    if (result==null) {
		       	  result = new ArrayList<>();
			    }
			    result.add(fix);
			} 
			from_x = useFix==null?to_x:to_x + useFix.getWidth();
		}
		for (IFixture f:result) {
		  // Добавляем полку в список использованных полок
	      fixtureUsed.add(f);
	      // Уменьшаем свободное место на полке
	      availableWidth -= f.getWidth();
		}
		return result;
	}
	
	@Override
	public void remove() {
		if (parent!=null) {
			parent.remove(this);
		}
	}
	@Override
	public void remove(IFixture fixture) {
		if (fixtureUsed != null) {
		   if (fixtureUsed.remove(fixture)) {
			   // Увеличиваем свободное место на полке
			   availableWidth += fixture.getWidth();  
		   };
		}
	}
	
	@Override
	public void setParent(IFixture parent) {
		this.parent = parent;
	}

	@Override
	public IFixture getParent() {
		return parent;
	}

	@Override
	public int getHeight() {
		return this.fixtureData.getHeight();
	}

	@Override
	public int getSectionNo() {
		return fixtureData.getSection().getSectionNo();
	}
	
	@Override
	public int getSectionFixtureCount() {
		return fixtureData.getSection().getSectionFixtureCount();
	}

	@Override
	public int getGroupLeftEqualNo() {
		return fixtureData.getSection().getGroupLeftEqualNo();
	}

	@Override
	public int getGroupRightEqualNo() {
		return fixtureData.getSection().getGroupRightEqualNo();
	}

	@Override
	public int getGroupLeftNo() {
		return fixtureData.getSection().getGroupLeftNo();
	}

	@Override
	public int getGroupRightNo() {
		return fixtureData.getSection().getGroupRightNo();
	}

	@Override
	public int getLeftNo() {
		return fixtureData.getSection().getLeftNo();
	}

	@Override
	public int getRightNo() {
		return fixtureData.getSection().getRightNo();
	}
}
