package Chapter_4;
public class DebugBox
{
    private int width;
    private int length;
    private int height;

    // Default constructor
    public DebugBox()
    {
        length = 1;
        width = 1;
        height = 1;
    }

    // Parameterized constructor
    public DebugBox(int width, int length, int height)
    {
        this.width = width;
        this.length = length;  // ✅ fixed
        this.height = height;  // ✅ fixed
    }

    // Method to display box data
    public void showData()
    {
        System.out.println("Width: " + width + " Length: " + length + " Height: " + height);
    }

    // Method to compute volume
    public double getVolume()
    {
        return length * width * height;
    }
}
