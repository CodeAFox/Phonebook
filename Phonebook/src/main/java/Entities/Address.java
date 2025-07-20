package Entities;

import java.util.ArrayList;
import java.util.Objects;

public class Address
{
  private String address;
  private ArrayList<ContactInfo> contacts = new ArrayList<>();

  public Address()
  {
  }

  public Address(String address)
  {
    this.address = address;
    this.contacts = new ArrayList<>();
  }

  public void setAddress(String address)
  {
    this.address = address;
  }

  public void setContacts(ArrayList<ContactInfo> contacts)
  {
    this.contacts = new ArrayList<>(contacts);
  }

  public void addContact(ContactInfo contact)
  {
    contacts.add(contact);
  }

  public String getAddress()
  {
    return address;
  }

  public ArrayList<ContactInfo> getContacts()
  {
    return new ArrayList<>(contacts);
  }

  public ContactInfo removeContact(ContactInfo contact)
  {
    return contacts.remove(contacts.indexOf(contact));
  }

  public boolean equals(Object obj)
  {
    if(obj == this)
    {
      return true;
    }
    if(obj == null || obj.getClass() != this.getClass())
    {
      return false;
    }

    Address other = (Address) obj;
    return other.address.equals(this.address) && other.contacts.containsAll(this.contacts);
  }

  public String toString()
  {
    String contactList = "";

    for (int i = 0; i < contacts.size(); i++)
    {
      contactList = contactList.concat(contacts.get(i) + "\n");
    }

    return "Address: " + address + "\nList of Contacts:\n";
  }
}
