package jpacontrol;

import entity.*;
import facade.FacadeCustomer;
import facade.IFacadeCustomer;
import java.util.List;
import javax.persistence.Persistence;


/**
 *
 * @author xu
 */
public class UseFacade {
    public static void main(String[] args) {
        IFacadeCustomer facade = new FacadeCustomer(Persistence.createEntityManagerFactory("PU"));
        
        //Tilføjelse af ny kunder
        System.out.println("NY KUNE: " + facade.addCustomer(new Customer("Adam","adam@dk.dk")));
        System.out.println("NY KUNE: " + facade.addCustomer(new Customer("Bent","bent@dk.dk")));
        System.out.println("NY KUNE: " + facade.addCustomer(new Customer("Claus","claus@dk.dk")));
        System.out.println("NY KUNE: " + facade.addCustomer(new Customer("Dennis","dennis@dk.dk")));
        
        //Finde en kunde
        System.out.println("FUNDET DEN FØRSTE KUNE: " + facade.findACustomer(1l));
        
        //Print alle kunder ud
        List<Customer> customers = facade.findAllCustomers();
        for (Customer customer : customers) {
            System.out.println("KUNDE: " + customer);
        }
        
        //Tilføjelse af ny ordre
        Customer cust = facade.findACustomer(2l);
        ProductOrder order = new ProductOrder();
        System.out.println("NY ORDER BLEV OPRETTET: " + facade.addOrderByCustomer(order, cust));
        
        //Tilføjer en kunde til en bestemt ordre
        Customer cust2 = facade.findACustomer(1l);
        System.out.println("NY ORDRE BLEV OPRETTET AF NY KUNDE: "+ facade.addOrderByCustomer(new ProductOrder(), cust2));
        
        //FIND EN ORDRE
        System.out.println("ORDREN BLEV FUNDET: " + facade.findAOrder(2l));
        
        //TILFØJELSE AF NY VARER
        System.out.println("NY VARER BLEV TILFØJET: "+ facade.addItemType(new ItemType("Danse Sko","FINE SKO",1200)));
        System.out.println("NY VARER BLEV TILFØJET: "+ facade.addItemType(new ItemType("ACNE JAKKE","FINE JAKKE",2200)));
        System.out.println("NY VARER BLEV TILFØJET: "+ facade.addItemType(new ItemType("T-SHIRT","FINE T-SHIRT",800)));
        
        //TILFØJELSE AF ORDRE LINE
        ProductOrder po1 = facade.findAOrder(1l); //find den ordre by ordre id og gemt i en ordre
        ItemType it1 = facade.findAItemType(3l); // find order lines fra den ordre.
        System.out.println("NY ORDER LINE BLEV OPRETTET: " + facade.addOrderLine(new OrderLine(10,po1,it1))); //input(quantity,item,order nr)
        
        ProductOrder po2 = facade.findAOrder(1l); //find den ordre by ordre id og gemt i en ordre
        ItemType it2 = facade.findAItemType(1l); // find order lines fra den ordre.
        System.out.println("NY ORDER LINE BLEV OPRETTET: " + facade.addOrderLine(new OrderLine(2,po2,it2))); //input(quantity,item,order nr)
        
        ProductOrder po3 = facade.findAOrder(1l); //find den ordre by ordre id og gemt i en ordre
        ItemType it3 = facade.findAItemType(2l); // find order lines fra den ordre.
        System.out.println("NY ORDER LINE BLEV OPRETTET: " + facade.addOrderLine(new OrderLine(1,po3,it3))); //input(quantity,item,order nr)
        
        //UDREGN SUM AF ORDRE 1
        ProductOrder order1 = facade.findAOrder(1l);
        double total = facade.getTotalPrice(order1);
        System.out.println("SUM AF ORDRE 1: " + total);
    }
}
