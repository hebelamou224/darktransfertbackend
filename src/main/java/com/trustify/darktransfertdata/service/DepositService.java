package com.trustify.darktransfertdata.service;

import com.trustify.darktransfertdata.model.Customer;
import com.trustify.darktransfertdata.model.Operation;
import com.trustify.darktransfertdata.repository.CustomerRepository;
import com.trustify.darktransfertdata.repository.DepositRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepositService {

    DepositRepository depositRepository;
    CustomerRepository customerRepository;

    public String generateIdentifyCustomer(Customer customer){
        String date = new Date().toString()
                .replaceAll("\\s","")
                .toUpperCase();
        date = StringUtils.replace(date,":","");
        return customer.getFullname().
                substring(0,4).
                toUpperCase().
                trim()+
                depositRepository.count()+
                date;
    }

    public Customer deposit(Customer customer){

        customer.setIdentify(generateIdentifyCustomer(customer));

        if(customer.getOperation().getType() == com.trustify.darktransfertdata.Operation.DEPOSIT)
            customer.getOperation().setCode(generateIdentifyCustomer(customer));
        else
            throw new RuntimeException("Cette operation est inconnue");

        customer.getOperation().setDate(new Date());
        customer.getOperation().setDateWithdrawal(null);
        customer.getOperation().setCodeWithdrawal(null);

        return customerRepository.save(customer);
    }

    public Operation findById(Long id){
        Optional<Operation> operation = depositRepository.findById(id);
        return operation.orElse(null);
    }

    public Iterable<Operation> findAll(){
        return depositRepository.findAll();
    }

    public Operation findByCode(String code){

        if(code == null)
            throw new RuntimeException("Le code operation ne peut pas etre null");

        Optional<Operation> operation = depositRepository.findByCode(code);
        if(operation.isPresent())
            return operation.get();
        else
             throw new RuntimeException("Ce code ne correspond pas à une operation");
    }

    public Customer withdrawal(Customer customer,String code){

        Optional<Operation> operation = this.depositRepository.findByCode(code);
        if(operation.isPresent()){
            if(operation.get().getType() == com.trustify.darktransfertdata.Operation.DEPOSIT ){

                customer.setIdentify(generateIdentifyCustomer(customer));

                //Create new operation(withdrawal)
                Operation op = new Operation();
                op.setAmount(operation.get().getAmount());
                op.setDate(operation.get().getDate());
                op.setCode(generateIdentifyCustomer(customer));
                op.setStatus(true);
                op.setCodeWithdrawal(code);
                op.setType(com.trustify.darktransfertdata.Operation.WITHDRAWAL);
                op.setDateWithdrawal(Instant.now());

                customer.setOperation(op);

                //Update the information of operation
                operation.get().setStatus(true);
                operation.get().setDateWithdrawal(Instant.now());
                operation.get().setCodeWithdrawal("OK");
                this.depositRepository.save(operation.get());
                return this.customerRepository.save(customer);

            }else {
                throw new RuntimeException("Cette transaction n'est pas reconnue comme un depot");
            }

        }
        throw new RuntimeException("Code ne correspond pas à un depot");
    }
}
