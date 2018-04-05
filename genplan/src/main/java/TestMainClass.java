
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



import ru.genplan.equipment.IEquipment;
import ru.genplan.equipment.IEquipmentFactory;
import ru.genplan.equipment.IFixture;
import ru.genplan.mybatis.BaseEquipmentFactory;
import ru.genplan.mybatis.BaseFixture;
import ru.genplan.mybatis.mappers.EquipmentService;
import ru.genplan.mybatis.mappers.FixtureData;
import ru.genplan.mybatis.mappers.PredicateService;
import ru.genplan.mybatis.mappers.SectionData;
import ru.genplan.predicate.GenplanLogicalOperation;
import ru.genplan.predicate.GenplanPredicate;
import ru.genplan.predicate.GenplanPredicateMember;


public class TestMainClass {
	
	private static final Logger LOG = LogManager.getLogger(BaseEquipmentFactory.class);
	public static void main(String[] args) {
		int planogram_id = 1239720;
		IEquipmentFactory factory = new BaseEquipmentFactory();
		IEquipment eq = factory.generateEquipment(planogram_id);
		List<GenplanPredicate> predicates = new PredicateService().getAllPredicates(1);
		for(GenplanPredicate p:predicates) {
			/*for(GenplanPredicateMember m: p.getPredicateMember()) {
				System.out.println(m.getPredicateCode());
				System.out.println(m.getPredicateValues().size());
			}*/
		}
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
