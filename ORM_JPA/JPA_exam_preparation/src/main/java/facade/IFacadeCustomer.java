package facade;

import entity.*;
import java.util.List;

/**
 *
 * @author xu
 */
public interface IFacadeCustomer {
    
    
    //customer
    public Customer addCustomer(Customer c);
    public Customer findACustomer (Long id);
    public String deleteSingleCustomer (Customer c);
    public Customer editCustomer (Customer c);
    public List<Customer> findAllCustomers ();
    
    //order
    public ProductOrder addOrder(ProductOrder o);
    public Customer addOrderByCustomer(ProductOrder o, Customer c);
    public ProductOrder findAOrder(Long id);
    
    //itemType
    public ItemType addItemType(ItemType it);
    public ItemType findAItemType(Long id);
    
    //orderline
    public OrderLine addOrderLine(OrderLine ol);
    
    //totalPrice
    public double getTotalPrice(ProductOrder po);
}
