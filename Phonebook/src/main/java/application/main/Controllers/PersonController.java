package application.main.Controllers;

import application.main.Entities.Address;
import application.main.Entities.Person;
import application.main.Services.Interfaces.IPersonService;
import application.main.Services.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/people")
public class PersonController
{
  IPersonService personService = new PersonService();
  @PostMapping
  public ResponseEntity<Person> createPerson(@RequestBody Person person)
  {
    try
    {
      Person created = personService.createPerson(person);
      return new ResponseEntity<>(person, HttpStatus.CREATED);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Person> getPerson(@PathVariable("id") int id)
  {
    try
    {
      Person person = personService.getPerson(id);
      return new ResponseEntity<>(person, HttpStatus.OK);
    }
    catch (NoSuchElementException f)
    {
      f.printStackTrace();
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping
  public ResponseEntity<ArrayList<Person>> getAllPeople()
  {
    try
    {
      ArrayList<Person> allPeople = personService.getAllPeople();
      return new ResponseEntity<>(allPeople, HttpStatus.OK);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping
  public ResponseEntity<Person> updatePerson(@RequestBody Person person)
  {
    try
    {
      Person updated = personService.updatePerson(person);
      return new ResponseEntity<>(updated, HttpStatus.OK);
    }
    catch (NoSuchElementException f)
    {
      f.printStackTrace();
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Person> deletePerson(@PathVariable("id") int id)
  {
    try
    {
      Person deleted = personService.deletePerson(id);
      return new ResponseEntity<>(deleted, HttpStatus.OK);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }
}
