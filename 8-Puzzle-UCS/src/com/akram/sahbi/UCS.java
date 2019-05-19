package com.akram.sahbi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class UCS {
	Integer[][] initialState = new Integer[3][3];
	Node root;
	Node currentNode;
	Integer[][] goalState = {{0,1,2},{3,4,5},{6,7,8}}; 
	PriorityQueue<Node> fringe = new PriorityQueue<Node>();
	List<Node> exploredNodes = new ArrayList<>();
	
	public UCS(String... state)
	{
		int index = 0;
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				
				if(state[index].equals("0")) {
					root = new Node(0, i, j,initialState,"",0);
				}
				initialState[i][j] = Integer.parseInt(state[index++]);
				
			}
		}
		root.setState(initialState);
	}
	
	public boolean solve()
	{
		boolean solved = false;
		fringe.add(root);
		
		while(!fringe.isEmpty())
		{
			
			currentNode = fringe.poll();
			exploredNodes.add(currentNode);
			if(gloalReached())
			{
				solved = true;
				printCurrentState();
				return solved;
			}
			else
			{
				printCurrentState();
			}
			
			
			for(Node neighbor : currentNode.getNeighbors())
			{
				if(exploredNodes.indexOf(neighbor) == -1 && fringe.contains(neighbor) == false)
				{
					fringe.add(neighbor);
					
				}
				else if(fringe.contains(neighbor) == true)
				{
					for(Node n : fringe)
					{
						if(n.equals(neighbor))
						{
							fringe.remove(neighbor);
							n.setCostOfPath(neighbor.getCostOfPath());
							fringe.add(n);
							break;
						}
					}
					
				}
			}
			currentNode.setNeighbors(null);
			
		}
		
		
		
		
		
		return solved;
	}

	private boolean gloalReached() {
		boolean success = true;
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if(!currentNode.getState()[i][j].equals(goalState[i][j]))
				{
					success = false;
					break;
				}
						
			}
			if(success == true)
			{
				break;
			}
			
		}
		
		return success;
	}

	private void printCurrentState() {
		System.out.println("Current state after: " + currentNode.getPathToGoal());
		for(int i = 0;i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				System.out.print(currentNode.getState()[i][j]+"");
			}
			System.out.println("");
		}
		System.out.println("");
	}

	public Integer[][] getInitialState() {
		return initialState;
	}

	public void setInitialState(Integer[][] initialState) {
		this.initialState = initialState;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public Node getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(Node currentNode) {
		this.currentNode = currentNode;
	}

	public Integer[][] getGoalState() {
		return goalState;
	}

	public void setGoalState(Integer[][] goalState) {
		this.goalState = goalState;
	}

	public PriorityQueue<Node> getFringe() {
		return fringe;
	}

	public void setFringe(PriorityQueue<Node> fringe) {
		this.fringe = fringe;
	}

	public List<Node> getExploredNodes() {
		return exploredNodes;
	}

	public void setExploredNodes(List<Node> exploredNodes) {
		this.exploredNodes = exploredNodes;
	}

	
	
	
}
