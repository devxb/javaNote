package junit.parameterizedtest;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class NumberUtilsTest {

	private static final NumberUtils NUMBER_UTILS = new NumberUtils();

	@ParameterizedTest
	@MethodSource("testSources")
	void checkCorrectResult(List<Integer> left, List<Integer> right, List<Integer> expected){
		assertEquals(expected, NUMBER_UTILS.sum(left, right));
	}

	static Stream<Arguments> testSources(){
		return Stream.of(
			Arguments.of(List.of(1, 2, 3), List.of(4, 5, 6), List.of(5, 7, 9)),
			Arguments.of(List.of(1, 2), List.of(4, 5, 6), List.of(5, 7, 6)),
			Arguments.of(List.of(1, 2, 3), List.of(4), List.of(5, 2, 3)),

			Arguments.of(List.of(), List.of(), List.of()),
			Arguments.of(List.of(1), List.of(), List.of(1)),
			Arguments.of(List.of(), List.of(1), List.of(1)),
			Arguments.of(List.of(1, 1, 1), List.of(1, 1, 1), List.of(3, 3, 3)), // <- 일부러 실패하게 짬. 에러메시지 확인용

			Arguments.of(null, null, null)
		);
	}

}
