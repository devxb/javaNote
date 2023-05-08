package junit.parameterizedtest;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.List;

class NumberUtils {

	List<Integer> sum(List<Integer> left, List<Integer> right){
		if(left == null || right == null){
			return null;
		}
		List<Integer> result = new ArrayList<>();
		for(int i = 0; i < min(left.size(), right.size()); i++){
			result.add(left.get(i) + right.get(i));
		}
		for(int i = min(left.size(), right.size()); i < max(left.size(), right.size()); i++){
			result.add(left.size() < right.size() ? right.get(i) : left.get(i));
		}
		return result;
	}

}
