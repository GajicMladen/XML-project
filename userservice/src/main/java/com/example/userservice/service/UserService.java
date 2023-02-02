package com.example.userservice.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.example.userservice.beans.Role;
import com.example.userservice.beans.User;
import com.example.userservice.dto.RegistrationDTO;
import com.example.userservice.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	private static final String BEANS_PACKAGE = "com.example.userservice.beans";
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passEncoder;
	
	public User getMarshalledUser(XMLResource xml) {
		User user = null;
		try {
			JAXBContext context = JAXBContext.newInstance(BEANS_PACKAGE);
			Unmarshaller um = context.createUnmarshaller();
			user = (User) um.unmarshal(xml.getContentAsDOM());
		} catch(JAXBException e) {
			e.printStackTrace();
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public void writeUserXMLToDB(String path) {
		userRepository.writeXMLFiletoDB(path);
	}
	
	public String readXMLfromDB(String documentId) {
		XMLResource xml = userRepository.readXMLfromDB(documentId);
		if(xml == null) return null;
		
		String ret = null;
		try {
			ret = xml.getContent().toString();
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with u '%s'.", username));
		} else {
			return user;
		}
	}
	
	public User getUserByUsername(String username) {
		return userRepository.getUserByUsername(username);
	}
	
	public User saveUser(RegistrationDTO dto) {
		User user = new User();
		
		user.setName(dto.getName());
		user.setSurname(dto.getSurname());
		user.setUsername(dto.getUsername());
		user.setEmail(dto.getEmail());
		user.setPassword(passEncoder.encode(dto.getPassword()));
		user.setIdentificationNumber(dto.getIdentificationNumber());
		List<Role> roles = new ArrayList<Role>();
		roles.add(Role.fromValue(dto.getRole()));
		user.setRoles(roles);
		
		userRepository.saveUserToDB(user);
		return user;
	}
}
