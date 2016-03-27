package org.abacus.common.security;
 
import java.util.List;
import java.util.Set;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.core.persistance.repository.OrganizationRepository;
import org.abacus.organization.core.util.OrganizationUtils;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.user.core.persistance.repository.AuthorityRepository;
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
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private OrganizationRepository organizationRepository;
	
	@Autowired
	private OrganizationUtils organizationUtils;

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		SecUserEntity userEntity = userRepository.findOne(username);

		if (userEntity == null) {
			throw new UsernameNotFoundException(username);
		}

		SecUser secUser = new SecUser(userEntity);
		
		List<String> authorityNames = authorityRepository.findUserAuthorities(userEntity.getId());
		secUser.setAuthorityNames(authorityNames);
		
		List<OrganizationEntity> userOrganizationList = organizationRepository.findByUsername(username);
		
		OrganizationEntity defaultOrganization = null; //userOrganizationList.get(0);
		
		Set<FiscalYearEntity> companyFiscalYearSet = organizationUtils.findFiscalYearSet(defaultOrganization);
		FiscalYearEntity defaultFiscalYear = organizationUtils.findDefaultFiscalYear(companyFiscalYearSet);
		
		secUser.init(userOrganizationList, defaultOrganization,companyFiscalYearSet,defaultFiscalYear);
		return secUser;
	}

}
