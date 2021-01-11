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
@RequestMapping("/api/v1/speaker")
public class SpeakerController {

	@Autowired
	private SpeakerRepository speakerrepository;
		
	@GetMapping
	public List<Speaker> list(){
		return speakerrepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Speaker create(@RequestBody final Speaker speaker) {
		return speakerrepository.saveAndFlush(speaker);
	}

	@GetMapping
	@RequestMapping("{id}")
	public Speaker get(@PathVariable Long id) {
		return speakerrepository.getOne(id);
	}

	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		 speakerrepository.deleteById(id);
	}

	@RequestMapping(value="{id}", method =RequestMethod.PUT)
	public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker) {
		Speaker exsitingspeaker = speakerrepository.getOne(id);
		BeanUtils.copyProperties(speaker, exsitingspeaker, "speaker_id");
		return speakerrepository.saveAndFlush(exsitingspeaker);
	}
		
}
