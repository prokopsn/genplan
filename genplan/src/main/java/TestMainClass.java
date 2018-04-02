import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;

import ru.genplan.equipment.Equipment;
import ru.genplan.equipment.EquipmentUtils;
import ru.genplan.equipment.IEquipment;
import ru.genplan.equipment.IEquipmentFactory;
import ru.genplan.equipment.IFixture;
import ru.genplan.mybatis.BaseEquipmentFactory;
import ru.genplan.mybatis.BaseFixture;
import ru.genplan.mybatis.mappers.EquipmentService;
import ru.genplan.mybatis.mappers.FixtureData;
import ru.genplan.mybatis.mappers.PlanogramData;
import ru.genplan.mybatis.mappers.SectionData;
import ru.genplan.mybatis.mappers.TestEquipmentService;

public class TestMainClass {
	public static void test() {
		List<FixtureData> ff = new EquipmentService().getAllFixture(1239720);
		List<SectionData> ss = new EquipmentService().getAllSection(1239720);
		FixtureData fix = null;
		SectionData sec = null;
		for(FixtureData f:ff) {
			if (f.getId()==119044207) {
				fix = f;
				break;
			}
		}
		for(SectionData s:ss) {
			if (s.getId()==3376617) {
				sec = s;
				break;
			}
		}
		fix.setSection(sec);
		BaseFixture basefix = new BaseFixture(fix);
		List<? extends IFixture> fixs = null;
		List<? extends IFixture> fixs1 = null;
		fixs = basefix.generate(26, 0, 44, 40);	
		fixs = basefix.generate(226, 0, 44, 40);
		fixs = basefix.generate(146, 120, 44, 40);	
		fixs1 = basefix.generate(135, 120, 80, 40);	
	
		List<? extends IFixture> fixs2 = null;
		fixs2 = basefix.generate(130, 120, 100, 40);	
	}
	
	private static final Logger LOG = LogManager.getLogger(BaseEquipmentFactory.class);
	public static void main(String[] args) {
		test();
		int planogram_id = 1239720;
		IEquipmentFactory factory = new BaseEquipmentFactory();
		IEquipment eq = factory.generateEquipment(planogram_id);
	    /*List<FixtureData> fixtures = new EquipmentService().getAllFixture(planogram_id);
		List<SectionData> sections = new EquipmentService().getAllSection(planogram_id);
		List<IFixture> fixs = new ArrayList<>();
		List<? extends IFixture> gen = new ArrayList<>();
		IFixture fForRemove = null;
		for (FixtureData f:fixtures) {
			fixs.add(new BaseFixture(f));
			System.out.println("1 Y="+f.getY());
		}
		IFixture example = fixs.get(1);
		gen = example.generate(0, 0, 50, 200);
		for(IFixture f:gen) {
			fForRemove = f;
			System.out.println("1 —генерилась полка="+f.getX()+":"+f.getWidth());
		}
		System.out.println("AvailableWidth = "+example.getAvailableWidth());
		System.out.println("AvailableWidthLeft = "+example.getAvailableWidthLeft(100));
		System.out.println("AvailableWidthRigth = "+example.getAvailableWidthRight(20));
		gen = example.generate(50, 0, 190, 200);
		for(IFixture f:gen) {
			System.out.println("2 —генерилась полка="+f.getX()+":"+f.getWidth());
		}
		System.out.println("AvailableWidth = "+example.getAvailableWidth());
		System.out.println("AvailableWidthLeft = "+example.getAvailableWidthLeft(100));
		System.out.println("AvailableWidthRigth = "+example.getAvailableWidthRight(20));
		gen = example.generate(0, 0, 200, 200);
		for(IFixture f:gen) {
			System.out.println("3 —генерилась полка="+f.getX()+":"+f.getWidth());
		}
		example.remove((fForRemove);
		System.out.println("AvailableWidth = "+example.getAvailableWidth());
		System.out.println("AvailableWidthLeft = "+example.getAvailableWidthLeft(100));
		System.out.println("AvailableWidthRigth = "+example.getAvailableWidthRight(20));
		gen = example.generate(0, 0, 20, 200);
		for(IFixture f:gen) {
			System.out.println("4 —генерилась полка="+f.getX()+":"+f.getWidth());
		}
		System.out.println("AvailableWidth = "+example.getAvailableWidth());
		System.out.println("AvailableWidthLeft = "+example.getAvailableWidthLeft(100));
		System.out.println("AvailableWidthRigth = "+example.getAvailableWidthRight(20));
		//Predicate<FixtureData> p = EquipmentUtils.checkFixTag(new String[]{"TTGG","b","c"});
		for (SectionData s:sections) {
			System.out.println("SectionNo="+s.getSectionNo());
		}
		PlanogramData planogram = new EquipmentService().getPlanogram(planogram_id);*/
		//Equipment eq = new Equipment(planogram, sections, fixtures);
		/*for (IFixtureData f:fixtures) {
			System.out.println("1 Y="+f.getY());
		}
		fixtures.sort(EquipmentUtils.fixtureByX());
		for (FixtureData f:fixtures) {
			System.out.println("2 Y="+f.getY());
		}
		
		List<FixtureData> fd = fixtures
				              .stream()
				              //.filter(EquipmentUtils.checkFixTag(new String[]{"TTGG","b","c"}))
				              .filter(EquipmentUtils.checkCoordinate(10, 20, 25, 25))
				              .collect(Collectors.toList());
		sections.sort(EquipmentUtils.sectionByNo());
		for (SectionData s:sections) {
			System.out.println("X="+s.getX()+" width="+s.getWidth()+" SectionNo "+s.getSectionNo()+" LeftGroup="+s.getGroupLeftNo()+" RightGroup="+s.getGroupRightNo()+" leftNo="+s.getLeftNo()+" rightNo="+s.getRightNo());
		}
		System.out.println(EquipmentUtils.fixtureByX());
		System.out.println(EquipmentUtils.fixtureByX());
		System.out.println(EquipmentUtils.fixtureByX());*/
	}

}
