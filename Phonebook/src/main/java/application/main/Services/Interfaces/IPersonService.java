package application.main.Services.Interfaces;

import application.main.Entities.Person;

import java.util.ArrayList;

public interface IPersonService
{
  public Person createPerson(Person person);
  public Person getPerson(int Id);
  public ArrayList<Person> getAllPeople();
  public Person updatePerson(Person person);
  public Person deletePerson(int Id);
}
