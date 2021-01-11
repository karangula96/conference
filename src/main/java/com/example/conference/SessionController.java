package com.example.conference;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/session")
public class SessionController {
@Autowired
private SessionRepository sessionrepository;
	
@GetMapping
public List<Session> list(){
	return sessionrepository.findAll();
}

@PostMapping
@ResponseStatus(HttpStatus.CREATED)
public Session create(@RequestBody final Session session) {
	return sessionrepository.saveAndFlush(session);
}

@GetMapping
@RequestMapping("{id}")
public Session get(@PathVariable Long id) {
	return sessionrepository.getOne(id);
}

@RequestMapping(value="{id}", method = RequestMethod.DELETE)
public void delete(@PathVariable Long id) {
	 sessionrepository.deleteById(id);
}

@RequestMapping(value="{id}", method =RequestMethod.PUT)
public Session update(@PathVariable Long id, @RequestBody Session session) {
	Session exsitingsession = sessionrepository.getOne(id);
	BeanUtils.copyProperties(session, exsitingsession, "session_id");
	return sessionrepository.saveAndFlush(exsitingsession);
}
	

}
