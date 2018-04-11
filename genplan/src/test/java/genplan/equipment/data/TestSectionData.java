package genplan.equipment.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ru.genplan.equipment.data.SectionData;
import ru.genplan.mybatis.equipment.TestEquipmentService;

@DisplayName("Test SectionData")
public class TestSectionData {
	private static List<SectionData> sec;
	private static SectionData sd;
	@BeforeAll
	public static void getSectionData() {
		sec = new TestEquipmentService().getAllSection(1239720);
		if (sec.size()>0) {
		  sec.sort(Comparator.comparingInt(SectionData::getX));
		  sd = sec.get(0);
		}
	}
	
	@Test
	@DisplayName("���-�� ������")
	public void testCount() {
		assertEquals(sec.size(), 10,"����� ������ ������ ���� 1");
	}
	
	@Test
	@DisplayName("������������ ������")
	public void testName() {
		assertEquals(sd.getName(), "7","������������ ������");
	}
	
	@Test
	@DisplayName("���� ������")
	public void testSectionTag() {
		assertNull(sd.getSectionTag());
		assertNull(sd.getSecTag());
		sd.setSectionTag("THIS;IS SECTION;Tag");
		assertEquals(sd.getSectionTag(), "THIS;IS SECTION;Tag");
		assertEquals(sd.getSecTag().contains("THIS"),true);
		assertEquals(sd.getSecTag().contains("TAG"),false);
	}
	
	@Test
	@DisplayName("����� ������")
	public void testSectionNo() {
		assertEquals(sd.getSectionNo(), 1);
	}
	
	@Test
	@DisplayName("���������� ������")
	public void testXYZ() {
		assertEquals(sd.getX(), 0);
		assertEquals(sd.getY(), 0);
		assertEquals(sd.getZ(), 0);
		assertEquals(sd.getWidth(), 125);
		assertEquals(sd.getHeight(), 200);
		assertEquals(sd.getDepth(), 60);
	}
	
	@Test
	@DisplayName("����������������� ����� � ��������")
	public void testEffective() {
		assertEquals(sd.getEffectiveX(), -1);
		assertEquals(sd.getEffectiveY(), -1);
		assertEquals(sd.getEffectiveZ(), -1);
		assertEquals(sd.getEffectiveWidth(), -1);
		assertEquals(sd.getEffectiveHeight(), -1);
		assertEquals(sd.getEffectiveDepth(), -1);
	}
	
	@Test
	@DisplayName("�������������� ���������")
	public void testAddition() {
		assertEquals(sd.getLeftBorder(), false);
		assertEquals(sd.getRightBorder(), false);
		assertEquals(sd.getLeftOverHang(), 0);
		assertEquals(sd.getRightOverHang(), 0);
	}
}
