package application.main.Services;

import application.main.Database.DAOs.AddressDAO;
import application.main.Database.DAOs.ContactInfoDAO;
import application.main.Database.DAOs.PersonDAO;
import application.main.Entities.Address;
import application.main.Entities.ContactInfo;
import application.main.Entities.DTOs.SimplePersonDTO;
import application.main.Entities.Person;
import application.main.Services.Interfaces.IPersonService;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class PersonService implements IPersonService
{
  private PersonDAO personDAO = PersonDAO.getInstance();
  private AddressDAO addressDAO = AddressDAO.getInstance();
  private ContactInfoDAO contactInfoDAO = ContactInfoDAO.getInstance();
  @Override public Person createPerson(Person person)
  {
    return personDAO.createPerson(person);
  }

  @Override public Person getPerson(int Id)
  {
    ArrayList<Address> addresses = addressDAO.getAllAddressesForPerson(Id);
    SimplePersonDTO person = personDAO.getPerson(Id);

    if (person == null)
    {
      throw new NoSuchElementException("The person under id " + Id + " does not exist");
    }

    Person fetched = new Person();
    fetched.setName(person.getName());
    fetched.setAge(person.getAge());
    fetched.setId(person.getId());

    for (int i = 0; i < addresses.size(); i++)
    {
      Address address = addresses.get(i);
      address.setContacts(contactInfoDAO.getAllContactInfoForAddress(addresses.get(i).getAddressId()));

      if(addresses.get(i).getType().equals("permanent"))
      {
        fetched.setPermanentAddress(addresses.get(i));
      }
      else
      {
        fetched.setTemporaryAddress(addresses.get(i));
      }
    }
    return fetched;
  }

  @Override public ArrayList<Person> getAllPeople()
  {
    ArrayList<SimplePersonDTO> allPeopleDTO = personDAO.getAllPeople();
    ArrayList<Person> allPeople = new ArrayList<>();

    for (int i = 0; i < allPeopleDTO.size(); i++)
    {
      ArrayList<Address> addresses = addressDAO.getAllAddressesForPerson(allPeopleDTO.get(i).getId());
      SimplePersonDTO person = personDAO.getPerson(allPeopleDTO.get(i).getId());

      Person fetched = new Person();
      fetched.setName(person.getName());
      fetched.setAge(person.getAge());
      fetched.setId(person.getId());

      for (int j = 0; j < addresses.size(); j++)
      {
        Address address = addresses.get(j);
        address.setContacts(contactInfoDAO.getAllContactInfoForAddress(addresses.get(i).getAddressId()));

        if(addresses.get(j).getType().equals("permanent"))
        {
          fetched.setPermanentAddress(addresses.get(j));
        }
        else
        {
          fetched.setTemporaryAddress(addresses.get(j));
        }
      }

      allPeople.add(fetched);
    }
    return allPeople;
  }

  @Override public Person updatePerson(Person person)
  {
    Person updated = personDAO.updatePerson(person);

    if(updated == null)
    {
      throw new NoSuchElementException("The person under id " + person.getId() + " does not exist");
    }

    return updated;
  }

  @Override public Person deletePerson(int Id)
  {
    //Deleting a person means addresses and contacts need to be deleted too
    ArrayList<Address> addresses = addressDAO.getAllAddressesForPerson(Id);

    Person deleted = new Person();


    for (int i = 0; i < addresses.size(); i++)
    {
      Address address = addresses.get(i);

      //Delete contacts
      ArrayList<ContactInfo> contactsList = contactInfoDAO.getAllContactInfoForAddress(address.getAddressId());

      for (int j = 0; j < contactsList.size(); j++)
      {
        contactInfoDAO.deleteContactInfo(contactsList.get(j).getContact(), address.getAddressId());
      }

      if(addresses.get(i).getType().equals("permanent"))
      {
        deleted.setPermanentAddress(addresses.get(i));
      }
      else
      {
        deleted.setTemporaryAddress(addresses.get(i));
      }

      addressDAO.deleteAddress(address.getAddressId());
    }

    //Lastly, delete the person
    SimplePersonDTO person = personDAO.deletePerson(Id);

    deleted.setId(person.getId());
    deleted.setName(person.getName());
    deleted.setAge(person.getAge());

    return deleted;
  }
}
