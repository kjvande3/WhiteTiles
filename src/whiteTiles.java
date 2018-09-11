

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import java.util.Scanner;

import finalPrograms.JoeApplet;
import finalPrograms.SolidObject;

//Kevin Vander Horn
//Period C
//Apr 30, 2014
//Description:
//

public class whiteTiles extends JoeApplet implements KeyListener, MouseListener, MouseMotionListener
{
	//imports
	Scanner scan = new Scanner(System.in);
	Color mar = new Color(179,0,0);
	Random gen = new Random();
	
	//ints
	int tile1y = 425;
	int tile1x = 275;
	int tile2y = 300;
	int tile2x = 200;
	int tile3y = 175;
	int tile3x = 125;
	int tile4y = 50;
	int tile4x = 50;
	int down1 = 125;
	int num1;
	int timer=0;
	int counter=0;
	int count;
	
	//booleans
	boolean ballVisible = true;
	boolean firstHit = true;
	boolean startScreen = true;
	boolean instructScreen = true;
	boolean gameOver = false;
	boolean restartGame = true;
	
	//solid objects
	SolidObject bottom = new SolidObject();
	SolidObject hKey = new SolidObject();
	SolidObject jKey = new SolidObject();
	SolidObject kKey = new SolidObject();
	SolidObject lKey = new SolidObject();
	SolidObject startTile1SO = new SolidObject();
	SolidObject startTile2SO = new SolidObject();
	SolidObject startTile3SO = new SolidObject();
	SolidObject startTile4SO = new SolidObject();
	
	//fonts
	Font HJKL = new Font("Agency FB", 20, 25);
	Font score = new Font("Agency FB", 20, 120);
	
	//image imports
	Image startUp, instruct, logo, endScreen;

	//adds the even listeners
	public void init()
	{
		startUp = getImage(getCodeBase(),"White Tiles Start Screen.jpg");
		instruct = getImage(getCodeBase(),"White Tiles Instructions Screen.jpg");
		logo = getImage(getCodeBase(),"White Tiles Logo.png");
		endScreen = getImage(getCodeBase(),"Game Over.jpg");
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	//outer background of game
	public void outerBackground(Graphics art)
	{
		art.setColor(Color.DARK_GRAY);
		art.fillRect(0, 0, 400, 600);
		bottom.makeSolidObject(0, 0, 400, 600);
		art.drawImage(logo,37,-40,250,125,this);
	}
	
	//inner background of game
	public void innerBackground(Graphics art)
	{
		art.setColor(Color.black);
		art.fillRect(50, 50, 300, 500);
	}
	
	//grid for solid objects to press keys
	public void grid(Graphics art)
	{
		hKey.makeSolidObject(50, 450, 75, 100);
		jKey.makeSolidObject(125, 450, 75, 100);
		kKey.makeSolidObject(200, 450, 75, 100);
		lKey.makeSolidObject(275, 450, 75, 100);
	}

	//draws the letters for the keys
	public void HJKLKeys (Graphics art)
	{
		art.setColor(Color.black);
		art.setFont(HJKL);
		art.drawString("L",308,495);
		art.drawString("K",235,495);
		art.drawString("J",157,495);
		art.drawString("H", 83, 495);
	}
	
	//draws the tile in first position
	public void startTile1(Graphics art)
	{
		art.setColor(Color.white);
		art.fillRect(tile1x,tile1y,75,125);
		startTile1SO.makeSolidObject(tile1x,tile1y,75,125);
	}

	//draws the tile in second position
	public void startTile2(Graphics art)
	{
		art.setColor(Color.white);
		art.fillRect(tile2x,tile2y,75,125);
		startTile2SO.makeSolidObject(tile2x,tile2y,75,125);
	}

	//draws the tile in third position
	public void startTile3(Graphics art)
	{
		art.setColor(Color.white);
		art.fillRect(tile3x,tile3y,75,125);
		startTile3SO.makeSolidObject(tile3x,tile3y,75,125);
	}

	//draws the tile in fourth position
	public void startTile4(Graphics art)
	{
		art.setColor(Color.white);
		art.fillRect(tile4x,tile4y,75,125);
		startTile4SO.makeSolidObject(tile4x,tile4y,75,125);
	}
	
	//randomly generates the next block
	public void drawBlock1(Graphics art)
	{
		num1 = 75*gen.nextInt(4)+50;
		art.setColor(Color.white);
		art.fillRect(num1,50,75,125);
	}
	
	//method for keys pressed
	public void keyPressed(KeyEvent k)
	{
		int key = k.getKeyCode();
		if(key == k.VK_SPACE)
		{
			instructScreen = false;
		}
		
		if(key == k.VK_ENTER)
		{
			startScreen = false;
		}
		
		if(key == k.VK_BACK_SPACE)
		{
			startScreen = true;
			instructScreen = true;
			count = 0;
			timer = 0;
			gameOver = false;
		}
		
		if(key == k.VK_L && !gameOver)
		{
			count = count -1;
			if(startTile1SO.isCollidingWith(lKey))
			{
				tile2y = tile2y + 125;
				tile3y = tile3y + 125;
				tile4y = tile4y + 125;
				count = count+2;
				
				if(startTile1SO.isCollidingWith(bottom))
				{
					tile1x = 75*gen.nextInt(4)+50;
					tile1y = 50;
				}
			}
			
			if(startTile2SO.isCollidingWith(lKey))
			{
				tile1y = tile1y + 125;
				tile3y = tile3y + 125;
				tile4y = tile4y + 125;
				count = count+2;
				
				if(startTile2SO.isCollidingWith(bottom))
				{
					tile2x = 75*gen.nextInt(4)+50;
					tile2y = 50;
				}
			}
			
			if(startTile3SO.isCollidingWith(lKey))
			{
				tile1y = tile1y + 125;
				tile2y = tile2y + 125;
				tile4y = tile4y + 125;
				count = count+2;
				
				if(startTile3SO.isCollidingWith(bottom))
				{
					tile3x = 75*gen.nextInt(4)+50;
					tile3y = 50;
				}
			}
			
			if(startTile4SO.isCollidingWith(lKey))
			{
				tile1y = tile1y + 125;
				tile2y = tile2y + 125;
				tile3y = tile3y + 125;
				count = count+2;
				
				if(startTile4SO.isCollidingWith(bottom))
				{
					tile4x = 75*gen.nextInt(4)+50;
					tile4y = 50;
				}
			}
		}
		
		if(key == k.VK_K && !gameOver)
		{
			count = count-1;
			if(startTile1SO.isCollidingWith(kKey))
			{
				tile2y = tile2y + 125;
				tile3y = tile3y + 125;
				tile4y = tile4y + 125;
				count = count+2;
				
				if(startTile1SO.isCollidingWith(bottom))
				{
					tile1x = 75*gen.nextInt(4)+50;
					tile1y = 50;
				}
			}
			
			if(startTile2SO.isCollidingWith(kKey))
			{
				tile1y = tile1y + 125;
				tile3y = tile3y + 125;
				tile4y = tile4y + 125;
				count = count+2;
				
				if(startTile2SO.isCollidingWith(bottom))
				{
					tile2x = 75*gen.nextInt(4)+50;
					tile2y = 50;
				}
			}
			
			if(startTile3SO.isCollidingWith(kKey))
			{
				tile1y = tile1y + 125;
				tile2y = tile2y + 125;
				tile4y = tile4y + 125;
				count = count+2;
				
				if(startTile3SO.isCollidingWith(bottom))
				{
					tile3x = 75*gen.nextInt(4)+50;
					tile3y = 50;
				}
			}
				
			if(startTile4SO.isCollidingWith(kKey))
			{
				tile1y = tile1y + 125;
				tile2y = tile2y + 125;
				tile3y = tile3y + 125;
				count = count+2;
				
				if(startTile4SO.isCollidingWith(bottom))
				{
					tile4x = 75*gen.nextInt(4)+50;
					tile4y = 50;
				}
			}
		}
		
		if(key == k.VK_J && !gameOver)
		{
			count = count-1;
			if(startTile1SO.isCollidingWith(jKey))
			{
				tile2y = tile2y + 125;
				tile3y = tile3y + 125;
				tile4y = tile4y + 125;
				count = count+2;
				
				if(startTile1SO.isCollidingWith(bottom))
				{
					tile1x = 75*gen.nextInt(4)+50;
					tile1y = 50;
				}			
			}
		
			if(startTile2SO.isCollidingWith(jKey))
			{
				tile1y = tile1y + 125;
				tile3y = tile3y + 125;
				tile4y = tile4y + 125;
				count = count+2;
				
				if(startTile2SO.isCollidingWith(bottom))
				{
					tile2x = 75*gen.nextInt(4)+50;
					tile2y = 50;
				}
			}
			
			if(startTile3SO.isCollidingWith(jKey))
			{
				tile1y = tile1y + 125;
				tile2y = tile2y + 125;
				tile4y = tile4y + 125;
				count = count+2;
				
				if(startTile3SO.isCollidingWith(bottom))
				{
					tile3x = 75*gen.nextInt(4)+50;
					tile3y = 50;
				}
			}
			
			if(startTile4SO.isCollidingWith(jKey))
			{
				tile1y = tile1y + 125;
				tile2y = tile2y + 125;
				tile3y = tile3y + 125;
				count = count+2;
				
				if(startTile4SO.isCollidingWith(bottom))
				{
					tile4x = 75*gen.nextInt(4)+50;
					tile4y = 50;
				}
			}
		}
		
		if(key == k.VK_H && !gameOver)
		{
			count = count-1;
			if(startTile1SO.isCollidingWith(hKey))
			{
				tile2y = tile2y + 125;
				tile3y = tile3y + 125;
				tile4y = tile4y + 125;
				count = count+2;
				
				if(startTile1SO.isCollidingWith(bottom))
				{
					tile1x = 75*gen.nextInt(4)+50;
					tile1y = 50;
				}
			}
			
			if(startTile2SO.isCollidingWith(hKey))
			{
				tile1y = tile1y + 125;
				tile3y = tile3y + 125;
				tile4y = tile4y + 125;
				count = count+2;
				
				if(startTile2SO.isCollidingWith(bottom))
				{
					tile2x = 75*gen.nextInt(4)+50;
					tile2y = 50;
				}
			}
			
			if(startTile3SO.isCollidingWith(hKey))
			{
				tile1y = tile1y + 125;
				tile2y = tile2y + 125;
				tile4y = tile4y + 125;
				count = count+2;
				
				if(startTile3SO.isCollidingWith(bottom))
				{
					tile3x = 75*gen.nextInt(4)+50;
					tile3y = 50;
					
				}
			}
			
			if(startTile4SO.isCollidingWith(hKey))
			{
				tile1y = tile1y + 125;
				tile2y = tile2y + 125;
				tile3y = tile3y + 125;
				count = count+2;
				
				if(startTile4SO.isCollidingWith(bottom))
				{
					tile4x = 75*gen.nextInt(4)+50;
					tile4y = 50;
				}
			}
		}
	}
	
	//when the game ends
	public void gameOver(Graphics art)
	{
		art.drawImage(endScreen,0,0,400,600,this);
		art.setFont(score);
		art.setColor(mar);
		art.drawString(""+count,156,414);
	}
	
	//method to end the game
	public void gameOver()
	{
		if(timer==10)
		{
			gameOver = true;
		}
	}
	
	//method for the timer
	public void timerClock()
	{
		counter++;
		if(counter%32==0)
		{
			timer++;
		}
	}
	
	//method to draw the score
	public void drawScore(Graphics art)
	{
		art.drawString("Count: "+count,180,583);
		art.drawString("Time: "+timer, 207, 40);
	}
	
	//start screen of the game
	public void drawStartScreen(Graphics art)
	{
		art.drawImage(startUp,0,0,400,600,this);
	}
	
	//instruction screen method
	public void drawInstructScreen(Graphics art)
	{
		art.drawImage(instruct,0,0,400,600,this);
	}
	
	//runs the program
	public void paint(Graphics art)
	{
		setSize(400,600);
		if(startScreen)
		{
			drawStartScreen(art);
		}
		else
		{
			if(instructScreen)
			{
				drawInstructScreen(art);
			}
			else
			{
				
				if(!gameOver)
				{
					outerBackground(art);
					innerBackground(art);
					grid(art);
					timerClock();
					startTile1(art);
					startTile2(art);
					startTile3(art);
					startTile4(art);
					HJKLKeys(art);
					drawScore(art);
					gameOver();
				}
				else
				{
					gameOver(art);
				}
				
			}
		}
	}

	
	
	
	
	
	
	
	
	
	
	public void mouseDragged(MouseEvent k)
	{
	}
	public void mouseExited()
	{
	}
	public void mouseMoved(MouseEvent e)
	{
	}
	public void mouseClicked(MouseEvent arg0)
	{
	}
	public void mouseEntered(MouseEvent arg0)
	{
	}
	public void mouseExited(MouseEvent arg0)
	{
	}
	public void mousePressed(MouseEvent arg0)
	{
	}
	public void mouseReleased(MouseEvent arg0)
	{
	}
	public void keyReleased(KeyEvent arg0)
	{
	}
	public void keyTyped(KeyEvent arg0)
	{
	}
}
