import java.awt.Graphics;

/**
 * Implements the list of messages for teletext
 */
public class TeletextList
{
  private ListNode2 heading, topNode;

  /**
   * Creates a circular list of headlines.
   * First creates a circular list with one node, "Today's headlines:".
   * Saves a reference to that node in heading.
   * Adds a node holding an empty string before heading
   * and another node holding an empty string after heading.
   * Appends all the strings from headlines to the list, after
   * the blank line that follows heading,
   * preserving their order.  Sets topNode equal to heading.
   */
  public TeletextList(String[] headlines)
  {
    // YOUR CODE HERE
	heading = new ListNode2("Today's headlines:");
	heading.setNext(new ListNode2(""));
	heading.getNext().setPrevious(heading);
	heading.setPrevious(new ListNode2(""));
	heading.getPrevious().setNext(heading);
	int x=0;
	for (ListNode2 list=heading.getNext();1==1;list=list.getNext())
	{
		if (x>=headlines.length)
		{
			list.setNext(heading.getPrevious());
			heading.getPrevious().setPrevious(list);
			break;
		}
		list.setNext(new ListNode2(headlines[x]));
		list.getNext().setPrevious(list);
		
		x++;
		
	}
	topNode=heading;
  }

  /**
   * Inserts a node with msg into the headlines list after the blank
   * line that follows heading.
   */
  public void insert(String msg)
  {
    // YOUR CODE HERE
	  heading.getNext().setNext(new ListNode2(msg, heading.getNext(),heading.getNext().getNext()));
	  heading.getNext().getNext().getNext().setPrevious(heading.getNext().getNext());
  }

  /**
   * Deletes the node that follows topNode from the headlines list,
   * unless that node happens to be heading or the node before or after
   * heading that holds a blank line.
   */
  public void delete()
  {
    // YOUR CODE HERE
	  if (topNode!=heading&&topNode.getNext()!=heading&&topNode.getNext()!=heading.getPrevious())
	  {
		  topNode.setNext(topNode.getNext().getNext());
		  topNode.getNext().setPrevious(topNode);
	  }
  }	

  /**
   * Scrolls up the headlines list, advancing topNode to the next node.
   */
  public void scrollUp()
  {
    topNode=topNode.getNext();
  }

  /**
   * Adds a new node with msg to the headlines list before a given node.
   * Returns a reference to the added node.
   */
  private ListNode2 addBefore(ListNode2 node, String msg)
  {
    // YOUR CODE HERE
	  node.getPrevious().setNext(new ListNode2 (msg,node.getPrevious(),node));
	  node.setPrevious(node.getPrevious().getNext());
	  return node.getPrevious();
  }

  /**
   * Adds a new node with msg to the headlines list after a given node.
   * Returns a reference to the added node.
   */
  private ListNode2 addAfter(ListNode2 node, String msg)
  {
    // YOUR CODE HERE
	  node.setNext(new ListNode2 (msg, node,node.getNext()));
	  node.getNext().getNext().setPrevious(node.getNext());
	  return node.getNext();
  }

  /**
   * Removes a given node from the list.
   */
  private void remove(ListNode2 node)
  {
    // YOUR CODE HERE
	  node.getPrevious().setNext(node.getNext());
	  node.getNext().setPrevious(node.getPrevious());
  }

  /**
   * Draws nLines headlines in g, starting with topNode at x, y
   * and incrementing y by lineHeight after each headline.
   */
  public void draw(Graphics g, int x, int y, int lineHeight, int nLines)
  {
    ListNode2 node = topNode;
    for (int k = 1; k <= nLines; k++)
    {
      g.drawString((String)node.getValue(), x, y);
      y += lineHeight;
      node = node.getNext();
    }
  }
  
  
}
//worked with casey crosson
