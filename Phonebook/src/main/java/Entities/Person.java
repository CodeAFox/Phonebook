package Entities;

public class Person
{
  private String name;
  private int age;
  private int Id;
  private Address permanent;
  private Address temporary;

  public Person(){}

  public Person(String name, int age, int Id)
  {
    this.name = name;
    this.age = age;
    this.Id = Id;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public void setAge(int age)
  {
    this.age = age;
  }

  public void setId(int Id)
  {
    this.Id = Id;
  }

  public void setPermanentAddress(Address permanent)
  {
    this.permanent = permanent;
  }

  public void setTemporaryAddress(Address temporary)
  {
    this.temporary = temporary;
  }

  public String getName()
  {
    return name;
  }

  public int getAge()
  {
    return age;
  }

  public int getId()
  {
    return Id;
  }

  public Address getPermanentAddress()
  {
    return permanent;
  }

  public Address getTemporaryAddress()
  {
    return temporary;
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
