package br.com.yuri.cavalcante.tcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.yuri.cavalcante.tcc.domain.Catalog;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Integer> {

//	@Query("DELETE FROM Softgoal s WHERE s.parent.id = :id")
//	public void deleteSoftGoalsByIdFather(@Param("id") Integer id);
}
