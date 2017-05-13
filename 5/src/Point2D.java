public class Point2D {
    
	private final int x;
	private final int y;
	
	public Point2D( int x, int y ) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return " [" + x + ", " + y + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if ( ! ( obj instanceof Point2D ) ) return false;
		
		Point2D toCompare = (Point2D)obj;
		
		if ( x != toCompare.x ) return false;
		if ( y != toCompare.y ) return false;
		
		return true;
	}
}