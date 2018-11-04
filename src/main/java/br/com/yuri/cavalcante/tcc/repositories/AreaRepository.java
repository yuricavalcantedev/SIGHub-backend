package br.com.yuri.cavalcante.tcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.yuri.cavalcante.tcc.domain.Area;

@Repository
public interface AreaRepository extends JpaRepository<Area, Integer>{

}
