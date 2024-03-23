package com.example.demo.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Response;
import com.example.demo.entity.ArraysBartenderEntity;
import com.example.demo.repository.BartenderRepository;
import com.example.demo.service.BarService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BarServiceImpl implements BarService {

	private static final int[] PRIME_NUMBERS = { 2, 3, 5, 7, 11, 13, 17 };
	private static final int MIN_STACK_ID = 1;
	private static final int MAX_STACK_ID = 5;
	
    private final BartenderRepository bartenderRepository;

    public BarServiceImpl(BartenderRepository bartenderRepository) {
        this.bartenderRepository = bartenderRepository;
    }

	@Override
	public Response getDivisibleNumberList(Integer iterations, Integer stackId) {
		Response response = new Response();
		log.info("Execute getDivisibleNumber service");

		if (stackId == null || iterations == null || stackId < MIN_STACK_ID || stackId > MAX_STACK_ID) {
			setResponse(response, HttpStatus.BAD_REQUEST, "Invalid input parameters.", Collections.emptyList());
			return response;
		}

		if (iterations <= 0) {
			setResponse(response, HttpStatus.BAD_REQUEST, "Iterations must be greater than 0.",
					Collections.emptyList());
			return response;
		}

		try {
			Optional<ArraysBartenderEntity> arraysBartenderOptional = bartenderRepository.findById(stackId);
			if (arraysBartenderOptional.isPresent()) {
				List<Integer> inputList = Arrays.stream(arraysBartenderOptional.get().getInputArray().split(","))
						.map(Integer::valueOf).collect(Collectors.toList());

				List<Integer> listB = inputList.stream().filter(number -> isDivisibleByAnyPrime(number, iterations))
						.distinct().collect(Collectors.toList());

				log.info("List B: {}", listB);
				setResponse(response, HttpStatus.OK, "Ok", listB);
			} else {
				setResponse(response, HttpStatus.NOT_FOUND, "StackId not found.", Collections.emptyList());
			}
		} catch (NumberFormatException e) {
			log.error("Invalid input format.", e);
			setResponse(response, HttpStatus.BAD_REQUEST, "Invalid input format.", Collections.emptyList());
		} catch (Exception e) {
			log.error("Error in service getDivisibleNumber ", e);
			setResponse(response, HttpStatus.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() + ". " + e.getMessage(),
					Collections.emptyList());
		}
		return response;
	}

	private boolean isDivisibleByAnyPrime(int number, int iterations) {
		int maxIterations = Math.min(iterations, PRIME_NUMBERS.length);
		for (int i = 0; i < maxIterations; i++) {
			if (number % PRIME_NUMBERS[i] == 0) {
				log.info("Number {} % {} is evenly divisible. Added to List B", number, PRIME_NUMBERS[i]);
				return true;
			}
		}
		return false;
	}

	private void setResponse(Response response, HttpStatus status, String reason, List<Integer> answer) {
		response.setStatus(status.value());
		response.setReason(reason);
		response.setAnswer(answer);
	}
}
