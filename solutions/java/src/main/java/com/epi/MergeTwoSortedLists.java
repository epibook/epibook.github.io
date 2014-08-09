class Node {
  int key;

  @Override 
  public String toString() {
    return "" + key + ", " + ((next == null) ? "" : next.toString());
  }

  Node next;
  Node(int key, Node next) {
    this.key = key;
    this.next = next;
  }

  public static Node MergeTwoSortedLists( Node L1, Node L2 ) {
    if ( L1 == null ) {
      return L2;
    } else if ( L2 == null ) {
      return L1;
    }

    Node curr1 = L1;
    Node curr2 = L2;
    Node prev1 = L1;
    Node prev2 = L2;

    // walk the two lists, merging till one (or both) end
    while ( true ) {
      System.out.println("curr1, curr2, prev1, prev2:" + 
            curr1 + ";" + curr2 + ";" + prev1 + ";" + prev2 );
      if ( curr1 == null || curr2 == null ) {
        break;
      }
      if ( curr1.key < curr2.key ) {
        prev1 = curr1;
        curr1 = curr1.next;
        prev1.next = (curr1 == null)
                        ? curr2
                        : (curr2.key < curr1.key) ? curr2 : curr1;
      } else {
        prev2 = curr2;
        curr2 = curr2.next;
        prev2.next = (curr2 == null) 
                        ?  curr1 
                        : (curr1.key < curr2.key ) ? curr1 : curr2;
      }
    }

    // add in any remaining nodes
    if ( curr1 != null ) {
      prev2.next = curr1;
    } else if ( curr2 != null ) {
      prev1.next = curr2;
    }

    return  (L1.key < L2.key) ? L1 : L2;
  }

  public static void main(String[] args) {
    Node L1 = new Node(1, new Node( 3, new Node( 5, null ) ) );
    Node L2 = new Node(2, new Node( 4, new Node( 6, null ) ) );
    Node L = null;
    System.out.println("L1 = " + L1.toString());
    System.out.println("L2 = " + L2.toString());
    // L = MergeTwoSortedLists( L1, L2 );
    // System.out.println("L = " + L.toString());
    
    L1 = new Node(1, new Node( 3,  null ) );
    L2 = new Node(2, new Node( 4, new Node( 6,  new Node(8, null ) ) ) );
    System.out.println("L1 = " + L1.toString());
    System.out.println("L2 = " + L2.toString());
    // L = MergeTwoSortedLists( L1, L2 );
    // System.out.println("L = " + L.toString());

    L1 = new Node(1, new Node( 9,  null ) );
    L2 = new Node(2, new Node( 4, new Node( 6,  new Node(6, null ) ) ) );
    System.out.println("L1 = " + L1.toString());
    System.out.println("L2 = " + L2.toString());
    L = MergeTwoSortedLists( L1, L2 );
    System.out.println("L = " + L.toString());

    L2 = new Node(1, new Node( 9,  null ) );
    L1 = new Node(2, new Node( 4, new Node( 6,  new Node(6, null ) ) ) );
    System.out.println("L1 = " + L1.toString());
    System.out.println("L2 = " + L2.toString());
    L = MergeTwoSortedLists( L1, L2 );
    System.out.println("L = " + L.toString());

    L1 = new Node(1, null);
    L2 = new Node(2, null);
    System.out.println("L1 = " + L1.toString());
    System.out.println("L2 = " + L2.toString());
    L = MergeTwoSortedLists( L1, L2 );
    System.out.println("L = " + L.toString());

    L1 = new Node(1, new Node(5, new Node( 9, null ) ) ); 
    L2 = new Node(2,  new Node(6, new Node( 7, new Node( 9, new Node( 12, new Node( 15, null ) ) ) ) ) ); 
    System.out.println("L1 = " + L1.toString());
    System.out.println("L2 = " + L2.toString());
    L = MergeTwoSortedLists( L1, L2 );
    System.out.println("L = " + L.toString());
  }
}
