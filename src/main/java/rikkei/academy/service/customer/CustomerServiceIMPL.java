package rikkei.academy.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rikkei.academy.model.Customer;
import rikkei.academy.repository.customerRepository.ICustomerRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class CustomerServiceIMPL implements ICustomerService{
    @Autowired
    private ICustomerRepository customerRepository;
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(long id) {
        return customerRepository.findByID(id);
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);

    }

    @Override
    public void remove(Long id) {
        customerRepository.remove(id);

    }

    @Override
    public List<Customer> search(String search) {
        List<Customer>customerList = new ArrayList<>();
        for (Customer c : findAll()) {
            if (c.getFirstName().toLowerCase().contains(search.trim().toLowerCase())){
                customerList.add(c);
            }
        }

        return customerList;
    }
}
