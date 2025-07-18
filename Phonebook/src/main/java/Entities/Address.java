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

  public Address(String address, ArrayList<ContactInfo> contacts)
  {
    this.address = address;
    this.contacts = new ArrayList<>(contacts);
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
    //    placeholder
    return true;
  }

  public String toString()
  {
    //    placeholder
    return "";
  }
}
