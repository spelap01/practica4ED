package ule.edi.SimpleList;



import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class SingleLinkedListImplTests {

	

	private SingleLinkedListImpl<String> lS;
	private SingleLinkedListImpl<String> lSABC;
	

	@Before
	public void setUp() {
		this.lS = new SingleLinkedListImpl<String>();
		
		
		this.lSABC = new SingleLinkedListImpl<String>("A", "B", "C");
	}
	
   @Test
   public void constructorElemens(){
	   System.out.println("22222222222222222222222222222222222222222222222222");

	   lS=new SingleLinkedListImpl<String>("A", "B", "C", "D");
	   System.out.println("22222222222222222222222222222222222222222222222222");

	   Assert.assertEquals("[A, B, C, D]", lS.toString());
   }

   @Test
   public void addAtPos(){
	   
	   lS.addAtPos("A", 1);
	   Assert.assertEquals("[A]", lS.toString());
	   lS.addAtPos("C", 1);
	   Assert.assertEquals("[C, A]", lS.toString());
	   lS.addAtPos("B", 2);
	   Assert.assertEquals("[C, B, A]", lS.toString());
	   lS.addAtPos("D", 5);
	   Assert.assertEquals("[C, B, A, D]", lS.toString());

   }
   @Test
   public void testParaTodo() throws EmptyCollectionException{
	   System.out.println(lS.size());
	   lS.addLast("C");
	   lS.addFirst("A");
	   lS.addFirst("B");
	   System.out.println(lS.toString());
	   
	   lS.addLast("C");
	   lS.addLast("A");
	   lS.addLast("D");
	   lS.addLast("C");
	   System.out.println(lS.toString());
	   System.out.println(lS.size());
	   System.out.println(lS.indexOf("C"));
	   lS.removeLast("A");
	   lS.removeLast();
	   System.out.println(lS.size());
	   System.out.println(lS.toString()+"11111111111111111");

	   System.out.println(lS.reverse().toString()+"11111111111111111");

   }
   
   @Test
   public void addNTimes(){
	   
	   lS.addNTimes("A", 3);
	   Assert.assertEquals("[A, A, A]", lS.toString());
	   lS.addNTimes("B", 2);
	   Assert.assertEquals("[A, A, A, B, B]", lS.toString());
	   
   }
// TEST DE SUBLIST
	@Test
	public void tesSubListEnListaVacia() {
	
		Assert.assertEquals(-1, lS.isSubList(lSABC));		
	}

		@Test
		public void tesSubListConSubListaVacia() {
			Assert.assertEquals(1, lSABC.isSubList(lS));		
		}
		
		
		@Test
		public void subListVarios() {
			lS = new SingleLinkedListImpl<String>("A", "B", "C", "D", "E");
			Assert.assertEquals(1, lS.isSubList(lSABC));	
			lS = new SingleLinkedListImpl<String>("Z", "X", "A", "B", "C", "D", "E");
			Assert.assertEquals(3, lS.isSubList(lSABC));	
			lS = new SingleLinkedListImpl<String>("Z", "X", "A", "B", "C");
			Assert.assertEquals(3, lS.isSubList(lSABC));	
			lS = new SingleLinkedListImpl<String>("A", "B", "C");
			Assert.assertEquals(1, lS.isSubList(lSABC));	
		}
	 
   

}
