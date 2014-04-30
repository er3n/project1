package org.abacus.common.security;
 
import java.util.List;

import org.abacus.user.core.persistance.repository.UserRepository;
import org.abacus.user.shared.entity.SecUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value="securityHandler")
public class SecurityHandler implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		SecUserEntity user = userRepository.findOne(username);

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		SecUser secUser = new SecUser();
		
		List<String> authorityNames = userRepository.findUserAuthorities(user.getId());
		
		
		secUser.setUsername(user.getId());
		secUser.setPassword(user.getPassword());
		secUser.setActive(user.getActive());
		secUser.setAuthorityNames(authorityNames);
		secUser.setCompany(user.getCompanyEntity().getId());

		return secUser;

	}

}
