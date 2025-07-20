package application.main.Database.Interfaces;

import application.main.Entities.DTOs.SimplePersonDTO;
import application.main.Entities.Person;

import java.util.ArrayList;

public interface IPersonDAO
{
  public Person createPerson(Person person);
  public SimplePersonDTO getPerson(int Id);
  public ArrayList<SimplePersonDTO> getAllPeople();
  public Person updatePerson(Person person);
  public SimplePersonDTO deletePerson(int Id);
}
