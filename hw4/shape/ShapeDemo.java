import java.util.*;

class ShapeDemo {
    public static double totalArea(Shape[] arr) {
        double total = 0;

        for (Shape s : arr)
            if (s != null)
                total += s.area();

        return total;
    }

    public static double totalArea(java.util.List<? extends Shape> arr) {
        double total = 0;

        for (Shape s : arr)
            if (s != null)
                total += s.area();

        return total;
    }

    public static double totalSemiperimeter(Shape[] arr) {
        double total = 0;

        for (Shape s : arr)
            if (s != null)
                total += s.semiperimeter();

        return total;
    }

    public static void printAll(Shape[] arr) {
        for (Shape s : arr)
            System.out.println(s);
    }

    public static void main(String[] args) {
        Shape[] a = { new Circle(2.0), new Rectangle(1.0, 3.0), new Circle(0.5) };


        Arrays.sort(a, Shape::compareTo);

        // iterate through array,
        // if shape is instance of Stretchable,
        // then call method to stretch the shape
        for (Shape A: a) {
            if (A instanceof Stretchable) {
                Stretchable newA = (Stretchable)A;
                newA.stretch(2);
            }
        }
        for (Shape A: a) {
            System.out.println(A.area());
        }
        /*output:
        0.7853981633974483
        6.0
        12.566370614359172 
        */
        

        System.out.println("Total area = " + totalArea(a));
        System.out.println("Total semiperimeter = " + totalSemiperimeter(a));

        java.util.List<Circle> lst = new java.util.ArrayList<Circle>();
        lst.add(new Circle(2.0));
        lst.add(new Circle(1.0));
        System.out.println("Total area = " + totalArea(lst));

        System.out.println(" Before sort = " + Arrays.toString(a));
        // Arrays.sort(a,Comparator.comparingDouble(Shape::area));

        Arrays.sort(a, Shape::compareTo);

        System.out.println(" After sort = " + Arrays.toString(a));

        printAll(a);

            // create a_tree_set, passing in the AreaComparator class
            Set<Shape> a_tree_set = new TreeSet<Shape>(new AreaComparator());
            // add the shapes into the tree set
            a_tree_set.add(new Circle(2.0));
            a_tree_set.add(new Rectangle(1.0, 3.0));
            a_tree_set.add(new Circle(0.5));

    }
}
        // A Area comparator class which implements the Comparater interface 
        // and defines the compare method to compare the shape's area
        class AreaComparator implements Comparator<Shape> {
            @Override
            public int compare(Shape s1, Shape s2) {
                return s1.area() > s2.area() ? 1 : (s1.area() == s2.area() ? 0 : -1);
            }
        }




