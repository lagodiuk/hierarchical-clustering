import com.lagodiuk.clustering.DistanceCalculator;

public class Distances {

	private static final DistanceCalculator<Integer> integerDistCalc =
			new DistanceCalculator<Integer>() {
				@Override
				public double distance(Integer base, Integer target) {
					return Math.abs(base - target);
				}
			};

	public static DistanceCalculator<Integer> integerDistCalc() {
		return integerDistCalc;
	}

}
