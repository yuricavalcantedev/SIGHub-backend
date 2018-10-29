package br.com.yuri.cavalcante.tcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationDomainRepository extends JpaRepository<ApplicationDomainRepository, Integer>{

}
