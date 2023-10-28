package com.example.springsecurity.service.Impt;

import com.example.springsecurity.service.GitService;
import org.springframework.stereotype.Service;

@Service
public class gitImplService implements GitService {
    @Override
    public void gitTest() {
        System.out.println("git updata test1.0");
    }
    @Override
    public void gitTest2() {
        System.out.println("git updata test2.0");
    }

}
