package lk.ijse.sports_zone.bo;

import lk.ijse.sports_zone.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO{
    ArrayList<CustomerDTO> getAllCustomers() throws SQLException;

    boolean saveCustomer(CustomerDTO dto) throws SQLException;

    boolean updateCustomer(CustomerDTO dto) throws SQLException;

    boolean deleteCustomer(String id) throws SQLException;

    String generateNewCustID() throws SQLException;

    CustomerDTO searchByCustID(String id) throws SQLException;
}
