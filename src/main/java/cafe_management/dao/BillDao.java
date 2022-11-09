package cafe_management.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cafe_management.pojo.Bill;

public interface BillDao extends JpaRepository<Bill, Integer> {

}
