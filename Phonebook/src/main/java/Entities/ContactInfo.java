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
    if(obj == this)
    {
      return true;
    }
    if(obj == null || obj.getClass() != this.getClass())
    {
      return false;
    }

    ContactInfo other = (ContactInfo) obj;
    return other.type.equals(this.type) && other.contact.equals(this.contact);
  }

  public String toString()
  {
    return type + ": " + contact;
  }
}
