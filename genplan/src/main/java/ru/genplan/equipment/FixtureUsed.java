package ru.genplan.equipment;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author Sergey Prokopchik
 *
 */
public class FixtureUsed<T extends IFixture> {
	private static final Logger LOG = LogManager.getLogger(BaseFixture.class);
	private Set<T> usedFixtures;
	public FixtureUsed() {
		this.usedFixtures = new TreeSet<>(Comparator.comparingInt(T::getX));
	}
	
	public void add(T fix) {
		LOG.debug("SIZE BEFORE ADD = "+usedFixtures.size()+" "+fix);
		usedFixtures.add(fix);	
		LOG.debug("SIZE AFTER ADD = "+usedFixtures.size());
	}
	
	public boolean remove(T fix) {
		return usedFixtures.remove(fix);
	}
	
	public Iterator<T> iterator() {
		return usedFixtures.iterator();
	}
}
