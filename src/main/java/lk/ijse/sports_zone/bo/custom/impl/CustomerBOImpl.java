package lk.ijse.sports_zone.bo;

import lk.ijse.sports_zone.dao.custom.CustomerDAO;
import lk.ijse.sports_zone.dao.DAOFactory;
import lk.ijse.sports_zone.dto.CustomerDTO;
import lk.ijse.sports_zone.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO{
    CustomerDAO customerDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException {
        ArrayList<Customer> allCustomer = customerDAO.getAll();
        ArrayList<CustomerDTO> arrayList = new ArrayList<>();
        for (Customer c : allCustomer) {
            arrayList.add(new CustomerDTO(
                    c.getCustId(),
                    c.getCustName(),
                    c.getContactNo(),
                    c.getAddress(),
                    c.getEmail()
            ));
        }
        return arrayList;
    }

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws SQLException {
        return customerDAO.save(new Customer(dto.getCustId(),
                dto.getCustName(),
                dto.getContactNo(),
                dto.getAddress(),
                dto.getEmail()
        ));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException {
        return customerDAO.update(new Customer(
                dto.getCustId(),
                dto.getCustName(),
                dto.getContactNo(),
                dto.getAddress(),
                dto.getEmail()
        ));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException {
        return customerDAO.delete(id);
    }

    @Override
    public String generateNewCustID() throws SQLException {
        String currentId = customerDAO.generateNewID();

        if(currentId != null) {
            String[] strings = currentId.split("C-");
            int id = Integer.parseInt(strings[1]);
            ++id;
            String digit=String.format("%03d", id);
            return "C-" + digit;
        }
        return "C-001";
    }

    @Override
    public CustomerDTO searchByCustID(String id) throws SQLException {
        Customer c = customerDAO.search(id);

        if(c != null){
            return new CustomerDTO(
                    c.getCustId(),
                    c.getCustName(),
                    c.getContactNo(),
                    c.getAddress(),
                    c.getEmail()
            );
        }
        return null;
    }
}
