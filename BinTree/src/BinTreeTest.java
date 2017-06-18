import CITS2200.BinaryTree;
import CITS2200.Iterator;

public class BinTreeTest {
	public static void main(String[] args) {
		
		BinTree a = new BinTree(0, 
				new BinTree(),
				new BinTree(5, 
						new BinTree(6, 
								new BinTree(2, 
										new BinTree(2, 
												new BinTree(), new BinTree()), 
										new BinTree()),
								new BinTree(2, 
										new BinTree(2, 
												new BinTree(), new BinTree()), 
										new BinTree(2, 
												new BinTree(), new BinTree(3, 
														new BinTree(), new BinTree()
														)
												)
										)
								), 
						new BinTree())
		);
		
		BinTree b = new BinTree(0, 
				new BinTree(),
				new BinTree(5, 
						new BinTree(6, 
								new BinTree(2, 
										new BinTree(2, 
												new BinTree(), new BinTree()), 
										new BinTree()),
								new BinTree(2, 
										new BinTree(2, 
												new BinTree(), new BinTree()), 
										new BinTree(2, 
												new BinTree(), new BinTree(3, 
														new BinTree(), new BinTree()
														)
												)
										)
								), 
						new BinTree())
		);
		
		System.out.println("Items in A");
		int count1 = 0;
		/*Iterator i = a.iterator();
		while (i.hasNext()){
			System.out.println("Item "+count1++ + " is " +i.next() + " in a");
		}*/
		
		System.out.println();
		System.out.println("Items in B");
		int count2 = 0;
		/*Iterator i2 = a.iterator();
		while (i2.hasNext()){
			System.out.println("Item "+count2++ + " is " +i2.next() + " in b");
		}*/
		
		System.out.println();	
		System.out.println("A equals B: "+ a.equals(b));
		System.out.println("B equals A: "+ b.equals(a));
	}

}
