package org.abacus.common.security;
 
import java.util.List;

import org.abacus.organization.core.persistance.repository.OrganizationRepository;
import org.abacus.organization.shared.entity.OrganizationEntity;
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
	
	@Autowired
	private OrganizationRepository companyRepository;

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		SecUserEntity user = userRepository.findOne(username);

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		SecUser secUser = new SecUser();
		
		secUser.setUsername(user.getId());
		secUser.setPassword(user.getPassword());
		secUser.setActive(user.getActive());

		List<String> authorityNames = userRepository.findUserAuthorities(user.getId());
		secUser.setAuthorityNames(authorityNames);
		
		List<OrganizationEntity> userOrganizationList = companyRepository.findByUsername(username);
		secUser.init(userOrganizationList, userOrganizationList.get(0));

		return secUser;
	}

}
