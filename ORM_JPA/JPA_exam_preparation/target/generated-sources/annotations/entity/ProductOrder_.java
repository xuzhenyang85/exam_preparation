package entity;

import entity.Customer;
import entity.OrderLine;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-22T16:03:30")
@StaticMetamodel(ProductOrder.class)
public class ProductOrder_ { 

    public static volatile ListAttribute<ProductOrder, OrderLine> orderlines;
    public static volatile SingularAttribute<ProductOrder, Long> id;
    public static volatile SingularAttribute<ProductOrder, Customer> customer;

}