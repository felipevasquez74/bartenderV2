package com.example.demo.bartender.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.Response;
import com.example.demo.entity.ArraysBartenderEntity;
import com.example.demo.repository.BartenderRepository;
import com.example.demo.service.BarService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class BarServiceImplTest {

	@Autowired
	private BarService barService;
	
	@Mock
	private BartenderRepository bartenderRepository;

	@InjectMocks
	private BarServiceImplTest barServiceImplTest;

	private ArraysBartenderEntity arraysBartender;

	@BeforeEach
	public void initTest() {
		MockitoAnnotations.openMocks(this);
		Mockito.reset(bartenderRepository);

		arraysBartender = new ArraysBartenderEntity();
		arraysBartender.setId(1);
		arraysBartender.setInputArray("2,4,5,6,7,8");

	}

	@Test
	void getAllArrays() {
		when(bartenderRepository.findAll()).thenReturn(Arrays.asList(arraysBartender));
		assertNotNull(bartenderRepository.findAll());
	}

	@Test
	void getArrayByIdButIsNotPresent() {
		Optional<ArraysBartenderEntity> arraysBartender1 = this.bartenderRepository.findById(6);
		assertFalse(arraysBartender1.isPresent());
	}
	
	@Test
	public void testGetDivisibleNumberAnswerNotEmpty() throws Exception {
		Response response = barService.getDivisibleNumberList(1, 5);
        assertTrue(!response.getAnswer().isEmpty()); 
	}
	
	@Test
	public void testGetDivisibleNumberComparingList() throws Exception {
		Response response = barService.getDivisibleNumberList(5, 1);
		assertEquals(List.of(2,4,5,6,7,8), response.getAnswer());
	}

}
