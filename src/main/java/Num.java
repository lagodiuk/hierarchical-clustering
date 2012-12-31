import com.lagodiuk.clustering.Distanceable;

public class Num implements Distanceable<Num> {

	public final int num;

	public Num(int num) {
		this.num = num;
	}

	@Override
	public double distance(Num other) {
		return Math.abs(this.num - other.num);
	}

	@Override
	public String toString() {
		return "" + this.num;
	}

}
