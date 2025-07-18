package Entities;

public class ContactInfo
{
  private String type;
  private String contact;

  public ContactInfo(){}

  public ContactInfo(String type, String contact)
  {
    this.type = type;
    this.contact = contact;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public void setContact(String contact)
  {
    this.contact = contact;
  }

  public String getType()
  {
    return type;
  }

  public String getContact()
  {
    return contact;
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
