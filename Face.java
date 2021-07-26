public class Face
{
	int[] corners;
	Vertex center;
	public Face(int[] cr, Vertex cen)
	{
		corners = cr;
		center = cen;
	}
	public Vertex getCenter()
	{
		return center;
	}
	public int[] getCorners()
	{
		return corners;
	}
}