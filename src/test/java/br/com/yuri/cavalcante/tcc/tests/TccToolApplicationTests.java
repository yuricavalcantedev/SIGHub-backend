package br.com.yuri.cavalcante.tcc.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.yuri.cavalcante.tcc.domain.Area;
import br.com.yuri.cavalcante.tcc.repositories.AreaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class TccToolApplicationTests {


	@Autowired
	private AreaRepository areaRepository;
	
	@Test
	public void testingArea() {
		
		Area area = new Area(null, "Business", "description", "examples");
		areaRepository.save(area);
		
		Optional<Area> areaFounded = areaRepository.findById(area.getId());
		assertThat(areaFounded.get().getId()).isEqualTo(area.getId());
	}
	

}
