package com.pfe.servicetache.Security;

import com.pfe.servicetache.Service.EmployeeServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Component
public class Security {


   static public boolean check(Long id,String tokenH,String tokenDb){
        return tokenH.equals(tokenDb);
    }
}
