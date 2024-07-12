package Pharmacie;

public class Pair<A,Q> {
	private final A first;
    private final Q second;

    public Pair(A first, Q second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public Q getSecond() {
        return second;
    }
	
}
