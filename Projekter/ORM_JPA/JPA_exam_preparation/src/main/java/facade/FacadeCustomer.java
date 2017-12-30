package facade;

import entity.*;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author xu zhen yang
 */
public class FacadeCustomer implements IFacadeCustomer{
    
    private EntityManagerFactory emf; // interface som brugt til holde relationer for persistence unit

    public FacadeCustomer(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    /**
     * Create a new Customer. This method returns a new Customer instance 
     * @param Customer
     * @return Customer instance
     */
    @Override
    public Customer addCustomer(Customer c) {
        EntityManager em = emf.createEntityManager(); // interface som brugt til holde kontakt med persistence context, som er sæt entity instanser
        
        try{
            em.getTransaction().begin(); //den entityTransaktion instance starter konstant 
            em.persist(c);
            em.getTransaction().commit(); //multiple transaktioner
            return c; //returner entitytransaktion instance
        }
        finally{
            em.close();
        }
    }

    /**
     * Find an eksist Customer. This method returns the Customer instance you want to find
     * @param id of Customer
     * @return Customer instance
     */
    @Override
    public Customer findACustomer(Long id) {
        EntityManager em = emf.createEntityManager();
        
        try{
            em.getTransaction().begin();
            Customer c = em.find(Customer.class,id);
            em.getTransaction().commit();
            return c;
        }
        finally{
            em.close();
        }
    }

    /**
     * Delete a Customer. This method returns a text of string
     * @param id of customer
     * @return a text that tell you about the method is succes or false
     */
    @Override
    public void deleteSingleCustomer(Long id) {
        EntityManager em = emf.createEntityManager();
        
        try{
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE FROM Customer c WHERE c.id =:customerid");
            query.setParameter("customerid", id);
            int deletedCount = query.executeUpdate();
            if(deletedCount > 0 ){
                System.out.println("Done");
            }
            em.getTransaction().commit();
        }
        finally{
            em.close();
        }
    }

    /**
     * Update a Customer if you want to. This method returns the updated Customer instance 
     * @param Customer
     * @return updated Customer instance
     */
    @Override
    public Customer editCustomer(Customer c) {
        EntityManager em = emf.createEntityManager();
        
        try{
            em.getTransaction().begin();
            em.merge(c);
            em.getTransaction().commit();
            return c;
        }
        finally{
            em.close();
        }
    }

    /**
     * Find a list of all eksist Customers. This method returns a list of Customers 
     * @return list of Customers
     */
    @Override
    public List<Customer> findAllCustomers() {
        EntityManager em = emf.createEntityManager();
        
        try{
            em.getTransaction().begin();
            List<Customer> customers= em.createQuery("SELECT c FROM Customer c").getResultList();
            em.getTransaction().commit();
            return customers;
        }
        finally{
            em.close();
        }
    }

    /**
     * Create a new Order. This method returns a new Order instance 
     * @param Order
     * @return Order instance
     */
    @Override
    public ProductOrder addOrder(ProductOrder po) {
        EntityManager em = emf.createEntityManager();
        
        try{
            em.getTransaction().begin();
            em.persist(po);
            em.getTransaction().commit();
            return po;
        }
        finally{
            em.close();
        }
    }

    /**
     * Find the Order if you want to. This method returns the Order instance you want to find
     * @param id of the order
     * @return Order instance
     */
    @Override
    public ProductOrder findAOrder(Long id) {
        EntityManager em = emf.createEntityManager();
        
        try{
            em.getTransaction().begin();
            ProductOrder po = em.find(ProductOrder.class,id);
            em.getTransaction().commit();
            return po;
        }
        finally{
            em.close();
        }
    }

    /**
     * Create a new Item Type. This method returns a new ItemType instance 
     * @param itemType 
     * @return itemtype instance
     */
    @Override
    public ItemType addItemType(ItemType it) {
        EntityManager em = emf.createEntityManager();
        
        try{
            em.getTransaction().begin();
            em.persist(it);
            em.getTransaction().commit();
            return it;
        }
        finally{
            em.close();
        }
    }

    /**
     * Find a ItemType. This method returns the ItemType instance you want to find
     * @param id of ItemType
     * @return ItemType instance
     */
    @Override
    public ItemType findAItemType(Long id) {
        EntityManager em = emf.createEntityManager();
        
        try{
            em.getTransaction().begin();
            ItemType it = em.find(ItemType.class,id);
            em.getTransaction().commit();
            return it;
        }
        finally{
            em.close();
        }
    }
    
    /**
     * Create a new OrderLine. This method returns a new OrderLine instance
     * @param OrderLine
     * @return a new OrderLine instance
     */
    @Override
    public OrderLine addOrderLine(OrderLine ol) {
        EntityManager em = emf.createEntityManager();
        
        try{
            em.getTransaction().begin();
            em.persist(ol);
            em.getTransaction().commit();
            return ol;
        }
        finally{
            em.close();
        }
    }
    
    /**
     * Add a Order by Customer. This method returns the customer instance
     * @param Order and Customer
     * @return Customer instance
     */
    @Override
    public Customer addOrderByCustomer(ProductOrder o, Customer cust) {
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            
            cust.addOrder(o);
            o.addCust(cust);
            em.persist(o);
            em.merge(cust);
            
            em.getTransaction().commit();
            return cust;
        } finally
        {
            em.close();
        }
    }

    /**
     * Calculate the total price for a order. This method returns a total price
     * @param Order
     * @return double number 
     */
    @Override
    public double getTotalPrice(ProductOrder po) {
        EntityManager em = emf.createEntityManager();
        
        try{
            em.getTransaction().begin();
            
            List<OrderLine> orderLines = po.getOrderlines(); // hent orderlines og gemt i en list
            double totalPrice = 0; // new variable
            
            for (OrderLine orderLine : orderLines) {
                int quantity = orderLine.getQuantity();  // hent antal fra hver orderline 
                double itemPrice = orderLine.getItemType().getPrice(); // får hver varpris
                totalPrice += quantity * itemPrice;
            }
            em.getTransaction().commit();
            return totalPrice;
        }
        finally{
            em.close();
        }
    }
    
    public static void main(String[] args) {
        //Here can you get the total price for order one
        FacadeCustomer fc = new FacadeCustomer(Persistence.createEntityManagerFactory("PU"));
        ProductOrder po1 = fc.findAOrder(1l);
        double total = fc.getTotalPrice(po1);
        System.out.println("total pris "+  total);
        
        
    }
    
}
