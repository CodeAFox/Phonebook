package application.main.Services.Interfaces;

import application.main.Entities.Person;

import java.util.ArrayList;

public interface IPersonService
{
  public Person addPerson();
  public Person getPerson(int Id);
  public ArrayList<Person> getAllPeople();
  public void updatePerson(Person person);
  public Person deletePerson(int Id);
}
