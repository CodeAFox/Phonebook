package application.main.Database.DAOs;

import application.main.Database.DatabaseHandlerFactory;
import application.main.Database.Interfaces.IAddressDAO;
import application.main.Entities.Address;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import java.sql.*;
import java.util.ArrayList;

public class AddressDAO extends DatabaseHandlerFactory implements IAddressDAO
{
  private static AddressDAO instance;

  private AddressDAO() throws SQLException
  {
    DriverManager.registerDriver(new SQLServerDriver());
  }

  public static synchronized AddressDAO getInstance()
  {
    try
    {
      if (instance == null)
      {
        instance = new AddressDAO();
      }
      return instance;
    }
    catch (SQLException e)
    {
      throw new RuntimeException("Issue getting singleton instance of AddressDAO");
    }
  }
  @Override public synchronized Address createAddress(Address address, int personId)
  {
    try(Connection connection = super.establishConnection())
    {
      PreparedStatement statement = connection.prepareStatement("insert into phonebook.address(address, person_id, type) values (?, ?, ?);");
      statement.setString(1, address.getAddress());
      statement.setInt(2, personId);
      statement.setString(3, address.getType());
      statement.executeUpdate();

      ResultSet generatedKeys = statement.getGeneratedKeys();
      if(generatedKeys.next())
      {
        address.setAddressId(generatedKeys.getInt("address_id"));
      }
      else
      {
        throw new RuntimeException("No keys were generated.");
      }

      return address;
    }
    catch (SQLException e)
    {
      throw new RuntimeException("Something went wrong while creating an address: " + e.getMessage());
    }
  }

  @Override public Address getAddress(int Id)
  {
    try(Connection connection = super.establishConnection())
    {
      PreparedStatement statement = connection.prepareStatement("select address.address, address.type, address.address_id from phonebook.address where address_id = ?;");
      statement.setInt(1, Id);

      ResultSet rs = statement.executeQuery();
      Address fetched = null;
      while (rs.next())
      {
        fetched = new Address(rs.getString("address"), rs.getString("type"), Id);
      }

      return fetched;
    }
    catch (SQLException e)
    {
      throw new RuntimeException("Something went wrong while getting an address from the database: " + e.getMessage());
    }
  }

  @Override public ArrayList<Address> getAllAddresses()
  {
    try(Connection connection = super.establishConnection())
    {
      ArrayList<Address> allAddresses = new ArrayList<>();

      PreparedStatement statement = connection.prepareStatement("select address.address, address.type, address.address_id from phonebook.address;");
      ResultSet rs = statement.executeQuery();

      while (rs.next())
      {
        allAddresses.add(new Address(rs.getString("address"), rs.getString("type"),
            rs.getInt("address_id")));
      }

      return allAddresses;
    }
    catch (SQLException e)
    {
      throw new RuntimeException("Something went wrong while fetching all addresses from the database: " + e.getMessage());
    }
  }

  @Override public synchronized Address updateAddress(Address address)
  {
    try(Connection connection = super.establishConnection())
    {
      PreparedStatement statement = connection.prepareStatement("update phonebook.address set address.address = ?, address.type = ? where address.address_id = ?;");
      statement.setString(1, address.getAddress());
      statement.setString(2, address.getType());
      statement.setInt(3, address.getAddressId());
      statement.executeUpdate();

      return address;
    }
    catch (SQLException e)
    {
      throw new RuntimeException("Something went wrong while updating an address in the database: " + e.getMessage());
    }
  }

  @Override public synchronized Address deleteAddress(int addressId)
  {
    try(Connection connection = super.establishConnection())
    {
      Address deleted = getAddress(addressId);

      PreparedStatement statement = connection.prepareStatement("delete from phonebook.address where address_id = ?;");
      statement.setInt(1, addressId);
      statement.executeUpdate();

      return deleted;
    }
    catch (SQLException e)
    {
      throw new RuntimeException("Something went wrong while deleting an address from the database: " + e.getMessage());
    }
  }
}
