package com.example.springpostgregforclients.controller;

import com.example.springpostgregforclients.dto.CustomerRequest;
import com.example.springpostgregforclients.dto.CustomerResponse;
import com.example.springpostgregforclients.exception.NotFoundException;
import com.example.springpostgregforclients.exception.ServiceException;
import com.example.springpostgregforclients.model.Customer;
import com.example.springpostgregforclients.repo.CustomerRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.springpostgregforclients.service.AESUtil.*;

@Api
@RestController
@RequestMapping("/api")
public class CustomerController {
    private final CustomerRepository customerRepository;
    final static Logger log = LogManager.getLogger(CustomerController.class);

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customers")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        log.debug("Getting customers...");
        try {
            List<Customer> customers = new ArrayList<>(customerRepository.findAll());
            if (customers.isEmpty()) {
                log.debug("Customers dataset is empty.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                log.debug("Customer data received.");
                return new ResponseEntity<>(customers, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = CustomerResponse.class,
                    message = "SingleResponseWrap with CustomerResponse in it")
    })
    public ResponseEntity<?> getCustomer(
            @RequestBody CustomerRequest customerRequest) throws ServiceException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        try {
            log.debug("Trying to find the customer's surname by id:" + customerRequest.getId());
            Customer optCustomer = customerRepository.findById(Long.valueOf(customerRequest.getId())).orElseGet(Customer::new);
            CustomerResponse customerResponse = new CustomerResponse(optCustomer.getSurnameAndInitials());
            log.debug("Found data by id:" + customerRequest.getId() + " about the customer : " + customerResponse.toString());
            SecretKey key = generateKey(256);
            IvParameterSpec ivParameterSpec = generateIv();
            String inputText = customerResponse.toString();
            String cipherText = encrypt("AES/CBC/PKCS5Padding", inputText, key, ivParameterSpec);
            log.debug("=== encryption:" + cipherText);
            String plainText = decrypt("AES/CBC/PKCS5Padding", cipherText, key, ivParameterSpec);
            log.debug("=== decryption:" + plainText);
            return new ResponseEntity<>(customerResponse, HttpStatus.OK);
        } catch
        (NoSuchAlgorithmException | InvalidAlgorithmParameterException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException
                        e) {
            log.debug("An encryption problem has been detected.");
            throw new ServiceException("An encryption problem has been detected.", e);
        } catch (NumberFormatException e) {
            log.debug("No customer data with this index was found:" + customerRequest.getId());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
