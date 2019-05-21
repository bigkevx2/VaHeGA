//package Test;
//
//import logic.Administrator;
//import logic.MedewerkerRepository;
//import logic.PersistenceLayerException;
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//public class AdministratorTest {
//    private Administrator administrator;
//    MedewerkerRepository medewerkerRepository = new MedewerkerRepository();
//
//
//    @BeforeMethod
//    public void setUp() throws Exception {
//        administrator = new Administrator("Test","Test", "Persoon",
//                4,"test","1",0);
////        administrator.setMedewerkerRepository(medewerkerRepository);
//            medewerkerRepository.add(administrator);
//    }
//
//    @AfterMethod
//    public void tearDown() {
//        administrator = null;
//    }
//
//    @Test
//    public void testSetMedewerkerRepository() {
////        administrator.setMedewerkerRepository(medewerkerRepository);
//    }
//
//    @Test
//    public void testToevoegenGebebruiker() throws Exception {
//        medewerkerRepository.add(administrator);
//    }
//
//    @Test
//    public void testGetGebruikers() {
//        // Arrange
//        final boolean expected = true;
//        // Act
//        boolean actual = false;
//        try {
//            actual = administrator.getGebruikers().getRowCount() > 0;
//            System.out.println("actual = " + administrator.getGebruikers().getRowCount());
//        }
//        catch (PersistenceLayerException e) {
//            System.out.println("oeps");
//        }
//        // Assert
//        Assert.assertEquals(expected,actual);
//    }
//
//    @Test
//    public void testBuildUpdate() {
//    }
//
//    @Test
//    public void testUpdateGebruikers() {
//    }
//
//
//}