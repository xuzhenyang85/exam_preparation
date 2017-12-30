package facade;

import entity.Customer;
import entity.ItemType;
import entity.OrderLine;
import entity.ProductOrder;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author xu zhen yang
 */
public class FacadeCustomerTest {
    private static EntityManager em = null;
    private static IFacadeCustomer facade = null;
    
    public FacadeCustomerTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception{
        if(em == null){
            em = (EntityManager)Persistence.createEntityManagerFactory("PU").createEntityManager();
        }
        if(facade == null) {
            facade = new FacadeCustomer(Persistence.createEntityManagerFactory("PU"));
        }
    }
    
    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Test
    public void testAddCustomer() {
        // Start a transaction
        em.getTransaction().begin();
        
        //---------- Create a Customer c1 ----------
        Customer c1 = new Customer();
        c1.setName("Ben");
        c1.setEmail("ben@dk.dk");
        // At this Point the Entity does not have a 
        // Persistence Identity and is  not assciated
        // with a persistent Context
        em.persist(c1); // Persist the Entity
        em.flush();
        // At this point the Entity has a Persistent
        // Identity and is associated witha Persistent
        // context.
         
        //---------- Create a Customer c2 ----------
        Customer c2 = new Customer();
        c2.setName("Kim");
        c2.setEmail("kim@dk.dk");
        em.persist(c2); // Persist the Entity
        em.flush(); // empty the internal SQL cache, and excute it immediately to the db
        
        System.out.println("Customer 1 Id :" + c1.getId());
        System.out.println("Customer 2 Id :" + c2.getId());
        
        // ------------  Perform Selects ---------
        Query query = em.createQuery("SELECT c FROM Customer c WHERE c.id =:customerid");
        
        query.setParameter("customerid", c1.getId());
        Customer retrieved1 = (Customer) query.getSingleResult();
        assertSame(c1, retrieved1);
        
        query.setParameter("customerid", c2.getId());
        Customer retrieved2 = (Customer) query.getSingleResult();
        assertSame(c2, retrieved2);
        
        assertNotSame(c1, c2);
        assertNotSame(retrieved1, retrieved2);
        
        // ------------  Update  ---------
        
        c2.setName("No one");
        c2.setEmail("No mail");
        em.merge(c2);
        em.flush();
        
        System.out.println("Customer 2 Id : " + c2.getId());
        System.out.println("Customer 2 Name : " + c2.getName());
        System.out.println("Customer 2 Email : " + c2.getEmail());
        
        // ------------  Remove Entries  ---------
        em.remove(c1);
        em.remove(c2);
        // Both c1 c2 (and obviously retrieved1 and retrieved2) are removed,
        // which will happen upon commit of the Transaction
        
        em.getTransaction().commit();
    }

//    @Test
//    public void testFindACustomer() {
//        System.out.println("Find A Customer");
//        Customer c1 = facade.addCustomer(new Customer("Bent","bent@dk.dk"));
//        Long id = c1.getId();
//        String expResult = "Bent";
//        String result = facade.findACustomer(id).getName();
//        assertEquals(expResult, result);
//    }

//    @Test
//    public void testDeleteSingleCustomer() {
//        
//        // Start a transaction
//        em.getTransaction().begin();
//        
//        //---------- Create a Customer ----------
//        Customer c1 = new Customer();
//        c1.setName("Bent");
//        c1.setEmail("bent@dk.dk");
//        em.persist(c1);
//        em.flush();
//        
//        System.out.println("Customer Id :" + c1.getId());
//        
//        // ------------  Perform Selects ---------
//        Query query = em.createQuery("DELETE FROM Customer c WHERE c.id =:customerid");
//        
//        query.setParameter("customerid", c1.getId());
//        String expResult = "Removed the customer";
//        String retrieved1 = (String) query.getSingleResult();
//        assertEquals(expResult, retrieved1);
//        
//        em.getTransaction().commit();
//    }
//
//    @Test
//    public void testEditCustomer() {
//        System.out.println("editCustomer");
//        Customer c = null;
//        FacadeCustomer instance = null;
//        Customer expResult = null;
//        Customer result = instance.editCustomer(c);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testFindAllCustomers() {
//        System.out.println("findAllCustomers");
//        FacadeCustomer instance = null;
//        List<Customer> expResult = null;
//        List<Customer> result = instance.findAllCustomers();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testAddOrder() {
//        System.out.println("addOrder");
//        ProductOrder po = null;
//        FacadeCustomer instance = null;
//        ProductOrder expResult = null;
//        ProductOrder result = instance.addOrder(po);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testFindAOrder() {
//        System.out.println("findAOrder");
//        Long id = null;
//        FacadeCustomer instance = null;
//        ProductOrder expResult = null;
//        ProductOrder result = instance.findAOrder(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testAddItemType() {
//        System.out.println("addItemType");
//        ItemType it = null;
//        FacadeCustomer instance = null;
//        ItemType expResult = null;
//        ItemType result = instance.addItemType(it);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testFindAItemType() {
//        System.out.println("findAItemType");
//        Long id = null;
//        FacadeCustomer instance = null;
//        ItemType expResult = null;
//        ItemType result = instance.findAItemType(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testAddOrderLine() {
//        System.out.println("addOrderLine");
//        OrderLine ol = null;
//        FacadeCustomer instance = null;
//        OrderLine expResult = null;
//        OrderLine result = instance.addOrderLine(ol);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testAddOrderByCustomer() {
//        System.out.println("addOrderByCustomer");
//        ProductOrder o = null;
//        Customer cust = null;
//        FacadeCustomer instance = null;
//        Customer expResult = null;
//        Customer result = instance.addOrderByCustomer(o, cust);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetTotalPrice() {
//        System.out.println("getTotalPrice");
//        ProductOrder po = null;
//        FacadeCustomer instance = null;
//        double expResult = 0.0;
//        double result = instance.getTotalPrice(po);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testMain() {
//        System.out.println("main");
//        String[] args = null;
//        FacadeCustomer.main(args);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
