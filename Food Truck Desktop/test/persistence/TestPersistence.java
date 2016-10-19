package persistence;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistence.PersistenceXStream;
import model.Equipment;
import model.FTMS;

public class TestPersistence {

	@Before
	public void setUp() throws Exception {

		// create FoodSupply
		Equipment eq1 = new Equipment("Butter knife", "Careful it is sharp!", 5);
		Equipment eq2 = new Equipment("Chef hat", "Do not wear it if you are not a chef!", 10);

		// manage FoodSupply
		FTMS master = FTMS.getInstance();
		master.addEquipment(eq1);
		master.addEquipment(eq2);
	}

	@After
	public void tearDown() throws Exception {
		// clear all Equipments
		FTMS master = FTMS.getInstance();
		master.delete();
	}

	@Test
	public void test() {
		// save model
		FTMS master = FTMS.getInstance();
		PersistenceXStream.setFilename("test" + File.separator + "persistence" + File.separator + "data.xml");
		PersistenceXStream.setAlias("equipment", Equipment.class);
		PersistenceXStream.setAlias("FTMS", FTMS.class);
		if(!PersistenceXStream.saveToXMLwithXStream(master))
			fail("Could not save file.");

		// clear the model in memory
		master.delete();
		assertEquals(0, master.getEquipments().size());

		// load model
		master = (FTMS) PersistenceXStream.loadFromXMLwithXStream();
		if(master == null)
			fail("Could not load file.");

		// check food supplies
		assertEquals(2, master.getEquipments().size());
		assertEquals("Butter knife", master.getEquipment(0).getName());
		assertEquals("Chef hat", master.getEquipment(1).getName());

		assertEquals("Careful it is sharp!", master.getEquipment(0).getDescription());
		assertEquals("Do not wear it if you are not a chef!", master.getEquipment(1).getDescription());

		assertEquals(5, master.getEquipment(0).getQuantity());
		assertEquals(10, master.getEquipment(1).getQuantity());
	}

}
