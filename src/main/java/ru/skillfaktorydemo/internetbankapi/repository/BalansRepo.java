package ru.skillfaktorydemo.internetbankapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillfaktorydemo.internetbankapi.model.ClientsBalance;

@Repository
public interface BalansRepo extends JpaRepository<ClientsBalance,Integer> {
}
