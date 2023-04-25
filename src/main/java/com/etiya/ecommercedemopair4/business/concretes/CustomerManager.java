package com.etiya.ecommercedemopair4.business.concretes;

import com.etiya.ecommercedemopair4.core.utilities.mappers.ModelMapperService;
import com.etiya.ecommercedemopair4.repositories.abstracts.CustomerDao;
import com.etiya.ecommercedemopair4.entities.concretes.Customer;
import com.etiya.ecommercedemopair4.business.abstracts.CustomerService;
import com.etiya.ecommercedemopair4.business.requests.CreateCustomerRequest;
import com.etiya.ecommercedemopair4.business.responses.GetAllCustomersResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {

    private CustomerDao customerDao;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetAllCustomersResponse> getAll() {

        List<Customer> customers = customerDao.findAll();

        //elimizde bir list yani bir stream varsa tek tek dolaşmanı sağlıuor ve her bir customer için bir mapleme yap
        List<GetAllCustomersResponse> customersResponse = customers.stream()
                .map(customer -> this.modelMapperService.forResponse()
                        .map(customer, GetAllCustomersResponse.class)).collect(Collectors.toList());
                                                                //topla ve şu tipe çevir demek toList

        return customersResponse;
    }

    @Override
    public void add(CreateCustomerRequest createCustomerRequest) {

        //mapper - for request => senin yerine arka planda customerı new liyo,
        // bütün alanları tek tek karşılaştırıyo,
        // aynı olanları newlediğine aktarıyo
        Customer customer = this.modelMapperService.forRequest().map(createCustomerRequest, Customer.class);
        this.customerDao.save(customer);
    }
}
