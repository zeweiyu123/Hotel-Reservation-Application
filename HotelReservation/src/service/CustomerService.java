package service;

import model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CustomerService {
    private static CustomerService customerService = null;
    private ArrayList<Customer> array;
    public void addCustomer(String email, String firstName, String lastName) {
        Customer customer=new Customer(lastName,firstName,email);
        array.add(customer);
    }

    public Customer getCustomer(String customerEmail){
        for(Customer customer:array){
            if(customer.getEmail().equals(customerEmail)){
                return customer;}
        }
        return null;
    }

    public Collection<Customer> getAllCustomers(){return array;}


}
