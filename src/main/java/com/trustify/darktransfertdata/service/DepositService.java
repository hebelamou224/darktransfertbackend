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
import java.util.List;
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

    public Customer deposit(Customer customer, double amount) {

        Operation operation = new Operation();
        customer.setIdentify(generateIdentifyCustomer(customer));
        operation.setCode(generateIdentifyCustomer(customer));//the code for withdrawal
        operation.setDateDeposit(new Date());
        operation.setDateWithdrawal(null);
        operation.setCodeWithdrawal("");
        operation.setAmount(amount);
        operation.setType(com.trustify.darktransfertdata.Operation.DEPOSIT);
        customer.setOperation(operation);

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

    public Operation withdrawal(String code) {

        Optional<Operation> operation = this.depositRepository.findByCode(code);
        if(operation.isPresent()){
            if(operation.get().getType() == com.trustify.darktransfertdata.Operation.DEPOSIT ){
                //Create new operation(withdrawal)
                Operation op;
                op = operation.get();
                op.setCodeWithdrawal(op.getCode());
                op.setDateWithdrawal(Instant.now());
                op.setStatus(true);
                op.setType(com.trustify.darktransfertdata.Operation.WITHDRAWAL);
                return this.depositRepository.save(op);
            }else {
                return null;
            }
        }
        return null;
        //throw new RuntimeException("Code ne correspond pas à un depot");
    }

    public List<Operation> findByType(com.trustify.darktransfertdata.Operation type){
        return this.depositRepository.findAllByType(type);
    }

    public Operation findByCodeWithdrawal(String codeWithdrawal){
        Optional<Operation> operation = this.depositRepository.findOperationByCodeWithdrawal(codeWithdrawal);
        if(operation.isPresent())
            return operation.get();

        throw new RuntimeException("Ce code de retrait nexiste pas");
    }

}
