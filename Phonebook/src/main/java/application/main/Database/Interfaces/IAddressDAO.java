package application.main.Database.Interfaces;

import application.main.Entities.Address;

import java.util.ArrayList;

public interface IAddressDAO
{
  public Address createAddress(Address address, int personId);
  public Address getAddress(int Id);
  public ArrayList<Address> getAllAddresses();
  public ArrayList<Address> getAllAddressesForPerson(int personId);
  public Address updateAddress(Address address);
  public Address deleteAddress(int addressId);
}
