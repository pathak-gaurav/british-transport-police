package com.gaurav.securelogin.databoot;

import com.gaurav.securelogin.entity.Authority;
import com.gaurav.securelogin.entity.OfficerDetails;
import com.gaurav.securelogin.entity.User;
import com.gaurav.securelogin.repository.AuthorityRepository;
import com.gaurav.securelogin.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;

    public DataLoader(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User zackUser = new User("zack", ("{noop}zack"), "false");
        Authority zackAuthority = new Authority("ROLE_ADMIN");
        OfficerDetails zackOfficerDetails = new OfficerDetails("Zack", "Anderson", 39L, "zack.anderson@btp.co.uk",
                "Senior Police Officer", "Male", "High Street Kensington", "London",
                "London", "+44-1234567890");
        zackUser.setOfficerDetails(zackOfficerDetails);
        authorityRepository.save(zackAuthority);
        zackUser.addAuthority(zackAuthority);
        userRepository.save(zackUser);




        User heneryUser = new User("henery", ("{noop}henery"), "true");
        Authority heneryAuthority = new Authority("ROLE_CHIEF");
        OfficerDetails heneryOfficerDetails = new OfficerDetails("Henery", "Smith", 55L, "henery.smith@btp.co.uk",
                "Chief Officer", "Male", "Westminister Circle", "London",
                "London", "+44-141567890");
        heneryUser.setOfficerDetails(heneryOfficerDetails);
        authorityRepository.save(heneryAuthority);
        heneryUser.addAuthority(heneryAuthority);
        userRepository.save(heneryUser);
    }
}
