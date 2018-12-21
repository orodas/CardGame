//Oscar Rodas

//CardGame.java


//This program simulates a card game with 2 players 

import java.util.*;
import java.text.*;

public class CardGame extends MyLinkedList
{
	public static String[] cards = {"AH","AC","AS","AD","1H","1C","1S","1D","2H","2C","2S","2D","3H","3C","3S","3D","4H","4C","4S","4D","5H","5C","5S","5D","6H","6C","6S","6D","7H","7C","7S","7D","8H","8C","8S","8D","9H","9C","9S","9D","JH","JC","JS","JD","QH","QC","QS","QD","KH","KC","KS","KD"};
	public static int pick = 0;								
	public static MyLinkedList p1 = new MyLinkedList();		//1st player's hand
	public static MyLinkedList p2 = new MyLinkedList();		//2nd player's hand
	public static String[] p1cards = new String[26];		//player1's initial cards
	public static String[] p2cards = new String[26];		//player2's initial cards
	public static String[] table = new String[52];			//cards on the table
	public static int[][] randy = new int[1][3];
	
	public static void main(String[] args) 
    {
		p1.addFirst("A");
		p2.addFirst2("A");
		deal(pick);
		shuffle(p1cards);									
		System.out.println("Player 1 drew the following cards:");
		p1.printList(head);
		shuffle2(p2cards);
		System.out.println("Player 2 drew the following cards:");
		p2.printList2(head2);
		pickPlayer();										
	}
	
	public static void deal(int p)//deals the cards
	{
		Random randomGenerator = new Random();
		int counter[] = {0,0};
		for(int i = 0; i<52; i++)
		{
			p = randomGenerator.nextInt(2) +1;
			if(counter[0] >= 26)						//makes sure each player gets 26 cards
				p = 2;
			if(counter[1] >= 26)
				p = 1;
			if(p == 1)
			{
				p1cards[counter[0]] = cards[i];
				counter[0]++;
			}
			else
			{
				p2cards[counter[1]] = cards[i];
				counter[1]++;
			}
		}
	}
	
	public static void shuffle(String[] array)			//shuffles the player's hand
	{
		int rand;
		String temp;
		Random randomGenerator = new Random();
		for(int i = 0; i<26; i++)						//randomly swap all the cards' indexes
		{
			rand = randomGenerator.nextInt(26);			
			randy[0][0] = rand;
			temp = array[i];
			array[i] = array[rand];
			array[rand] = temp;
		}
		
		for(int i = 0; i<26; i++)
		{
			p1.add(array[i]);							//creates the player's hand
		}
	}
	
	public static void shuffle2(String[] array)			//shuffles the player's hand
	{
		int rand;
		String temp;
		Random randomGenerator = new Random();
		for(int i = 0; i<26; i++)						//randomly swap all the cards' indexes
		{
			rand = randomGenerator.nextInt(26);	
			randy[0][1] = rand;
			temp = array[i];
			array[i] = array[rand];
			array[rand] = temp;
		}
		
		for(int i = 0; i<26; i++)
		{
			p2.add2(array[i]);							//creates the player's hand
		}
	}
	
	public static void pickPlayer()						//randomly selects player to start game
	{
		Random randomGenerator = new Random();
		int player = randomGenerator.nextInt(2)+1;
		randy[0][2] = player;
		if(randy[0][2] == 1)
			System.out.println("player 1 goes first");
		else
			System.out.println("player 2 goes first");
		playGame(player);
	}
	
	public static void playGame(int player)
	{
		int i =0;
		for(int k = 0; k<10; k++)
		{
			System.out.println("round " + (k+1) + ":");	//prints the current round number
			if(player == 1)								//this code executes only if player 1 goes first
			{
				table[i] = p1.removeFirst();			//remove the card from the hand and put it on the table
				if(i>=1 && table[i].charAt(1)==(table[i-1].charAt(1)))	//if the suits match, give all the cards on the table to the player
				{
					for(int l = i; l>=0; l--)
					{	
						p1.add(table[l]);				//take all the cards
					}
					i=-1;								//resets the index for the table
				}
				i++;									
				table[i] = p2.removeFirst2();
				if(i>=1 && table[i].charAt(1)==(table[i-1].charAt(1)))
				{
					for(int l = i; l>=0; l--)
					{	
						p2.add2(table[l]);
					}
					i=-1;
				}
				i++;
			}
			else if(player == 2)						//this code executes only if player 1 goes first
			{
				table[i] = p2.removeFirst2();
				if(i>=1 && table[i].charAt(1)==(table[i-1].charAt(1)))
				{
					for(int l = i; l>=0; l--)
					{	
						p2.add2(table[l]);
					}
					i=-1;
				}
				i++;
				table[i] = p1.removeFirst();
				if(i>=1 && table[i].charAt(1)==(table[i-1].charAt(1)))
				{
					for(int l = i; l>=0; l--)
					{	
						p1.add(table[l]);
					}
					i=-1;
				}
				i++;
			}
			System.out.println("Player 1's hand:");
			p1.printList(head);							//prints the current hand of the player
			System.out.println("Player 2's hand:");
			p2.printList2(head2);
			System.out.println("Cards on the table:");
			for(int j = 0; j<i; j++)					//prints the cards on the table
			{
				System.out.print(table[j] + ", ");
			}
			System.out.println("\n");
		}
		if(size > size2)
			System.out.println("Player 1 wins!");	//player1 has more cards
		else if(size < size2)
			System.out.println("Player 2 wins!");	//player 2 has more cards
		else
			System.out.println("It's a tie!");
	}
	
}

class MyLinkedList										//my implementation of a single link list
{
    public static Node head = new Node(null);
	public static Node head2 = new Node(null);
    public static int size = 0;
	public static int size2 = 0;
     
    public void addFirst(String item)
    {
        Node First = new Node(item);
		if(size>0)
		{
        First.next = head.next;
        head.next = First;
        size++;
		}
		else
		{
			head.next = First;
			size++;
		}
    }
	
	public void addFirst2(String item)
    {
        Node First2 = new Node(item);
		if(size>0)
		{
        First2.next = head2.next;
        head2.next = First2;
        size2++;
		}
		else
		{
			head2.next = First2;
			size2++;
		}
    }
     
    public void addAfter(String item, Node target)
    {
        Node After = new Node(item);
        After.next = target.next;
        target.next = After;
        size++;
    }
	
	 public void addAfter2(String item, Node target)
    {
        Node After2 = new Node(item);
        After2.next = target.next;
        target.next = After2;
        size2++;
    }
     
    public String removeFirst()
    {
        if(size>0)
        {
            Node temp = head.next;
            head.next = head.next.next;
            size--;
            return temp.data;
        }
        return null;
    }
	
	public String removeFirst2()
    {
        if(size2>0)
        {
            Node temp = head2.next;
            head2.next = head2.next.next;
            size2--;
            return temp.data;
        }
        return null;
    }
     
    public String removeAfter(Node target)
    {
        Node temp = target.next;
        if (temp != null)
        {
            target.next = target.next.next;
            size--;
            return temp.data;
        }
        return null;
    }
     
    public Node getNode(int index)
    {
        Node node = head;
        for(int i = 0; i < index && node != null; i++)
            node = node.next;
        return node;
    }
	
	public Node getNode2(int index)
    {
        Node node = head2;
        for(int i = 0; i < index && node != null; i++)
            node = node.next;
        return node;
    }
     
    //Adds to the end of the list
    public void add(String item)
    {
        addAfter(item, getNode(size-1));
    }
	
	public void add2(String item)
    {
        addAfter2(item, getNode2(size2-1));
    }
     
    public void add(String item, int index)
    {
        if (index < 0 || index > size)
             return;
        if(index == 0)
             addFirst(item);
        else
             addAfter(item, getNode(index-1));
    }
	 
	public void printList(Node head)
	{
        Node temp = head.next;
        while(temp.next != null)
		{
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
		System.out.println("");
		System.out.println("");
    }
	
	public void printList2(Node head2)
	{
        Node temp2 = head2.next;
        while(temp2.next != null)
		{
            System.out.print(temp2.data + "->");
            temp2 = temp2.next;
        }
		System.out.println("");
		System.out.println("");
    }
}

class Node 
{
    String data;
    Node next;

    Node(String value)
	{
        this.data = value;
        this.next = null;
    }
	public void printList(Node head)
	{
        Node temp = head.next;
        while(temp.next != null)
		{
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
		System.out.println("");
		System.out.println("");
    }
	
	public void printList2(Node head2)
	{
        Node temp2 = head2.next;
        while(temp2.next != null)
		{
            System.out.print(temp2.data + "->");
            temp2 = temp2.next;
        }
		System.out.println("");
		System.out.println("");
    }
}