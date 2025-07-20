package application.main.Database.Interfaces;

import application.main.Entities.ContactInfo;

import java.util.ArrayList;

public interface IContactInfoDAO
{
  public ContactInfo createContactInfo(ContactInfo contactInfo, int addressId);
  public ContactInfo getContactInfo(String contact);
  public ArrayList<ContactInfo> getAllContactInfo();
  public ArrayList<ContactInfo> getAllContactInfoForAddress(int addressId);
  public ContactInfo deleteContactInfo(String contact, int addressId);
}
