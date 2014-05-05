package org.abacus.user.core.handler;

import org.abacus.user.shared.GroupNameInUseException;
import org.abacus.user.shared.UserExistsInGroupException;
import org.abacus.user.shared.UserNameExistsException;
import org.abacus.user.shared.event.CreateGroupEvent;
import org.abacus.user.shared.event.CreateUserEvent;
import org.abacus.user.shared.event.DeleteGroupEvent;
import org.abacus.user.shared.event.GroupCreatedEvent;
import org.abacus.user.shared.event.GroupDeletedEvent;
import org.abacus.user.shared.event.GroupUpdatedEvent;
import org.abacus.user.shared.event.ReadAuthoritiesEvent;
import org.abacus.user.shared.event.ReadOrganizationsEvent;
import org.abacus.user.shared.event.ReadGroupsEvent;
import org.abacus.user.shared.event.ReadUserEvent;
import org.abacus.user.shared.event.RequestReadAuthoritiesEvent;
import org.abacus.user.shared.event.RequestReadOrganizationsEvent;
import org.abacus.user.shared.event.RequestReadGroupsEvent;
import org.abacus.user.shared.event.RequestReadUserEvent;
import org.abacus.user.shared.event.UpdateGroupEvent;
import org.abacus.user.shared.event.UpdateUserEvent;
import org.abacus.user.shared.event.UserCreatedEvent;
import org.abacus.user.shared.event.UserUpdatedEvent;

public interface UserService {
	
	ReadUserEvent requestUser(RequestReadUserEvent event);
	
	UserCreatedEvent createUser(CreateUserEvent event) throws UserNameExistsException;
	
	UserUpdatedEvent updateUser(UpdateUserEvent event);
	
	ReadGroupsEvent requestGroup(RequestReadGroupsEvent event);
	
	GroupCreatedEvent createGroup(CreateGroupEvent event) throws GroupNameInUseException;
	
	GroupUpdatedEvent updateGroup(UpdateGroupEvent event) throws GroupNameInUseException;
	
	GroupDeletedEvent deleteGroup(DeleteGroupEvent event) throws UserExistsInGroupException;
	
	ReadAuthoritiesEvent requestAuthorities(RequestReadAuthoritiesEvent event);

	ReadOrganizationsEvent requestOrganization(RequestReadOrganizationsEvent requestReadOrganizationsEvent);
	
	
}
