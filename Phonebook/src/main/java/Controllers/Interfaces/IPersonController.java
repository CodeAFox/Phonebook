package Controllers.Interfaces;

import Entities.Person;

import java.util.ArrayList;

public interface IPersonController
{
  public Person addPerson();
  public Person getPerson(int Id);
  public ArrayList<Person> getAllPeople();
  public void updatePerson(Person person);
  public Person deletePerson(int Id);
}
