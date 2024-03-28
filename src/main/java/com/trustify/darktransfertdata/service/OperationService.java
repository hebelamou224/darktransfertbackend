package com.trustify.darktransfertdata.service;

import com.trustify.darktransfertdata.model.Action;
import com.trustify.darktransfertdata.model.Customer;
import com.trustify.darktransfertdata.model.Operation;
import com.trustify.darktransfertdata.repository.ActionRepository;
import com.trustify.darktransfertdata.repository.CustomerRepository;
import com.trustify.darktransfertdata.repository.OperationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OperationService {

    OperationRepository operationRepository;
    CustomerRepository customerRepository;
    private ActionRepository actionRepository;

    public String generateIdentifyCustomer(Customer customer){
        String date = new Date().toString()
                .replaceAll("\\s","")
                .toUpperCase();
        date = StringUtils.replace(date,":","");
        return customer.getFullname().
                substring(0,4).
                toUpperCase().
                trim()+
                operationRepository.count() +
                date;
    }

    public Customer deposit(Customer customer, double amount, Long idSource) {

        Operation operation = new Operation();
        customer.setIdentify(generateIdentifyCustomer(customer));
        operation.setCode(generateIdentifyCustomer(customer));//the code for withdrawal
        operation.setDateDeposit(Instant.now());
        operation.setDateWithdrawal(null);
        operation.setDateModify(Instant.now());
        operation.setCodeWithdrawal("");
        operation.setAmount(amount);
        operation.setType(com.trustify.darktransfertdata.Operation.DEPOSIT);
        customer.setOperation(operation);

        Customer customerSaved = customerRepository.save(customer);

        //Register action which done
        Action action = new Action();
        action.setDateAction(LocalDate.now());
        action.setDescription("Depot d'argen d'une somme de " + amount + " GNF");
        action.setTypeAction("DEPOT");
        action.setIdAction(customerSaved.getId());
        action.setIdSource(idSource);
        this.actionRepository.save(action);

        return customerSaved;
    }

    public Operation findById(Long id){
        Optional<Operation> operation = operationRepository.findById(id);
        return operation.orElse(null);
    }

    public Iterable<Operation> findAll(){
        return operationRepository.findAll();
    }

    public Operation findByCode(String code){

        if(code == null)
            throw new RuntimeException("Le code operation ne peut pas etre null");

        Optional<Operation> operation = operationRepository.findByCode(code);
        if(operation.isPresent())
            return operation.get();
        else
             throw new RuntimeException("Ce code ne correspond pas à une operation");
    }

    public Operation withdrawal(String code, Long idSource) {

        Optional<Operation> operation = this.operationRepository.findByCode(code);
        if(operation.isPresent()){
            if(operation.get().getType() == com.trustify.darktransfertdata.Operation.DEPOSIT ){
                //Update operation(withdrawal)
                Operation op;
                op = operation.get();
                op.setCodeWithdrawal(op.getCode());
                op.setDateWithdrawal(Instant.now());
                op.setDateModify(Instant.now());
                op.setStatus(true);
                op.setType(com.trustify.darktransfertdata.Operation.WITHDRAWAL);

                //Register action which done
                Action action = new Action();
                action.setDateAction(LocalDate.now());
                action.setDescription("Retrait d'argent d'une somme de " + op.getAmount() + " GNF");
                action.setTypeAction("RETRAIT");
                action.setIdAction(customerRepository.findByIdentify(code).orElseThrow().getId());
                action.setIdSource(idSource);
                this.actionRepository.save(action);

                return this.operationRepository.save(op);
            }else {
                return null;
            }
        }
        return null;
        //throw new RuntimeException("Code ne correspond pas à un depot");
    }

    public List<Operation> findByType(com.trustify.darktransfertdata.Operation type){
        return this.operationRepository.findAllByType(type);
    }

    public Operation findByCodeWithdrawal(String codeWithdrawal){
        Optional<Operation> operation = this.operationRepository.findOperationByCodeWithdrawal(codeWithdrawal);
        if(operation.isPresent())
            return operation.get();

        throw new RuntimeException("Ce code de retrait nexiste pas");
    }


}
