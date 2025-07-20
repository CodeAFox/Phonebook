package application.main.Controllers;

import application.main.Entities.Address;
import application.main.Entities.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/people")
public class PersonController
{
  ObjectMapper mapper = new ObjectMapper();
  @GetMapping("/{id}")
  public String getPerson(@PathVariable("id") int id)
  {
    Person temp = new Person("Kate", 32, id, new Address("somewhere"));
    try
    {
      return mapper.writeValueAsString(temp);
    }
    catch (JsonProcessingException e)
    {
      return e.getMessage();
    }
  }
}
